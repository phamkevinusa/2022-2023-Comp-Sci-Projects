import java.awt.Color;
public class BigLetter extends Letter {
    public BigLetter(TypoCommand t){
        super(t);
        super.setColor(Color.red);
        super.setSpeed(1);
        super.setDamage(2);
        super.setSize(50);
    }

}
