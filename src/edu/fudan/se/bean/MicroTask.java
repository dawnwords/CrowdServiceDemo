package edu.fudan.se.bean;

public class MicroTask {

    public static final double OFFER_RATIO = 0.3;
    public final long id;
    public final String template;
    public final String consumer;
    public final int cost;
    public final int deadline;
    public final String compositeService;
    public final String crowdService;
    public final int resultNum;
    public final double longitude;
    public final double latitude;

    public MicroTask(long id, String template, String consumer, int cost,
                     int deadline, String compositeService, String crowdService, int resultNum, double longitude, double latitude) {
        this.id = id;
        this.template = template;
        this.consumer = consumer;
        this.cost = cost;
        this.deadline = deadline;
        this.compositeService = compositeService;
        this.crowdService = crowdService;
        this.resultNum = resultNum;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int offerDDL() {
        return (int) (deadline * OFFER_RATIO);
    }

    public int delegateDDL() {
        return (int) (deadline * (1 - OFFER_RATIO));
    }

    @Override
    public String toString() {
        return "MicroTask [id=" + id + ", template=" + template + ", consumer="
                + consumer + ", cost=" + cost + ", deadline=" + deadline
                + ", compositeService=" + compositeService + ", crowdService="
                + crowdService + "]";
    }

    public static enum State {
        INITIAL, OFFER, PLANNING, PROCESSING, FINISHED
    }

}
