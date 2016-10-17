package Briskula;

import java.awt.image.BufferedImage;

public class Card {

    protected final String type; // coppe, dinare, bastoni, spade
    protected final String name; //as, trica, kralj, konj, fanta
    protected final int value; //as je 10, 3 je 9, king je 8, horse je 7, fante je 6, 7 je 5, 6-4, 5-3, 4-2, 3-1, 2-0 
    protected final int score;
    protected final BufferedImage cardImage;

    public Card(String t, String n, int v, int s, BufferedImage cImage) {

        this.type = t;
        this.name = n;
        this.value = v;
        this.score = s;
        this.cardImage = cImage;

    }
}
