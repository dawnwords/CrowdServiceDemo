package edu.fudan.se.crowdservice.priceassessment;

import edu.fudan.se.dbopration.InsertMicrotaskOperator;
import jade.util.Logger;
import org.apache.commons.codec.binary.Base64;

import javax.jws.WebService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

@WebService(endpointInterface = "edu.fudan.se.crowdservice.priceassessment.AssessPriceService")
public class AssessPriceServiceImpl implements AssessPriceService {

    static {
        try {
            System.setErr(new PrintStream(new FileOutputStream(new File("PA-error" + new Date().getTime())), true));
            System.setOut(new PrintStream(new FileOutputStream(new File("PA-out" + new Date().getTime())), true));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private Logger logger = Logger.getJADELogger(getClass().getSimpleName());

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

    @Override
    public String assessPrice(double latitude, double longitude, String consumerId, int cost, int deadline,
                              String compositeService, int resultNum, String brand, String series,
                              String newness, String CPU, String memory, String hardDisk,
                              String base64Image) {
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

        String xmlDoc = "<Root><Description>Please assess the price of the given second-hand "
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
        String crowdService = "service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService";
        long taskId = new InsertMicrotaskOperator(xmlDoc, consumerId, cost,
                deadline, compositeService, crowdService, resultNum, latitude, longitude).getResult();
        String result = new SelectedAverageResponseAggregator().aggregate(
                taskId, deadline);
        logger.info(result);
        return result;
    }
}
