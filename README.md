# BlackJack
Halve week opdracht Young Colfield TEP 14

#### Doel
Het doel van het spel is om de bank (dealer) te verslaan. Hierbij moet men proberen dichter bij de 21 punten te komen dan de bank. Als de speler boven de 21 punten uitkomt heeft hij verloren, ongeacht wat de bank heeft.

#### Project Java
Het BlackJack project is verdeeld in meerdere onderdelen. Ik heb geprobeerd een programma te schrijven die bestaat uit de volgende componenten:


1) Start van het spel
2) Schudden van de 52 kaarten
3) Uitdeling van de "bovenste" twee kaarten van het deck
4) Vraag aan de speler voor een nieuwe kaart
    - Bij een ja, zal er een nieuwe kaart getrokken worden
    - Bij een nee, zal er besloten moeten worden door de dealer voor het trekken van een nieuwe kaart
5) De ronde zal eindigen bij het overtreffen van 21 of als de dealer bepaalt geen kaart meer te pakken
6) Een volgende ronde zal starten met de overgebleven kaarten uit het deck. 

#### Sheet
deck == arraylist of string items

shuffle ==  java.util.Random

score == private static int counter 

user input == scanner

charat index to string
