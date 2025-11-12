package woowa.myapp.controller;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.AddCardPanel;

public class AddCardController {
    private DeckManager deckManager;

    private MainController mainController;

    public AddCardController(DeckManager deckManager, MainController mainController) {
        this.deckManager = deckManager;
        this.mainController = mainController;
    }

    public void getAddSaveButtonEvent(Deck deck, JTextArea frontField, JTextArea backField, AddCardPanel addCardPanel) {
        String front = frontField.getText().trim();
        String back = backField.getText().trim();
        if (front.isEmpty() || back.isEmpty()) {
            JOptionPane.showMessageDialog(addCardPanel, "앞면과 뒷면을 모두 입력하세요!");
            return;
        }
        deck.addCard(new Card(front, back));

        deckManager.saveToFile();

        JOptionPane.showMessageDialog(addCardPanel, "카드가 추가되었습니다!");
        frontField.setText("");
        backField.setText("");
    }

    public void getDoneButtonEvent() {
        mainController.getMainButtonEvent();
    }
}
