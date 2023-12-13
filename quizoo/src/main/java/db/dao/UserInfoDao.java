package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.bean.UserInfoBean;
import frame.exception.ResourceException;

public class UserInfoDao extends Dao {

	//ログイン
	public UserInfoBean selectUser(String user_id) throws ResourceException {

		PreparedStatement st = null;
		ResultSet rs = null;
		UserInfoBean userbean = new UserInfoBean();

		try {
			connect();
			//userinfo表とnickname表を結合
			String sql = "SELECT * FROM userinfo JOIN nickname USING(user_no) WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, user_id);
			rs = st.executeQuery();
			if (rs.next()) {
				//UserInfoBeanに追加
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
	//新規登録
	public void insertUser(UserInfoBean user) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			
			cn.setAutoCommit(false);
			//userinfo表にidとpasswordを追加する、サインアップのSQL
			String sql = " INSERT INTO userinfo(user_id,password) VALUES(?,?)";
			st = cn.prepareStatement(sql);
		
			st.setString(1, user.getUserId());
			st.setString(2, user.getPassword());
			st.executeUpdate();
			
			//nickname表にuserNoとnicknameを追加する、サインアップのSQL
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
	//ユーザー削除
	public void deleteUser(String user_id) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			//ユーザー削除のSQL
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
	//パスワードの変更
	public void updatePassword(String user_id, String password) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			//パスワードの変更処理のSQL
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

	//スコアの更新
	public void updateScore(int answered, int correct) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			//合計回答数と合計正解数のアップデートのSQL
			String sql = "UPDATE userinfo SET total_answered = ?, correct_ansewerd = ?";
      
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
