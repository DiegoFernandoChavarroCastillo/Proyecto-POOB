
/**
 * Write a description of class Hole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole extends Circle
{
    // instance variables - replace the example below with your own
    private int xPosition;
    private int yPosition;
    private int capMax;
    private int cap;

    /**
     * Constructor for objects of class Hole
     */
    public Hole(int xPos, int yPos, int capMax)
    {
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.capMax = capMax;
        super.setDiameter(MaxwellContainer.getWidth()/25);
        changeColor("grey");
        this.makeVisible();
    }

}
