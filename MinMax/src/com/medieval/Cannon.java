package com.medieval;

public class Cannon {
	
	private boolean alive;
	private Cannon leftNeighbour;
	private Cannon rightNeighbour;
	
	public Cannon(Cannon leftNeighbour,Cannon rightNeighbour){
		this.alive = true;
		this.leftNeighbour = leftNeighbour;
		this.rightNeighbour = rightNeighbour;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Cannon getLeftNeighbour() {
		return leftNeighbour;
	}

	public Cannon getRightNeighbour() {
		return rightNeighbour;
	}
	
	
	
	

}
