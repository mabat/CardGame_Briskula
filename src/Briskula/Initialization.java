package Briskula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Initialization {

    protected final List<Card> cardsSpil = new ArrayList<>();
    protected Card backCard = null; //zbog try catche blocka
    protected Card backRotatedCard = null;

    public Initialization() {

        try {
            //ubaci sve karte kao objekte u listu  cardSpil
            cardsSpil.add(new Card("bastoni", "as", 10, 11, ImageIO.read(getClass().getResource("img/b10.png"))));
            cardsSpil.add(new Card("bastoni", "trica", 9, 10, ImageIO.read(getClass().getResource("img/b9.png"))));
            cardsSpil.add(new Card("bastoni", "kralj", 8, 4, ImageIO.read(getClass().getResource("img/b8.png"))));
            cardsSpil.add(new Card("bastoni", "konj", 7, 3, ImageIO.read(getClass().getResource("img/b7.png"))));
            cardsSpil.add(new Card("bastoni", "fanta", 6, 2, ImageIO.read(getClass().getResource("img/b6.png"))));
            cardsSpil.add(new Card("bastoni", "sedmica", 5, 0, ImageIO.read(getClass().getResource("img/b5.png"))));
            cardsSpil.add(new Card("bastoni", "sestica", 4, 0, ImageIO.read(getClass().getResource("img/b4.png"))));
            cardsSpil.add(new Card("bastoni", "petica", 3, 0, ImageIO.read(getClass().getResource("img/b3.png"))));
            cardsSpil.add(new Card("bastoni", "cetvorka", 2, 0, ImageIO.read(getClass().getResource("img/b2.png"))));
            cardsSpil.add(new Card("bastoni", "dvojka", 1, 0, ImageIO.read(getClass().getResource("img/b1.png"))));

            cardsSpil.add(new Card("dinari", "as", 10, 11, ImageIO.read(getClass().getResource("img/d10.png"))));
            cardsSpil.add(new Card("dinari", "trica", 9, 10, ImageIO.read(getClass().getResource("img/d9.png"))));
            cardsSpil.add(new Card("dinari", "kralj", 8, 4, ImageIO.read(getClass().getResource("img/d8.png"))));
            cardsSpil.add(new Card("dinari", "konj", 7, 3, ImageIO.read(getClass().getResource("img/d7.png"))));
            cardsSpil.add(new Card("dinari", "fanta", 6, 2, ImageIO.read(getClass().getResource("img/d6.png"))));
            cardsSpil.add(new Card("dinari", "sedmica", 5, 0, ImageIO.read(getClass().getResource("img/d5.png"))));
            cardsSpil.add(new Card("dinari", "sestica", 4, 0, ImageIO.read(getClass().getResource("img/d4.png"))));
            cardsSpil.add(new Card("dinari", "petica", 3, 0, ImageIO.read(getClass().getResource("img/d3.png"))));
            cardsSpil.add(new Card("dinari", "cetvorka", 2, 0, ImageIO.read(getClass().getResource("img/d2.png"))));
            cardsSpil.add(new Card("dinari", "dvojka", 1, 0, ImageIO.read(getClass().getResource("img/d1.png"))));

            cardsSpil.add(new Card("spade", "as", 10, 11, ImageIO.read(getClass().getResource("img/s10.png"))));
            cardsSpil.add(new Card("spade", "trica", 9, 10, ImageIO.read(getClass().getResource("img/s9.png"))));
            cardsSpil.add(new Card("spade", "kralj", 8, 4, ImageIO.read(getClass().getResource("img/s8.png"))));
            cardsSpil.add(new Card("spade", "konj", 7, 3, ImageIO.read(getClass().getResource("img/s7.png"))));
            cardsSpil.add(new Card("spade", "fanta", 6, 2, ImageIO.read(getClass().getResource("img/s6.png"))));
            cardsSpil.add(new Card("spade", "sedmica", 5, 0, ImageIO.read(getClass().getResource("img/s5.png"))));
            cardsSpil.add(new Card("spade", "sestica", 4, 0, ImageIO.read(getClass().getResource("img/s4.png"))));
            cardsSpil.add(new Card("spade", "petica", 3, 0, ImageIO.read(getClass().getResource("img/s3.png"))));
            cardsSpil.add(new Card("spade", "cetvorka", 2, 0, ImageIO.read(getClass().getResource("img/s2.png"))));
            cardsSpil.add(new Card("spade", "dvojka", 1, 0, ImageIO.read(getClass().getResource("img/s1.png"))));

            cardsSpil.add(new Card("coppe", "as", 10, 11, ImageIO.read(getClass().getResource("img/c10.png"))));
            cardsSpil.add(new Card("coppe", "trica", 9, 10, ImageIO.read(getClass().getResource("img/c9.png"))));
            cardsSpil.add(new Card("coppe", "kralj", 8, 4, ImageIO.read(getClass().getResource("img/c8.png"))));
            cardsSpil.add(new Card("coppe", "konj", 7, 3, ImageIO.read(getClass().getResource("img/c7.png"))));
            cardsSpil.add(new Card("coppe", "fanta", 6, 2, ImageIO.read(getClass().getResource("img/c6.png"))));
            cardsSpil.add(new Card("coppe", "sedmica", 5, 0, ImageIO.read(getClass().getResource("img/c5.png"))));
            cardsSpil.add(new Card("coppe", "sestica", 4, 0, ImageIO.read(getClass().getResource("img/c4.png"))));
            cardsSpil.add(new Card("coppe", "petica", 3, 0, ImageIO.read(getClass().getResource("img/c3.png"))));
            cardsSpil.add(new Card("coppe", "cetvorka", 2, 0, ImageIO.read(getClass().getResource("img/c2.png"))));
            cardsSpil.add(new Card("coppe", "dvojka", 1, 0, ImageIO.read(getClass().getResource("img/c1.png"))));

            backCard = new Card("back", "back", 0, 0, ImageIO.read(getClass().getResource("img/back.png"))); //karta sa poledjinom, sluzi za vizualni prikaz
            backRotatedCard = new Card("back", "back", 0, 0, ImageIO.read(getClass().getResource("img/back_rotated.png"))); //rotirana karta sa poledjinom, zasad neiskoristeno

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        //izmjesaj listu karata
        Collections.shuffle(cardsSpil);
    }

    public int cardSpilSize() {
        return cardsSpil.size();
    }

}
