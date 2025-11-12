package woowa.myapp.controller;

import java.awt.event.ActionEvent;
import java.util.List;
import woowa.myapp.model.Card;
import woowa.myapp.model.DeckManager;

public class CardsController {
    private DeckManager deckManager;

    public CardsController(DeckManager deckManager) {
        this.deckManager = deckManager;
    }

    public void getEasyButtonEvent(List<Card> targetCards, int currentIndex) {
        if (targetCards.isEmpty()) return;
        Card currentCard = targetCards.get(currentIndex);
        currentCard.setTarget(false); // 쉬움이면 다시 안나오게
        deckManager.saveToFile();
    }

}
