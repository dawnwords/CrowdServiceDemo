package edu.fudan.se.dbopration;

import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class SelectOfferByMicroTaskOperator extends
        BaseDBOperator<List<AgentOffer>> {

    private MicroTask task;

    public SelectOfferByMicroTaskOperator(MicroTask task) {
        this.task = task;
    }

    @Override
    protected List<AgentOffer> processData(Connection connection)
            throws Exception {
        List<AgentOffer> result = new LinkedList<AgentOffer>();

        String sql = "select guid,capacity,longitude,latitude,reputation,offer,timeEstimate from agentinfo,workerresponse "
                + "where workerresponse.taskid=? and agentinfo.guid = workerresponse.worker";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, task.id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String guid = rs.getString(1);
            String capacity = rs.getString(2);
            double longitude = rs.getDouble(3);
            double latitude = rs.getDouble(4);
            double reputation = rs.getDouble(5);
            int offer = rs.getInt(6);
            int timeEstimate = rs.getInt(7);
            result.add(new AgentOffer(guid, capacity, longitude, latitude,
                    reputation, offer, timeEstimate));
        }
        return result;
    }
}
