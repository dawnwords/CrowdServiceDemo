package edu.fudan.se.crowdservice.siteinspection;

import javax.xml.ws.Endpoint;

import edu.fudan.se.crowdservice.MyThreadPool;

public class InspectSiteSerivcePublisher {

	private Endpoint endpoint;

    public static void main(String[ ] args) {
    	InspectSiteSerivcePublisher self = new InspectSiteSerivcePublisher();
        self.create_endpoint();
        self.configure_endpoint();
        self.publish();
    }
    private void create_endpoint() {
        endpoint = Endpoint.create(new InspectSiteServiceImpl());
    }
    private void configure_endpoint() {
        endpoint.setExecutor(new MyThreadPool());
    }
    private void publish() {
        int port = 8888;
        String url = "http://10.131.253.172:" + port + "/siteinspect";
        endpoint.publish(url);
        System.out.println("Publishing Site Inspection Service on port " + port);
    }

}
