import java.awt.*;

public class City {
    private TypoCommand typo;
    private int health;
    private int size; // width and height
    private Color color;
    private int x, y;

    public City(int x, int y, TypoCommand typo) {
        this.x = x;
        this.y = y;
        this.typo = typo;
        health = 5;
        color = Color.green;
        size = 118;
    } 
    public void hit(int x){
        health-=x;
        color = color.darker().darker();
    }
    public int getHealth(){
        return health;
    }
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
    public Rectangle getBounds(){
		return new Rectangle(x,y, size, size);
	}
    public Boolean collision(Letter letter){
        return this.getBounds().intersects(letter.getBounds());
    }
}