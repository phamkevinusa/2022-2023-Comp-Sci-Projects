import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Letter {
	// super class for all other Letters
	private TypoCommand typo; // allows for communication with panel and object on the panel

	private int x, y;
	private int letterSize;
	private int speed;
	private String letter;
	private boolean alive, hit;
	private Color color;
    private int damage;
    private int shrink;
	public Letter( TypoCommand t) {
        damage = 1;
    	typo = t;
        speed = 2;
    	letterSize  = 50;
    	color = Color.BLACK;
        shrink = 0;
    	y = 50;
    	x = (int)(Math.random() * 775);
    	letter = (char)((int)(Math.random() * 26) + 65) + ""; // this gets random letter
	}
    public char getLetter(){
        return letter.charAt(0);
    }
	public void paint(Graphics g) {
        letterSize-= shrink;
    	g.setColor(color);
    	g.setFont(new Font("Arial", Font.BOLD, letterSize));
    	g.drawString(letter, x, y);
        g.drawRect(x,y-letterSize, letterSize, letterSize);
	}
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSize(){
        return letterSize;
    }
    public int getDamage(){
        return damage;
    }
    public void setDamage(int d){
        damage = d;
    }
	public void move() {
    		y += speed;
	}
    public void setSpeed(int s){
        speed = s;
    }
    public void setSize(int s){
        letterSize = s;
    }
    public void setColor(Color c){
        color = c;
    }
    public void setShrink(int x){
        shrink = x;
    }

	public Rectangle getBounds(){
		return new Rectangle(x,y-letterSize, letterSize, letterSize);
	}

}