package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.QuizBean;
import frame.exception.ResourceException;

/**
 * quiz表にアクセスします
 * @param orderColumn 並び替えに使用する列を指定します。nullを渡すとcreate_timeの降順でソートします。
 * @param genreNo ジャンルを指定します。-1を指定するとすべてのジャンルから取得します
 * @param searchStr タイトルによる検索用文字列。nullを指定するとすべてのクイズを取得。
 */
public class QuizDao extends Dao{
	
	public ArrayList<QuizBean> selectQuiz(String orderColumn, String genreNo, String searchStr) throws ResourceException{
		ArrayList<QuizBean> quizlist = new ArrayList<>();
		
		try {
			connect();
			ArrayList<String> params = new ArrayList<>();
			
			String sql = 
					"SELECT * FROM quiz "
					+ "INNER JOIN genre USING(genre_no) "
					+ "INNER JOIN nickname ON author_no = user_no"; 
			
			if(genreNo != null && !genreNo.isEmpty()) {
				sql += " WHERE genre_no = ? ";
				params.add(genreNo);
			}
			
			if(searchStr != null && !searchStr.isEmpty()) {
				if(genreNo != null && !genreNo.isEmpty()) {
					sql += " AND ";
				}else {
					sql += " WHERE ";
				}
				sql += " title LIKE ?";
				params.add("%"+searchStr+"%");
			}
			
			if(orderColumn == null || orderColumn.isEmpty()) {
				orderColumn = "create_time";
			}
			sql += " ORDER BY " + orderColumn + " desc ";
			
			
			System.out.println(sql);
			st = cn.prepareStatement(sql);
			
			
			for(int i = 0 ; i < params.size() ; i++ ) {
				st.setString(i+1, params.get(i));
			}
			
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				//ResultSetからQuizBeanのプロパティを設定
				QuizBean quizbean = new QuizBean();
				quizbean.setQuizId(rs.getInt("quiz_id"));
				quizbean.setAuthorNo(rs.getInt("author_no"));
				quizbean.setTitle(rs.getString("title"));
				quizbean.setQuestionCount(rs.getInt("question_count"));
				quizbean.setGenreNo(rs.getInt("genre_no"));
				quizbean.setGenre(rs.getString("genre_title"));
				quizbean.setExplanation(rs.getString("explanation"));
				quizbean.setCreateTime(rs.getString("create_time"));
				quizbean.setCorrectRate(rs.getFloat("correct_rate"));
				quizbean.setTotalParticipants(rs.getInt("total_participants"));	
				quizbean.setDeleted(rs.getBoolean("deleted"));
				quizbean.setAuthorNickname(rs.getString("nickname"));
				
				quizlist.add(quizbean);
				
			}
		} catch(SQLException e) {
			try {
				cn.rollback();
			} catch(SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			}
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
			} catch(SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			} finally {
				close();
			}
		}
		return quizlist.isEmpty() ? null : quizlist;
	}
	
	/**
	 * 指定されたクイズを取得します。
	 * @param quizId quiz表quiz_id列に対応した値。存在しない場合は例外が空のQuizBeanが帰る
	 * @return 取得されたクイズのQuizBean
	 * @throws ResourceException データ取得時に例外が発生した場合
	 */
	public QuizBean selectSearchedQuizByQuizId(int quizId) throws ResourceException {
		
		QuizBean quizbean = new QuizBean();
		
		try {
			connect();
			
			String sql = "SELECT * FROM quiz INNER JOIN genre USING(genre_no) WHERE quiz_id = ?"; 
			st = cn.prepareStatement(sql);
			st.setInt(1, quizId);
			rs = st.executeQuery();
			
			while(rs.next()) {
				//ResultSetからQuizBeanのプロパティを設定
				quizbean.setQuizId(rs.getInt("quiz_id"));
				quizbean.setAuthorNo(rs.getInt("author_no"));
				quizbean.setTitle(rs.getString("title"));
				quizbean.setQuestionCount(rs.getInt("question_count"));
				quizbean.setGenreNo(rs.getInt("genre_no"));
				quizbean.setGenre(rs.getString("genre"));
				quizbean.setExplanation(rs.getString("explanation"));
				quizbean.setCreateTime(rs.getString("create_time"));
				quizbean.setCorrectRate(rs.getFloat("correct_rate"));
				quizbean.setTotalParticipants(rs.getInt("total_participants"));	
				quizbean.setDeleted(rs.getBoolean("deleted"));
			
			}
		} catch(SQLException e) {
			//SQL例外を処理、必要に応じてロールバック
			try {
				cn.rollback();
			} catch(SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			}
		} finally {
			//リソースを閉じる
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
			} catch(SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			} finally {
				close();
			}
		}
		return quizbean;
	}
	

	/**
	 * quiz表にQuizBeanのデーターを挿入します
	 * @param quiz 挿入するQuizのBean
	 * @throws ResourceException データ挿入時に例外が発生した場合
	 */
	public void insertQuiz(QuizBean quiz) throws ResourceException {
		
		try {
			connect();
            
			String sql = "INSERT INTO quiz VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			st = cn.prepareStatement(sql);
			
			st.setInt(1, quiz.getQuizId());
			st.setInt(2, quiz.getAuthorNo());
			st.setString(3, quiz.getTitle());
			st.setInt(4, quiz.getQuestionCount());
			st.setInt(5, quiz.getGenreNo());
			st.setString(6, quiz.getExplanation());
			st.setString(7, quiz.getCreateTime());
			st.setFloat(8, quiz.getCorrectRate());
			st.setInt(9, quiz.getTotalParticipants());
			
			st.executeUpdate();
			
			cn.commit();
            System.out.println("quizのcommit完了");
			
	
		} catch(SQLException e) {
            try{
                cn.rollback();
            } catch(SQLException e2) {
            	throw new ResourceException(e2.getMessage(), e2);
            }
        } finally {
            close();
        }
		
	}
	
	/**
	 * quiz表からデータを削除します
	 * @param quizId 削除するQuizのquiz_no
	 * @throws ResourceException データーの削除時に例外が発生した場合
	 */
	public void deleteQuiz(int quizId,int userNo) throws ResourceException {
		
		try {
			connect();
			
			String sql = "UPDATE quiz SET deleted = 1 WHERE quiz_id =? AND author_no = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, quizId);
			st.setInt(2, userNo);
			
			if(st.executeUpdate() == 0) {
				throw new ResourceException("あなたが作った問題じゃないお",null);
			}
			
			cn.commit();	
		
		} catch(SQLException e) {
            try{
                cn.rollback();
            } catch(SQLException e2) {
            	throw new ResourceException(e2.getMessage(), e2);
            }
        } finally {
        	close();
        }
		
	}
	
	/**
	 * クイズのタイトルを変更します
	 * @param quizId 変更するデータのquiz_id
	 * @param title クイズの新しいタイトル
	 * @throws ResourceException タイトル変更時に例外が発生した場合
	 */
	public void updateTitle(int quizId, String title) throws ResourceException {
		
		try {
			connect();
			
			String sql = "UPDATE quiz SET title = ? WHERE quiz_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, title);
			st.setInt(2, quizId);
			
			st.executeUpdate();
			
			cn.commit();
			
		} catch(SQLException e) {
            try{
                cn.rollback();
            } catch(SQLException e2) {
            	throw new ResourceException(e2.getMessage(), e2);
            }
        } finally {
        	close();
        }
		
	}
	
	/**
	 * クイズのジャンルを変更します
	 * @param quizId 変更するデータのquiz_id
	 * @param genreNo クイズの新しいジャンル
	 * @throws ResourceException ジャンルの変更時に例外が発生した場合
	 */
	public void updateGenre(int quizId, int genreNo) throws ResourceException{
		
		try {
			connect();
			
			String sql = "UPDATE quiz SET genre_no = ? WHERE quiz_id = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, genreNo);
			st.setInt(2, quizId);
			
			st.executeUpdate();
			
			cn.commit();
			
		} catch(SQLException e) {
            try{
                cn.rollback();
            } catch(SQLException e2) {
            	throw new ResourceException(e2.getMessage(), e2);
            }
        } finally {
        	close();
        }
	}
	
	/**
	 * クイズの説明を変更します
	 * @param quizId 変更するデータのtitle
	 * @param explanation クイズの新しい説明
	 * @throws ResourceException 説明の変更時に例外が発生した場合
	 */
	public void updateExplanation(int quizId, String explanation) throws ResourceException {
		
		try {
			connect();
			
			String sql = "UPDATE quiz SET explanation = ? WHERE quiz_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, explanation);
			st.setInt(2, quizId);
			
			st.executeUpdate();
			
			cn.commit();
			
		} catch(SQLException e) {
            try{
                cn.rollback();
            } catch(SQLException e2) {
            	throw new ResourceException(e2.getMessage(), e2);
            }
        } finally {
        	close();
        }
		
	}
}
