package edu.fudan.se.crowdservice.priceassessment;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import edu.fudan.se.aggregator.Aggregator;
import edu.fudan.se.bean.WorkerResponse;

public class SelectedAverageResponseAggregator extends Aggregator {

	@Override
	protected String aggregateResult(
			List<WorkerResponse> responses) {
		if(responses.size() == 0){ 
			return null;
		}
		double avgPrice = 0;
		int i = responses.size();
		Document document = null;
		for(WorkerResponse response : responses){
			try {
				document = DocumentHelper.parseText(response.responseString);
			List<Node> nodes = document.selectNodes("/Root/TextInput/Value");
			avgPrice += Double.valueOf(nodes.get(0).getText());
			acceptWorker(response.worker);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		avgPrice = avgPrice/i;
		List<Node> nodes = document.selectNodes("/Root/TextInput/Value");
		nodes.get(0).setText(String.valueOf(avgPrice));
		return document.asXML();
	}
}
