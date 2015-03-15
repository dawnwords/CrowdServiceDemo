package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UpdateMicroTaskOffer2ProcessingOperator extends
        BaseDBOperator<List<MicroTask>> {

    @Override
    protected List<MicroTask> processData(Connection conn) throws Exception {
        List<MicroTask> result = new LinkedList<MicroTask>();

        String sql = "select id,template,consumer,cost,deadline,compositeService,crowdService,resultNum,longitude,latitude from microtask "
                + "where state=? and now() >= date_add(createTime, INTERVAL ?*deadline second)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, MicroTask.State.OFFER.ordinal());
        ps.setDouble(2, MicroTask.OFFER_RATIO);
        ResultSet rs = ps.executeQuery();

        String questionMarkList = "";
        while (rs.next()) {
            long id = rs.getLong(1);
            String template = rs.getString(2);
            String consumer = rs.getString(3);
            int cost = rs.getInt(4);
            int deadline = rs.getInt(5);
            String compositeService = rs.getString(6);
            String crowdService = rs.getString(7);
            int resultNum = rs.getInt(8);
            double longitude = rs.getDouble(9);
            double latitude = rs.getDouble(10);
            result.add(new MicroTask(id, template, consumer, cost, deadline,
                    compositeService, crowdService, resultNum, longitude, latitude));
            questionMarkList += "?,";
        }

        if (result.size() > 0) {
            questionMarkList = questionMarkList.substring(0, questionMarkList.length() - 1);
            sql = String.format("update microtask set state=? where id in (%s)", questionMarkList);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, MicroTask.State.PROCESSING.ordinal());
            int i = 2;
            for (MicroTask task : result) {
                ps.setLong(i++, task.id);
            }
            ps.executeUpdate();
        }
        return result;
    }

}
