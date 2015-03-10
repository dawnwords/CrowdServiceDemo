package sg.edu.sutd.ws;

import java.util.HashMap;

/**
 * Created by Jiahuan on 2015/3/7.
 */
public class BaseVariable{




    private static HashMap<String, BaseVariable> mapping = new HashMap<String, BaseVariable>();
    public static final double longitude = 121.598471;
    public static final double latitude = 31.191204;

    static{
        mapping.put("service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService", new BaseVariable(1,60,0));
        mapping.put("service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService", new BaseVariable(1,60,0.02));
    }

    private double baseCostConst;//基础价格常量
    private double baseTimeConst;//基本时间常量
    private double coefficient;//每米价格是多少  //也用于判断是否是位置相关。

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