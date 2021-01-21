// -------------------------------------------------------
// Assignment 4 Question 2
// Written by: Waleed Iqbal 40112596
// For COMP 248 Section W – Winter 2019
// April 15th, 2019
// --------------------------------------------------------

/*This program is a class Player which contains all the necessary methods for the Player
 * (the players' boards) to be able to run and play the Golf game.
 */

public class Player {

	//Data declaration
	//Creating Player attributes
	private String name;
	private char[][] board = new char[3][3];
	private boolean[][] turned = new boolean [3][3];
	
	//Default constructor that creates the board for a player and a turned array
	//initialized to false (faced down) and a default name
	public Player() {
		name = "Guest";
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = Golf.deckanddiscard.pickACard();
		for (int i = 0; i < getTurned().length; i++)
			for (int j = 0; j < getTurned().length; j++)
				getTurned()[i][j] = false;
	}
	
	//Constructor with a string parameter for the player's name
	public Player(String name) {
		this.name = name;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = Golf.deckanddiscard.pickACard();
		for (int i = 0; i < getTurned().length; i++)
			for (int j = 0; j < getTurned().length; j++)
				getTurned()[i][j] = false;
	}
	
	//Accessor method for the player's name
	public String getName() {
		return name;
	}
	
	//Mutator method for the player's name
	public void setName(String name) {
		this.name = name;
	}
	
	//cardAt method which returns the card at desired location of the player's board
	public char cardAt(int r, int c) {
		return board[r][c];
	}
	
	//flip method that flips a player's card by setting the location of the card on the
	//2D boolean array to true and displays the card
	public boolean flip(int r, int c) {
		boolean flip = false;
		if (getTurned()[r][c]==false)
		{
			getTurned()[r][c]=true;
			System.out.print(getTurned()[r][c]);
			flip = true;
		}
		return flip;
	}
	
	//setTo method which sets the card in board at the specified location to be the passed card
	public void setTo(int r, int c, char card) {
		board[r][c] = card;
	}
	
	//isTurned method that checks if a card is turned
	public boolean isTurned(int r, int c) {
		return getTurned()[r][c];
	}
	
	//turn method that turns a card
	public void turn(int r, int c) {
		getTurned()[r][c] = true;
	}
	
	//allTurned method that checks if all cards on a player's board are turned.
	public boolean allTurned() {
		boolean allflipped = true;
		for (int i = 0; i < getTurned().length; i++)
			for (int j = 0; j < getTurned().length; j++)
				if (getTurned()[i][j] == false)
					allflipped = false;
		return allflipped;
	}
	
	//calculatePoints method that calculates a player's total points by converting the 
	//cards to their respective points using a switch.
	public int calculatePoints() {
		int[][] points = new int [3][3];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				switch (board[i][j]) 
				{
				case 'A': points[i][j] = 1;
				break;
				case '2': points[i][j] = 2;
				break;
				case '3': points[i][j] = 3;
				break;
				case '4': points[i][j] = 4;
				break;
				case '5': points[i][j] = 5;
				break;
				case '6': points[i][j] = 6;
				break;
				case '7': points[i][j] = 7;
				break;
				case '8': points[i][j] = 8;
				break;
				case '9': points[i][j] = 9;
				break;
				case 'T': 
				case 'J':
				case 'Q': points[i][j] = 10;
				break;
				case 'K': points[i][j] = 0;
				break;
				case '?': points[i][j] = -5;
				break;
				}
		//Checking if columns are identical and setting the points to 0 if yes
		for (int i = 0; i < board.length; i++)
			if (board[i][0]==board[i][1] && board[i][0]==board[i][2])
			{
				points[i][0] = 0;
				points[i][1] = 0;
				points[i][2] = 0;
			}
		//Checking if rows are identical and setting the points to 0 if yes
		for (int i = 0; i < board.length; i++)
			if (board[0][i]==board[1][i] && board[0][i]==board[2][i])
			{
				points[0][i] = 0;
				points[1][i] = 0;
				points[2][i] = 0;
			}
		//Checking if diagonals are identical and setting the points to 0 if yes
		if (board[0][0]==board[1][1] && board[0][0]==board[2][2])
		{
			points[0][0] = 0;
			points[1][1] = 0;
			points[2][2] = 0;
		}
		if (board[0][2]==board[1][1] && board[0][2]==board[2][0])
		{
			points[0][2] = 0;
			points[1][1] = 0;
			points[2][0] = 0;
		}
		//Calculating total points
		int totalpoints = 0;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				totalpoints += points[i][j];
		return totalpoints;
	}
	
	//displayBoard method that displays a player's board
	public void displayBoard() {
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
				if (turned[i][j] == false)
					System.out.print("*"+"  ");
				else if (turned[i][j] == true)
					System.out.print(board[i][j]+"  ");
			System.out.println();
		}
	}
	
	//Accessor method for the turned array
	public boolean[][] getTurned() {
		return turned;
	}

	//Mutator method for the turned array
	public void setTurned(int r, int c, boolean flipped) {
		turned[r][c] = flipped;
	}
	
	//Mutator method for the turned array
	public void setBoard(int r, int c, char card) {
		board[r][c] = card;
	}
	
	//Accessor method for the board array
	public char getBoard(int r, int c) {
		return board[r][c];
	}
	
}
