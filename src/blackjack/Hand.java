package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//this class is for every player
public class Hand {
	//some class will extend Hand, so
	protected final List<Card> cards = new ArrayList<>(); 
	public int score() {
		int score = 0;
		for (Card card : cards) {
			score += card.value();
		}
		return score;
	}
	public void addCards(Card[] c) {
		Collections.addAll(cards, c);
	}
	public int size() {
		return cards.size();
	}
	public void printCards() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i).value() + " ");
		}
	}
}
