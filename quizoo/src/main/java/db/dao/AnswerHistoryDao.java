package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.AnswerhistoryBean;
import db.bean.QuizBean;
import frame.exception.ResourceException;

public class AnswerHistoryDao extends Dao {
	public ArrayList<AnswerhistoryBean> selectAnswerHistory(int userNo) throws ResourceException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<AnswerhistoryBean> Answerhistory = new ArrayList<>();
		

		connect();
		
		String sql = "SELECT q.quiz_id, title, answered_time, a.question_count,correct_count, explanation, create_time, correct_rate, total_participants "
				+ " FROM answerhistory a"
				+ " INNER JOIN quiz q"
				+ " USING (quiz_id)"
				+ " WHERE user_no = ?";
		
		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, userNo);
			
			rs = st.executeQuery();
			
			
			while(rs.next()) {
			
				AnswerhistoryBean answerhistorybean = new AnswerhistoryBean();
				answerhistorybean.setAnsweredTime(rs.getString("answered_time"));
				answerhistorybean.setQuestionCount(rs.getInt("a.question_count"));
				answerhistorybean.setCorrectCount(rs.getInt("correct_count"));
				
				QuizBean quizbean = new QuizBean();
				quizbean.setQuizId(rs.getInt("q.quiz_id"));
				quizbean.setTitle(rs.getString("title"));
				quizbean.setExplanation(rs.getString("explanation"));
				quizbean.setCreateTime(rs.getString("create_time"));
				quizbean.setCorrectRate(rs.getFloat("correct_rate"));
				quizbean.setTotalParticipants(rs.getInt("totle_participants"));
				
				answerhistorybean.setQuizBean(quizbean);
				
				Answerhistory.add(answerhistorybean);
			}
			
			
			
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
		
		return Answerhistory;
	}
	
	public void insertAnswerHistory(int userNo, int quizId, String answeredTime, int correctCount) throws ResourceException {
		
		PreparedStatement st = null;
		
		connect();
		
		String sql = "INSERT INTO answerhistory (user_no, quiz_id, answered_time, correct_count)"
				+ "VALUES(?, ?, now(0), ?)";
		
		try {
			st = cn.prepareStatement(sql);
			
			st.setInt(1, userNo);
			st.setInt(2, quizId);
			st.setInt(3,correctCount);
			
			st.executeUpdate();
			
			cn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ResourceException(e.getMessage(), e);
		}
		
		
		close();		
		
	}
}





