package Briskula;

import java.util.LinkedList;
import java.util.List;

public class PlayDoubleBriskula extends Initialization {

    private int playerScore;
    private int compScore;
    private final List<Card> player = new LinkedList<>();
    private final List<Card> comp = new LinkedList<>();
    private final Card briskulaCard; //tip u koji je igra
    private final Card playerCard;
    private final Card compCard;
    private boolean flag;           //odredjuje ciji je red na igru
    private boolean gameCardCheck; //je li podignuta ultima
    private Card firstCard; //kada comp baci prvu kartu ne smijemo je odmah dodati u listu jer ce biti uracunata u algoritam

    public PlayDoubleBriskula() {

        super();
        playerScore = 0;
        compScore = 0;
        playerCard = null;
        compCard = null;
        // briskulaCard = null;
        flag = false;

        //####################################### INICIJALIZACIJA DVIJU LISTA S KOJIMA VODIMU IGRU ###############
        // u listu player dodaj 4 karte i izbrisi ih iz spila
        player.add(cardsSpil.remove(0));
        player.add(cardsSpil.remove(1));
        player.add(cardsSpil.remove(2));
        player.add(cardsSpil.remove(3));

        //u listu comp dodaj 4 karte i izbrisi ih iz spila
        comp.add(cardsSpil.remove(4));
        comp.add(cardsSpil.remove(5));
        comp.add(cardsSpil.remove(6));
        comp.add(cardsSpil.remove(7));

        //gameCard je karta u koju je igra(briskula), dodaj je i izbrisi iz spila
        briskulaCard = cardsSpil.remove(8);
        gameCardCheck = false;
    }

    //vraca bodove od playera
    public int playerScore() {
        return playerScore;
    }

    //vraca bodove od comp-a
    public int compScore() {
        return compScore;
    }

    //vraca flag ovisno ciji je red za igru. true je player, a false je comp
    public boolean checkFlag() {
        return flag;
    }

    //vrati cijelu listu karata od playera
    public List<Card> getPlayerCards() {
        return player;
    }

    //vrati cijelu listu karata od compa
    public List<Card> getCompCards() {
        return comp;
    }

    //vrati pojedinacnu kartu iz liste playera (sluzi za vizualnu inicijalizaciju karte)
    public Card getMe(int num) {

        return player.get(num);
    }

    //vrati pojedinacnu kartu iz liste compa 
    public Card getComp(int num) {

        return comp.get(num);
    }

    //vrati kartu u koju je igra (briskula) sluzi za vizualnu inicijalizaciju karte)
    public Card getGameCard() {

        return briskulaCard;
    }

    //vrati kartu sa poledjinom (sluzi za vizualnu inicijalizaciju karte
    public Card getBackCard() {
        return backCard;
    }

    //vrati kartu sa rotiranom poledjinom (sluzi za vizualnu inicijalizaciju karte    
    public Card getBackRotatedCard() {
        return backRotatedCard;
    }

    //dodaj kartu u listu playera i izbrisi je iz spila
    public void setMe() {
        player.add(cardsSpil.remove(0));
    }

    //dodaj kartu u listu comp i izbrisi je iz spila
    public void setComp() {
        comp.add(cardsSpil.remove(0));
    }

    //######################################################################## IGRA #######################################
    //vrati kartu sa odredjenog indexa kada player klikne labelu i odmah taj index popuni sa novom kartom iz spila
    public Card getMeCard(int num) {
        Card temp;
        if (gameCardCheck == false) { //ako nije podignuta ultima peskaj
            temp = player.get(num);
            player.set(num, newHand());
        } else {
            temp = player.get(num);
            player.set(num, null); //ne smijemo izbrisati mjesto radi zadnjeg poteza kada se podigne ultima
        }
        return temp;
    }

    //metoda koja sluzi za bacanje karte kada comp igra prvi
    public Card getFirstCompCard() {
        // "1" 0 1 0
        Card tempCard = comp.get(0);
        int i = 0;

        for (Card c : comp) {

            if (c.value < tempCard.value && !c.type.equals(briskulaCard.type)) {
                tempCard = c;
                i = comp.indexOf(c);
            } else if (c.value < tempCard.value) {
                tempCard = c;
                i = comp.indexOf(c);
            }
        }

        if (gameCardCheck == false) { //ako nije podignuta ultima peskaj

            comp.remove(i);
            firstCard = newHand();
            return tempCard;
        } else { //ako je podignuta ultima nemoj peskat, samo izbrisi kartu i

            comp.remove(i);
            System.out.println(i);
            return tempCard;
        }

    }

    //metoda koja vraca kartu za prvi odgovor kada comp igra nakon playera
    public Card getSecondCompCard(Card currentPlayerCard) {

        //0 '1' 0 1 - situacija
        Card tempComp = comp.get(0);
        int i = 0;
        boolean haveBriskula = false; //briskula
        boolean haveKark = false;    //kark
        boolean haveGreater = false; //veca karta
        boolean checked = false; //ako pronadje bilo koju kartu ne provjeravaj druge uvjete

        if (currentPlayerCard.type.equals(briskulaCard.type)) { //ako JE player bacio briskulu

            for (Card c : comp) {
                //veca od igraceve bacene              i      //briskula
                if (c.value == 10 || c.value == 9) {
                    haveKark = true;
                }

                if (c.value > currentPlayerCard.value && c.type.equals(briskulaCard.type)) {//
                    haveGreater = true;
                    haveBriskula = true;
                }
            }

            if (haveKark == true && haveBriskula == true) {

                for (Card c : comp) {
                    if (c.value > tempComp.value) { //baci karka
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    }
                }
            } else {
                for (Card c : comp) {
                    if (!c.type.equals(briskulaCard.type) && c.value < tempComp.value) { //baci najmanju ne briskulu
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && c.value < tempComp.value) { //bilo koju najmanju
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }
            }

        } else //ako player NIJE bacio briskulu
        {
            for (Card c : comp) {

                if (c.value == 10 || c.value == 9) { //kark
                    haveKark = true;
                }

                if (c.type.equals(briskulaCard.type)) {//briskula
                    haveBriskula = true;
                }
                if (c.value > currentPlayerCard.value && c.type.equals(currentPlayerCard.type)) {//veca
                    haveGreater = true;
                }
            }
        }

        if ((haveBriskula == true || haveGreater == true) && haveKark == true) {

            for (Card c : comp) {
                if (c.value > currentPlayerCard.value && c.type.equals(currentPlayerCard.type)) { //baci vecu
                    tempComp = c;
                    i = comp.indexOf(c);
                }
            }

        } else if (haveKark == false) {

            for (Card c : comp) {
                if (c.value < currentPlayerCard.value && !c.type.equals(briskulaCard.type)) { //baci najmnaju
                    tempComp = c;
                    i = comp.indexOf(c);
                    checked = true;
                } else if (checked == false && c.value < tempComp.value) { //bilo koju najmanju
                    tempComp = c;
                    i = comp.indexOf(c);
                }
            }

        }
        if (gameCardCheck == false) { //ako nije podignuta ultima peskaj

            comp.remove(i);
            firstCard = newHand();
            return tempComp;
        } else { //ako je podignuta ultima nemoj peskat, samo izbrisi kartu i

            comp.remove(i);
            System.out.println(i);
            return tempComp;
        }

    }

    //metoda koja vraca drugi odgovor
    public Card getFourthCompCard(Card player1, Card comp1, Card player2) {

        //0 1 0 '1'
        Card tempComp = comp.get(0);
        int i = 0;
        boolean checked = false; //ako pronadje bilo koju kartu ne provjeravaj druge uvjete
        int tempScore = player1.score + player2.score + comp1.score;

        if (player1.type.equals(briskulaCard.type) || player2.type.equals(briskulaCard.type)) { //ako JE player bacio briskulu

            if (player1.value == 9 || player1.value == 8 || player1.value == 7 || player1.value == 6
                    || player2.value == 9 || player2.value == 8 || player2.value == 7 || player2.value == 6) {//trica,kralj,konj,fanat

                for (Card c : comp) {
                    //veca od igraceve bacene                    //od igre
                    if (c.value > player1.value && c.value > player2.value && c.type.equals(briskulaCard.type)) {//
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && !c.type.equals(briskulaCard.type)) { //baci bilo koju drugu kartu
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    }
                    //ubi najmanjom vecom briskulom
                    if (checked == true && c.value > player1.value && c.value > player2.value && c.value < tempComp.value && c.type.equals(tempComp.type)) {
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (checked == true && c.value < tempComp.value && !c.type.equals(briskulaCard.type)) { //ta druga karta neka bude najmanja iz liste
                        tempComp = c;
                        i = comp.indexOf(c);
                    }

                }

            } else { //ako player baci asa ili liso briskule
                for (Card c : comp) {
                    if (!c.type.equals(briskulaCard.type) && c.value < tempComp.value) { //baci ne briskulu, najmanju iz liste
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }
            }

        } else //ako player NIJE bacio briskulu
        {
            if (tempScore > 10) { //ako igrac baci punte

                boolean throwAnySmall = false; //- za bacanje najmanje karte ako nema boljeg odgovora
                for (Card c : comp) {
                    //veca od igraceve                   //istog tipa kojeg je bacio igrac
                    if (c.value > player1.value && c.value > player2.value && c.type.equals(player1.type)) { //baci vecu od igracevog tipa
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && c.type.equals(briskulaCard.type)) { // briskulu
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && c.value <= tempComp.value) { //bilo koju najmanju kartu
                        tempComp = c;
                        i = comp.indexOf(c);
                        throwAnySmall = true;
                    }
                    //baci najvecu od igre
                    if (checked == true && c.value > tempComp.value && c.type.equals(tempComp.type) && c.type.equals(player1.type)) {//najveci stroc
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (checked == true && c.value < tempComp.value && c.type.equals(tempComp.type)) {//najmanju briskulu
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (throwAnySmall == true && c.value < tempComp.value) {//najmanju kartu
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }

            } else { //ako igrac baci bilo koju liso kartu

                for (Card c : comp) {
                    //strocaj
                    if (c.type.equals(player1.type) && c.value > player1.value) { //najveca od igre
                        if (!player2.type.equals(player1.type)) {

                            tempComp = c;
                            i = comp.indexOf(c);
                            checked = true;
                        } else if (player2.type.equals(player1.type) && c.value > player2.value) {

                            tempComp = c;
                            i = comp.indexOf(c);
                            checked = true;

                        }

                        //bilo koja najmanja vrijednost
                    } else if (checked == false && c.value < tempComp.value) {
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    }

                    if (checked == true && c.value > tempComp.value && c.type.equals(tempComp.type) && c.type.equals(player1.type)) {//najveci stroc
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (checked == true && c.value < tempComp.value && c.type.equals(tempComp.type)) {//najmanja kartu
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }
            }
        }
        if (gameCardCheck == false) {
            comp.set(i, newHand()); //kartu koju peskamo postavljamo na mjesto na kojem je bila karta koju smo vratili
            comp.add(firstCard); //prva podignuta karta
            return tempComp;
        } else {
            comp.remove(i);
            return tempComp;
        }

    }

    public Card getThirdCompCard(Card player1, Card comp1) {

        //1 0 "1" 0
        Card tempComp = comp.get(0);
        int i = 0;
        boolean checked = false; //ako pronadje bilo koju kartu ne provjeravaj druge uvjete
        int tempScore = player1.score + comp1.score;

        if (player1.type.equals(briskulaCard.type) && !comp1.type.equals(briskulaCard.type)) { //ako JE player bacio briskulu

            for (Card c : comp) {
                //veca od igraceve bacene                    //od igre
                if (c.value > player1.value && c.type.equals(briskulaCard.type)) {//
                    tempComp = c;
                    i = comp.indexOf(c);
                    checked = true;
                } else if (checked == false && !c.type.equals(briskulaCard.type)) { //baci bilo koju drugu kartu
                    tempComp = c;
                    i = comp.indexOf(c);
                    checked = true;
                }
                //ubi najmanjom vecom briskulom
                if (checked == true && c.value > player1.value && c.value < tempComp.value && c.type.equals(tempComp.type)) {
                    tempComp = c;
                    i = comp.indexOf(c);
                } else if (checked == true && c.value < tempComp.value && !c.type.equals(briskulaCard.type)) { //ta druga karta neka bude najmanja iz liste
                    tempComp = c;
                    i = comp.indexOf(c);
                }
            }

        } else //ako player NIJE bacio briskulu
        {
            if (player1.type.equals(comp1.type) && player1.value > comp1.value) { //ako JE player bacio jacu od prve

                for (Card c : comp) {
                    //veca od igraceve bacene                    
                    if (c.value > player1.value && c.type.equals(comp1.type)) {//
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && !c.type.equals(briskulaCard.type)) { //baci bilo koju drugu kartu
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    }
                    //ubi najmanjom vecom briskulom
                    if (checked == true && c.value > player1.value && c.value < tempComp.value && c.type.equals(tempComp.type)) {
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (checked == true && c.value < tempComp.value && !c.type.equals(briskulaCard.type)) { //ta druga karta neka bude najmanja iz liste
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }
            } else if (!player1.type.equals(comp1.type)) {

                for (Card c : comp) {

                    if (c.value == 8 || c.value == 7 || c.value == 6 && !c.type.equals(briskulaCard.type)) {

                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;
                    } else if (checked == false && !c.type.equals(briskulaCard.type)) {
                        tempComp = c;
                        i = comp.indexOf(c);
                        checked = true;

                    }

                    if (checked == true && c.value == 8 || c.value == 7 || c.value == 6 && !c.type.equals(briskulaCard.type)) {
                        tempComp = c;
                        i = comp.indexOf(c);
                    } else if (checked == true && c.value < tempComp.value && !c.type.equals(briskulaCard.type)) { //ta druga karta neka bude najmanja iz liste
                        tempComp = c;
                        i = comp.indexOf(c);
                    }
                }

            }
        }

        if (gameCardCheck == false) {
            comp.set(i, newHand()); //kartu koju peskamo postavljamo na mjesto na kojem je bila karta koju smo vratili
            comp.add(firstCard); //prva podignuta karta
            return tempComp;
        } else {
            comp.remove(i);
            return tempComp;
        }

    }

//metoda za peskanje nove karte
    public Card newHand() {

        if (cardsSpil.isEmpty()) { //ako je spil sa kartama prazan
            if (gameCardCheck == false) { //ultima
                gameCardCheck = true;
                return briskulaCard; //vrati ultimu
            }
        } else {
            return cardsSpil.remove(0); //vrati prvu kartu iz spila
        }
        return null;
    }

    public void hand(Card p1, Card c1, Card p2, Card c2) { //metoda koja odredjuje tko nosi ruku, zbraja punte i dodjeljuje flag iducem igracu

        if ((p1.type.equals(briskulaCard.type) || p2.type.equals(briskulaCard.type)) && !c1.type.equals(briskulaCard.type) && !c2.type.equals(briskulaCard.type)) { //ako je player briskula, a comp nije
            playerScore += p1.score + c1.score + p2.score + c2.score;
            flag = true;
        } else if (!p1.type.equals(briskulaCard.type) && !p2.type.equals(briskulaCard.type) && (c1.type.equals(briskulaCard.type) || c2.type.equals(briskulaCard.type))) { //ako je comp briskula, a player nije
            compScore += p1.score + c1.score + p2.score + c2.score;
            flag = false;
        } else if ((p1.type.equals(briskulaCard.type) && c1.type.equals(briskulaCard.type) && !c2.type.equals(briskulaCard.type) && p1.value > c1.value)
                || (p1.type.equals(briskulaCard.type) && c2.type.equals(briskulaCard.type) && !c1.type.equals(briskulaCard.type) && p1.value > c2.value)
                || (p2.type.equals(briskulaCard.type) && c1.type.equals(briskulaCard.type) && !c2.type.equals(briskulaCard.type) && p2.value > c1.value)
                || (p2.type.equals(briskulaCard.type) && c2.type.equals(briskulaCard.type) && !c1.type.equals(briskulaCard.type) && p2.value > c2.value)) {
            playerScore += p1.score + c1.score + p2.score + c2.score;
            flag = true;
        } else if ((p1.type.equals(briskulaCard.type) && c1.type.equals(briskulaCard.type) && !p2.type.equals(briskulaCard.type) && p1.value < c1.value)
                || (p1.type.equals(briskulaCard.type) && c2.type.equals(briskulaCard.type) && !p1.type.equals(briskulaCard.type) && p1.value < c2.value)
                || (p2.type.equals(briskulaCard.type) && c1.type.equals(briskulaCard.type) && !p2.type.equals(briskulaCard.type) && p2.value < c1.value)
                || (p2.type.equals(briskulaCard.type) && c2.type.equals(briskulaCard.type) && !p1.type.equals(briskulaCard.type) && p2.value < c2.value)) {
            compScore += p1.score + c1.score + p2.score + c2.score;
            flag = false;
        } else if (!p1.type.equals(c1.type) && !p1.type.equals(c2.type) && !c1.type.equals(briskulaCard.type) && !c2.type.equals(briskulaCard.type) && flag == true) { //ako player prvi baca, a comp baci razlicit tip
            playerScore += p1.score + c1.score + p2.score + c2.score;
            flag = true;
        } else if (!p1.type.equals(c1.type) && !p1.type.equals(c2.type) && !p1.type.equals(briskulaCard.type) && !p2.type.equals(briskulaCard.type) && flag == false) { //ako comp prvi baca, a player baci razlicit tip
            compScore += p1.score + c1.score + p2.score + c2.score;
            flag = false;
        } else if (flag == true && p1.type.equals(c1.type) && p1.type.equals(c2.type) && p2.type.equals(p1.type) && (p1.value > c1.value && p1.value > c2.value)
                || (p2.value > c1.value && p2.value > c2.value)) { //ako je isti tip i ako je player1 vrijednost veca ili player2 veca od svih
            playerScore += p1.score + c1.score + p2.score + c2.score;
            flag = true;
        } else if (flag == true && (p1.type.equals(c1.type) && p1.value > c1.value && !p1.type.equals(c2.type)) || (p1.type.equals(c2.type) && p1.value > c2.value && !p1.type.equals(c1.type))
                || (p1.type.equals(p2.type) && p1.type.equals(c1.type) && p2.value > c1.value && !p1.type.equals(c2.type))
                || (p1.type.equals(p2.type) && p1.type.equals(c2.type) && p2.value > c2.value && !p1.type.equals(c1.type))) {
            playerScore += p1.score + c1.score + p2.score + c2.score;
            flag = true;
        } else if (flag == false && (p1.type.equals(c1.type) && p1.type.equals(c2.type) && p2.type.equals(p1.type) && (p1.value < c1.value && p2.value < c1.value)) || ((p1.value < c2.value && p2.value < c2.value))) { //ako je isti tip i ako je comp vrijednost veca
            compScore += p1.score + c1.score + p2.score + c2.score;
            flag = false;
        } else if (flag == false && (p1.type.equals(c1.type) && c1.value > p1.value && !c1.type.equals(p2.type)) || (c1.type.equals(p2.type) && c1.value > p2.value && !p1.type.equals(c1.type))
                || (p1.type.equals(p2.type) && p1.type.equals(c1.type) && c2.value > p1.value && !p1.type.equals(c2.type)) || (p1.type.equals(p2.type) && p1.type.equals(c2.type) && c2.value > p2.value && !p1.type.equals(c1.type))) {
            compScore += p1.score + c1.score + p2.score + c2.score;
            flag = false;
        }
    }

    //vrati je li ultima podignuta
    public boolean checkUltima() {
        return gameCardCheck;
    }

    //pomoc za ispis na konzolu trenutnog stanja karata playera i compa
    @Override
    public String toString() {
        return "Me: " + player.get(0).name + " " + player.get(0).type + ", " + player.get(1).name + " " + player.get(1).type + ", " + player.get(2).name + " " + player.get(2).type + "," + player.get(3).name + " " + player.get(3).type + "\n "
                + "Comp: " + comp.get(0).name + " " + comp.get(0).type + ", " + comp.get(1).name + " " + comp.get(1).type + ", " + comp.get(2).name + " " + comp.get(2).type + "," + comp.get(3).name + " " + comp.get(3).type + "\n";
    }

}
