package woowa.myapp.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.DeckManager;

public class MainFrame extends JFrame {

    private JPanel mainContainer;

    public MainFrame(DeckManager deckManager) {

        setTitle("암기빵");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 화면 중앙 배치
        setLayout(new BorderLayout());

        mainContainer = new JPanel(new BorderLayout());
        add(mainContainer, BorderLayout.CENTER);
        setVisible(true);

    }

    public void setPanel(JPanel panel) {
        mainContainer.removeAll();
        mainContainer.add(panel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
    }




}
