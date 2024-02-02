package db.bean;

import java.io.Serializable;

public class CreateHistoryBean implements Serializable {
	private String title;
	private String explanation;
	
	private String nickname;
	private String createTime;
	private String genre;
	private float correctRate;
	
	public CreateHistoryBean() {}
	
	public CreateHistoryBean(String title, String explanation, String nickname, String createTime, String genre, float correctRate) {
		this.setTitle(title);
		this.setExplanation(explanation);
		this.setNickname(nickname);
		this.setCreateTime(createTime);
		this.setGenre(genre);
		this.setCorrectRate(correctRate);
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
}
