package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackHand extends Hand{
	@Override
	public int score() {
		//list all possible scores --> "A" 1 or 11
		List<Integer> scores = possibleScores();
		//the max value under <=21
		int max = Integer.MIN_VALUE;
		//the min value > 21
		int min = Integer.MAX_VALUE;
		for (Integer score : scores) {
			if (score <= 21 && score > max) {
				max = score;
			} else if (score > 21 && score < min) {
				min = score;
			}
		}
		return max == Integer.MIN_VALUE ? min : max;
	}
	private List<Integer> possibleScores() {
		List<Integer> scores = new ArrayList<>();
		for (Card card : cards) {
			updateScores(scores, card);
		}
		return scores;
	}
	/*
	 * not change 2 - 10
	 * 11 - 13 --> 10
	 * 1 --> 1 or 11
	 * */
	private void updateScores(List<Integer> scores, Card card) {
		final int[] toAdd = getScores(card);
		if (scores.isEmpty()) {
			for (Integer num : toAdd) {
				scores.add(num);
			}
		} else {
			final int length = scores.size();
			for (int i = 0; i < length; i++) {
				int oldValue = scores.get(i);
				scores.set(i, oldValue + toAdd[0]);
				for (int j = 1; j < toAdd.length; j++) {
					scores.add(oldValue + toAdd[j]);
				}
			}
		}
	}
	private int[] getScores(Card card) {
		if (card.value() > 1) {
			return new int[] {Math.min(10, card.value())};
		} else {
			return new int[] {1, 11};
		}
	}
	//if hand > 21? busted?
	public boolean isbusted() {
		return score() > 21;
	}
	//become blackjack only has two cards
	public boolean isBlackJack() {
		if (cards.size() != 2) {
			return false;
		}
		Card first = cards.get(0);
		Card second = cards.get(1);
		return (isAce(first) && isFaceCard(second))
			|| (isAce(second) && isFaceCard(first));
	}
	//these two function don't need to based on class, so could be static 
	private static boolean isAce(Card card) {
		return card.value() == 1;
	}
	private static boolean isFaceCard(Card card) {
		return card.value() >= 10 && card.value() <= 13;
	}
}
