package com.quizme.bean;

public class Question {
	
	String question;
	String choice1;
	String choice2;
	String choice3;
	String choice4;
	String rightChoice;
	String category;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Question(String question, String choice1, String choice2, String choice3, String choice4) {
		super();
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getRightChoice() {
		return rightChoice;
	}
	public void setRightChoice(String rightChoice) {
		this.rightChoice = rightChoice;
	}
	@Override
	public String toString() {
		return "Question [question=" + question + ",\n choice1=" + choice1 + ", \nchoice2=" + choice2 + ", \nchoice3="
				+ choice3 + ", \nchoice4=" + choice4 + ", \ncategory=" + category + "]";
	}
	
	
}
