package edu.fudan.se.crowdservice.siteinspection;

import edu.fudan.se.dbopration.InsertMicrotaskOperator;
import jade.util.Logger;

import javax.jws.WebService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

@WebService(endpointInterface = "edu.fudan.se.crowdservice.siteinspection.InspectSiteService")
public class InspectSiteServiceImpl implements InspectSiteService {
    static {
        try {
            System.setErr(new PrintStream(new FileOutputStream(new File("SI-error" + new Date().getTime())), true));
            System.setOut(new PrintStream(new FileOutputStream(new File("SI-out" + new Date().getTime())), true));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private Logger logger = Logger.getJADELogger(getClass().getSimpleName());

    @Override
    public String siteInspect(double latitude, double longitude, String consumerId, int cost,
                              int deadline, String compositeService, int resultNum, String brand, String series, String newness,
                              String CPU, String memory, String hardDisk, String location) {
        logger.info("latitude:" + latitude);
        logger.info("longitude:" + longitude);
        logger.info("consumerId:" + consumerId);
        logger.info("cost:" + cost);
        logger.info("deadline:" + deadline);
        logger.info("compositeService:" + compositeService);
        logger.info("resultNum:" + resultNum);
        logger.info("brand:" + brand);
        logger.info("series:" + series);
        logger.info("newness:" + newness);
        logger.info("CPU:" + CPU);
        logger.info("memory:" + memory);
        logger.info("hardDisk:" + hardDisk);
        logger.info("location:" + location);

        String xmlDoc = "<Root><Description>Please get to the designated location : " + location + "  and inspect the second-hand computer specified "
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
                deadline, compositeService, crowdService, resultNum, latitude, longitude).getResult();
        String result = new SelectedLatestResponseAggregator().aggregate(
                taskId, deadline);
        logger.info(result);
        return result;
    }

}
