package woowa.myapp.controller;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.AddDeckDialog;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.MainFrame;

public class DeckScreenController {
    private DeckManager deckManager;
    private MainFrame mainFrame;

    public DeckScreenController(DeckManager deckManager, MainFrame mainFrame) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
    }

    public void getAddDeckButtonEvent(DeckManager deckManager, MainFrame mainFrame, DeckListPanel deckListPanel) {
        AddDeckDialog dialog = new AddDeckDialog(mainFrame, this);
        dialog.setVisible(true);
        String newDeckName = dialog.getName();
        if(newDeckName != null){
            Deck newDeck = new Deck(newDeckName);
            deckManager.addDeck(newDeck);

            deckManager.saveDeckList();

            deckListPanel.refresh();
        }
    };

    public void getOkButtonEvent(String name, AddDeckDialog dialog) {
        if(!name.isEmpty()) {
            dialog.setDeckName(name);
            dialog.dispose();
        }
        else {
            JOptionPane.showMessageDialog(dialog, "이름을 입력해주세요.");
        }
    }
}
