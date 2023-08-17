import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private JFrame window;
    private JButton button1, button2;
    private JPanel mainPanel;

    public Menu(JFrame win, JPanel panel) {
        window = win;
        mainPanel = panel;
        setBackground(Color.yellow);
        setSize(window.getWidth(), window.getHeight());
        setLayout(null);
        button1 = new JButton("Aim Trainer");
        button2 = new JButton("Sequence Memory");
        button1.setBounds(100, 100, 300, 100);
        button2.setBounds(100, 300, 300, 100);
        add(button1);

        add(button2);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AimTrainer aimTrainer = new AimTrainer(window, mainPanel);
                mainPanel.removeAll();
                mainPanel.add(aimTrainer);
            }

        });
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Sequence sequence = new Sequence(window, mainPanel);
                mainPanel.removeAll();
                mainPanel.add(sequence);

            }

        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("HUMAN BENCHMARK", 110, 50);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Click the targets as quickly as you can. ", 100, 250);
        g.drawString("Your average time per target will be displayed.", 100, 290);
        g.drawString("Memorize the sequence of buttons that light up, then press them in order.", 100, 500);
        g.drawString("Every time you finish the pattern, it gets longer.", 100, 540);
        g.drawString("Make a mistake and the test is over.", 100, 580);

    }

}