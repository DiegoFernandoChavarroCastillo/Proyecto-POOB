
/**
 * Write a description of class Demon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Demon
{
    private int yPosition;
    private Triangle cuerpo;

    /**
     * Constructor for objects of class Demon
     */
    public Demon(int yPosition)
    {
        this.yPosition = yPosition;
        cuerpo = new Triangle(MaxwellContainer.getHeight()/20, MaxwellContainer.getWidth()/20, MaxwellContainer.getWidth()/2+ MaxwellContainer.getWidth()/100, yPosition);
        cuerpo.makeVisible();
    }

    public void makeVisible(){
        cuerpo.makeVisible();
    }
    
    public void erase(){
        cuerpo.erase();
    }
}
