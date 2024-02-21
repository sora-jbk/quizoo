package db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import frame.exception.ResourceException;


/**
 * Dao作成の為の抽象クラスです。<br>
 * このクラスを継承してDaoを作成してください
 */
public abstract class Dao {
	/**
	 * データベースとのコネクションを保持します
	 */
	protected Connection cn;
	/**
	 * サブクラスで使用するPreparedStatementです
	 */
	protected PreparedStatement st = null;
	/**
	 * サブクラスで使用するResultSetです
	 */
	protected ResultSet rs = null;

	
	/**
	 *Tomcatのコネクションプールを使用してデータベースに接続します。<br>
	 *失敗した場合は直接データベースに接続します。
	 *
	 * @throws ResourceException 接続時に例外が発生した場合
	 */
	protected void connect() throws ResourceException {
		
			try {
//				----Tomcatからコネクションを取得----
				Context c;
				String dataSource = "java:comp/env/jdbc/LoginTemplate";
				c = new InitialContext();
				
				DataSource ds = (DataSource)c.lookup(dataSource);
				
				
				cn = ds.getConnection();
				cn.setAutoCommit(false);
				
				System.out.println("コネクションプールからの取得に成功しました\u001B[42m"+cn+"\u001B[00m");
				
			} catch (NamingException | SQLException e) {
//				---Tomcatからコネクションが取得できなかったとき---
				
				close(); //Tomcatで取得しようとしたコネクションを切断
				
				System.out.println("\u001B[101m コネクションプールからの取得に失敗しました\u001B[0m");
				e.printStackTrace();
				System.out.println("コネクションを再度取得します（プールを使用しない）");
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver"); //jdbcドライバの読み込み
					
					// quizoo_appでコネクションを取得
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizoo","quizoo_app","app");
					
					System.out.println("コネクション\u001B[42m" + cn + "\u001B[0mが取得されました");
					cn.setAutoCommit(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("\u001B[101m 取得に失敗しました\u001B[0m");
					// 独自例外でラップ
					throw new ResourceException(e.getMessage(), e);
				}
			}
			
		}
		
		

	/**
	 * データベースとのコネクションを切断します
	 * 
	 * @throws ResourceException 切断時に例外が発生した場合
	 */
	protected void close() throws ResourceException{
		try {
			// cnが解放されていないとき
			if(cn!=null) {
				// ログのために文字列を取得
				String cnStr = cn.toString();
				// 切断
				cn.close();	
				// 切断されたことを確認するメッセージ
				System.out.println("コネクション\u001B[42m"+cnStr+"\u001B[0mがクローズされました");
			}
		} catch (SQLException e) {
			throw new ResourceException(e.getMessage(), e);
		}
	}

}
