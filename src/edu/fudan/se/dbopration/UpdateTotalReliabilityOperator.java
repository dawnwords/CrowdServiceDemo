package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/3/16.
 */
public class UpdateTotalReliabilityOperator extends BaseDBOperator<Boolean> {
    private long taskid;
    private double reliability;

    public UpdateTotalReliabilityOperator(long taskid, double reliability) {
        this.taskid = taskid;
        this.reliability = reliability;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "update microtask set totalReliability=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, reliability);
        ps.setLong(2, taskid);
        return ps.executeUpdate() > 0;
    }
}
