package edu.fudan.se.crowdservice.siteinspection;

import java.util.List;

import edu.fudan.se.aggregator.Aggregator;
import edu.fudan.se.bean.WorkerResponse;

public class SelectedLatestResponseAggregator extends Aggregator {

	@Override
	protected String aggregateResult(
			List<WorkerResponse> responses) {
		if(responses.size() == 0){ 
			return null;
		}
	    WorkerResponse  wr = responses.get(0);
		for(WorkerResponse response : responses){
			if(wr.date.before(response.date)){
				wr = response;
			}
			acceptWorkerResponseId(response.id);
		}
		return wr.responseString;
	}
}
