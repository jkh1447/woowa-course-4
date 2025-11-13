package woowa.myapp.controller;

import javax.swing.JPanel;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.DeckScreenPanel;
import woowa.myapp.view.MainFrame;

public class MainController {

    private MainFrame mainFrame;
    private DeckManager deckManager;
    private JPanel deckScreenPanel;
    private DeckListPanel deckListPanel;

    private DeckScreenController deckScreenController;
    private DeckListController deckListController;
    private DeckSettingController deckSettingController;
    private AddCardController addCardController;
    private CardsController cardsController;

    public MainController() {
        init();
        mainFrame.setPanel(deckScreenPanel);
    }

    public void init() {
        this.deckManager = new DeckManager();
        this.mainFrame = new MainFrame(deckManager);

        // controller
        this.deckScreenController = new DeckScreenController(deckManager, mainFrame);
        this.deckSettingController = new DeckSettingController(deckManager);
        this.addCardController = new AddCardController(deckManager, this);
        this.cardsController = new CardsController(deckManager);
        this.deckListController = new DeckListController(mainFrame, deckManager, deckSettingController, this,
                addCardController, cardsController);

        // panel
        this.deckListPanel = new DeckListPanel(deckManager, mainFrame, deckListController);
        this.deckScreenPanel = new DeckScreenPanel(mainFrame, deckManager, deckListPanel, deckScreenController);

        // 종료시 lazy loading을 위한 clean up
        //cleanUp();

    }

    public void getMainButtonEvent() {
        mainFrame.setPanel(deckScreenPanel);
    }

    /*public void cleanUp() {
        List<Deck> decks = deckManager.getDecks();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Deck deck: decks) {
                    deck.setIsCardsLoaded(false);
                }
                System.out.println("all cleaned up\n");
            }
        });


    }*/

}
