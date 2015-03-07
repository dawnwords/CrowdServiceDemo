package sg.edu.sutd.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface GlobalOptimization {
	@WebMethod
	String globalOptimize(String xml, long globalTime, double globalCost, String[] resultNum, String[] location);
}
  