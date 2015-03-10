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
import java.util.Map;

@WebService(endpointInterface = "sg.edu.sutd.ws.GlobalOptimization")
public class GlobalOptimizationImpl implements GlobalOptimization {

    final public static double PACE_SPEED = 1.1;
    static final String[] STEP_XML = {"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process name=\"Intermediary\"\n" +
            "xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"\n" +
            "targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"\n" +
            "xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"\n" +
            "xmlns:bpel=\"http://ultraBpel/\">  \n" +
            "<sequence>\n" +
            "\t<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"BuySecondhandItem\" variable=\"var\"  createInstance=\"yes\"/>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"300;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<flow>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"20;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\t\t\n" +
            "\t</flow>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"120;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t<condition>ContinueYes</condition>\n" +
            "\t\t<sequence>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"60;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
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
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"20;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\t\t\n" +
            "\t</flow>\n" +
            "\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"120;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t<condition>ContinueYes</condition>\n" +
            "\t\t<sequence>\n" +
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"60;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
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
            "\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService\" partnerLink=\"PBS1\" operation=\"requestMS1\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t<if ext:ifProb=\"0.5;0.5\">\n" +
            "\t\t\t<condition>PriceOK</condition>\n" +
            "\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"60;0;0.9\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t<else>\n" +
            "\t\t\t\t<invoke bpel:BPELCategory=\"GeneralService\" ext:QoS=\"0;0;1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>\n" +
            "\t\t\t</else>\n" +
            "\t\t</if>\n" +
            "\t<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"BuySecondhandItem\" variable=\"result\"/>\n" +
            "</sequence>\n" +
            "</process>"};
    //locationStr的格式类似于{[InspectSiteService:latitude:longitude]},
    //即,针对于全局优化的时候，对于单个用户的某一次执行请求的时候，

    //	private HashMap<String, BaseVariable> mapping = new HashMap<String, BaseVariable>();
    public GlobalOptimizationImpl() {

    }

    /**
     * 根据位置中的经纬度计算出距离
     *
     * @return 两个位置之间的距离
     */
    private static double getShortDistance(String location1, String location2) {

        double lat1 = Double.parseDouble(location1.split(":")[0]);
        double lon1 = Double.parseDouble(location1.split(":")[1]);

        double lat2 = Double.parseDouble(location2.split(":")[0]);
        double lon2 = Double.parseDouble(location2.split(":")[1]);

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

        double latitudePara = request.getTargetLatitude();
        double longitudePara = request.getTargetLongitude();

        Response response = new Response();

        String xml = STEP_XML[request.getServiceSequence().length];

        int idCounter = 0;
//		int locationNum = locationStr.length;
//		HashMap<String, String> locationMap = new HashMap<String, String>();
//		for(int i = 0; i < locationNum ; i++){
//			int startLocation = locationStr[i].indexOf(":");
//			String csName = locationStr[i].substring(0, startLocation);//crowdService Name
//			String correspondingLocation = locationStr[i].substring(startLocation+1, locationStr[i].length());
//			locationMap.put(csName, correspondingLocation);
//		}

        //=======================================
        ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums = {};
        ArrayList<ArrayOfKeyValueOfstringintKeyValueOfstringint> al = new ArrayList<ArrayOfKeyValueOfstringintKeyValueOfstringint>();

        for (Map.Entry<String, Integer> entry : request.getResultNumbers().entrySet()) {
            al.add(new ArrayOfKeyValueOfstringintKeyValueOfstringint(entry.getKey(), entry.getValue()));
        }

        resultNums = al.toArray(resultNums);
        int resultNumLen = resultNums.length;
        //=======================================
        ArrayList<AgentInfo> agentInfos = removeRequester(request.getConsumerId());

        if (agentInfos.size() > 0) {

            //delete consumer information who launches the request from the list of the online agents .
            CrowdWorker[] workers = new CrowdWorker[agentInfos.size()];
            ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] aov =
                    new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[resultNumLen];


            if(latitudePara <= 1e-6 && longitudePara <= 1e-6) {

                double baseLongitude = BaseVariable.longitude;
                double baseLatitude = BaseVariable.latitude;

                for (int i = 0; i < resultNumLen; i++) {
                    String crowdServiceName = resultNums[i].getKey();
                    double baseCostConstant = BaseVariable.getCorrespondingBaseCostConst(crowdServiceName);
                    double baseTimeConstant = BaseVariable.getCorrespondingBaseTimeConst(crowdServiceName);
                    double coefficient = BaseVariable.getCorrespondingCoefficient(crowdServiceName);
                    for (int j = 0; j < agentInfos.size(); j++) {
                        double cost;
                        long responseTime;
                        AgentInfo agentInfo = agentInfos.get(j);
                        if (coefficient > 1e-6) {
                            //即这个服务是位置相关的
                            double distance = getShortDistance(baseLatitude + ":" + baseLongitude, agentInfo.latitude + ":" + agentInfo.longitude);
                            cost = baseCostConstant + coefficient <= 1e-6 ? 0 : coefficient * distance;
                            responseTime = (long) (baseTimeConstant + distance / PACE_SPEED);
                        } else {
                            cost = 2;
                            responseTime = 30;
                        }
                        CrowdWorker worker = new CrowdWorker(cost, idCounter++, agentInfo.reputation, responseTime, false);
                        workers[j] = worker;
                    }

                    aov[i] = new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdServiceName, workers);
                }
            }else{
                double baseLongitude ;
                double baseLatitude ;
                for (int i = 0; i < resultNumLen; i++) {
                    String crowdServiceName = resultNums[i].getKey();
                    if(crowdServiceName.contains("SiteInspectionService")){
                        baseLongitude = longitudePara;
                        baseLatitude = latitudePara;
                    }
                    else{
                        baseLongitude = BaseVariable.longitude;
                        baseLatitude = BaseVariable.latitude;
                    }
                    double baseCostConstant = BaseVariable.getCorrespondingBaseCostConst(crowdServiceName);
                    double baseTimeConstant = BaseVariable.getCorrespondingBaseTimeConst(crowdServiceName);
                    double coefficient = BaseVariable.getCorrespondingCoefficient(crowdServiceName);
                    for (int j = 0; j < agentInfos.size(); j++) {
                        double cost = 0;
                        long responseTime = 0;
                        AgentInfo agentInfo = agentInfos.get(j);
                        if (coefficient > 1e-6) {
                            //即这个服务是位置相关的
                            double distance = getShortDistance(baseLatitude + ":" + baseLongitude, agentInfo.latitude + ":" + agentInfo.longitude);
                            cost = baseCostConstant + coefficient <= 1e-6 ? 0 : coefficient * distance;
                            responseTime = (long) (baseTimeConstant + distance / PACE_SPEED);
                        } else {
                            cost = 2;
                            responseTime = 30;
                        }
                        CrowdWorker worker = new CrowdWorker(cost, idCounter++, agentInfo.reputation, responseTime, false);
                        workers[j] = worker;
                    }

                    aov[i] = new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdServiceName, workers);
                }
            }


//
////				BaseVariable bv = mapping.get(crowdServiceName);
////					if(bv == null) return null;
//                    double baseCostConstant = BaseVariable.getCorrespondingBaseCostConst(crowdServiceName);
//                    double baseTimeConstant = BaseVariable.getCorrespondingBaseTimeConst(crowdServiceName);
//                    double coefficient = BaseVariable.getCorrespondingCoefficient(crowdServiceName);
////			    String correspondingLocation = locationMap.get(crowdServiceName);
//
//                    for (int j = 0; j < agentInfos.size(); j++) {
//                        AgentInfo agentInfo = agentInfos.get(j);
//                        double distance = correspondingLocation ==
//                                null ? 0 : getShortDistance(correspondingLocation,agentInfo.latitude+":"+agentInfo.longitude);
//                        double cost = baseCostConstant + coefficient <= 1e-6 ? 0 : coefficient*distance;
//                        long responseTime =  (long)(baseTimeConstant + distance / PACE_SPEED);
//
////                    double cost = 3 + 4 * Math.random();//TODO
////                    long responseTime = 200 + (long) (200 * Math.random());
//
//                        CrowdWorker worker = new CrowdWorker(cost, idCounter++, agentInfos.get(j).reputation, responseTime, false);
//                        workers[j] = worker;
//                    }
//                    aov[i] = new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdServiceName, workers);
//                }
//
//
//            }
            try {
                CrowdOptimizationResult ret = new CrowdServiceProxy().globalOptimize(
                        xml,
                        (long) request.getGlobalTime(),
                        (double) request.getGlobalCost(),
                        aov,
                        resultNums,
                        400);
                if (ret != null) {
                    double totalReliability = ret.getTotalReliability();
                    if (ret.getCrowdServiceSelection().length > 0) {
                        ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 seletedWorker =
                                ret.getCrowdServiceSelection()[0];
                        CrowdWorker[] cw = seletedWorker.getValue();
                        long partTime = 0;
                        double partCost = 0;
                        for (CrowdWorker tmp : cw) {
                            partTime += tmp.getResponseTime();
                            partCost += tmp.getCost();
                        }
                        response.setCost((int) partCost);
                        response.setTime((int) partTime);
                        System.out.println("partCost:" + partCost + " \t " + "partTime:" + partTime);
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
            AgentInfo agentInfo2 = iterator.next();
            if (consumerID.equals(agentInfo2.guid)) {
                iterator.remove();
            }
        }
        return agentInfos;
    }
}
