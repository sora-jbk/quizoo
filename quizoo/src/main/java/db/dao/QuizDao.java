package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.bean.QuizBean;
import frame.exception.ResourceException;

public class QuizDao extends Dao{
	public ArrayList<QuizBean> selectQuiz() throws ResourceException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<QuizBean> quizlist = new ArrayList<>();
		
		try {
			connect();
			
			String sql = "SELECT * FROM quiz INNER JOIN genre USING(genre_no)"; 
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
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
		return quizlist;
	}
	
	public QuizBean selectQuiz(int quizId) throws ResourceException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		QuizBean quizbean = new QuizBean();
		
		try {
			connect();
			
			String sql = "SELECT * FROM quiz INNER JOIN genre USING(genre_no) WHERE quiz_id = ?"; 
			st = cn.prepareStatement(sql);
			st.setInt(1, quizId);
			rs = st.executeQuery();
			
			while(rs.next()) {
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
			
				cn.commit();
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
		return quizbean;
	}
	
	public void insertQuiz(QuizBean quiz) throws ResourceException {
		PreparedStatement st = null;
		
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
	
	public ArrayList<QuizBean> sortGenreQuiz() throws ResourceException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<QuizBean> quizlist = new ArrayList<>();
		
		try {
			connect();
			
			String sql = "SELECT * FROM quiz INNER JOIN genre USING(genre_no) ORDER BY genre_no"; 
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
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
		return quizlist;
	}
	
	public void deleteQuiz(int quizId) throws ResourceException {
		PreparedStatement st = null;
		
		try {
			connect();
			
			String sql = "DELETE FROM quiz WHERE quiz_id = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, quizId);
			
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
	
	public void updateTitle(int quizId, String title) throws ResourceException {
		PreparedStatement st = null;
		
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
	
	public void updateGenre(int quizId, int genreNo) throws ResourceException{
		PreparedStatement st = null;
		
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
	
	public void updateExplanation(int quizId, String explanation) throws ResourceException {
		PreparedStatement st = null;
		
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
	
	public void updateRateAndTotalPaticipants(int quizId, int score) throws ResourceException {
		PreparedStatement st = null;
		
		try {
			connect();
			
			String sql = "UPDATE quiz SET score = ? WHERE quiz_id = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, score);
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
