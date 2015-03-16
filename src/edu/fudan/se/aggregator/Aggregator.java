package edu.fudan.se.aggregator;

import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.bean.WorkerResponse;
import edu.fudan.se.dbopration.SelectMicrotaskStateByMicrotaskIdOperator;
import edu.fudan.se.dbopration.SelectOfferSelectedNumByMicrotaskIdOperator;
import edu.fudan.se.dbopration.SelectResponseByMicroTaskOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskProcessing2FinishOperator;
import org.apache.axis.encoding.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Aggregator {
    private List<Long> acceptWorkerResponseIds;

    public Aggregator() {
        acceptWorkerResponseIds = new LinkedList<Long>();
    }

    public String aggregate(long taskId, int executingTime) {
        Date deadline = new Date(new Date().getTime() + executingTime * 1000);
        waitForProcessing(taskId);
        List<WorkerResponse> responses = waitForWorkerResponses(taskId, deadline);

        int offerSum = 0;
        for (WorkerResponse response : responses) {
            offerSum += response.offer;
        }

        String result = imagePath2Base64(aggregateResult(responses));
        new UpdateMicroTaskProcessing2FinishOperator(taskId, acceptWorkerResponseIds).getResult();
        return result + ":" + offerSum;
    }

    private void waitForProcessing(long taskId) {
        while (new SelectMicrotaskStateByMicrotaskIdOperator(taskId).getResult() != MicroTask.State.PROCESSING) {
            sleep(1);
        }
    }

    private List<WorkerResponse> waitForWorkerResponses(long taskId, Date deadline) {
        List<WorkerResponse> responses;
        do {
            int selectedNum = new SelectOfferSelectedNumByMicrotaskIdOperator(taskId).getResult();
            responses = new SelectResponseByMicroTaskOperator(taskId).getResult();
            if (selectedNum <= responses.size()) {
                break;
            }
            sleep(1);
        } while (new Date().before(deadline));
        return responses;
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String imagePath2Base64(String originXML) {
        if (originXML == null || "".equals(originXML)) {
            return "";
        }
        Document document = null;
        try {
            document = DocumentHelper.parseText(originXML);
            List<Node> nodes = document.selectNodes("/Root/ImageInput/Value");
            for (Node node : nodes) {
                node.setText(file2Base64(node.getText()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document == null ? "" : document.asXML();
    }

    private String file2Base64(String path) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        String base64 = "";
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024 * 4];
            int size;
            while ((size = bis.read(buffer)) > 0) {
                baos.write(buffer, 0, size);
            }
            base64 = Base64.encode(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bis);
            close(baos);
        }
        return base64;
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void acceptWorkerResponseId(long workerId) {
        acceptWorkerResponseIds.add(workerId);
    }

    protected abstract String aggregateResult(List<WorkerResponse> responses);
}
