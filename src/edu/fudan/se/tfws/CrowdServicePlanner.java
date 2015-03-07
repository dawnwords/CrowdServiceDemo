package edu.fudan.se.tfws;

import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringintKeyValueOfstringint;
import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;
import sutd.edu.sg.CrowdOptimizationResult;
import sutd.edu.sg.CrowdServiceProxy;
import sutd.edu.sg.CrowdWorker;

import java.rmi.RemoteException;
import java.util.*;

public class CrowdServicePlanner {
	public static List<AgentOffer> getSelectedAgent(String template,
			String crowdService, int cost, int deadline,int resultNum,double longitude,double latitude,
			List<AgentOffer> candidates) {

		String xml = crowdService;
		ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums = {new ArrayOfKeyValueOfstringintKeyValueOfstringint(crowdService,resultNum)};

		HashMap<Integer,AgentOffer> guidMapping = new HashMap<Integer, AgentOffer>();
		//accelerate the mapping speed when the selected result is back.
		CrowdWorker[] partWorkers = new CrowdWorker[candidates.size()];

		for(int i = 0; i <= partWorkers.length;i++){
			AgentOffer ao = candidates.get(i);
			guidMapping.put(i,ao);
			double responseTime = getShortDistance(latitude+":"+longitude,ao.latitude+":"+ao.longitude)/1.1;
			partWorkers[i] = new CrowdWorker((double)ao.offer,i,ao.reputation,(long)responseTime,false);
		}

		ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] workers = {
				new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(crowdService,partWorkers)};

		try {
			CrowdOptimizationResult ret = new CrowdServiceProxy().globalOptimize(
                    xml,
                    (long)cost,
                    deadline*(1- MicroTask.OFFER_RATIO),
					workers,
                    resultNums,
                    400);
			if( ret==null || ret.getCrowdServiceSelection().length < 0 ) return null;

			ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 firstCSSeteled = ret.getCrowdServiceSelection()[0];
			CrowdWorker[] values = firstCSSeteled.getValue();

			ArrayList<AgentOffer> retList = new ArrayList<AgentOffer>();

			for(CrowdWorker cw : values){
				retList.add(guidMapping.get(cw.getIndex()));
			}
			return retList;

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 根据位置中的经纬度计算出距离

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
}
