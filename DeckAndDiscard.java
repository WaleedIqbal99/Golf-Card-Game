// -------------------------------------------------------
// Assignment 4 Question 2
// Written by: Waleed Iqbal 40112596
// For COMP 248 Section W – Winter 2019
// April 15th, 2019
// --------------------------------------------------------

/*This program is a class for the golf game. It contains all the attributes regarding the game's
 * deck and discard pile. It creates the deck and discard pile, shuffles the deck, contains all the
 * needed accessor and mutator methods, a method that picks a card, another that discards a card and
 * the methods to display the deck and discard pile.
 */

//Importing random class
import java.util.Random;

public class DeckAndDiscard {

	//Data declaration
	//Creating the attributes
	private char[] deck = {'A','A','A','A','2','2','2','2','3','3','3','3','4','4','4','4','5','5','5','5',
			'6','6','6','6','7','7','7','7','8','8','8','8','9','9','9','9','T','T','T','T','J','J','J','J',
			'Q','Q','Q','Q','K','K','K','K','?','?'};
	private char[] discardPile = new char[54];
	private int discardposition;
	private int deckposition;
	private int cardsleft;
	
	//Shuffle method that shuffles the deck by switching the cards of two random different positions 1000 times
	public void shuffle() {
		Random rn = new Random();
		char temp1;
		for (int i = 0; i < 1000; i++)
		{
			int shuffle1 = rn.nextInt(54);
			int shuffle2 = rn.nextInt(54);
			temp1 = deck[shuffle1];
			deck[shuffle1] = deck[shuffle2];
			deck[shuffle2] = temp1;
		}
	}
	
	//Default constructor that shuffles the deck and sets the starting indexes to 0
	public DeckAndDiscard() {
		shuffle();
		discardposition = 0;
		deckposition = 0;
		cardsleft = 54;	
	}
	
	//Accessor method for the deck array
	public char getDeck(int getdeck) {
		return deck[getdeck];
	}
	
	//Accessor method for the discard pile array
	public char getDiscardPile(int getdiscard) {
		return discardPile[getdiscard];
	}
	
	//Accessor method for the discard position attribute
	public int getDiscardPosition() {
		return discardposition;
	}
	
	//Accessor method for the deck position attribute
	public int getDeckPosition() {
		return deckposition;
	}
	
	//Accessor method for the cards left attribute
	public int getCardsLeft() {
		return cardsleft;
	}
	
	//pickACard method which returns the top card of the deck and increments the deck position
	public char pickACard() {
		char card = getDeck(deckposition);
		deck[deckposition] = '-';
		deckposition++;
		cardsleft--;
		return card;
	}
	
	//discard method that adds a card to the discard pile and increments the discard position
	public void discard(char addedcard) {
		discardPile[discardposition] = addedcard;
		discardposition++;
	}
	
	//displayDeck method thats displays the remaining deck
	public void displayDeck() {
		for (int j = deckposition, count1 = 0; count1 < cardsleft; j++)
		{
			if (deckposition >= 54)
				break;
			if (count1%13 == 0)
				System.out.println();
			System.out.print(deck[j] + " ");
			count1++;
		}
	}
	
	//displayDiscardPile that displays the discard pile
	public void displayDiscardPile() {
		for (int k = 0, count2 = 0; k <= discardposition; k++)
		{
			if (count2%13 == 0)
				System.out.println();
			System.out.print(getDiscardPile(k) + " ");
			count2++;
		}
	}
	
	//Mutator method for the discard pile array
	public void setDiscardPile(int r, char card) {
		discardPile[r] = card;
	}
	
	//A method that swaps a card with the one on the discard pile instead of discarding
	//for when the player swaps a card on the board with the one on the discard pile
	public void swapWithDiscard(char card) {
		char temp;
		temp = getDiscardPile(discardposition-1);
		setDiscardPile(discardposition-1, card);
		card = temp;
	}
	
}
