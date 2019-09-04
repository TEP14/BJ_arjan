// Gemaakt samen met Ayman Rakha. Moet eerlijk toegeven dat het een erg Pythonise scriptje is geworden. 
// Veel gewerkt met for-loops, meer omdat ik daar nu steeds beter in aan het worden ben (en dat snap xD)
// Daarnaast heb ik de code voor maakDeck() en shuffle() gestolen van het internet. Natuurlijk wel aangepast, 
// maar ik ben bleef maar kloten op twee arrays dat ik het maar gewoon opgaf. Anders zou ik nooit meer gaan slapen.
// Ook snap ik nog niet helemaal het verschil tussen public en private, dus uiteindelijk heb ik alle functies 
// maar als private in zijn class gemaakt. Deze Game.java heeft alleen maar private, waar de andere alleen maar public hebben. 

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Desktop;
import java.net.URL;


public class Game {
	public static void main(String... args) {
		Game newGame = new Game();
		newGame.maakDeck();
		newGame.maakDealer();
		newGame.maakSpeler();
		newGame.startSpel();
	}
	
	private Player dealer;
	private Player user;
	private Card[] deck = new Card[52];
	private Stack<Card> gamedeck = new Stack<Card>();
	private ArrayList<Card> cardsInUse = new ArrayList<Card>();

	private void maakDeck() {
		String[] figuur = { "ruiten", "harten", "schoppen", "klaver" };
		int index = 0;
		for (int i = 0; i < 4; i++) {	
			for (int j = 0; j < 13; j++) {
				if (j < 10) {
					char kaartWaarde = (char) j;
					if (j == 1) {
						kaartWaarde = 'A';
					} else {
						int convertToASCII = j + 48;
						kaartWaarde = (char) convertToASCII;
					}
					Card nieuweKaart = new Card(kaartWaarde, figuur[i]);
					deck[index] = nieuweKaart;
					index++;
				} else {
					char kaartWaarde = 'B';
					if (j == 10) {
						kaartWaarde = 'B';
					} else if (j == 11) {
						kaartWaarde = 'V';
					} else if (j == 12) {
						kaartWaarde = 'K';
					}
					Card nieuweKaart = new Card(kaartWaarde, figuur[i]);
					deck[index] = nieuweKaart;
					index++;
				}
			}
		}
		this.shuffle();
	}
	
	private void shuffle() {
		for (int numberOfShuffles = 0; numberOfShuffles < 20; numberOfShuffles++) {
			Card temp;
			Random rand = new Random();
			for (int i = 0; i < deck.length; i++) {
				int card1Index = rand.nextInt(deck.length);
				int card2Index = rand.nextInt(deck.length);
				temp = deck[card1Index];
				deck[card1Index] = deck[card2Index];
				deck[card2Index] = temp;
			}
		}
	}
	
	private void maakDealer() {
		Player newDealer = new Player("Dealer", 0);
		dealer = newDealer;
	}
	
	private void maakSpeler() {
		System.out.println("Wat is je naam?");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		Player newPlayer = new Player(name, 1);
		user = newPlayer;
		System.out.println("Welkom " + user.getName() + "!");
	}
	

	private void startSpel() {
		int playAgain = 1;
		for (int i = 0; i < deck.length; i++) {
			gamedeck.push(deck[i]);
		}
		
		int roundNumber = 0;
		while (playAgain == 1) {			
			user.getHand().add(checkCardDelt());
			cardsInUse.add(user.getHand().get(0));
			System.out.println(user.getName() + " kreeg de kaarten: ");
			printCard(user.getHand().get(0));
			user.getHand().add(checkCardDelt());
			cardsInUse.add(user.getHand().get(1));
			printCard(user.getHand().get(1));
			dealer.getHand().add(checkCardDelt());
			cardsInUse.add(dealer.getHand().get(0));
			dealer.getHand().add(checkCardDelt());
			cardsInUse.add(dealer.getHand().get(1));
			System.out.println("De dealer heeft een ");		
			printCard(dealer.getHand().get(1));
			System.out.println("Je aantal punten is " + user.getScore());
			Scanner scan = new Scanner(System.in);		
			
			if (user.getScore() == 21) {				
				System.out.println("Blackjack! Wil je nog een ronde spelen? Y/N");
				String promptToPlayAgain = scan.nextLine();
				if (promptToPlayAgain.toLowerCase().equals("y")) {
					roundNumber++;
					for (int i = 0; i < user.getHand().size(); i++) {
						user.getHand().remove(i);
					}
					for (int i = 0; i < dealer.getHand().size(); i++) {
						dealer.getHand().remove(i);
					}
				} 
				else {
				      try{
				    	  String url =  "https://youtu.be/0MV3AftdHiA?t=37";
				          Desktop.getDesktop().browse(new URL(url).toURI());
				        }
				        catch(Exception E){
				            System.err.println("Exp : "+E.getMessage());
				        }
				    System.exit(0);
					}
				} 
			else {
				int usersTurn = 0;
				String userAction = ""; 				
				while (usersTurn == 0) {					
					int validInput = 0; 					
					while (validInput == 0) {
						System.out.println("Wil je nog een kaart? (K voor nieuwe kaart/P voor passen)");
						userAction = scan.nextLine();
						if (userAction.toLowerCase().equals("k")|| userAction.toLowerCase().equals("p")) {
							validInput = 1;
						} else {
							validInput = 0;
						}
					}

					if (userAction.toLowerCase().equals("k")) {
						if (gamedeck.isEmpty() == false) {
							Card ontvangenKaart = checkCardDelt();
							user.getHand().add(ontvangenKaart);
							cardsInUse.add(ontvangenKaart);
							printCard(ontvangenKaart);
							System.out.println("Je huidige score is " + user.getScore());
							if (user.getScore() == 21) {
								System.out.println("Blackjack!");
							} else if (user.getScore() >= 21) {
								System.out.println("Over 21 heen. Wil je nog een ronde spelen? Y/N");
								String promptToPlayAgain = scan.nextLine();
								if (promptToPlayAgain.toLowerCase().equals("y")) {
									roundNumber++;
									usersTurn = 1;
									user.setScore(0);
									dealer.setScore(0);
									for (int i = 0; i < user.getHand().size(); i++) {
										user.getHand().remove(i);
									}
									for (int i = 0; i < dealer.getHand().size(); i++) {
										dealer.getHand().remove(i);
									}
								} else {
									try{
								    	  String url =  "https://youtu.be/0MV3AftdHiA?t=37";
								          Desktop.getDesktop().browse(new URL(url).toURI());
								        }
								        catch(Exception E){
								            System.err.println("Exp : "+E.getMessage());
								        }
								    System.exit(0);
								}
							}
						} else {
							shuffle();
							for (int i = 0; i < deck.length; i++) {
								gamedeck.push(deck[i]);
							}
							Card ontvangenKaart = checkCardDelt();
							user.getHand().add(ontvangenKaart);
							cardsInUse.add(ontvangenKaart);
							printCard(ontvangenKaart);
							System.out.println("Jouw aantal punten zijn "+ user.getScore());
							if (user.getScore() == 21) {							
								System.out.println("Blackjack!");
							} 
							else if (user.getScore() > 21) {
								System.out.println("Jij bent over de 21 heen.");
							}
							System.out.println("Wil je nog een ronde spelen? Y/N");
							String promptToPlayAgain = scan.nextLine();
								if (promptToPlayAgain.toLowerCase().equals("y")) {
									roundNumber++;
									usersTurn = 1;
									user.setScore(0);
									dealer.setScore(0);
									for (int i = 0; i < user.getHand().size(); i++) {
										user.getHand().remove(i);
									}
									for (int i = 0; i < dealer.getHand().size(); i++) {
										dealer.getHand().remove(i);
									}
								} else {
									try{
								    	  String url =  "https://youtu.be/0MV3AftdHiA?t=37";
								          Desktop.getDesktop().browse(new URL(url).toURI());
								        }
								        catch(Exception E){
								            System.err.println("Exp : "+E.getMessage());
								        }
								    System.exit(0);
								}
							}
						
					} else {
						usersTurn = 1;
					}
				}			
				if (userAction.toLowerCase().equals("p")) {
					System.out.println("De dealer heeft een ");
					printCard(dealer.getHand().get(0));
					if (dealer.getScore() < 17) {
						System.out.println("De dealer trekt een kaart en heeft een ");
						Card ontvangenKaart = checkCardDelt();
						dealer.getHand().add(ontvangenKaart);
						cardsInUse.add(ontvangenKaart);
						printCard(ontvangenKaart);
						}
					
					if (dealer.getScore() > 21) {
						System.out.println("De dealer is over de 21 heen. Jij wint!");}
					System.out.println("De dealer had "+ dealer.getScore());
					System.out.println("Wil je nog een ronde spelen? Y/N");
					String promptToPlayAgain = scan.nextLine();
					if (promptToPlayAgain.toLowerCase().equals("y")) {
						roundNumber++;
						usersTurn = 1;
						user.setScore(0);
						dealer.setScore(0);
						for (int i = 0; i < user.getHand().size(); i++) {
							user.getHand().remove(i);
							
						}
						for (int i = 0; i < dealer.getHand().size(); i++) {
							dealer.getHand().remove(i);
						}
					} else {
						try{
					    	  String url =  "https://youtu.be/0MV3AftdHiA?t=37";
					          Desktop.getDesktop().browse(new URL(url).toURI());
					        }
					        catch(Exception E){
					            System.err.println("Exp : "+E.getMessage());
					        }
						System.exit(0);
						}
				}
			}
			
				for (int i = 0; i < user.getHand().size(); i++) {
					user.getHand().remove(i);
					}
				user.setScore(0);
				for (int i = 0; i < dealer.getHand().size(); i++) {
					dealer.getHand().remove(i);
					}
				dealer.setScore(0);
				for (int i = 0; i < cardsInUse.size(); i++) {
					cardsInUse.remove(i);
					}
		}
	}

	private Card checkCardDelt() {
		if (gamedeck.isEmpty() == true) {
			shuffle();
			for (int i = 0; i < deck.length; i++) {
				gamedeck.push(deck[i]);
			}
		}
		Card nieuweKaart = gamedeck.pop();
		int foundValidCard = 0;
		if (cardsInUse.size() != 0) {
			while (foundValidCard == 0) {
				for (int i = 0; i < cardsInUse.size(); i++) {
					if (nieuweKaart.getkaartWaarde() == cardsInUse.get(i).getkaartWaarde()) {
						nieuweKaart = checkCardDelt();
					} else {
						foundValidCard = 1;
					}
				}
			}
		}
		return nieuweKaart;
	}

	private void printCard(Card ontvangenKaart) {
		if (ontvangenKaart.getFiguur() == "ruiten") {
			System.out.println("ruiten " + ontvangenKaart.getkaartWaarde() + " gekregen");
		} else if (ontvangenKaart.getFiguur() == "schoppen") {
			System.out.println("schoppen " + ontvangenKaart.getkaartWaarde() + " gekregen");
		} else if (ontvangenKaart.getFiguur() == "harten") {
			System.out.println("harten " + ontvangenKaart.getkaartWaarde() + " gekregen");
		} else if (ontvangenKaart.getFiguur() == "klaver") {
			System.out.println("klaver " + ontvangenKaart.getkaartWaarde() + " gekregen");
		}
	}
}
