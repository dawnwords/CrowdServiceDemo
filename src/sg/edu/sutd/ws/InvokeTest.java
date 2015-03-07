package sg.edu.sutd.ws;

import sutd.edu.sg.*;

import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringintKeyValueOfstringint;

public class InvokeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<process name=\"Intermediary\"" +
                " xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/process/executable\"" +
                " targetNamespace=\"http://enterprise.netbeans.org/bpel/BpelModule/Initiator\"" +
                " xmlns:ext=\"http://pat.comp.nus.edu.sg/BPEL\"" +
                " xmlns:bpel=\"http://ultraBpel/\">  " +
                "<sequence>" +
                "<receive ext:responseTimeTag=\"customer\" partnerLink=\"customer\" operation=\"CheckFlightAvailability\" variable=\"var\"  createInstance=\"yes\"/>" +
                "<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"cr1\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>" +
                "<invoke bpel:BPELCategory=\"GeneralService\" ext:crowdServiceName=\"cr2\" partnerLink=\"PBS\" operation=\"requestMS\" inputVariable=\"MSInfo\"  outputVariable=\"ISOutput\"/>" +
                "<reply partnerLink=\"customer\" bpel:ReplyUser=\"true\" operation=\"CheckFlightAvailability\" variable=\"result\"/>" +
                "</sequence>" +
                "</process>" 
                ;
			
		Long globalTimeConstraint = 4000L;
		Double globalCostConstraint = 30.0;
		CrowdWorker[] cr1 = {new CrowdWorker(10d,0,0.9,1000L,false),new CrowdWorker(10d,1,0.94,2000L,false),new CrowdWorker(10d,2,0.9,3000L,false)};
		CrowdWorker[] cr2 = {new CrowdWorker(10d,3,0.9,1000L,false),new CrowdWorker(10d,4,0.9,2000L,false)};
		
		ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] workers = {
				
				new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(
						"cr1",cr1),
				new ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9(
						"cr2",cr2)
		};		
		ArrayOfKeyValueOfstringintKeyValueOfstringint[] resultNums = {new ArrayOfKeyValueOfstringintKeyValueOfstringint("cr1",1),
				new ArrayOfKeyValueOfstringintKeyValueOfstringint("cr2",2)};
		
		
		try{
			CrowdOptimizationResult ret = new CrowdServiceProxy().globalOptimize(xml, globalTimeConstraint,globalCostConstraint, workers, resultNums, 100);
			System.out.println("getTotalReliability" + ret.getTotalReliability());
			ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] tmps = ret.getCrowdServiceSelection();
			System.out.println(tmps.length);
			for(ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9 tmp:tmps){
				System.out.println("Key  :  "+tmp.getKey());
				for(CrowdWorker cw : tmp.getValue()){
					System.out.println("Value  :  ");
					System.out.println("  cw.getCost() :" + cw.getCost() + 
					"  cw.getIndex() :" + cw.getIndex() + 
					"  cw.getReliability() :" + cw.getReliability() + 
					"  cw.getResponseTime() :" + cw.getResponseTime() +
					"  cw.getSelected() :" + cw.getSelected());
				}
			}
		}catch(java.rmi.RemoteException exp){
			System.out.println(exp);
		}
	}
}















