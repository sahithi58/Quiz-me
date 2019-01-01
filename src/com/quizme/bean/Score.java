package com.quizme.bean;

public class Score {
	int scoreId;
	String username;
	int score;
	String category;
	
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Score [username=" + username + ", score=" + score + ", category=" + category
				+ "]";
	}
}
	