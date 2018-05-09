package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private static final Random random = new Random();
	//a cards deck, to be final because it will not change
	private final List<Card> deck = new ArrayList<>();
	private int dealtIndex = 0;
	//initial a cards deck --> 52 cards in one deck
	public Deck() {
		for (int i = 1; i <= 13; i++) {
			for (Suit suit : Suit.values()) {
				deck.add(new Card(suit, i));
			}
		}
	}
	//shuffle cards deck
	public void shuffle() {
		//i is traverse deck excepted last card
		for (int i = 0; i < deck.size() - 1; i++) {
			int j = random.nextInt(deck.size() - i) + i;
			Card card1 = deck.get(i);
			Card card2 = deck.get(j);
			deck.set(i, card2);
			deck.set(j, card1);
		}
	}
	//return number of cards can be deal
	private int remainingCards() {
		return deck.size() - dealtIndex;
	}
	//dealhand number of cards
	public Card[] dealHand(int number) {
		if (remainingCards() < number) {
			return null;
		}
		Card[] result = new Card[number];
		for (int i = 0; i < number; i++) {
			result[i] = dealCard();
		}
		return result;
	}
	public Card dealCard() {
		return remainingCards() == 0 ? null : deck.get(dealtIndex++);
	}
}
