package woowa.myapp.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.AddCardPanel;
import woowa.myapp.view.CardsPanel;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.DeckSettingPanel;
import woowa.myapp.view.MainFrame;

public class DeckListController {

    private MainFrame mainFrame;
    private DeckManager deckManager;

    private DeckSettingController deckSettingController;
    private MainController mainController;
    private AddCardController addCardController;
    private CardsController cardsController;

    public DeckListController(MainFrame mainFrame, DeckManager deckManager, DeckSettingController deckSettingController, MainController mainController, AddCardController addCardController, CardsController cardsController) {
        this.mainFrame = mainFrame;
        this.deckManager = deckManager;

        this.deckSettingController = deckSettingController;
        this.mainController = mainController;
        this.addCardController = addCardController;
        this.cardsController =  cardsController;

    }

    public MouseListener getDeckNameMouseEvent(JButton deckNameButton, Color defaultColor) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                deckNameButton.setForeground(Color.BLUE); // hover 시 파란색으로 변경
                deckNameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손가락 모양 커서
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                deckNameButton.setForeground(defaultColor); // 원래 색으로 복귀
                deckNameButton.setCursor(Cursor.getDefaultCursor());
            }
        };
    }

    public void getDeckNameButtonEvent(Deck deck) {
        mainFrame.setPanel(new CardsPanel(deck, deckManager, mainFrame, cardsController, mainController));
    }

    public void getAddButtonEvent(Deck deck) {
        mainFrame.setPanel(new AddCardPanel(deckManager, mainFrame, deck, addCardController));
    }

    public void getSettingButtonEvent(Deck deck) {
        mainFrame.setPanel(new DeckSettingPanel(mainFrame, deckManager, deck, deckSettingController, mainController));
    }

    public void getDeleteButtonEvent(List<Deck> decks, Deck deck, DeckListPanel deckListPanel) {
        int confirm = JOptionPane.showConfirmDialog(deckListPanel, "선택한 덱을 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            decks.remove(deck);
            try {
                deckManager.removeDeck(deck);
                deckManager.saveDeckList();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(deckListPanel, "삭제 중 오류 발생: " + ex.getMessage());
            }
        }
    };
}
