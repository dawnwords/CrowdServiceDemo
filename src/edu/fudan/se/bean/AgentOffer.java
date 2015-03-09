package edu.fudan.se.bean;

public class AgentOffer extends AgentInfo {

	public final int offer;
	
	public AgentOffer(String guid, String capacity, double longitude,
					  double latitude, double reputation, int offer) {
		super(guid, capacity, longitude, latitude, reputation);
		this.offer = offer;
	}

	@Override
	public String toString() {
		return "AgentOffer [offer=" + offer + ", guid=" + guid + ", capacity="
				+ capacity + ", longitude=" + longitude + ", latitude="
				+ latitude + ", reputation=" + reputation + "]";
	}

}
