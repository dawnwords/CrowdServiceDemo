package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dawnwords on 2015/3/16.
 */
public class SelectMicrotaskStateByMicrotaskIdOperator extends BaseDBOperator<MicroTask.State> {
    private long taskid;

    public SelectMicrotaskStateByMicrotaskIdOperator(long taskid) {
        this.taskid = taskid;
    }

    @Override
    protected MicroTask.State processData(Connection connection) throws Exception {
        String sql = "select state from microtask where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, taskid);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? MicroTask.State.values()[rs.getInt(1)] : MicroTask.State.INITIAL;
    }
}
