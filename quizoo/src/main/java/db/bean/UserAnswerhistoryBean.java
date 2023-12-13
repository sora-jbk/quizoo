package db.bean;

import java.io.Serializable;

public class UserAnswerhistoryBean implements Serializable{
	private int quizId;
	private String answeredTime;
	private int questionCount;
	private int correctCount;
	
    private String userId; 
    private int userNo;  
    private String nickname;
	private String password;
    private int totalAnswer;
    private int correctAnswer;
    private float rating;
    
    
    public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getAnsweredTime() {
		return answeredTime;
	}
	public void setAnsweredTime(String answeredTime) {
		this.answeredTime = answeredTime;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public int getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
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
