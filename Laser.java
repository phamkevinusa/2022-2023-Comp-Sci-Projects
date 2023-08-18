import java.awt.Color;
import java.awt.Graphics;

public class Laser {
    int x, y;
    int time;

    public Laser(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.drawLine(412, 500, x, y);
        g.fillArc(x - 15, y - 15, 30, 30, 0, 360);

    }

    public int getTime() {
        return time;

    }

    // public boolean expired(int x) {
    //     if (time - x > 500) {
    //         return true;
    //     }
    //     return false;
    // }
}
