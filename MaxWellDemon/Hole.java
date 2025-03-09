
/**
 * Write a description of class Hole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole extends Circle
{

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
        this.cap = 0;
        super.setDiameter(MaxwellContainer.getWidth() / 25);
        super.moveTo(xPosition, yPosition);
        super.changeColor("grey");
        this.makeVisible();
    }

}
