public class Card {
	private char kaartWaarde; 
	private String suit; 
	private int value;

	public Card(char nieuweKaartkaartWaarde, String nieuweKaartSuit) {
		kaartWaarde = nieuweKaartkaartWaarde;
		suit = nieuweKaartSuit;

		if (kaartWaarde == 'A' ) {
			value = 11; 
		} else if (kaartWaarde == '2') {
			value = 2;
		} else if (kaartWaarde == '3') {
			value = 3;
		} else if (kaartWaarde == '4') {
			value = 4;
		} else if (kaartWaarde == '5') {
			value = 5;
		} else if (kaartWaarde == '6') {
			value = 6;
		} else if (kaartWaarde == '7') {
			value = 7;
		} else if (kaartWaarde == '8') {
			value = 8;
		} else if (kaartWaarde == '9') {
			value = 9;
		} else if ((kaartWaarde == '0') || (kaartWaarde == 'B') || (kaartWaarde == 'V') || (kaartWaarde == 'K')) {
			value = 10;
		} 
	}

	public char getkaartWaarde() {
		return kaartWaarde;
	}

	public String getFiguur() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
