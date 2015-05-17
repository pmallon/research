package com.tictactoe;

public class PointsAndScores implements Comparable<PointsAndScores>{

	private int score;
	private Point point;

	PointsAndScores(int score, Point point) {
		this.score = score;
		this.point = point;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public int compareTo(PointsAndScores o) {
		return Integer.compare(o.getScore(),score);
		
	}
	
	

}
