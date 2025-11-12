package woowa.myapp.view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import woowa.myapp.controller.DeckScreenController;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;

public class DeckScreenPanel extends JPanel {
    private DeckManager deckManager;
    private JButton addDeckButton;
    private DeckListPanel deckListPanel;
    private MainFrame mainFrame;
    private DeckScreenController deckScreenController;

    public DeckScreenPanel(MainFrame mainFrame, DeckManager deckManager, DeckListPanel deckListPanel, DeckScreenController deckScreenController) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
        this.deckListPanel = deckListPanel;
        this.deckScreenController = deckScreenController;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        wrapper.add(deckListPanel);

        JScrollPane scrollPane = new JScrollPane(wrapper);
        add(scrollPane, BorderLayout.CENTER);

        addDeckButton = new JButton("덱 추가");
        addDeckButtonEvent(addDeckButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addDeckButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    void addDeckButtonEvent(JButton addDeckButton) {
        addDeckButton.addActionListener(e -> {
            deckScreenController.getAddDeckButtonEvent(deckManager, mainFrame, deckListPanel);
        });
    }
}
