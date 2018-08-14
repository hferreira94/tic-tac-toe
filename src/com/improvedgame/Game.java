package com.improvedgame;

import java.util.Scanner;

public class Game {
	public static String currentPlayer;
	static Player playerOne = new Player();
	static Player playerTwo = new Player();
	
	static Computer computerOne = new Computer();
	static Computer computerTwo = new Computer();
	
	public static int currentState;

	private static UI aUI = new UI();
	public static Scanner input = new Scanner(System.in); // the input Scanner

	private static final String MESSAGE_SELECT_GAME_MODE = "Select your game mode: \n 1-CVC \n 2-HVC \n 3-HVH";
	private static final String MESSAGE_SELECT_GAME_SYMBOL = "Player 1: select your game symbol: \n 1-X \n 2-O";
	private static final String MESSAGE_STARTING_PLAYER = "Who starts first? \n 1- Player 1 \n 2- Player 2";
	private static final String ERROR_INVALID_OPTION = "Invalid option, please select a valid one...";
	
	private static Board gameBoard = new Board();
	
	public static void main(String[] args) 
	{
		aUI.writeMessage(aUI.getWelcomeMessage());
		setupGameMode();	
	}
	
	public static void setupGameMode() 
	{
		int gameModeOption = aUI.promptUser(MESSAGE_SELECT_GAME_MODE);
		switch(gameModeOption) 
		{
			case 1: // Computer vs computer
				initCVC();
				initBoard();
				cvcGame(gameBoard);
				break;
			case 2: // Human vs Computer
				initHVC();
				initBoard();
				hvcGame(gameBoard);
				break;
			case 3: // Human vs Human
				initHVH();
				initBoard();
				hvhGame(gameBoard);
				break;
			default:
				aUI.writeMessage(ERROR_INVALID_OPTION);
				gameModeOption = aUI.promptUser(MESSAGE_SELECT_GAME_MODE);
				break;
		}
		
	}
	
	public static void hvcGame(Board board) {
		do {
		      getHumanSpot();
		      if (!board.gameIsOver() && !board.tie()) {
		        board.evalBoardComputer();
		      }
		    } while (!board.gameIsOver() && !board.tie());
	}
	
	
	public static void cvcGame(Board board)
	{
		do {
			computerOne.evalBoard(board);;
			if(!board.gameIsOver() && !board.tie()) {
				computerTwo.evalBoard(board);
			}
		} while(!board.gameIsOver() && !board.tie());
	}
	
	public static void hvhGame(Board board) 
	{
		int startingPlayerOption = aUI.promptUser(MESSAGE_STARTING_PLAYER);
		if(startingPlayerOption == 1)
		{
			do {
				getPlayerOneSpot();
				if(!board.gameIsOver() && !board.tie()) {
					getPlayerTwoSpot();
				}
			} while (!board.gameIsOver() && !board.tie());
		}
		else if (startingPlayerOption == 2)
		{
			do {
				getPlayerTwoSpot();
				if(!board.gameIsOver() && !board.tie()) {
					getPlayerOneSpot();
				}
			} while (!board.gameIsOver() && !board.tie());
		}
	}
	
	public static void getPlayerOneSpot()
	{
		boolean validInput = false;  // for input validation
	    System.out.print("PLAYER 1: Enter [0-8]:\n");
	    do {
	      int spot = input.nextInt();
	      if (gameBoard.board[spot] != "X" && gameBoard.board[spot] != "O") {
	        gameBoard.board[spot] = playerOne.getSelectedSymbol();  // update game-board content
	        aUI.writeMessage("Player 1 played in spot " + spot);
	        gameBoard.printBoard();
	        validInput = true;  // input okay, exit loop
	      }
	      currentPlayer = nextPlayer();  // cross plays first
	    } while (!validInput);  // repeat until input is valid
	}
	
	public static void getPlayerTwoSpot()
	{
		boolean validInput = false;  // for input validation
	    System.out.print("PLAYER 2: Enter [0-8]:\n");
	    do {
	      int spot = input.nextInt();
	      if (gameBoard.board[spot] != "X" && gameBoard.board[spot] != "O") {
	        gameBoard.board[spot] = playerTwo.getSelectedSymbol();  // update game-board content
	        aUI.writeMessage("Player 2 played in spot " + spot);
	        gameBoard.printBoard();
	        validInput = true;  // input okay, exit loop
	      }
	      currentPlayer = nextPlayer();  // cross plays first
	    } while (!validInput);  // repeat until input is valid
	}
	
	/** Update global variables "board" and "currentPlayer". */
	public static void getHumanSpot() 
	{
	    boolean validInput = false;  // for input validation
	    System.out.print("Enter [0-8]:\n");
	    do {
	      int spot = input.nextInt();
	      if (gameBoard.board[spot] != "X" && gameBoard.board[spot] != "O") {
	        gameBoard.board[spot] = "X";  // update game-board content
	        aUI.writeMessage("Player played in spot "+spot);
	        gameBoard.printBoard();
	        validInput = true;  // input okay, exit loop
	      }
	      currentPlayer = nextPlayer();  // cross plays first
	    } while (!validInput);  // repeat until input is valid
	}
	
	public static void initCVC()
	{
		aUI.writeMessage("Computer 1 will be playing vs Computer 2");
		computerOne.setSelectedSymbol("X");
		computerTwo.setSelectedSymbol("O");
	}
	
	public static void initHVH()
	{
		aUI.writeMessage("Player 1 will be playing vs Player 2");
		int pOneSymbolOption = 0;
		try 
		{
			pOneSymbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
			
			while(playerOne.getSelectedSymbol() != "X" && playerOne.getSelectedSymbol() != "O")
			{
				switch(pOneSymbolOption)
				{
					case 1:
						currentState = 0;
						playerOne.setSelectedSymbol("X");
						playerTwo.setSelectedSymbol("O");
						break;
					case 2:
						currentState = 0;
						playerOne.setSelectedSymbol("O");
						playerTwo.setSelectedSymbol("X");
						break;
					default:
						aUI.writeMessage(ERROR_INVALID_OPTION);
						pOneSymbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
						break;
				}
			}
		}
		catch(Exception e)
		{
			aUI.writeMessage(ERROR_INVALID_OPTION);
			pOneSymbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
		}
	}
	  
	public static void initHVC() 
	{
		aUI.writeMessage("You will be playing vs the computer...");
		int symbolOption = 0;
		try {
			symbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
			Player playerOne = new Player();
			while(playerOne.getSelectedSymbol() != "X" && playerOne.getSelectedSymbol() != "O")
			{
				switch(symbolOption)
				{
					case 1: //Selected the X
						currentState = 0;
						playerOne.setSelectedSymbol("X");
						break;
					case 2://Selected the O
						currentState = 0;
						playerOne.setSelectedSymbol("O");
						break;
					default:
						aUI.writeMessage(ERROR_INVALID_OPTION);
						symbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
						break;
				}
			}
		}
		catch(Exception e)
		{
			aUI.writeMessage(ERROR_INVALID_OPTION);
			symbolOption = aUI.promptUser(MESSAGE_SELECT_GAME_SYMBOL);
		}
		
	}
	
	public static String nextPlayer() {
	    if (currentPlayer == "X") {
	      return "O";
	    } else {
	      return "X";
	    }
	  }
	
	public static void initBoard()
	{
		gameBoard.printBoard();
	}

}
