package sg.edu.sutd.ws;

import javax.xml.ws.Endpoint;

import edu.fudan.se.crowdservice.MyThreadPool;

public class GlobalOptimizationPublisher {

	private Endpoint endpoint;

    public static void main(String[ ] args) {
    	GlobalOptimizationPublisher self = new GlobalOptimizationPublisher();
        self.create_endpoint();
        self.configure_endpoint();
        self.publish();
    }
    
    private void create_endpoint() {
        endpoint = Endpoint.create(new GlobalOptimizationImpl());
    }
    
    private void configure_endpoint() {
        endpoint.setExecutor(new MyThreadPool());
    }
    private void publish() {
        int port = 8885;
        String url = "http://10.131.253.172:" + port + "/globalOptimization";
        endpoint.publish(url);
        System.out.println("globalOptimization on Port : " + port);
    }
}
