package db.bean;

import java.io.Serializable;

public class CreateHistoryBean implements Serializable {
	private String title;
	private String explanation;
	
	private String createTime;
	private String genre;
	private float correctRate;
	private int questionCount;
	
	public CreateHistoryBean() {}
	
	public CreateHistoryBean(String title, String explanation, String createTime, String genre, float correctRate, int questionCount) {
		this.setTitle(title);
		this.setExplanation(explanation);
		this.setCreateTime(createTime);
		this.setGenre(genre);
		this.setCorrectRate(correctRate);
		this.setQuestionCount(questionCount);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public float getCorrectRate() {
		return correctRate;
	}

	public void setCorrectRate(float correctRate) {
		this.correctRate = correctRate;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
}
