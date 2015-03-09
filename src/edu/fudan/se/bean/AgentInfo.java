package edu.fudan.se.bean;

public class AgentInfo {
    public final String guid, capacity;
    public final double longitude, latitude;
    public final double reputation;

    public AgentInfo(String guid, String capacity, double longitude,
                     double latitude, double reputation) {
        this.guid = guid;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.reputation = reputation;
    }

    @Override
    public String toString() {
        return "AgentInfo [guid=" + guid + ", capacity=" + capacity
                + ", longitude=" + longitude + ", latitude=" + latitude
                + ", reputation=" + reputation + "]";
    }
}
