import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class TypoCommand extends JPanel {
    public static void main(String[] args) throws InterruptedException {
        JFrame win = new JFrame();
        win.setSize(825, 600);
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setTitle("TypoCommand - Pham");

        TypoCommand typo = new TypoCommand(win);
        win.add(typo);

        win.setVisible(true);

        // game loop
        boolean gameOver = false;
        while (!gameOver) {
            if (Math.random() < typo.getLevel()) {
                typo.addLetter();
            }
            typo.repaint();
            typo.move();
            Thread.sleep(30);
            typo.addTime(30);
            if (typo.getTime() % 5000 == 0) {
                typo.setLevel(typo.getLevel() + .01);
            }
            if (gameOver) {

            }
            gameOver = typo.getGameStatus();
        }
    }

    // instance variables
    private JFrame window;
    private ArrayList<Letter> letters;
    private ArrayList<City> cities;
    private ArrayList<Laser> lasers;
    private ArrayList<Integer> pressed;
    private City city1;
    private City city2;
    private City city3;
    private City city4;
    private City city5;
    private City city6;
    private boolean gameOver;
    private int time;
    private double level;
    private int presses;
    private int misses;

    public TypoCommand(JFrame win) {
        letters = new ArrayList<>();
        cities = new ArrayList<>();
        lasers = new ArrayList<>();
        pressed = new ArrayList<>();
        window = win;
        setSize(window.getWidth(), window.getHeight());
        setBackground(Color.BLUE);

        level = .01;
        gameOver = false;
        // let = new Letter(this);
        city1 = new City(0, 500, this);
        city2 = new City(118, 450, this);
        city3 = new City(235, 475, this);
        city4 = new City(471, 450, this);
        city5 = new City(589, 500, this);
        city6 = new City(707, 475, this);
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        presses = 0;
        misses = 0;
        setFocusable(true);
    }

    public void move() {
        // let.move();
        for (int x = 0; x < letters.size(); x++) {
            letters.get(x).move();
            if (letters.get(x).getBounds().getMinY() > 600.0
                    || letters.get(x).getBounds().intersects(382, 500, 60, 200)) {
                gameOver = true;
            }
            for (int y = 0; y < cities.size(); y++) {
                if (cities.get(y).collision(letters.get(x))) {
                    cities.get(y).hit(letters.get(x).getDamage());
                    letters.remove(x);
                    break;
                }
                if (cities.get(y).getHealth() <= 0) {
                    cities.remove(y);
                }
            }

        }
        for (int x = 0; x < lasers.size(); x++) {
            if (time - lasers.get(x).getTime() > 300) {
                lasers.remove(x);
            }
        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!pressed.contains(e.getKeyCode())) {
                    presses++;
                    boolean hit = false;
                    for (int x = 0; x < letters.size(); x++) {
                        if (Character.toUpperCase(e.getKeyChar()) == letters.get(x).getLetter()) {
                            lasers.add(new Laser(letters.get(x).getX(), letters.get(x).getY(), time));
                            letters.remove(x);
                            hit = true;
                        }
                    }
                    if (!hit) {
                        misses++;
                    }

                }
                pressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (int x = 0; x < pressed.size(); x++) {
                    if (pressed.get(x) == e.getKeyCode()) {
                        pressed.remove(x);
                    }
                }

            }
        });
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double s) {
        level = s;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(382, 500, 60, 200);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (misses > 0) {
            // g.drawString("LVL: " + (int) (level * 100) + "\n Score: " + time + " Accuracy: "
            //         + ((presses - misses) / (double)presses)*100  + "%", 30, 30);

            g.drawString(String.format("LVL: %d Score: %d  Accuracy: %.2f%%", (int)(level * 100), time, ((presses - misses) / (double)presses)*100),30,30);
        } else
            g.drawString("LVL: " + (int) (level * 100) + "\n Score: " + time + " Accuracy: 100%", 30, 30);

        for (int x = 0; x < lasers.size(); x++) {
            lasers.get(x).paint(g);
        }
        for (int x = 0; x < letters.size(); x++) {
            letters.get(x).paint(g);
        }
        for (int x = 0; x < cities.size(); x++) {
            cities.get(x).paint(g);
        }
        if (gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));

            g.drawString("GAME OVER", 300, 300);
        }

    }

    public void laser(int x, int y, Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.drawLine(412, 500, x, y);
        g.fillArc(x, y, 30, 30, 0, 360);
    }

    public int getTime() {
        return time;
    }

    public void addTime(int x) {
        time += x;
    }

    public void addLetter() {
        if (Math.random() < .1)
            letters.add(new BigLetter(this));
        else if (Math.random() < .2)
            letters.add(new ShrinkLetter(this));
        else if (Math.random() < .4)
            letters.add(new FastLetter(this));
        else
            letters.add(new Letter(this));
    }

    public boolean getGameStatus() {
        return gameOver;
    }
}