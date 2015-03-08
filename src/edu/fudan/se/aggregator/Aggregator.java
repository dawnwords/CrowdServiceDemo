package edu.fudan.se.aggregator;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import javafx.concurrent.Worker;
import org.apache.axis.encoding.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import edu.fudan.se.bean.WorkerResponse;
import edu.fudan.se.dbopration.SelectResponseByMicroTaskOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskProcessing2FinishOperator;

public abstract class Aggregator {
	private List<String> acceptWorkerIds;

	public Aggregator() {
		acceptWorkerIds = new LinkedList<String>();
	}

	public String aggregate(long taskId, int deadline) {
		try {
			Thread.sleep(deadline * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WorkerResponse> responses = new SelectResponseByMicroTaskOperator(
				taskId).getResult();
		int offerSum = 0;

		for(WorkerResponse response : responses){
			offerSum += response.offer;
		}

		String result = imagePath2Base64(aggregateResult(responses));
		new UpdateMicroTaskProcessing2FinishOperator(taskId, acceptWorkerIds)
				.getResult();
		return result+":"+offerSum;
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

	protected void acceptWorker(String workerId) {
		acceptWorkerIds.add(workerId);
	}

	protected abstract String aggregateResult(List<WorkerResponse> responses);
}
