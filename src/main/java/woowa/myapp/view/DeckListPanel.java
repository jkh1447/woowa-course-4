package woowa.myapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import woowa.myapp.controller.DeckListController;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Deck;

public class DeckListPanel extends JPanel {
    private DeckManager deckManager;
    private MainFrame mainFrame;
    private DeckListController deckListController;

    public DeckListPanel(DeckManager deckManager, MainFrame mainFrame, DeckListController deckListController) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
        this.deckListController = deckListController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        refresh();
    }

    public void refresh() {
        removeAll();

        // 새로 고침할 때마다 새로 받아와야 함.
        List<Deck> decks = deckManager.getDecks();
        for (Deck deck : decks) {

            // 각 덱을 표현할 panel
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BorderLayout());
            rowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rowPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1, true), // 1픽셀, 둥근 모서리
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)          // 내부 여백
            ));
            rowPanel.setMaximumSize(new Dimension(400, 40));
            rowPanel.setPreferredSize(new Dimension(400, 40));

            String name = getDeckPanelName(deck);

            // 덱 이름 버튼
            JButton deckNameButton = new JButton(name);
            deckNameButton.setFocusPainted(false);
            deckNameButton.setContentAreaFilled(false); // 버튼처럼 보이지 않게
            deckNameButton.setBorderPainted(false);
            deckNameButton.setOpaque(false);
            deckNameButton.setPreferredSize(new Dimension(200, 30));
            deckNameButton.setMaximumSize(new Dimension(200, 30));
            deckNameButton.setHorizontalAlignment(SwingConstants.LEFT);

            // 덱 이름 마우스 피드백
            Color defaultColor = deckNameButton.getForeground();
            deckNameMouseEvent(deckNameButton, defaultColor);

            // 덱 이름 버튼 이벤트
            deckNameButtonEvent(deckNameButton, deck);
            rowPanel.add(deckNameButton, BorderLayout.WEST);


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));

            // 추가 버튼
            JButton addButton = new JButton("추가");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButtonEvent(addButton, deck);

            // 설정 버튼
            JButton settingButton = new JButton("설정");
            settingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            settingButtonEvent(settingButton, deck);

            // 삭제버튼
            JButton deleteButton = new JButton("삭제");
            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteButtonEvent(deleteButton, decks, deck, this);

            buttonPanel.add(addButton);
            buttonPanel.add(settingButton);
            buttonPanel.add(deleteButton);

            rowPanel.add(buttonPanel, BorderLayout.EAST);

            add(rowPanel);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }

        revalidate();
        repaint();
    }

    public String getDeckPanelName(Deck deck) {
        String name = deck.getName();
        if (name.length() > 15) {
            name = name.substring(0, 15) + "...";
        }
        return name;
    }

    void deckNameMouseEvent(JButton deckNameButton, Color defaultColor) {
        deckNameButton.addMouseListener(deckListController.getDeckNameMouseEvent(deckNameButton, defaultColor));
    }

    void deckNameButtonEvent(JButton deckNameButton, Deck deck) {
        deckNameButton.addActionListener(e -> deckListController.getDeckNameButtonEvent(deck));
    }

    void addButtonEvent(JButton addButton, Deck deck) {
        addButton.addActionListener(e -> {deckListController.getAddButtonEvent(deck);});
    }

    void settingButtonEvent(JButton settingButton, Deck deck) {
        settingButton.addActionListener(e -> {deckListController.getSettingButtonEvent(deck);});
    }

    void deleteButtonEvent(JButton deleteButton, List<Deck> decks, Deck deck, DeckListPanel deckListPanel) {
        deleteButton.addActionListener(e -> {
            deckListController.getDeleteButtonEvent(decks, deck, deckListPanel);
            refresh();
        });
    }
}
