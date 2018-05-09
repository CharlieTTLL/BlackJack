package blackjack;

public class Card {
	//value is from 1 - 11
	private int value;
	private Suit type;
	public Card(Suit type, int value) {
		this.type = type;
		this.value = value;
	}
	public int value() {
		return this.value;
	}
	public Suit type() {
		return this.type;
	}
}
