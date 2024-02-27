package db.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable{ 
    private String userId; 
    private int userNo = -1;  
    private String nickname;
    private String password;
    private int totalAnswer = -1;
    private int correctAnswer = -1;
    private float rating = -1.0f;
    
    public UserInfoBean() {}
    
    public UserInfoBean(String userId, int userNo, String nickname, String password, int totalAnswer, int correctAnswer, float rating) {
    	this.userId = userId;
    	this.userNo = userNo;
    	this.nickname = nickname;
    	this.password = password;
    	this.totalAnswer = totalAnswer;
    	this.correctAnswer = correctAnswer;
    	this.rating = rating;
    	
    }
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTotalAnswer() {
		return totalAnswer;
	}
	public void setTotalAnswer(int totalAnswer) {
		this.totalAnswer = totalAnswer;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
    
    
} 
