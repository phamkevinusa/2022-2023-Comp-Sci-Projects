import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ShrinkLetter extends Letter {
    double size;

    public ShrinkLetter(TypoCommand t) {
        super(t);
        size = super.getSize();
    }

    public void paint(Graphics g) {

        if (super.getSize() > 10) {
            super.setSize((int) size);
            size -= .3;
        }
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.BOLD, super.getSize()));
        g.drawString(super.getLetter() + "", super.getX(), super.getY());
        g.drawRect(super.getX(), super.getY() - super.getSize(), super.getSize(), super.getSize());
    }
}
