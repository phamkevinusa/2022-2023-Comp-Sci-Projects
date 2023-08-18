import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
public class BigLetter extends Letter {
    public BigLetter(TypoCommand t){
        super(t);
        super.setColor(Color.red);
        super.setSpeed(1);
        super.setDamage(2);
        super.setSize(50);
    }

}
