package blackjack;

import java.util.ArrayList;
import java.util.List;

/*
 * simulate game flow
 * first, everyone will have two cards initially 
 * must guarantee sum of cards will > 16
 * winner will be almost near 21 <= 21
 */
public class BlackJackGameAutomator {
	private Deck deck;
	private BlackJackHand[] hands;
	private static final int HIT_UNIT = 16;
	private static final int CARDS_INITIAL = 2;
	//initial number players will in the game
	public BlackJackGameAutomator(int number) {
		hands = new BlackJackHand[number];
		for (int i = 0; i < number; i++) {
			hands[i] = new BlackJackHand();
		}
	}
	//initial deck
	public void initialDeck() {
		deck = new Deck();
		deck.shuffle();
	}
	//dealhand two cards for per person initial
	public boolean dealInitial() {
		for (BlackJackHand hand : hands) {
			Card[] cards = this.deck.dealHand(CARDS_INITIAL);
			if (cards == null) {
				return false;
			}
			hand.addCards(cards);
		}
		return true;
	}
	//after first round there is blackjack or not?
	public List<Integer> getBlackJack() {
		List<Integer> winners = new ArrayList<>();
		for (int i = 0; i < hands.length; i++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}
	//add cards until >= 16 for per person
	private boolean playHand(BlackJackHand hand) {
		while (hand.score() < 16) {
			Card card = deck.dealCard();
			if (card == null) {
				return false;
			}
			hand.addCards(new Card[] {card});
		}
		return true;
	}
	//add cards until >= 16 for all person
	public boolean playAllHands() {
		for (BlackJackHand hand : hands) {
			if (!playHand(hand)) {
				return false;
			}
		}
		return true;
	}
	//get results of winners
	public List<Integer> getWinners() {
		List<Integer> winners = new ArrayList<>();
		int winnerScore = 0;
		for (int i = 0; i < hands.length; i++) {
			if (!hands[i].isbusted()) {
				if (hands[i].score() > winnerScore) {
					winnerScore = hands[i].score();
					winners.clear();
					winners.add(i);
				} else if (hands[i].score() == winnerScore) {
					winners.add(i);
				}
			}
		}
		return winners;
	}
	//print players with cards
	public void printHandsAndScores() {
		for (int i = 0; i < hands.length; i++) {
			System.out.print("Player" + i + "(" + hands[i].score() + ")");
			hands[i].printCards();
			System.out.println();
		}
	}
	public void simulate() {
		//shuffle cards
		initialDeck();
		//send card to players first
		boolean success = dealInitial();
		if (!success) {
			System.out.println("Error! Out of cards!");
		} else {
			System.out.println("-----------Initially-----------");
		}
		printHandsAndScores();
		List<Integer> blackJacks = getBlackJack();
		if (blackJacks.size() > 0) {
			System.out.print("BlackJacks at: ");
			for (int i : blackJacks) {
				System.out.print(i + " ");
			}
			System.out.print("---Game completed---");
		} else {
			success = playAllHands();
			if (!success) {
				System.out.println("Error! Out of cards!");
			} else {
				System.out.println("---Game completed---");
				printHandsAndScores();
				List<Integer> winners = getWinners();
				if (winners.size() > 0) {
					System.out.print("Winners at: ");
					for (int i : winners) {
						System.out.print(i + " ");
					}
				} else {
					System.out.println("No Winners, All players have busted");
				}
			}
		}
	}
	public static void main(String[] args) {
		BlackJackGameAutomator automator = new BlackJackGameAutomator(6);
		automator.simulate();
	}
}
