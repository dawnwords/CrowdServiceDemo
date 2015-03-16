package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dawnwords on 2015/3/16.
 */
public class UpdateMicroTaskPlanning2ProcessingOperator extends BaseDBOperator<Boolean> {
    private long taskId;

    public UpdateMicroTaskPlanning2ProcessingOperator(long taskId) {
        this.taskId = taskId;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "update microtask set state=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, MicroTask.State.PROCESSING.ordinal());
        ps.setLong(2, taskId);
        int count = ps.executeUpdate();
        return count > 0;
    }
}
