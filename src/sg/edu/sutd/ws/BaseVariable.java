package sg.edu.sutd.ws;

import java.util.HashMap;

/**
 * Created by Jiahuan on 2015/3/7.
 */
public class BaseVariable {

    public static final double longitude = 121.598471;
    public static final double latitude = 31.191204;
    private static HashMap<String, BaseVariable> mapping = new HashMap<String, BaseVariable>();

    static {
        mapping.put("service.shcomputer.cs.siteinspection.interfaces.SiteInspectionService", new BaseVariable(2, 50, 10.0 / 1000, 1 / 0.6));
        mapping.put("service.shcomputer.cs.priceassessment.interfaces.PriceAssessmentService", new BaseVariable(2, 100, 0, 0));
    }

    public final double baseCostConst;//基础价格常量
    public final double baseTimeConst;//基本时间常量
    public final double coeCost;//每米价格是多少  //也用于判断是否是位置相关。
    public final double coeTime;

    private BaseVariable(double baseCostConst, double baseTimeConst, double coeCost, double coeTime) {
        this.baseCostConst = baseCostConst;
        this.baseTimeConst = baseTimeConst;
        this.coeCost = coeCost;
        this.coeTime = coeTime;
    }

    public static BaseVariable getBaseVariableByServiceName(String crowdService) {
        return mapping.get(crowdService);
    }
}