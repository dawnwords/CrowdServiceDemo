package sg.edu.sutd.ws;

import com.google.gson.Gson;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringintKeyValueOfstringint;
import edu.fudan.se.bean.AgentInfo;
import edu.fudan.se.crowdservice.plan.Request;
import edu.fudan.se.crowdservice.plan.Response;
import edu.fudan.se.dbopration.SelectOnlineAgentInfoOperator;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdServiceProxy;
import sutd.edu.sg.CrowdWorker;

import javax.jws.WebService;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

@WebService(endpointInterface = "sg.edu.sutd.ws.GlobalOptimization")
public class GlobalOptimizationImpl implements GlobalOptimization {

    public static final String SITE_INSPECTION = "service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService";
    //locationStr的格式类似于{[InspectSiteService:latitude:longitude]},
    //即,针对于全局优化的时候，对于单个用户的某一次执行请求的时候，

    //	private HashMap<String, BaseVariable> mapping = new HashMap<String, BaseVariable>();
    public static final String PRICE_ASSESSMENT = "service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService";
    static final String[] STEP_XML = {"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "\t<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"10;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<flow>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\t\t\n" +
            "\t</flow>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t<condition>ContinueYes</condition>\n" +
            "\t\t<sequence>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t<else>\n" +
            "\t\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t</else>\n" +
            "\t\t</if>\n" +
            "\t\t</sequence>\n" +
            "\t\t<else>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t</else>\n" +
            "\t</if>\t\n" +
            "\t<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "\t<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "\t<flow>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\t\t\n" +
            "\t</flow>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t<condition>ContinueYes</condition>\n" +
            "\t\t<sequence>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t<else>\n" +
            "\t\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t</else>\n" +
            "\t\t</if>\n" +
            "\t\t</sequence>\n" +
            "\t\t<else>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t</else>\n" +
            "\t</if>\t\n" +
            "\t<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>", "", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "\t<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"1;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t<else>\n" +
            "\t\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t</else>\n" +
            "\t\t</if>\n" +
            "\t<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>"};

    public GlobalOptimizationImpl() {

    }

    /**
     * 根据位置中的经纬度计算出距离
     *
     * @return 两个位置之间的距离
     */
    private static double getShortDistance(double lat1, double lon1, double lat2, double lon2) {

        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (lon1 - lon2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    @Override
    public String globalOptimize(String content) {
        Request request = new Gson().fromJson(content, Request.class);

//        System.out.println(content);

        double latitudePara = request.getTargetLatitude();
        double longitudePara = request.getTargetLongitude();

        Response response = new Response();

        String xml = STEP_XML[request.getServiceSequence().length];

//        System.out.println(xml + "\n");

        int idCounter = 0;

        ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums = {};
        ArrayList<ArrayOfKeyValueOfstringintKeyValueOfstringint> al = new ArrayList<ArrayOfKeyValueOfstringintKeyValueOfstringint>();

        if (request.getServiceSequence().length != 3) {
            al.add(new ArrayOfKeyValueOfstringintKeyValueOfstringint(SITE_INSPECTION, request.getResultNumbers().get(SITE_INSPECTION)));
        }
        al.add(new ArrayOfKeyValueOfstringintKeyValueOfstringint(PRICE_ASSESSMENT, request.getResultNumbers().get(PRICE_ASSESSMENT)));

        resultNums = al.toArray(resultNums);
        int resultNumLen = resultNums.length;

        ArrayList<AgentInfo> agentInfos = removeRequester(request.getConsumerId());

        if (agentInfos.size() > 0) {

            //delete consumer information who launches the request from the list of the online agents .

            ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] aov =
                    new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[resultNumLen];


            if (latitudePara <= 1e-6 && longitudePara <= 1e-6) {
//                System.out.println("invoking.....");
                double baseLongitude = BaseVariable.longitude;
                double baseLatitude = BaseVariable.latitude;

                for (int i = 0; i < resultNumLen; i++) {
                    String crowdServiceName = resultNums[i].getKey();
                    BaseVariable v = BaseVariable.getBaseVariableByServiceName(crowdServiceName);
                    CrowdWorker[] workers = new CrowdWorker[agentInfos.size()];
                    for (int j = 0; j < agentInfos.size(); j++) {
                        double cost;
                        long responseTime;
                        AgentInfo agentInfo = agentInfos.get(j);

                        double distance = getShortDistance(baseLatitude, baseLongitude, agentInfo.latitude, agentInfo.longitude);
                        cost = v.baseCostConst + v.coeCost * distance;
                        responseTime = (long) (v.baseTimeConst + v.coeTime * distance);

                        CrowdWorker worker = new CrowdWorker(cost, idCounter++, agentInfo.reputation, responseTime, false);
//                        System.out.println(crowdServiceName + ":::::;WworkerInformation " + "cost" + cost + "index" + (idCounter - 1) + "reputation" + agentInfo.reputation + "responseTime" + responseTime + "\n");
                        workers[j] = worker;
                    }

                    aov[i] = new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdServiceName, workers);
                }
            } else {
                double baseLongitude;
                double baseLatitude;
                for (int i = 0; i < resultNumLen; i++) {
                    String crowdServiceName = resultNums[i].getKey();

//                    System.out.println("crowdServiceName : " + crowdServiceName);

                    if (crowdServiceName.contains("SiteInspectionService")) {
                        baseLongitude = longitudePara;
                        baseLatitude = latitudePara;
                    } else {
                        baseLongitude = BaseVariable.longitude;
                        baseLatitude = BaseVariable.latitude;
                    }
                    CrowdWorker[] workers = new CrowdWorker[agentInfos.size()];
                    BaseVariable v = BaseVariable.getBaseVariableByServiceName(crowdServiceName);

                    for (int j = 0; j < agentInfos.size(); j++) {
                        AgentInfo agentInfo = agentInfos.get(j);
                        double distance = getShortDistance(baseLatitude, baseLongitude, agentInfo.latitude, agentInfo.longitude);
                        double cost = v.baseCostConst + v.coeCost * distance;
                        long responseTime = (long) (v.baseTimeConst + v.coeTime * distance);

                        CrowdWorker worker = new CrowdWorker(cost, idCounter++, agentInfo.reputation, responseTime, false);
                        workers[j] = worker;
                    }

                    aov[i] = new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdServiceName, workers);
                }
            }

            try {
                CrowdOptimizationResult ret = new CrowdServiceProxy().globalOptimize(
                        xml,
                        (long) request.getGlobalTime(),
                        (double) request.getGlobalCost(),
                        aov,
                        resultNums,
                        50);
                if (ret != null) {
                    double totalReliability = ret.getTotalReliability();
                    if (ret.getCrowdServiceSelection().length > 0) {
                        ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 seletedWorker
                                = ret.getCrowdServiceSelection()[(request.getServiceSequence().length == 3 ? 0 : 1)];
                        CrowdWorker[] cw = seletedWorker.getValue();
                        long partTime = 0;
                        double partCost = 0;
                        for (CrowdWorker tmp : cw) {
                            if (tmp.getResponseTime() > partTime) {
                                partTime = tmp.getResponseTime();
                            }
                            partCost += tmp.getCost();
                        }
                        response.setCost((int) Math.rint(partCost));
                        response.setTime((int) partTime);
//                        System.out.println("partCost:" + partCost + " \t " + "partTime:" + partTime);
//                        System.out.println("seletedWorker : " + seletedWorker);
                    }
                    response.setGlobalReliability(totalReliability);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return new Gson().toJson(response);
    }

    private ArrayList<AgentInfo> removeRequester(String consumerID) {
        ArrayList<AgentInfo> agentInfos = new SelectOnlineAgentInfoOperator().getResult();

        Iterator<AgentInfo> iterator = agentInfos.iterator();
        while (iterator.hasNext()) {
            AgentInfo agentInfo = iterator.next();
            if (consumerID.equals(agentInfo.guid)) {
                iterator.remove();
            }
//            System.out.println("AgentInfo:" + agentInfo);
        }
        return agentInfos;
    }
}
