package com.tictactoe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Answer1 {	

	public static void main(String[] args) throws IOException {

		List<Board> testCases = initTestCases(args[0]);

		int i =1;
		for (Board testCase : testCases) {			

			while (!testCase.isGameOver()) {
				testCase.playerMove();							
			}
			StringBuilder testCaseSummary = new StringBuilder("Case ");
			testCaseSummary.append(i++);
			testCaseSummary.append(": ");
			
			if(testCase.hasOponentWon()){
				testCaseSummary.append(testCase.getOponent());
				testCaseSummary.append(" Wins");
			}else if (testCase.hasPlayerWon()){
				testCaseSummary.append(testCase.getPlayer());
				testCaseSummary.append(" Wins");
			}else{
				testCaseSummary.append("Draw");
			}					
			System.out.println(testCaseSummary.toString());			

		}

	}

	

	private static List<Board> initTestCases(String testCaseFile)
			throws IOException {
		Path filePath = FileSystems.getDefault().getPath(testCaseFile);
		InputStream file = Files.newInputStream(filePath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			int testCases = scanner.nextInt();
			scanner.nextLine();
			List<Board> boards = new ArrayList<Board>(testCases);
			for (int i = 0; i < testCases; i++) {
				char nextPlayer = getPlayer(scanner.nextLine());
				char[] rowOne = parseRow(scanner.nextLine());
				char[] rowTwo = parseRow(scanner.nextLine());
				char[] rowThree = parseRow(scanner.nextLine());
				char[][] initBoard = { rowOne, rowTwo, rowThree };
				boards.add(new Board(initBoard,nextPlayer));
			}

			return boards;
		} finally {
			if (scanner != null) {
				scanner.close();
			}

		}
	}

	private static char getPlayer(String nextLine) {	
		assert nextLine!=null && nextLine.length() ==1;
		return nextLine.charAt(0);
	}

	private static char[] parseRow(String nextLine) {
		assert nextLine!=null && nextLine.length() ==3;
		char[] board =  new char[3];
		for(int i =0 ; i <3 ; i++){
			board[i] = nextLine.charAt(i);
			
		}
		return board;
	}

}
