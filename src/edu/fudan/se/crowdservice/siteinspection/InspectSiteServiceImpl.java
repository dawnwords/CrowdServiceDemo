package edu.fudan.se.crowdservice.siteinspection;

import javax.jws.WebService;

import edu.fudan.se.dbopration.InsertMicrotaskOperator;

@WebService(endpointInterface = "edu.fudan.se.crowdservice.siteinspection.InspectSiteService")
public class InspectSiteServiceImpl implements InspectSiteService {

	@Override
	public String siteInspect(double latitude,double longitude,String consumerId, int cost,
			int deadline,String compositeService,int resultNum,  String brand, String series, String newness,
			String CPU, String memory, String hardDisk, String location
			) {
		String xmlDoc = "<Root><Description>Please get to the designated location and inspect the second-hand computer specified "
				+ "below. Check it and evaluate whether it is consistent with its description given below. Take a picture "
				+ "of computer and upload the picture.</Description>"
				+ "<TextDisplay><Key>Brand</Key><Value>"
				+ brand
				+ "</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>"
				+ series
				+ "</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>"
				+ newness
				+ "</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>"
				+ CPU
				+ "</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>"
				+ memory
				+ "</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>"
				+ hardDisk
				+ "</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>"
				+ location
				+ "</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key>"
				+ "<Value></Value></ImageInput><ChoiceInput>"
				+ "<Key>Please Choose Consistent Degree</Key>"
				+ "<Value>Very Consistent,Consistent,Not Consistent</Value>"
				+ "</ChoiceInput></Root>";
		String crowdService = "service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService";
		long taskId = new InsertMicrotaskOperator(xmlDoc, consumerId, cost,
				deadline, compositeService, crowdService,resultNum,latitude,longitude).getResult();
		String result = new SelectedLatestResponseAggregator().aggregate(
				taskId, deadline);
		System.out.println(result);
		return result;
	}

}
