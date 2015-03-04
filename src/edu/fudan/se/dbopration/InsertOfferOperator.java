package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertOfferOperator extends BaseDBOperator<Boolean> {

    private String worker;
    private long taskid;
    private int offer;

    public InsertOfferOperator(String worker, long taskId, int offer) {
        this.worker = worker;
        this.taskid = taskId;
        this.offer = offer;
    }

    @Override
    protected Boolean processData(Connection conn) throws Exception {
        String sql = "select (?*deadline - time_to_sec(timediff(now(), createTime)) < 0) as outdated from microtask where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, MicroTask.OFFER_RATIO);
        ps.setLong(2, taskid);
        ResultSet rs = ps.executeQuery();
        if (!rs.next() || rs.getBoolean(1)) {
            return false;
        }

        sql = "insert into workerresponse (worker, taskid, offer) values (?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, worker);
        ps.setLong(2, taskid);
        ps.setInt(3, offer);
        if (ps.executeUpdate() == 0) {
            throw new Exception(
                    "insertResponse(): error occured when inserting.");
        }
        return true;
    }

}
