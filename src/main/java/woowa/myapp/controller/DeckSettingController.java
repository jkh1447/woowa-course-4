package woowa.myapp.controller;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckSettingPanel;

public class DeckSettingController {
    private DeckManager deckManager;

    public DeckSettingController(DeckManager deckManager) {
        this.deckManager = deckManager;
    }

    public void getSaveButtonEvent(Deck deck, List<JCheckBox> checkBoxes, DeckSettingPanel deckSettingPanel) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            deck.getCards().get(i).setTarget(checkBoxes.get(i).isSelected());
        }

        try {
            deckManager.saveToFile();
            JOptionPane.showMessageDialog(deckSettingPanel, "설정이 저장되었습니다!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(deckSettingPanel, "저장 중 오류 발생: " + ex.getMessage());
        }
    }

    public void getDeleteButtonEvent(List<JCheckBox> deleteBoxes, DeckSettingPanel deckSettingPanel, Deck deck) {
        int confirm = JOptionPane.showConfirmDialog(deckSettingPanel, "선택한 카드를 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for(int i=deleteBoxes.size()-1; i>=0; i--) {
                if(deleteBoxes.get(i).isSelected()) {
                    deck.getCards().remove(i);
                }
            }
            try {
                deckManager.saveToFile();
                JOptionPane.showMessageDialog(deckSettingPanel, "삭제 완료!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(deckSettingPanel, "삭제 중 오류 발생: " + ex.getMessage());
            }
        }
    }

    public void getDeleteAllCheckBoxEvent(List<JCheckBox> deleteBoxes, JCheckBox deleteAllCheckBox) {
        boolean selected = deleteAllCheckBox.isSelected();
        for (JCheckBox box : deleteBoxes) {
            box.setSelected(selected);
        }
    }

    public void getTargetAllCheckBoxEvent(List<JCheckBox> checkBoxes, JCheckBox targetAllCheckBox) {
        boolean selected = targetAllCheckBox.isSelected();
        for (JCheckBox box : checkBoxes) {
            box.setSelected(selected);
        }
    }
}
