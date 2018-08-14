package com.improvedgame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Computer {
	private static String selectedSymbol;

	public String getSelectedSymbol() {
		return selectedSymbol;
	}

	public void setSelectedSymbol(String selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
	}
	
	public static void evalBoard(Board gameBoard) {
	    boolean foundSpot = false;
	    do {
	      if (gameBoard.board[4] == "4") {
	    	  gameBoard.board[4] = selectedSymbol;
	        foundSpot = true;
	      } else {
	        int spot = getBestMove(gameBoard);
	        if (gameBoard.board[spot] != "X" && gameBoard.board[spot] != "O") {
	          foundSpot = true;
	          gameBoard.board[spot] = selectedSymbol;
	        } else {
	          foundSpot = false;
	        }
	      }
	    } while (!foundSpot);
	    gameBoard.printBoard();
	  }
	
	public static int getBestMove(Board gameBoard) 
	{
	    ArrayList<String> availableSpaces = new ArrayList<String>();
	    boolean foundBestMove = false;
	    int spot = 100;
	    for (String s: gameBoard.board) {
	      if (s != "X" && s != "O") {
	        availableSpaces.add(s);
	      }
	    }
	    for (String as: availableSpaces) {
	      spot = Integer.parseInt(as);
	      gameBoard.board[spot] = "O";
	      if (gameBoard.gameIsOver()) {
	        foundBestMove = true;
	        gameBoard.board[spot] = as;
	        return spot;
	      } else {
	    	  gameBoard.board[spot] = "X";
	        if (gameBoard.gameIsOver()) {
	          foundBestMove = true;
	          gameBoard.board[spot] = as;
	          return spot;
	        } else {
	        	gameBoard.board[spot] = as;
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
