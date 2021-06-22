//@author: Sarah Sweeney
import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {
		
	public static int countHand(ArrayList<Card> hand) {
		int deckValue = 0;
		int numAces = 0;
		for (Card c : hand) {
			deckValue += c.getRank().getValue();
			if (c.getRank().getValue() == 11){
				numAces++;
			}
		}
		while (deckValue > 21 && numAces > 0) {
			deckValue = deckValue - 10;
			numAces--;
		}
		return deckValue;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Card card;
		CardDeck myDeck = new CardDeck();
		int playerHandValue = 0;
		int dealerHandValue = 0;
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		myDeck.shuffle();
		
		//dealing to player
		for (int i =0; i <2; i++) {
			Card c = myDeck.nextCard();
			playerHand.add(c);
		}
		
		//testing!!!!
		//Card card1 = new Card(Card.Rank.Ace, Card.Suit.Club);
		//Card card2 = new Card(Card.Rank.Ace, Card.Suit.Club);		
		//playerHand.add(card1);
		//playerHand.add(card2);
		
		//printing player's hand
		System.out.println("Your hand: ");
		for (Card c : playerHand) {
			System.out.println(c);
		}
		
		System.out.println(countHand(playerHand));
		
		//dealing to the dealer
		ArrayList<Card> dealerHand = new ArrayList<Card>(2);
		myDeck.shuffle();
		for (int i = 0; i < 2; i++) {
			Card dc = myDeck.nextCard();
			dealerHand.add(dc);
		}
		
		//displaying dealer's first card
		System.out.println("The dealer's first card is ");
		System.out.println(dealerHand.get(0));
		
		//for my reference only -- DELETE LATER
		//for (Card sarah : dealerHand) {
		//	System.out.println(sarah);
		//}
		//System.out.println("Dealer's hand value: " + countHand(dealerHand));
		
		//player loop
		int flag = 0;
		while (flag == 0) {
			System.out.println("Would you like to hit (H) or stay (S) ?");
			char answer = scan.nextLine().charAt(0);
			if (answer == 'H') {
				Card newCard = myDeck.nextCard();
				playerHand.add(newCard);
				System.out.println("You have been dealt " + newCard);
				System.out.println("Your hand value is now " + countHand(playerHand));
			
				if (countHand(playerHand) > 21) {
					System.out.println("Your hand is greater than 21. You lose!");
					System.exit(0);
				}
			}
			if (answer == 'S') {
				flag = 1;
			}
		}
		
		//dealer play loop
		if (flag == 1) {
			System.out.println("The dealer is now playing...");
			while (countHand(dealerHand) < 17) {
				System.out.println("The dealer hits...");
				Card dealerNewCard = myDeck.nextCard();
				dealerHand.add(dealerNewCard);
				System.out.println("The dealer drew " + dealerNewCard);
				System.out.println("Their deck value is now " + countHand(dealerHand));
				if (countHand(dealerHand) > 21) {
					System.out.println("The dealer went over 21! You win!");
					return;
				}
			}
			if (countHand(dealerHand) >= 17) {
				System.out.println("The dealer stays...");
				System.out.println("Their hand value is " + countHand(dealerHand));
				//result scenarios
				if (countHand(dealerHand) > countHand(playerHand)) {
					System.out.println("The dealer's deck is higher valued. You lose!");
				}
				else if (countHand(dealerHand) < countHand(playerHand)) {
					System.out.println("The dealer's deck is lower valued. You win!");
				}
				else if (countHand(dealerHand) == countHand(playerHand)) {
					System.out.println("It's a tie!");
				}
			}
		}
	}
	
	
}
