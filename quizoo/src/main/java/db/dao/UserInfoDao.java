package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.bean.UserInfoBean;
import frame.exception.ResourceException;

/**
 * UserInfo表にアクセスします
 */
public class UserInfoDao extends Dao {

	/**
	 * user_idからユーザデータを取得する
	 * @param user_id 取得するユーザーのuser_id
	 * @return 取得されたユーザーデータ
	 * @throws ResourceException データ取得時に例外が発生した場合
	 */
	public UserInfoBean selectSearchedUserByUserId(String user_id) throws ResourceException {

		PreparedStatement st = null;
		ResultSet rs = null;
		UserInfoBean userbean = new UserInfoBean();

		try {
			connect();
			//userinfo表とnickname表をuser_no列で結合
			String sql = "SELECT * FROM userinfo JOIN nickname USING(user_no) WHERE user_id = ?";
			st = cn.prepareStatement(sql);
			st.setString(1, user_id);
			rs = st.executeQuery();
			if (rs.next()) {
				//結果セットから取得したデータをUserInfoBeanに追加
				userbean.setUserId(rs.getString("user_id"));
				userbean.setUserNo(rs.getInt("user_no"));
				userbean.setNickname(rs.getString("nickname"));
				userbean.setPassword(rs.getString("password"));
				userbean.setTotalAnswer(rs.getInt("total_answer"));
				userbean.setCorrectAnswer(rs.getInt("correct_answer"));
				userbean.setRating(rs.getFloat("rating"));
			}

		} catch (SQLException e) {
			//SQL実行時に例外が発生した場合の処理
			try {
				cn.rollback();
			} catch (SQLException e2) {
				//ロールバック時に例外が発生した場合の処理
				throw new ResourceException(e2.getMessage(), e2);
			}
		} finally {
			//リソースを閉じる
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
	
	/**
	 * ユーザーデータを挿入します
	 * @param user 挿入するデーター
	 * @throws ResourceException データ挿入時に例外が発生した場合
	 */
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
			//SQL実行時に例外が発生した場合の処理
			throw new ResourceException(e.getMessage(), e);
		}finally {
			close();
		}

	}

	
	/**
	 * ユーザーデータを削除します
	 * @param user_id
	 * @throws ResourceException
	 */
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

	/**
	 * ユーザーのパスワードを変更します
	 * @param user_id 変更するユーザーのuser_id
	 * @param password 新しいパスワード
	 * @throws ResourceException 変更時に例外が発生した場合
	 */
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

	
	/**
	 * ユーザー毎のスコアを更新します
	 * XXX: コンパイルエラー有り、要修正
	 * @param answered 回答した問題数
	 * @param correct 正解した問題数
	 * @throws ResourceException 更新時に例外が発生した場合
	 */
	public void updateScore(int userNo, int answered, int correct) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			//合計回答数と合計正解数のアップデートのSQL
			String sql = "UPDATE userinfo SET total_answer = ?, correct_answer = ? WHERE user_no = ?";

      
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
	
	public void updateNickName(int userNo, String nickname) throws ResourceException {
		PreparedStatement st = null;
		try {
			connect();
			
			String sql = "UPDATE nickname SET nickname = ? WHERE user_no = ?";
      
			st = cn.prepareStatement(sql);

			st.setString(1, nickname);
			st.setInt(2, userNo);
			st.executeUpdate();

			cn.commit();
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
	}
	
	public String selectNickname(int userNo) throws ResourceException {
		PreparedStatement st = null;
		String nickname = null; 
		try {
			connect();
			
			String sql = "SELECT nickname FROM nickname WHERE user_no = ?";
      
			st = cn.prepareStatement(sql);

			st.setInt(1, userNo);
			rs = st.executeQuery();
			
			if(rs.next()) {				
				nickname = rs.getString(1);
			}

			cn.commit();
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
		
		close();
		
		return nickname;
	}
}
