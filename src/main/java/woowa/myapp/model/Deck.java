package woowa.myapp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deck implements Serializable {

    private static final String SAVE_FORDER = "save/decks/";

    private String name;
    private transient List<Card> cards;

    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        if(cards == null) {
            loadDeckData();
            if(cards == null) cards = new ArrayList<>();
        }

        return cards;
    }

    public void saveDeckData() {
        try {
            File folder = new File(SAVE_FORDER);
            if(!folder.exists()) folder.mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getDeckFilePath()))) {
                oos.writeObject(cards);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadDeckData() {
        System.out.println("Loading Deck...");
        File file = new File(getDeckFilePath());
        if (!file.exists()) {
            cards = new java.util.ArrayList<>();
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                cards = (List<Card>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                cards = new java.util.ArrayList<>();
            }
        }
    }

    private String getDeckFilePath() {
        return SAVE_FORDER + name + ".dat";
    }

    public void deleteDeckData() {
        File file = new File(getDeckFilePath());
        if (file.exists()) file.delete();
    }

}
