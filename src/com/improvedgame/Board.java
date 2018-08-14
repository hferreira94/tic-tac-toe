package com.improvedgame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	public static String[] board = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
	public static int currentBoardState;
	
	/** Print the game board */
	public static void printBoard() {
		System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + "\n===+===+===\n" + " " + board[3] + " | " + board[4] + " | " + board[5] + "\n===+===+===\n" + " " + board[6] + " | " + board[7] + " | " + board[8] + "\n"); // print all the board cells
	}
	
	public static boolean gameIsOver()
	{
		return board[0] == board[1] && board[1] == board[2] ||
		      board[3] == board[4] && board[4] == board[5] ||
		      board[6] == board[7] && board[7] == board[8] ||
		      board[0] == board[3] && board[3] == board[6] ||
		      board[1] == board[4] && board[4] == board[7] ||
		      board[2] == board[5] && board[5] == board[8] ||
		      board[0] == board[4] && board[4] == board[8] ||
		      board[2] == board[4] && board[4] == board[6];
	}

	public boolean tie() 
	{
		return board[0] != "0" &&
	           board[1] != "1" &&
	           board[2] != "2" &&
	           board[3] != "3" &&
	           board[4] != "4" &&
	           board[5] != "5" &&
	           board[6] != "6" &&
	           board[7] != "7" &&
	           board[8] != "8";
	}
	
	
	
	public static void evalBoardComputer() {
	    boolean foundSpot = false;
	    do {
	      if (board[4] == "4") {
	        board[4] = "O";
	        foundSpot = true;
	      } else {
	        int spot = getBestMove();
	        if (board[spot] != "X" && board[spot] != "O") {
	          foundSpot = true;
	          board[spot] = "O";
	        } else {
	          foundSpot = false;
	        }
	      }
	    } while (!foundSpot);
	    printBoard();
	  }
	
	public static int getBestMove() 
	{
	    ArrayList<String> availableSpaces = new ArrayList<String>();
	    boolean foundBestMove = false;
	    int spot = 100;
	    for (String s: board) {
	      if (s != "X" && s != "O") {
	        availableSpaces.add(s);
	      }
	    }
	    for (String as: availableSpaces) {
	      spot = Integer.parseInt(as);
	      board[spot] = "O";
	      if (gameIsOver()) {
	        foundBestMove = true;
	        board[spot] = as;
	        return spot;
	      } else {
	        board[spot] = "X";
	        if (gameIsOver()) {
	          foundBestMove = true;
	          board[spot] = as;
	          return spot;
	        } else {
	          board[spot] = as;
	        }
	      }
	    }
	    if (foundBestMove) {
	      return spot;
	    } else {
	      int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
	      return Integer.parseInt(availableSpaces.get(n));
	    }
	  }
}
