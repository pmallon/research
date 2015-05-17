package com.tictactoe;

import java.util.ArrayList;
import java.util.List;


public class Board {

	private List<Point> availablePoints;
	char[][] board;
	
	private char player;
	private char oponent;
	
	private List<PointsAndScores> rootsChildrenScores;
	

	public Board(char[][] board,char player) {
		this.board = board;
		setPlayer(player);
		
	}
	
	private void setPlayer(char player){
		this.player = player;
		this.oponent = (player == 'X')? 'O' :'X';
	}
	
	public char getPlayer() {
		return player;
	}

	public char getOponent() {
		return oponent;
	}

	
    public boolean isGameOver() {
        //Game is over is someone has won, or board is full (draw)
        return (hasPlayerWon() || hasOponentWon() || getAvailableStates().isEmpty());
    }

    public boolean hasPlayerWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player)) {           
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player))) {                
                return true;
            }
        }
        return false;
    }

    public boolean hasOponentWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == oponent) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == oponent)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == oponent)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == oponent)) {                
                return true;
            }
        }

        return false;
    }

    private List<Point> getAvailableStates() {
        availablePoints = new ArrayList();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == '_') {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    private void placeAMove(Point point, char player) {
        board[point.x][point.y] = player;   
    }

    private Point returnBestMove() {            
        if(rootsChildrenScores!=null && !rootsChildrenScores.isEmpty()){        	
        	rootsChildrenScores.sort(null);
        	return rootsChildrenScores.get(0).getPoint();
        }
        return null;

       
    }
    
    public void displayBoard() {
       /* System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();

        }*/
    }

    public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

  
    public void playerMove(){
    	displayBoard();
    	calculateMove();
		placeAMove(returnBestMove(), getPlayer());	
		
		//Change player
		setPlayer(getOponent());
		displayBoard();
    }
 
    private void calculateMove(){
        rootsChildrenScores = new ArrayList();
       minimax(0, player);
    }
    
    /*
     * Will return a winning score path.But not necessarily the shortest.
     * Weighting would need to be added to depth, but without impacting calculation
     */
    private int minimax(int depth, int turn) {    	

        if (hasPlayerWon()) return +10-depth;
        if (hasOponentWon()) return -10+depth;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0; 

        List scores = new ArrayList(); 

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);  

            if (turn == player) { //X's turn select the highest from below minimax() call
                placeAMove(point, player); 
                int currentScore = minimax(depth + 1, oponent);
                scores.add(currentScore);

                if (depth == 0) 
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                
            } else if (turn == oponent) {//O's turn select the lowest from below minimax() call
                placeAMove(point, oponent); 
                scores.add(minimax(depth + 1, player));
            }
            board[point.x][point.y] = '_'; //Reset this point
        }
        return turn == player ? returnMax(scores) : returnMin(scores);
    }

}
