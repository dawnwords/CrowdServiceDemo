package edu.fudan.se.bean;

public class AgentInfo {
	public final String guid,capacity;
	public final long longitude,latitude;
	public final double reputation;
	
	public AgentInfo(String guid, String capacity, long longitude,
			long latitude, double reputation) {
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
