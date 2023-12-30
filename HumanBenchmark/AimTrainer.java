import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class AimTrainer extends JPanel {

    // instance variables
    private JFrame window;
    private boolean gameOver;
    private int time;
    private int targetsLeft;
    private int targetX, targetY;
    private int targetSize;
    private PointerInfo a;
    private JButton button1;
    private JPanel mainPanel;

    public AimTrainer(JFrame win, JPanel panel) {
        targetsLeft = 30;
        time = 0;
        window = win;
        mainPanel = panel;
        setSize(window.getWidth(), window.getHeight());
        setBackground(Color.BLUE);
        targetX = (int) (Math.random() * (window.getWidth() - targetSize));
        targetY = (int) (Math.random() * (window.getHeight() - targetSize));
        targetSize = 30;
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
        a = MouseInfo.getPointerInfo();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (onTarget()) {
                    targetsLeft--;
                }
                repaint();
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        gameOver = false;
        // let = new Letter(this);

        setFocusable(true);
    }

    public void move() {
        // let.move();

    }

    public void paint(Graphics g) {

        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.red);
        String sf1 = String.format("Time: %d Targets Left: %d Average Time: %.2f", time, targetsLeft, (time / 1000.0) / (30 - targetsLeft));

        g.drawString(
                sf1,30, 30);

        if (targetsLeft > 0) {
            time += 30;
            g.fillRect(targetX, targetY, targetSize, targetSize);
        }

        else {
            add(button1);
        }

    }

    public int getTime() {
        return time;
    }

    public boolean onTarget() {
        a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX() - window.getLocation().x;
        int y = (int) b.getY() - window.getLocation().y - 32;
        if (x >= targetX && x <= targetX + targetSize && y >= targetY && y <= targetY
                + targetSize) {
            targetX = (int) (Math.random() * (window.getWidth() - targetSize * 2));
            targetY = (int) (Math.random() * (window.getHeight() - targetSize * 2));
            return true;
        }
        return false;
    }

    public boolean getGameStatus() {
        return gameOver;
    }
}