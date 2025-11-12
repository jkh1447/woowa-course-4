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
    private static final String SAVE_FILE = SAVE_FOLDER + "/save.dat";

    private ArrayList<Deck> decks;

    public DeckManager() {
        decks = new ArrayList<>();
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DeckManager loadFromFile() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
                return (DeckManager) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new DeckManager();
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
    }
}
