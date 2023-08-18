import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FastLetter extends Letter {
    public FastLetter(TypoCommand t){
        super(t);
        super.setSize(30);
        super.setSpeed(5);
    }
	public void paint(Graphics g) {
        if (Math.random() < .05){
            Color s = new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255 );
            g.setColor(s);
        }
        g.setColor(g.getColor());
    	g.setFont(new Font("Arial", Font.BOLD, super.getSize()));
    	g.drawString(super.getLetter() + "", super.getX(), super.getY());
        g.drawRect(super.getX(),super.getY()-super.getSize(), super.getSize(), super.getSize());
	}
}
