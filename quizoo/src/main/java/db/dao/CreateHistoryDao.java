package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.CreateHistoryBean;
import frame.exception.ResourceException;

public class CreateHistoryDao extends Dao {
	
	public ArrayList<CreateHistoryBean> selectCreateHistory(int userNo) throws ResourceException {
		//回答履歴を格納するArrayList
		ArrayList<CreateHistoryBean> createhistory = new ArrayList<>();
		
		connect();
		
		String sql = "SELECT q.title AS title,"
	            + "q.explanation AS explanation,"
	            + "q.create_time AS create_time,"
	            + "g.genre_title AS genre_title, "
	            + "q.correct_rate AS correct_rate, "
	            + "q.question_count AS question_count "
	            + "FROM genre g "
	            + "INNER JOIN quiz q "
	            + "ON g.genre_no = q.genre_no "
	            + "WHERE q.author_no = ? "
	            + "ORDER BY q.create_time DESC";
		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, userNo);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				//QuizBeanの作成とセット
				CreateHistoryBean createHistoryBean = new CreateHistoryBean();
				createHistoryBean.setTitle(rs.getString("title"));
				createHistoryBean.setExplanation(rs.getString("explanation"));
				createHistoryBean.setCreateTime(rs.getString("create_time"));
				createHistoryBean.setGenre(rs.getString("genre_title"));
				createHistoryBean.setCorrectRate(rs.getFloat("correct_rate"));
				createHistoryBean.setQuestionCount(rs.getInt("question_count"));
				
				//Listに追加
				createhistory.add(createHistoryBean);
			}
			
			
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
		
		return createhistory.isEmpty() ? null : createhistory;
	}
}
