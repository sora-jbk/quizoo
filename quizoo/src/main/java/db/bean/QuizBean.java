package db.bean;

import java.io.Serializable;

public class QuizBean implements Serializable{
	private int quizId;
	private int authorNo;
	private String authorNickname;
	private String title;
	private int questionCount;
	private int genreNo;
	private String genre;
	private String explanation;
	private String createTime;
	private float correctRate;
	private int totalParticipants;
	private boolean deleted;
	

	public QuizBean(){}
	
	public QuizBean(int quizId,int authorNo,String authorNickname,String title,int questionCount,int genreNo,String genre,
			String explanation,String createTime,float correctRate,int totalParticipants,boolean deleted){
		this.quizId=quizId;
		this.authorNo=authorNo;
		this.authorNickname=authorNickname;
		this.title=title;
		this.questionCount=questionCount;
		this.genreNo=genreNo;
		this.genre=genre;
		this.explanation=explanation;
		this.correctRate=correctRate;
		this.totalParticipants=totalParticipants;
		this.deleted = deleted;
	}
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getAuthorNo() {
		return authorNo;
	}

	public void setAuthorNo(int authorNo) {
		this.authorNo = authorNo;
	}

	public String getAuthorNickname() {
		return authorNickname;
	}

	public void setAuthorNickname(String authorNickname) {
		this.authorNickname = authorNickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getGenreNo() {
		return genreNo;
	}

	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public float getCorrectRate() {
		return correctRate;
	}

	public void setCorrectRate(float correctRate) {
		this.correctRate = correctRate;
	}

	public int getTotalParticipants() {
		return totalParticipants;
	}

	public void setTotalParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}
	
}
