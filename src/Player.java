import java.util.ArrayList;

public class Player {

	private String name;
	private int type;
	private ArrayList<Card> hand = new ArrayList<Card>(); 
	private int score = 0;

	public Player(String newPlayerName, int newPlayerType) {
		name = newPlayerName;
		type = newPlayerType;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public int getScore(){
		score = 0;
		for(int i=0; i<hand.size(); i++){
			score = score + hand.get(i).getValue();
		}
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

}
