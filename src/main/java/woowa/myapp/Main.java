package woowa.myapp;


import javax.swing.SwingUtilities;
import woowa.myapp.controller.MainController;
import woowa.myapp.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainController();
        });
    }
}