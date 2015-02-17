package edu.fudan.se.crowdservice.siteinspection;

import edu.fudan.se.crowdservice.MyThreadPool;

import javax.xml.ws.Endpoint;

public class InspectSiteServicePublisher {

    private Endpoint endpoint;

    public static void main(String[] args) {
        InspectSiteServicePublisher self = new InspectSiteServicePublisher();
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
        int port = 8887;
//        String ip = "10.131.253.172";
        String ip = "192.168.0.103";
        String url = String.format("http://%s:%d/siteinspect", ip, port);
        endpoint.publish(url);
        System.out.println("Publishing Site Inspection Service:" + url);
    }

}
