package edu.fudan.se.bean;

public class AgentOffer extends AgentInfo {

    public final long id;
    public final int offer;
    public final int timeEstimate;

    public AgentOffer(long id, String capacity, double longitude, double latitude, double reputation, String guid,
                      int offer, int timeEstimate) {
        super(guid, capacity, longitude, latitude, reputation);
        this.id = id;
        this.offer = offer;
        this.timeEstimate = timeEstimate;
    }

    @Override
    public String toString() {
        return "AgentOffer [id=" + id + ", offer=" + offer + ", timeEstimate=" + timeEstimate
                + ", guid=" + guid + ", capacity=" + capacity + ", longitude="
                + longitude + ", latitude=" + latitude + ", reputation=" + reputation + "]";
    }

}
