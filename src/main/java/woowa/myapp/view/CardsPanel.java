package woowa.myapp.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import woowa.myapp.controller.CardsController;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;

public class CardsPanel extends JPanel {

    private CardsController cardsController;
    private MainController mainController;

    private MainFrame mainFrame;
    private DeckManager deckManager;

    private List<Card> targetCards;

    private JLabel frontLabel;
    private JLabel backLabel;

    private JButton showAnswerButton;
    private JButton easyButton;
    private JButton againButton;
    private JButton mainButton;

    private JPanel buttonPanel;

    private int currentIndex = 0;

    private final String TARGET_CARD_EMPTY_MESSAGE = "모든 카드를 다 외웠어요!";

    public CardsPanel(Deck deck, DeckManager deckManager, MainFrame mainFrame, CardsController cardsController, MainController mainController) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;

        this.cardsController = cardsController;
        this.mainController = mainController;

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        targetCards = deck.getCards().stream()
                .filter(Card::isTarget)
                .collect(Collectors.toList());

        // 앞면
        frontLabel = new JLabel("", SwingConstants.CENTER);
        frontLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        frontLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 뒷면
        backLabel = new JLabel("", SwingConstants.CENTER);
        backLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        backLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backLabel.setVisible(false);

        // 정답 버튼
        showAnswerButton = new JButton("정답 보기");
        showAnswerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        showAnswerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addShowAnswerButtonEvent(showAnswerButton);

        // 메인 버튼
        mainButton = new JButton("메인으로");
        mainButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        mainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMainButtonEvent(mainButton);

        // 쉬움 버튼
        easyButton = new JButton("쉬움");
        easyButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEasyButtonEvent(easyButton, deck);

        // 다시 버튼
        againButton = new JButton("다시");
        againButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        againButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAgainButtonEvent(againButton);

        // 버튼 패널
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.add(mainButton);
        if(!targetCards.isEmpty()) buttonPanel.add(showAnswerButton);

//        add(createRatioPanel(frontLabel, 3));
//        add(createRatioPanel(backLabel, 5));
//        add(createRatioPanel(buttonPanel, 2));
        c.gridy = 0;
        c.weighty = 3;
        add(frontLabel, c);
        c.gridy = 1;
        c.weighty = 5;
        add(backLabel, c);
        c.gridy = 2;
        c.weighty = 2;
        add(buttonPanel, c);

        // 첫 카드 표시
        showCurrentCard();

    }

    private void onShowAnswer() {
        backLabel.setVisible(true);
        buttonPanel.removeAll();
        buttonPanel.add(easyButton);
        buttonPanel.add(againButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void onAgain() {
        // target 그대로 두고 다음 카드로 넘어감
        nextCard(false);
    }

    private void nextCard(boolean isEasy) {
        // target이 false인 카드 제외하고 리스트 갱신
        targetCards = targetCards.stream()
                .filter(Card::isTarget)
                .collect(Collectors.toList());

        if (targetCards.isEmpty()) {
            frontLabel.setText(TARGET_CARD_EMPTY_MESSAGE);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.NONE;
            c.gridy = 2;
            c.anchor = GridBagConstraints.CENTER;
            add(mainButton, c);

            backLabel.setText("");
            backLabel.setVisible(false);
            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();
            return;
        }

        if(!isEasy) {
            currentIndex = (currentIndex + 1) % targetCards.size();
        }
        else {
            if(currentIndex == targetCards.size()) {
                currentIndex = 0;
            }
        }

        showCurrentCard();
    }

    private void showCurrentCard() {
        if (targetCards.isEmpty()) {
            frontLabel.setText(TARGET_CARD_EMPTY_MESSAGE);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.NONE;
            c.gridy = 1;
            c.anchor = GridBagConstraints.CENTER;
            add(mainButton, c);
            return;
        }

        Card card = targetCards.get(currentIndex);
        frontLabel.setText("<html><div style='text-align:center;'>" + card.getFront() + "</div></html>");
        backLabel.setText("<html><div style='text-align:center;'>" + card.getBack() + "</div></html>");
        backLabel.setVisible(false);

        buttonPanel.removeAll();
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(mainButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    void addShowAnswerButtonEvent(JButton showAnswerButton) {
        showAnswerButton.addActionListener(e -> onShowAnswer());
    }

    void addEasyButtonEvent(JButton easyButton, Deck deck) {
        easyButton.addActionListener(e -> {
            cardsController.getEasyButtonEvent(targetCards, currentIndex, deck);
            nextCard(true);
        });
    }

    void addAgainButtonEvent(JButton againButton) {
        againButton.addActionListener(e -> onAgain());
    }

    void addMainButtonEvent(JButton mainButton) {
        mainButton.addActionListener(e -> {
            mainController.getMainButtonEvent();
        });
    }

}
