package db.bean;

import java.io.Serializable;

public class AnswerhistoryBean implements Serializable{
	private String answeredTime;
	private int questionCount;
	private int correctCount;
	
	private QuizBean quizbean;

	public AnswerhistoryBean() {}
	
	public AnswerhistoryBean(String answeredTime, int questionCount, int correctCount, QuizBean quizbean) {
		this.answeredTime = answeredTime;
		this.questionCount = questionCount;
		this.correctCount = correctCount;
		this.quizbean = quizbean;
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
	

	public QuizBean getQuizBean() {
		return quizbean;
	}

	public void setQuizBean(QuizBean quizbean) {
		this.quizbean = quizbean;
	}
	
}
