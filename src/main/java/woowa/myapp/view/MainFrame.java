package woowa.myapp.view;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Deck;

public class MainFrame extends JFrame {
    private DeckManager deckManager;
    private JPanel mainContainer;

    public MainFrame(DeckManager deckManager) {
        this.deckManager = deckManager;

        setTitle("암기빵");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 화면 중앙 배치
        setLayout(new BorderLayout());

        mainContainer = new JPanel(new BorderLayout());
        add(mainContainer, BorderLayout.CENTER);
        setVisible(true);

    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    public void setPanel(JPanel panel) {
        mainContainer.removeAll();
        mainContainer.add(panel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    private void createMainScreen() {
        getContentPane().removeAll();

        add(mainContainer, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void showDeckListPanel() {
        createMainScreen();
    }


}
