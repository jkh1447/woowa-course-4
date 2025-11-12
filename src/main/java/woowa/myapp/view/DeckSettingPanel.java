package woowa.myapp.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import woowa.myapp.controller.DeckSettingController;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;

public class DeckSettingPanel extends JPanel {
    private DeckManager deckManager;
    private Deck deck;

    private List<JCheckBox> checkBoxes;
    private List<JCheckBox> deleteBoxes;

    private MainFrame mainFrame;
    private JPanel listPanel;

    private DeckSettingController deckSettingController;
    private MainController mainController;

    public DeckSettingPanel(MainFrame mainFrame, DeckManager deckManager, Deck deck, DeckSettingController deckSettingController, MainController mainController) {
        this.deckManager = deckManager;
        this.deck = deck;

        this.checkBoxes = new ArrayList<>();
        this.deleteBoxes = new ArrayList<>();

        this.mainFrame = mainFrame;

        this.deckSettingController = deckSettingController;
        this.mainController = mainController;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 패널
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,  10, 5));

        // 저장 버튼
        JButton saveButton = new JButton("저장");
        addSaveButtonEvent(saveButton);

        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        addDeleteButtonEvent(deleteButton);

        // 메인 버튼
        JButton mainButton = new JButton("메인으로");
        addMainButtonEvent(mainButton);

        topPanel.add(saveButton);
        topPanel.add(deleteButton);
        topPanel.add(mainButton);
        add(topPanel, BorderLayout.NORTH);

        // 카드 목록 패널
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // 헤더 패널
        JPanel headerPanel = new JPanel(new BorderLayout());

        // 삭제 체크박스
        JCheckBox deleteAllCheck = new JCheckBox("삭제");
        addDeleteAllCheckBoxEvent(deleteAllCheck);

        // 공부할 내용 체크박스
        JCheckBox targetAllCheck = new JCheckBox("공부할 내용");
        addTargetAllCheckBoxEvent(targetAllCheck);

        headerPanel.add(deleteAllCheck, BorderLayout.WEST);
        headerPanel.add(targetAllCheck, BorderLayout.EAST);

        // 카드 목록 리스트
        refreshList();

        // 카드 목록 패널을 스크롤 패널에 감쌈
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(listPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(headerPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

    }

    private void refreshList() {
        listPanel.removeAll();
        checkBoxes.clear();
        deleteBoxes.clear();

        for (Card card : deck.getCards()) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // 왼쪽: 삭제 체크박스
            JCheckBox deleteCheck = new JCheckBox();
            deleteBoxes.add(deleteCheck);
            cardPanel.add(deleteCheck, BorderLayout.WEST);

            // 가운데: 카드 내용
            JLabel cardLabel = new JLabel(card.getFront());
            cardPanel.add(cardLabel, BorderLayout.CENTER);

            // 오른쪽: target 체크박스
            JCheckBox targetCheck = new JCheckBox("", card.isTarget());
            checkBoxes.add(targetCheck);
            cardPanel.add(targetCheck, BorderLayout.EAST);

            listPanel.add(cardPanel);
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    void addSaveButtonEvent(JButton saveButton) {
        saveButton.addActionListener(e -> {deckSettingController.getSaveButtonEvent(deck, checkBoxes, this);});
    }

    void addDeleteButtonEvent(JButton deleteButton) {
        deleteButton.addActionListener(e -> {
            deckSettingController.getDeleteButtonEvent(deleteBoxes, this, deck);
            refreshList();
        });

    }

    void addMainButtonEvent(JButton mainButton) {
        mainButton.addActionListener(e -> {mainController.getMainButtonEvent();});
    }

    void addDeleteAllCheckBoxEvent(JCheckBox deleteAllCheckBox) {
        deleteAllCheckBox.addActionListener(e -> {deckSettingController.getDeleteAllCheckBoxEvent(deleteBoxes, deleteAllCheckBox);});
    }

    void addTargetAllCheckBoxEvent(JCheckBox targetAllCheckBox) {
        targetAllCheckBox.addActionListener(e -> {deckSettingController.getTargetAllCheckBoxEvent(checkBoxes, targetAllCheckBox);});
    }

}
