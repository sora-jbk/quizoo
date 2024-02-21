package db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.AnswerhistoryBean;
import db.bean.QuizBean;
import frame.exception.ResourceException;

/**
 * AnswerHistory表にアクセスします
 */
public class AnswerHistoryDao extends Dao {
	/**
	 * userNoに対する回答履歴をすべて取得します
	 * @param userNo userinfo表user_no列に対応した値を渡します
	 * @return 回答履歴。存在しない場合は空のArrayList
	 * @throws ResourceException データ取得時に例外が発生した場合
	 */
	public ArrayList<AnswerhistoryBean> selectAnswerHistory(int userNo) throws ResourceException {
		//回答履歴を格納するArrayList
		ArrayList<AnswerhistoryBean> Answerhistory = new ArrayList<>();
		

		connect();
		
		String sql = "SELECT quiz_id, title, author_no, answered_time, a.question_count AS q_count, q.question_count AS now_count,genre_no,correct_count, explanation, create_time, correct_rate, total_participants, deleted "
				+ " FROM answerhistory a"
				+ " INNER JOIN quiz q"
				+ " USING (quiz_id)"
				+ " WHERE user_no = ?";
		
		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, userNo);
			
			rs = st.executeQuery();
			
			
			while(rs.next()) {
				//結果セットからデータを取得してAnswerhistoryBeanにセット
				AnswerhistoryBean answerhistorybean = new AnswerhistoryBean();
				answerhistorybean.setAnsweredTime(rs.getString("answered_time"));
				answerhistorybean.setQuestionCount(rs.getInt("q_count"));
				answerhistorybean.setCorrectCount(rs.getInt("correct_count"));
				
				//QuizBeanの作成とセット
				QuizBean quizbean = new QuizBean();
				quizbean.setQuizId(rs.getInt("quiz_id"));
				quizbean.setQuestionCount(rs.getInt("now_count"));
				quizbean.setTitle(rs.getString("title"));
				quizbean.setAuthorNo(rs.getInt("author_no"));
				quizbean.setExplanation(rs.getString("explanation"));
				quizbean.setCreateTime(rs.getString("create_time"));
				quizbean.setCorrectRate(rs.getFloat("correct_rate"));
				quizbean.setTotalParticipants(rs.getInt("total_participants"));
				quizbean.setGenreNo(rs.getInt("genre_no"));
				quizbean.setDeleted(rs.getBoolean("deleted"));
				
				answerhistorybean.setQuizBean(quizbean);
				
				//Listに追加
				Answerhistory.add(answerhistorybean);
			}
			
			
			
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
		
		return Answerhistory.isEmpty() ? null : Answerhistory;
	}
	
	/**
	 * AnswerHistoryに回答履歴を追加します
	 * @param userNo userinfo表user_no列に存在する値を渡します。値がデータベースに存在しない場合は例外が発生します
	 * @param quizId quiz表quiz_id列に対応した値を渡します。値がデータベースに存在しない場合は例外が発生します
	 * @param correctCount 回答したquizの正解数
	 * @throws ResourceException データ追加時に例が発生した場合	
	 */
	public void insertAnswerHistory(int userNo, int quizId,int correctCount) throws ResourceException {
		
		PreparedStatement st = null;
		
		connect();
		
		String sql = "INSERT INTO answerhistory (user_no, quiz_id, correct_count)"
				+ "VALUES(?, ?, ?)";
		
		try {
			st = cn.prepareStatement(sql);
			
			//パラメータの設定
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





