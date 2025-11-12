package woowa.myapp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import woowa.myapp.controller.DeckScreenController;

public class AddDeckDialog extends JDialog {

    private JTextField deckNameField;
    private String deckName = null;

    private DeckScreenController deckScreenController;

    public AddDeckDialog(JFrame parent, DeckScreenController deckScreenController) {
        super(parent, "새 덱 추가", true);

        this.deckScreenController = deckScreenController;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        deckNameField = new JTextField(40);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 5, 10);
        add(new JLabel("덱 이름: "), c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 10, 10);
        add(deckNameField, c);

        JPanel buttonPanel = new JPanel();

        // 확인 버튼
        JButton okButton = new JButton("확인");
        addOkButtonEvent(okButton);

        // 취소 버튼
        JButton cancelButton = new JButton("취소");
        addCancelButtonEvent(cancelButton);

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        c.gridx = 0;
        c.gridy = 2;
        add(buttonPanel, c);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getName() {
        return deckName;
    }

    void addOkButtonEvent(JButton okButton) {
        okButton.addActionListener(e -> {
            String name = deckNameField.getText().trim();
            deckScreenController.getOkButtonEvent(name, this);
        });
    }

    void addCancelButtonEvent(JButton cancelButton) {
        cancelButton.addActionListener(e -> {
            deckName=null;
            dispose();
        });
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
