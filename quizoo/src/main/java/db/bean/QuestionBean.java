package db.bean;

import java.io.Serializable;
import java.util.Arrays;

public class QuestionBean implements Serializable {
	private int quizId;
	private int questionId;
	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private boolean[] judge;
	
	
	public QuestionBean() {}
	
	public QuestionBean(int quizId, int questionId, String question, String choice1, String choice2, String choice3
						, String choice4, boolean[] judge) {
		this.setQuizId(quizId);
		this.setQuestionId(questionId);
		this.question = question;
		this.setChoice1(choice1);
		this.setChoice2(choice2);
		this.setChoice3(choice3);
		this.setChoice4(choice4);
		this.judge=judge;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public boolean[] getJudge() {
		return judge;
	}

	public void setJudge(boolean[] judge) {
		this.judge = judge;
	}
	public String toString() {
	    return "QuestionBean{" +
	            "quizId=" + quizId +
	            ", questionId=" + questionId +
	            ", question='" + question + '\'' +
	            ", choice1='" + choice1 + '\'' +
	            ", choice2='" + choice2 + '\'' +
	            ", choice3='" + choice3 + '\'' +
	            ", choice4='" + choice4 + '\'' +
	            ", judge=" + Arrays.toString(judge) +
	            '}';
	}

}
