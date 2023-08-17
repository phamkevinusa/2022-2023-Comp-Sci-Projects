import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        JFrame win = new JFrame();
        CardLayout cl = new CardLayout();
        JPanel mainPanel = new JPanel(cl);
        win.setSize(825, 600);
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setTitle("Human Benchmark");
        win.add(mainPanel);
        Menu menu = new Menu(win, mainPanel);
        mainPanel.add(menu);

        menu.setVisible(true);
        mainPanel.setVisible(true);

        win.setVisible(true);
        menu.repaint();

        while (true) {
            Thread.sleep(30);
            mainPanel.getComponent(0).repaint();
        }
    }
}
