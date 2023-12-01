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
				//answerhistory表を別名 a
				+ " FROM answerhistory a"
				//quiz表を別名 qでanswerhistory表と内部結合
				+ " INNER JOIN quiz q"
				//内部結合の対象はquiz_id
				+ " USING (quiz_id)"
				+ " WHERE user_no = ?";
		
		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, userNo);
			
			rs = st.executeQuery();
			
			
			while(rs.next()) {
			
				AnswerhistoryBean answerhistorybean = new AnswerhistoryBean();
				//AnswerhistoryBeanにデータセット
				answerhistorybean.setAnsweredTime(rs.getString("answered_time"));
				answerhistorybean.setQuestionCount(rs.getInt("a.question_count"));
				answerhistorybean.setCorrectCount(rs.getInt("correct_count"));
				
				QuizBean quizbean = new QuizBean();
				//QuizBeanにデータセット
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
	
	//AnswerHistory表にデータを挿入
	public void insertAnswerHistory(int userNo, int quizId, String answeredTime, int questionCount, int correctCount) throws ResourceException {
		
		PreparedStatement st = null;
		
		connect();
		//answered_timeは回答が行われた時間。NOW(0)は、クエリを実行した瞬間の現在の日時を挿入するMySQLの関数
		//(0)はミリ秒を無視することを示しています。
		//このクエリが実行され、ユーザーがどのクイズにいつ回答したか、それに対する正答率などが記録
		String sql = "INSERT INTO answeredhistory (user_no, quiz_id, answered_time, question_count, correct_count)"
				+ "VALUES(?, ?, now(0), ?, ?)";
		
		try {
			st = cn.prepareStatement(sql);
			
			st.setInt(1, userNo);
			st.setInt(2, quizId);
			st.setString(3, answeredTime);
			st.setInt(4, questionCount);
			st.setInt(5,correctCount);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ResourceException(e.getMessage(), e);
		}
		
		
		close();		
		
	}
}





