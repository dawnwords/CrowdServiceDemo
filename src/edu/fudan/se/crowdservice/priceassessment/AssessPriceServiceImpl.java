package edu.fudan.se.crowdservice.priceassessment;

import java.io.FileOutputStream;
import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.codec.binary.Base64;

import edu.fudan.se.crowdservice.siteinspection.SelectedLatestResponseAggregator;
import edu.fudan.se.dbopration.InsertMicrotaskOperator;

@WebService(endpointInterface = "edu.fudan.se.crowdservice.priceassessment.AssessPriceService")
public class AssessPriceServiceImpl implements AssessPriceService {

	@Override
	public String assessPrice(double latitude,double longitude,String consumerId, int cost, int deadline,
			String compositeService,int resultNum , String brand, String series,
			String newness, String CPU, String memory, String hardDisk,
			String base64Image) {
		String xmlDoc = "<Root><Description>Please access the price of the given second-hand "
				+ "computer configuration based on the photo given.</Description>"
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
				+ "</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key>"
				+ "<Value>"
				+ saveByteArray(new Date().getTime() + ".jpg",
						Base64.decodeBase64(base64Image))
				+ "</Value></ImageDisplay><TextInput>"
				+ "<Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>";
		String crowdService = AssessPriceService.class.getName();
		long taskId = new InsertMicrotaskOperator(xmlDoc, consumerId, cost,
				deadline, compositeService, crowdService,resultNum,latitude,longitude).getResult();
		String result = new SelectedAverageResponseAggregator().aggregate(
				taskId, deadline);
		System.out.println(result);
		return result;
	}

	private static String saveByteArray(String bytePath, byte[] bytes) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(bytePath);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bytePath;
	}
}
