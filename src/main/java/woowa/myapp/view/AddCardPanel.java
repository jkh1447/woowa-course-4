package woowa.myapp.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import woowa.myapp.controller.AddCardController;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Deck;

public class AddCardPanel extends JPanel {
    private Deck deck;
    private DeckManager deckManager;
    private MainFrame mainFrame;

    private AddCardController addCardController;

    public AddCardPanel(DeckManager deckManager, MainFrame mainFrame, Deck deck, AddCardController addCardController) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
        this.deck = deck;

        this.addCardController = addCardController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(deck.getName() + " 덱에 카드 추가");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        // 앞면
        JTextArea frontField = new JTextArea(10, 20); // 10줄, 20열
        frontField.setLineWrap(true); // 자동 줄바꿈
        frontField.setWrapStyleWord(true); // 단어 단위 줄바꿈
        frontField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane frontScroll = new JScrollPane(frontField);
        frontScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        frontScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        frontScroll.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        frontScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 뒷면
        JTextArea backField = new JTextArea(10, 20); // 10줄, 20열
        backField.setLineWrap(true); // 자동 줄바꿈
        backField.setWrapStyleWord(true); // 단어 단위 줄바꿈
        backField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane backScroll = new JScrollPane(backField);
        backScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        backScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 490));
        backScroll.setPreferredSize(new Dimension(Integer.MAX_VALUE, 490));
        backScroll.setAlignmentX(Component.LEFT_ALIGNMENT);


        add(new JLabel("앞면:") {{ setAlignmentX(Component.LEFT_ALIGNMENT); }});
        add(frontScroll);
        add(Box.createRigidArea(new Dimension(0, 5)));

        add(new JLabel("뒷면:") {{ setAlignmentX(Component.LEFT_ALIGNMENT); }});
        add(backScroll);

        // 저장 버튼
        JButton saveButton = new JButton("저장");
        addSaveButtonEvent(saveButton, frontField, backField);

        // 완료 버튼
        JButton doneButton = new JButton("완료");
        addDoneButtonEvent(doneButton);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(saveButton);
        buttonPanel.add(doneButton);

        add(buttonPanel);

    }

    void addSaveButtonEvent(JButton saveButton, JTextArea frontField, JTextArea backField) {
        saveButton.addActionListener(e -> {
            addCardController.getAddSaveButtonEvent(deck, frontField, backField, this);
        });
    }

    void addDoneButtonEvent(JButton doneButton) {
        doneButton.addActionListener(e -> {
           addCardController.getDoneButtonEvent();
        });
    }
}
