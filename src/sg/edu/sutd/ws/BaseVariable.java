package sg.edu.sutd.ws;

import java.util.HashMap;

/**
 * Created by Jiahuan on 2015/3/7.
 */
public class BaseVariable{


    private static HashMap<String, BaseVariable> mapping = new HashMap<String, BaseVariable>();

    static{
        mapping.put("InspectSiteService", new BaseVariable(1, 60,0.02));
        mapping.put("AssessPriceService", new BaseVariable(1, 60,0.02));
    }

    private double baseCostConst;//基础价格常量
    private double baseTimeConst;//基本时间常量
    private double coefficient;//每米价格是多少

    private BaseVariable(double baseCostConst, double baseTimeConst, double coefficient) {
        super();
        this.baseCostConst = baseCostConst;
        this.baseTimeConst = baseTimeConst;
        this.coefficient = coefficient;

    }

    public static double getCorrespondingBaseCostConst(String crowdService) {
        return mapping.get(crowdService).baseCostConst;
    }
    public static double getCorrespondingBaseTimeConst(String crowdService) {
        return mapping.get(crowdService).baseTimeConst;
    }
    public static double getCorrespondingCoefficient(String crowdService) {
        return mapping.get(crowdService).coefficient;
    }
}