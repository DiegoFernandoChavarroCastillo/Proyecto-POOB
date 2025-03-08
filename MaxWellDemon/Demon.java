
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
    private Triangle ojo;

    /**
     * Constructor for objects of class Demon
     */
    public Demon(int yPosition)
    {
        this.yPosition = yPosition;
        cuerpo = new Triangle(MaxwellContainer.getHeight()/20, MaxwellContainer.getWidth()/20, MaxwellContainer.getWidth()/2-(Math.max(1, MaxwellContainer.getWidth() / 100)), yPosition, "green");
        cuerpo.makeVisible();
        ojo = new Triangle(MaxwellContainer.getHeight()/40, MaxwellContainer.getWidth()/40, MaxwellContainer.getWidth()/2-(Math.max(1, MaxwellContainer.getWidth() / 100)), yPosition+(MaxwellContainer.getHeight()/60), "magenta");
        ojo.makeVisible();
    }

    public void makeVisible(){
        cuerpo.makeVisible();
        ojo.makeVisible();
    }
    
    public void erase(){
        cuerpo.erase();
        ojo.erase();
    }
    public int getYPosition(){
    return this.yPosition;
    }
}
