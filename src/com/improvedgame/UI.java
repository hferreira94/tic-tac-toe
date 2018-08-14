package com.improvedgame;

import java.util.Scanner;

public class UI {
	
	private String _message;
	
	public static Scanner input = new Scanner(System.in);
	
	private static final String WELCOME_MESSAGE = "| Welcome to the improved TIC-TAC-TOE Game! |";
	
	public UI() {}
	
	public UI(String message)
	{
		System.out.println(message);
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String _message) {
		this._message = _message;
	}
	
	
	public void writeMessage(String msg)
	{
		System.out.println(msg);
	}
	
	public int promptUser(String promptMsg)
	{
		int userOption;
		System.out.println(promptMsg);
		userOption = input.nextInt();
		
		return userOption;
	}

	public static String getWelcomeMessage() {
		return WELCOME_MESSAGE;
	}
	
}
