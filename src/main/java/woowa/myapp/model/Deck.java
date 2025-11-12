package woowa.myapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String name;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
