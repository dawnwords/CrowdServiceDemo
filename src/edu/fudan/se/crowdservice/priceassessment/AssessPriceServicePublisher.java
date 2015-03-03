package edu.fudan.se.crowdservice.priceassessment;

import edu.fudan.se.crowdservice.MyThreadPool;

import javax.xml.ws.Endpoint;

public class AssessPriceServicePublisher {

    private Endpoint endpoint;

    public static void main(String[] args) {
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
        int port = 8888;
        String ip = "10.131.253.211";
        String url = String.format("http://%s:%d/priceassess", ip, port);
        endpoint.publish(url);
        System.out.println("Publishing Price Assessment Service:" + url);
    }

}
