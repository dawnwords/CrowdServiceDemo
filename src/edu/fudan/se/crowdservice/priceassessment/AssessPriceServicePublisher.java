package edu.fudan.se.crowdservice.priceassessment;

import javax.xml.ws.Endpoint;

import edu.fudan.se.crowdservice.MyThreadPool;

public class AssessPriceServicePublisher {

	private Endpoint endpoint;

    public static void main(String[ ] args) {
    	AssessPriceServicePublisher self = new AssessPriceServicePublisher();
        self.create_endpoint();
        self.configure_endpoint();
        self.publish();
    }
    
    private void create_endpoint() {
        endpoint = Endpoint.create(new AssessPriceServiceImpl());
    }
    
    private void configure_endpoint() {
        endpoint.setExecutor(new MyThreadPool());
    }
    
    private void publish() {
        int port = 8887;
        String url = "http://10.131.253.172:" + port + "/priceassess";
        endpoint.publish(url);
        System.out.println("Publishing Price Accessment Service on port " + port);
    }

}
