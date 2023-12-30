import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sequence extends JPanel {
    private ArrayList<Integer> sequence;
    private JFrame window;
    private JPanel mainPanel;
    private int time, displayLevel, gameTime, playLevel;
    private boolean gameOver, display;
    private Point a;
    private ArrayList<Rectangle> boxes;
    private JButton button1;

    public Sequence(JFrame win, JPanel panel) {
        sequence = new ArrayList<>();
        window = win;
        mainPanel = panel;
        boxes = new ArrayList<>();
        setSize(window.getWidth(), window.getHeight());
        setBackground(Color.BLUE);
        gameOver = false;
        time = 0;
        displayLevel = 0;
        display = true;
        playLevel = 0;
        button1 = new JButton("Exit");
        button1.setBounds(100, 100, 300, 100);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu(window, mainPanel);
                mainPanel.removeAll();
                mainPanel.add(menu);
            }

        });
        for (int x = 200; x <= 320; x += 60) {
            for (int y = 200; y <= 320; y += 60) {
                boxes.add(new Rectangle(x, y, 60, 60));
            }
        }
        sequence.add((int) (Math.random() * 9));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                a = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(a, panel);
                if (hit(a, playLevel)) {
                    playLevel++;
                    gameTime = time;
                } else {
                    gameOver = true;
                }
                if (playLevel == sequence.size()) {

                    playLevel = 0;
                    sequence.add((int) (Math.random() * 9));
                    while (sequence.get(sequence.size() - 1) == (sequence.get(sequence.size() - 2))) {
                        sequence.set(sequence.size() - 1, (int) (Math.random() * 9));
                    }
                    display = true;
                }
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        setVisible(true);
    }

    public boolean hit(Point p, int l) {
        if (boxes.get(sequence.get(l)).contains(p)) {
            return true;
        } else
            return false;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.red);
        g.drawString("Level: " + sequence.size(), 30, 30);
        g.setColor(Color.white);
        if (gameOver) {
            g.drawString("GAME OVER.", 400, 400);

        }
        if (!gameOver) {

            for (int x = 200; x <= 320; x += 60) {
                for (int y = 200; y <= 320; y += 60) {
                    g.drawRoundRect(x, y, 60, 60, 20, 20);
                }
            }

            time += 30;
            if (time - gameTime >= 1000) {

                gameTime = time;
                if (displayLevel == sequence.size() - 1) {
                    displayLevel = 0;
                    display = false;
                }
                if (display) {
                    displayLevel++;

                }
            }
            if (display) {

                g.setColor(Color.white);
                g.fillRoundRect((int) boxes.get(sequence.get(displayLevel)).getX(),
                        (int) boxes.get(sequence.get(displayLevel)).getY(),
                        60, 60, 20, 20);
            }

        }
        if (gameOver) {
            g.drawString("GAMEOVER.", 400, 400);
            add(button1);
        }

    }
}
