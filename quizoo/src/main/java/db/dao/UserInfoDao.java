package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.bean.UserInfoBean;
import frame.exception.ResourceException;

public class UserInfoDao extends Dao {

	public UserInfoBean selectUser(String user_id) throws ResourceException {

		PreparedStatement st = null;
		ResultSet rs = null;
		UserInfoBean userbean = new UserInfoBean();

		try {
			connect();
			//ログインのSQL
			String sql = "SELECT * FROM userinfo JOIN nickname USING(user_no) WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, user_id);
			rs = st.executeQuery();
			if (rs.next()) {
				userbean.setUserId(rs.getString("user_id"));
				userbean.setUserNo(rs.getInt("user_no"));
				userbean.setNickname(rs.getString("nickname"));
				userbean.setPassword(rs.getString("password"));
				userbean.setTotalAnswer(rs.getInt("total_answer"));
				userbean.setCorrectAnswer(rs.getInt("correct_answer"));
				userbean.setRating(rs.getFloat("rating"));
			}

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			} finally {
				close();
			}
		}
		return userbean;
	}

	public void insertUser(UserInfoBean user) throws ResourceException {
		PreparedStatement st = null;
		System.out.println("insertUserにきた");
		try {
			connect();
			
			cn.setAutoCommit(false);
			//サインアップのSQL
			String sql = " INSERT INTO userinfo(user_id,password) VALUES(?,?)";
			st = cn.prepareStatement(sql);
			
			System.out.println(user.getUserId());
			

			st.setString(1, user.getUserId());
			st.setString(2, user.getPassword());
			st.executeUpdate();
			
			sql="INSERT INTO nickname(user_no,nickname) VALUES(last_insert_id(),?)";
			st = cn.prepareStatement(sql);
			st.setString(1,user.getNickname());
			st.executeUpdate();
			
			
			cn.commit();
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}finally {
			close();
		}

	}

	public void deleteUser(String user_id) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			
			String sql = "DELETE FROM userinfo WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, user_id);

			st.executeUpdate();
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e2) {
				throw new ResourceException(e2.getMessage(), e2);
			}
		} finally {
			close();
		}
	}

	public void updatePassword(String user_id, String password) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();

			String sql = "UPDATE userinfo SET password =? WHERE user_id =?";
			st = cn.prepareStatement(sql);
			st.setString(1, password);
			st.setString(2, user_id);

			st.executeUpdate();

			cn.commit();
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		} finally {
			close();
		}
	}

	public void updateScore(int userNo, int answered, int correct) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			String sql = "UPDATE userinfo SET total_answer = ?,correct_answer =? WHERE user_no = ?";
			st = cn.prepareStatement(sql);
			st.setInt(1, answered);
			st.setInt(2, correct);
			st.setInt(3, userNo);
			st.executeUpdate();

			cn.commit();
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
	}
}
