package edu.fudan.se.bean;

public class AgentOffer extends AgentInfo {

    public final int offer;
    public final int timeEstimate;

    public AgentOffer(String guid, String capacity, double longitude,
                      double latitude, double reputation, int offer, int timeEstimate) {
        super(guid, capacity, longitude, latitude, reputation);
        this.offer = offer;
        this.timeEstimate = timeEstimate;
    }

    @Override
    public String toString() {
        return "AgentOffer [offer=" + offer + ", timeEstimate=" + timeEstimate
                + ", guid=" + guid + ", capacity=" + capacity + ", longitude="
                + longitude + ", latitude=" + latitude + ", reputation=" + reputation + "]";
    }

}
