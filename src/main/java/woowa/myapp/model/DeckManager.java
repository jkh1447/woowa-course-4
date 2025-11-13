package woowa.myapp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DeckManager implements Serializable {

    private static final String SAVE_FOLDER = "save";
    private static final String DECK_FOLDER = SAVE_FOLDER + "/decks";
    private static final String DECK_LIST_FILE = SAVE_FOLDER + "/decklist.dat";
    private ArrayList<Deck> decks = new ArrayList<>();

    public DeckManager() {
        loadDeckList();
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
        saveDeckList();
        deck.deleteDeckData();
    }

    public void saveDeckList() {
        try {
            File folder = new File(SAVE_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DECK_LIST_FILE))) {
                oos.writeObject(decks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDeckList() {
        File file = new File(DECK_LIST_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            decks = (ArrayList<Deck>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
