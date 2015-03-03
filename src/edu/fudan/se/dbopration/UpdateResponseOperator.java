package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateResponseOperator extends BaseDBOperator<Boolean> {

    private String worker;
    private long taskid;
    private String response;

    public UpdateResponseOperator(String worker, long taskid, String response) {
        this.worker = worker;
        this.taskid = taskid;
        this.response = response;
    }

    @Override
    protected Boolean processData(Connection conn) throws Exception {
        String sql = "select workerresponse.id from workerresponse join microtask on workerresponse.taskid = microtask.id " +
                "where workerresponse.worker=? and microtask.id=? and " +
                "(microtask.deadline - time_to_sec(timediff(now(), microtask.createTime)) > 0)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, worker);
        ps.setLong(2, taskid);

        ResultSet rs = ps.executeQuery();

        if (rs.last()) {
            sql = "update workerresponse set responseString=?, answerTime=now() where worker=? and taskid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, response);
            ps.setString(2, worker);
            ps.setLong(3, taskid);
            return ps.executeUpdate() != 0;
        }
        return false;
    }

}
