package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import edu.fudan.se.bean.AgentOffer;

public class UpdateOfferSelectOperator extends BaseDBOperator<Boolean> {

	private List<AgentOffer> offers;

	public UpdateOfferSelectOperator(List<AgentOffer> offers) {
		this.offers = offers;
	}

	@Override
	protected Boolean processData(Connection connection) throws Exception {

		if (offers == null || offers.size() == 0) {
			System.out.println("UpdateOffer error");
			return false;
		}

		String sqlQuestionMark = "";
		for (int i = 0; i < offers.size(); i++) {
			sqlQuestionMark += "?,";
		}
		sqlQuestionMark = sqlQuestionMark.substring(0,
				sqlQuestionMark.length() - 1);
		String sql = String.format(
				"update workerresponse set isSelected=1 where worker in (%s)",
				sqlQuestionMark);
		PreparedStatement ps = connection.prepareStatement(sql);
		int i = 1;
		for (AgentOffer offer : offers) {
			ps.setString(i++, offer.guid);
		}
		ps.executeUpdate();
		return true;
	}

}
