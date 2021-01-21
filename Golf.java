// -------------------------------------------------------
// Assignment 4 Question 2
// Written by: Waleed Iqbal 40112596
// For COMP 248 Section W – Winter 2019
// April 15th, 2019
// --------------------------------------------------------

/*This is the driver class of the program. It uses the methods of the DeckAndDiscard class and the Player class
 * to program the Golf game. The game starts by asking the players their names and then creates their boards.
 * It then asks the players to flip over two cards and the game begins. The players take turns until one of them
 * has all cards turned over and the winner is the player with the least points. A turn starts by the player choosing
 * if he wants the card on the discard pile or a new one. If the user chooses the discard pile, he gets the options to
 * either swap it with a flipped card or flip a new card. If the player chooses a new card, he gets the option to replace
 * one of the cards on the board or discard it. If he chooses to replace, he chooses between a flipped card or flip a new
 * card. If the player chooses to flip a new card, the game redisplays the board and the user gets the option again to
 * replace or toss. In the end, for transparency, the game displays the cards remaining in the deck and the ones in the
 * discard pile.
 */

//Importing Scanner
import java.util.Scanner;

public class Golf {
	
	//Static method that creates a DeckAndDiscard object that can be used in Player class
	public static DeckAndDiscard deckanddiscard = new DeckAndDiscard();

	public static void main(String[] args) {
		
		//Displaying welcome banner and game rules
		System.out.println("-------****-------****-------****-------****-----****-----\n" + 
				" \tWelcome to Nancy's Card Golf Game!\n" + 
				"-------****-------****-------****-------****-----****-----");
		System.out.println("To win this game you need some luck with the cards and a bit of strategy.\n" + 
				"Just like the outdoor game of golf, the card game known as Golf has a goal\nof keeping " + 
				"the score as low as possible.\n" + 
				"Okay .. Let's start the game! May the best golfer win!!!\n");
		
		//Initializing Scanner
		Scanner kb = new Scanner(System.in);
		
		//Creating deck and discard object
		
		
		//Prompting users to enter their names and initializing names
		String playername1, playername2;
		System.out.println("What is the name of 1st player: ");
		playername1 = kb.nextLine();
		System.out.println("What is the name of 2nd player: ");
		playername2 = kb.nextLine();
		System.out.println();
		
		//Creating two player objects and setting the names
		Player player1 = new Player(playername1);
		Player player2 = new Player(playername2);
		
		//Prompting users to flip over 2 desired cards on their boards and flipping them over
		int fliprow1, flipcol1, fliprow2, flipcol2;
		System.out.println(playername1 + " time to decide which 2 cards you want to turn over.");
		for (int i = 0; i < 2; i++)
		{
			System.out.print("Which card to you want to flip (row col) ");
			fliprow1 = kb.nextInt();
			flipcol1 = kb.nextInt();
			//Error checking
			while (fliprow1 > 2 || fliprow1 < 0 || player1.getTurned()[fliprow1][flipcol1] || flipcol1 > 2 || flipcol1 < 0)
			{
				System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
				System.out.print("Which card to you want to flip (row col) ");
				fliprow1 = kb.nextInt();
				flipcol1 = kb.nextInt();
			}
			player1.setTurned(fliprow1, flipcol1, true);
		}
		System.out.println();

		
		System.out.println(playername2 + " time to decide which 2 cards you want to turn over.");
		for (int i = 0; i < 2; i++)
		{
			System.out.print("Which card to you want to flip (row col) ");
			fliprow2 = kb.nextInt();
			flipcol2 = kb.nextInt();
			//Error checking the user's entry
			while (fliprow2 > 2 || fliprow2 < 0 || player2.getTurned()[fliprow2][flipcol2] || flipcol2 > 2 || flipcol2 < 0)
			{
				System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
				System.out.print("Which card to you want to flip (row col) ");
				fliprow2 = kb.nextInt();
				flipcol2 = kb.nextInt();
			}
			player2.setTurned(fliprow2, flipcol2, true);
		}
		System.out.println();
		
		//Discarding the first card of the deck to the discard pile
		deckanddiscard.discard(deckanddiscard.pickACard());
		
		//Declaring and initializing an int that keeps track of the turn
		int turnnumber = 1;
		
		//Using a do while loop to keep the turns going until one player flips all the cards
		do
		{
			int choice, replace;
			char cardchoice;
			
			//Checking which player's turn it is (if it's player1's)
			if (turnnumber%2 != 0)
			{
				//Showing what's on the discard pile
				System.out.println("Discard pile has " + deckanddiscard.getDiscardPile(deckanddiscard.getDiscardPosition()-1));
				System.out.println(playername1 + "'s turn:\n--------------------------------");
				System.out.println("Here is your board.");
				player1.displayBoard();
				
				//Prompting the user to choose if he wants the discarded card or a new
				System.out.print("Do you want the card on the discard pile(0) or a new card(1) ");
				choice = kb.nextInt();
				//Error checking the user's entry
				while (choice != 0 && choice != 1)
				{
					System.out.println("Invalid entry. Answer must be 0 or 1.");
					System.out.print("Do you want the card on the discard pile(0) or a new card(1) ");
					choice = kb.nextInt();
				}
				//If the user chooses the discarded card
				if (choice == 0)
					{
					//Prompting the user to choose if they want to replace a flipped card or flip a new
					cardchoice = deckanddiscard.getDiscardPile(deckanddiscard.getDiscardPosition()-1);
					System.out.print(playername1 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
					replace = kb.nextInt();
					//Error checking
					while (replace != 0 && replace != 1)
					{
						System.out.println("Invalid entry. Answer must be 0 or 1.");
						System.out.print(playername1 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
						replace = kb.nextInt();
					}
					//If the player chooses to replace a flipped card
					if (replace == 0)
					{
						int r, c;
						System.out.print("Which flipped card do you want to replace (row col)? ");
						r = kb.nextInt();
						c = kb.nextInt();
						//Error checking (checking the location first so that it's not out of index for the array)
						while (r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						while (!player1.isTurned(r, c) || r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						//Swapping the card with the one on discard pile and changing the card on the board to the chosen card
						deckanddiscard.swapWithDiscard(player1.cardAt(r, c));
						player1.setBoard(r, c, cardchoice);
					}
					//If the player chooses to flip a new card
					if (replace == 1)
					{
						int r,c;
						System.out.print("Which non-flipped card do you want to flip (row col)? ");
						r = kb.nextInt();
						c = kb.nextInt();
						//Error checking (checking the location first so that it's not out of index for the array)
						while (r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
							System.out.print("Which non-flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						while (player1.isTurned(r, c) == true || r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						//Flipping the card by updating the turned array, discarding the card and updating player's board
						player1.setTurned(r, c, true);
						deckanddiscard.swapWithDiscard(player1.cardAt(r, c));
						player1.setBoard(r, c, cardchoice);
					}
					}
				
				//If player chooses to take a new card from the deck
				if (choice == 1)
					{
					//Asking the user if they want to replace a card or toss the chosen card
					cardchoice = deckanddiscard.pickACard();
					System.out.println("The card you are playing is " + cardchoice);
					System.out.print("Replace a card(0) or toss it(1)? ");
					replace = kb.nextInt();
					//Error checking 
					while (replace != 0 && replace != 1)
					{
						System.out.println("Invalid entry. Answer must be 0 or 1.");
						System.out.print("Replace a card(0) or toss it(1)? ");
						replace = kb.nextInt();
					}
					//If player want's to replace a card
					if (replace == 0)
					{
						//Asking user if they want to replace a flipped card or flip a new one
						int flipchoice;
						System.out.print(playername1 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
						flipchoice = kb.nextInt();
						//Error checking
						while (flipchoice != 0 && flipchoice != 1)
						{
							System.out.println("Invalid entry. Answer must be 0 or 1.");
							System.out.print(playername1 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
							flipchoice = kb.nextInt();
						}
						//If user wants to replace a flipped card
						if (flipchoice == 0)
						{
							//Asking which flipped card player wants to replace
							int r, c;
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
							//Error checking (checking the location first so that it's not out of index for the array)
							while (r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
								System.out.print("Which flipped card do you want to replace (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							while (player1.isTurned(r, c) == false || r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
								System.out.print("Which flipped card do you want to replace (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							//Discarding card at chosen location and updating player's board
							deckanddiscard.discard(player1.cardAt(r, c));
							player1.setBoard(r, c, cardchoice);
						}
						//If player wants to flip a new card
						if (flipchoice == 1)
						{
							//Asking which non-flipped card user wants to flip
							int r, c;
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
							//Error checking (checking the location first so that it's not out of index for the array)
							while (r > 2 || r < 0 || c > 2 || c < 0)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2.");
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							while (player1.isTurned(r, c) == true || r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							//Flipping the desired card, displaying the board and asking if user wants to replace card
							//at location or toss it
							player1.setTurned(r, c, true);
							player1.displayBoard();
							int fliportoss;
							System.out.print("Replace this card(0) or toss(1)? ");
							fliportoss = kb.nextInt();
							//Error checking
							while (fliportoss != 0 && fliportoss != 1)
							{
								System.out.println("Invalid entry. Answer must be 0 or 1.");
								System.out.print("Replace this card(0) or toss(1)? ");
								fliportoss = kb.nextInt();
							}
							//If user wants to replace
							if (fliportoss == 0)
							{
								deckanddiscard.discard(player1.cardAt(r, c));
								player1.setBoard(r, c, cardchoice);
							}
							//If user wants to toss
							if (fliportoss == 1)
								deckanddiscard.discard(cardchoice);
						}
					}
					//If user wants to toss
					if (replace == 1)
						deckanddiscard.discard(cardchoice);
					}
			}
			
			//Checking if it's player2's turn and programming the same thing as player 1
			if (turnnumber%2 == 0)
			{
				//Showing what's on the discard pile
				System.out.println("Discard pile has " + deckanddiscard.getDiscardPile(deckanddiscard.getDiscardPosition()-1));
				System.out.println(playername2 + "'s turn:\n--------------------------------");
				System.out.println("Here is your board.");
				player2.displayBoard();
				
				//Prompting the user to choose if he wants the discarded card or a new
				System.out.print("Do you want the card on the discard pile(0) or a new card(1) ");
				choice = kb.nextInt();
				//Error checking
				while (choice != 0 && choice != 1)
				{
					System.out.println("Invalid entry. Answer must be 0 or 1.");
					System.out.print("Do you want the card on the discard pile(0) or a new card(1) ");
					choice = kb.nextInt();
				}
				//If the user chooses the discarded card
				if (choice == 0)
					{
					//Prompting the user to choose if they want to replace a flipped card or flip a new
					cardchoice = deckanddiscard.getDiscardPile(deckanddiscard.getDiscardPosition()-1);
					System.out.print(playername2 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
					replace = kb.nextInt();
					//Error checking
					while (replace != 0 && replace != 1)
					{
						System.out.println("Invalid entry. Answer must be 0 or 1.");
						System.out.print(playername2 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
						replace = kb.nextInt();
					}
					//If the user chooses to replace a flipped card
					if (replace == 0)
					{
						int r, c;
						System.out.print("Which flipped card do you want to replace (row col)? ");
						r = kb.nextInt();
						c = kb.nextInt();
						//Error checking (checking the location first so that it's not out of index for the array)
						while (r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						while (player2.isTurned(r, c) == false || r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						//Swapping the card with the one on discard pile and changing the card on the board to the chosen card
						deckanddiscard.swapWithDiscard(player2.cardAt(r, c));
						player2.setBoard(r, c, cardchoice);
					}
					//If the player chooses to flip a new card
					if (replace == 1)
					{
						int r,c;
						System.out.print("Which non-flipped card do you want to flip (row col)? ");
						r = kb.nextInt();
						c = kb.nextInt();
						//Error checking (checking the location first so that it's not out of index for the array)
						while (r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
							System.out.print("Which non-flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						while (player2.isTurned(r, c) == true || r < 0 || r > 2 || c < 0 || c > 2)
						{
							System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
						}
						//Flipping the card by updating the turned array, discarding the card and updating player's board
						player2.setTurned(r, c, true);
						deckanddiscard.swapWithDiscard(player2.cardAt(r, c));
						player2.setBoard(r, c, cardchoice);
					}
					}
				
				//If player chooses to take a new card from the deck
				if (choice == 1)
					{
					//Asking the user if they want to replace a card or toss the chosen card
					cardchoice = deckanddiscard.pickACard();
					System.out.println("The card you are playing is " + cardchoice);
					System.out.print("Replace a card(0) or toss it(1)? ");
					replace = kb.nextInt();
					//Error checking
					while (replace != 0 && replace != 1)
					{
						System.out.println("Invalid entry. Answer must be 0 or 1.");
						System.out.print("Replace a card(0) or toss it(1)? ");
						replace = kb.nextInt();
					}
					//If the player chooses to replace a card
					if (replace == 0)
					{
						//Asking user if they want to replace a flipped card or flip a new one
						int flipchoice;
						System.out.print(playername2 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
						flipchoice = kb.nextInt();
						//Error checking
						while (flipchoice != 0 && flipchoice != 1)
						{
							System.out.println("Invalid entry. Answer must be 0 or 1.");
							System.out.print(playername2 + ", do you want to replace a flipped card(0) or flip a new card(1) ");
							flipchoice = kb.nextInt();
						}
						//If player wants to replace a flipped card
						if (flipchoice == 0)
						{
							//Asking which flipped card player wants to replace
							int r, c;
							System.out.print("Which flipped card do you want to replace (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
							//Error checking (checking the location first so that it's not out of index for the array)
							while (r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
								System.out.print("Which flipped card do you want to replace (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							while (player2.isTurned(r, c) == false || r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and already flipped.");
								System.out.print("Which flipped card do you want to replace (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							//Discarding card at chosen location and updating player's board
							deckanddiscard.discard(player2.cardAt(r, c));
							player2.setBoard(r, c, cardchoice);
						}
						//If player wants to flip a new card
						if (flipchoice == 1)
						{
							//Asking user which non-flipped card he wants to flip
							int r, c;
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							r = kb.nextInt();
							c = kb.nextInt();
							//Error checking (checking the location first so that it's not out of index for the array)
							while (r > 2 || r < 0 || c > 2 || c < 0)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2.");
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							while (player2.isTurned(r, c) == true || r < 0 || r > 2 || c < 0 || c > 2)
							{
								System.out.println("Invalid entry. Answers must be from 0 to 2 and not already flipped.");
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								r = kb.nextInt();
								c = kb.nextInt();
							}
							//Flipping the desired card, displaying the board and asking if user wants to replace card
							//at location or toss it
							player2.setTurned(r, c, true);
							player2.displayBoard();
							int fliportoss;
							System.out.print("Replace this card(0) or toss(1)? ");
							fliportoss = kb.nextInt();
							//Error checking
							while (fliportoss != 0 && fliportoss != 1)
							{
								System.out.println("Invalid entry. Answer must be 0 or 1.");
								System.out.print("Replace this card(0) or toss(1)? ");
								fliportoss = kb.nextInt();
							}
							//Replacing if user wants to replace
							if (fliportoss == 0)
							{
								deckanddiscard.discard(player2.cardAt(r, c));
								player2.setBoard(r, c, cardchoice);
							}
							//If user wants to toss
							if (fliportoss == 1)
								deckanddiscard.discard(cardchoice);
						}
					}
					//Tossing the card if user wants to toss
					if (replace == 1)
						deckanddiscard.discard(cardchoice);
					}
			}
			//Incrementing turn number
			turnnumber++;
			System.out.println();

		}
		while (!player1.allTurned() && !player2.allTurned());
		
		//Checking who turned all cards and displaying message accordingly
		if (player1.allTurned())
			System.out.println(player1.getName() + " has turned over all cards.");
		if (player2.allTurned())
			System.out.println(player2.getName() + " has turned over all cards.");

		//Turning both player's cards
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				player2.setTurned(i, j, true);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				player1.setTurned(i, j, true);
		
		//Calculating the points and displaying the results
		System.out.println("Time to calculate points! Here are your boards with all cards turned over.\n");
		System.out.println(player1.getName() + "\t\t\t\t"+player2.getName()+"\n" + "-----------------------------------------------------");
		for (int i = 0; i < 3; i++)
			{
				System.out.println(player1.getBoard(i, 0) + "\t" + player1.getBoard(i, 1) + "\t" + player1.getBoard(i, 2) + "\t\t"
						+ player2.getBoard(i, 0) + "\t" + player2.getBoard(i, 1) + "\t" + player2.getBoard(i, 2));
			}
		System.out.println("\nFinal results:");
		System.out.println("  " + playername1 + " scored " + player1.calculatePoints());
		System.out.println("  " + playername2 + " scored " + player2.calculatePoints());
		System.out.println();
		
		//Checking the winner and displaying the winner
		if (player1.calculatePoints() < player2.calculatePoints())
			System.out.println("CONGRATULATIONS!!!!!The winner is " + playername1);
		if (player2.calculatePoints() < player1.calculatePoints())
			System.out.println("CONGRATULATIONS!!!!!The winner is " + playername2);
		if (player1.calculatePoints() == player2.calculatePoints())
			System.out.println("It's a draw. No winner.");
		
		//Displaying the remaining cards in the deck and the cards in the discard pile for transparency
		System.out.println("-------------------------------------\n\n" + 
				"Just for information sake, here are the cards remaining in the deck:");
		deckanddiscard.displayDeck();
		System.out.println("\n\nHere are the cards in the discard pile:");
		deckanddiscard.displayDiscardPile();
		System.out.println("\n\nThat's it! Program ending now!");
		
		//Closing scanner
		kb.close();
	}
	

}
