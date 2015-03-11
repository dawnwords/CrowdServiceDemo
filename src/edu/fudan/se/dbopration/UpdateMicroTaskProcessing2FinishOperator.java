package edu.fudan.se.dbopration;

import edu.fudan.se.bean.MicroTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UpdateMicroTaskProcessing2FinishOperator extends
        BaseDBOperator<Boolean> {

    private long taskId;
    private List<Long> acceptWorkerIds;

    public UpdateMicroTaskProcessing2FinishOperator(long taskId, List<Long> acceptWorkerIds) {
        this.taskId = taskId;
        this.acceptWorkerIds = acceptWorkerIds;
    }

    @Override
    protected Boolean processData(Connection connection) throws Exception {
        String sql = "update microtask set state = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, MicroTask.State.FINISHED.ordinal());
        ps.setLong(2, taskId);
        int count = ps.executeUpdate();
        if (count > 0 && acceptWorkerIds.size() > 0) {
            String sqlQuestionMark = "";
            for (int i = 0; i < acceptWorkerIds.size(); i++) {
                sqlQuestionMark += "?,";
            }
            sqlQuestionMark = sqlQuestionMark.substring(0, sqlQuestionMark.length() - 1);
            sql = String.format("update workerresponse set isAccepted=1 where id in (%s)", sqlQuestionMark);
            ps = connection.prepareStatement(sql);
            int i = 1;
            for (long workerId : acceptWorkerIds) {
                ps.setLong(i++, workerId);
            }
            ps.executeUpdate();
            return true;
        }
        return false;
    }
}
