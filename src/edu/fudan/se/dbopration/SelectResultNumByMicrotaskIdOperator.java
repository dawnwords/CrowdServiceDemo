package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dawnwords on 2015/3/15.
 */
public class SelectResultNumByMicrotaskIdOperator extends BaseDBOperator<Integer> {
    private long taskId;

    public SelectResultNumByMicrotaskIdOperator(long taskId) {
        this.taskId = taskId;
    }

    @Override
    protected Integer processData(Connection connection) throws Exception {
        String sql = "select resultNum from microtask where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, taskId);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

}
