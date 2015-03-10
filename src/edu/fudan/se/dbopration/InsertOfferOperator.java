package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.crowdservice.wrapper.OfferWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertOfferOperator extends BaseDBOperator<Boolean> {

    private String worker;
    private OfferWrapper offer;

    public InsertOfferOperator(String worker, OfferWrapper offer) {
        this.worker = worker;
        this.offer = offer;
    }

    @Override
    protected Boolean processData(Connection conn) throws Exception {
        String sql = "select (? * deadline - time_to_sec(timediff(now(), createTime)) < 0) as outdated from microtask where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, MicroTask.OFFER_RATIO);
        ps.setLong(2, offer.taskId);
        ResultSet rs = ps.executeQuery();
        if (!rs.next() || rs.getBoolean(1)) {
            return false;
        }

        sql = "insert into workerresponse (worker, taskid, offer, timeEstimate) values (?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, worker);
        ps.setLong(2, offer.taskId);
        ps.setInt(3, offer.price);
        ps.setInt(4, offer.time);
        if (ps.executeUpdate() == 0) {
            throw new Exception(
                    "insertResponse(): error occurred when inserting.");
        }
        return true;
    }

}
