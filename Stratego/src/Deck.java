
import java.util.Arrays;
import java.util.Collections;

public class Deck {

	private int numLeft = 21;
	private Piece[] deck = new Piece[numLeft];
	
	public Deck() {
		//adds all cards to the deck using PlayingCard()
		for (int i = 0; i < numLeft; i++) {//adds the card
			deck[i] = new Piece();
		}
		Piece.lastNumber = 1;
	}

	public Piece getTop() {
		//returns the 0th card, and removes the
		//card from the deck (What if there is no 0th card?)
		Piece temp = null;
		if (numLeft > 0) {
			temp = deck[0];
			//System.out.println(deck[0].getCardSuit() + " " + deck[0].getCardID());
			--numLeft;

			for (int k = 0; k < deck.length; k++) {
				if (k < numLeft)
					deck[k] = deck[k + 1];
				else
					deck[k] = null;
			}
		} else
			System.err.println("No more cards in the deck!");
		return temp;
	}

	public boolean pushBack(Piece p) {
		//adds the card to the back of the array
		//making sure that the card is not 'right side up'
		//what if the deck is full
		if (numLeft < 21) {
			if (p.isFaceUp()) {
				p.turnPieceOver();
			}
			deck[numLeft++] = p;
			return true;
		} else {
			System.err.println("The deck is already full!");
			return false;
		}
	}

	public Piece getRandom() {
		//returns a random playing card in the deck and removes it from the deck
		int randomCard = (int) (Math.random() * numLeft);

		for (int i = randomCard; i < deck.length; i++) {
			//try{	
			if (i < numLeft) {
				deck[i] = deck[i + 1];
				//System.out.println(deck[i].getCardSuit() + " " + deck[i].getCardID());
				--numLeft;
			} else
				deck[i] = null;
		}
		//catch(Eception e);
		//}
		return deck[randomCard];
	}

	public void shuffle() {
		Collections.shuffle(Arrays.asList(deck));
	}
	
	public void showAll(Canvas c) {
		//Each card in the deck is turned face up and
		//displayed for 0.25 seconds before being returned to the deck face down.
		//Use the Tread.sleep() method in a try, catch block to get this to work       
		for (int i = 0; i < numLeft; i++) {
			//System.out.println(cardDeck[i].getCardSuit() + " " + cardDeck[i].getCardID());
			deck[i].turnPieceOver();
			c.addPiece(deck[i]);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			c.erase(deck[i]);
			deck[i].turnPieceOver();
		}
	}
	
	public int numLeft() {
		// returns the number of cards remaining in the deck
		return numLeft;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck c = new Deck();
		Piece p = new Piece();
		Canvas e = new Canvas("Piece Deck", 1000, 500, p.getColor());
		
		c.showAll(e);
		/*
		c.numLeft();

	 	c.getTop();

		c.pushBack(p);

		c.getRandom();

		
		*/
	}
}

/*
import java.awt.Color;
import java.util.*;
import java.util.NoSuchElementException;

public class Deck {
	
	Vector<Piece> deck = new Vector<Piece>(21);

	public Deck() { 
		for (int i = 0; i < deck.size(); i++) {
			Piece p = deck.get(i);
		}
	}

	public Piece getTop() {
		Piece temp = deck.firstElement();
		if (deck.size() > 0){
		deck.remove(deck.firstElement());
		}
		else{
		System.err.println("No more cards in the deck!");
		}
		deck.trimToSize();
		return temp;
	}

	public boolean pushBack(Piece p) {
		//adds the card to the back of the array
		//making sure that the card is not 'right side up'
		//what if the deck is full
		if (deck.size() < 21) {
			if (p.isFaceUp()) {
				p.turnPieceOver();
			}
			deck.addElement(p);
			return true;
		} else {
			System.err.println("The deck is already full!");
			return false;
		}
	}

	public Piece getRandom() {
		//returns a random playing card in the deck and removes it from the deck
		int randomCard = (int) (Math.random() * deck.size());
		deck.remove(randomCard);
		return deck.get(randomCard);
	}

	public void shuffle() {
		Collections.shuffle((deck));
	}

	public void showAll(Canvas c) {
		//Each card in the deck is turned face up and
		//displayed for 0.25 seconds before being returned to the deck face down.
		//Use the Tread.sleep() method in a try, catch block to get this to work       
		for (int i = 0; i < deck.size(); i++) {
			System.out.println(Deck.get(i).getPieceID() + " " + Deck.get(i).getCardID());
			deck.get(i).turnPieceOver();
			c.addPiece(deck.get(i));
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
			c.erase(deck.get(i));
			deck.get(i).turnPieceOver();
		}
	}

	public int numLeft() {
		// returns the number of cards remaining in the deck
		return deck.size();
	}

	
/*for (int j = 13; j< 19; j++){
			Piece p = deck.get(j);
		}/
		
		if (lastNumber == 13) {
			count++;
		}
		if (lastNumber == 19) {
			count2++;
		}
		if (lastNumber < 13 || count == 5 || count2 == 2 || lastNumber == 20) {
			lastNumber++;
		}
*/
