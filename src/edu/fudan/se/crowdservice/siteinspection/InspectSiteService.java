package edu.fudan.se.crowdservice.siteinspection;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InspectSiteService {
	@WebMethod
	String siteInspect(String consumerId, int cost, int deadline,String compositeService,int resultNum, double latitude,double longitude,
			String brand, String series,String newness, String CPU, String memory, String hardDisk,
			String location);
}
