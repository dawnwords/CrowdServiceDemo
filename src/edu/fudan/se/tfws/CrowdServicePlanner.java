package edu.fudan.se.tfws;

import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringintKeyValueOfstringint;
import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdServiceProxy;
import sutd.edu.sg.CrowdWorker;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CrowdServicePlanner {
    static String step1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "    <receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "       <invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "    <reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>";

    static String step2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "    <receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "        <invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "    <reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>";

    public static List<AgentOffer> getSelectedAgent(MicroTask task, List<AgentOffer> candidates) {

        System.out.println();

        String xml = "";
        System.out.println(task.crowdService);
        if ("service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService".equals(task.crowdService)) {
            xml = step1;
        }
        if ("service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService".equals(task.crowdService)) {
            xml = step2;
        }

        ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums = {new ArrayOfKeyValueOfstringintKeyValueOfstringint(task.crowdService, task.resultNum)};

        CrowdWorker[] partWorkers = new CrowdWorker[candidates.size()];

        for (int i = 0; i < partWorkers.length; i++) {
            AgentOffer ao = candidates.get(i);
            partWorkers[i] = new CrowdWorker((double) ao.offer, i, ao.reputation, (long) ao.timeEstimate, false);
            System.out.println("workInfo" + ao.offer + ":::index::::" + i + "ao.reputation" + ao.reputation + "ao.timeEstimate" + ao.timeEstimate);
        }

        ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] workers = {
                new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(task.crowdService, partWorkers)};

        System.out.println("xml" + xml + "cost" + task.cost + "timeconstaint" + task.deadline * (1 - MicroTask.OFFER_RATIO));

        ArrayList<AgentOffer> retList = new ArrayList<AgentOffer>();
        try {
            CrowdOptimizationResult ret = new CrowdServiceProxy().globalOptimize(
                    xml,
                    (long)(task.deadline * (1 - MicroTask.OFFER_RATIO)),
                    (double)task.cost,
                    workers,
                    resultNums,
                    400);
            if (ret == null || ret.getCrowdServiceSelection().length <= 0) {
                throw new Exception("the planner has a zero result");
            }

            ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 firstCSSeteled = ret.getCrowdServiceSelection()[0];
            CrowdWorker[] values = firstCSSeteled.getValue();

            for (CrowdWorker cw : values) {
                retList.add(candidates.get(cw.getIndex()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }
}
