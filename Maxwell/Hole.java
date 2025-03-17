import java.util.Iterator;

/**
 * Representa un agujero en el contenedor que puede absorber partículas hasta una capacidad máxima.
 */
public class Hole {

    private int xPosition;
    private int yPosition;
    private int capMax; 
    private int current;
    private Circle circle;

    /**
     * Constructor de la clase Hole
     * @param xPos Posición X
     * @param yPos Posición Y
     * @param capMax Capacidad máxima de partículas que puede absorber
     */
    public Hole(int xPos, int yPos, int capMax) {
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.capMax = capMax;
        this.current = 0; 
        
        this.circle = new Circle();
        circle.setDiameter(MaxwellContainer.getWidth() / 40);
        circle.moveTo(xPosition, yPosition);
        circle.changeColor("grey");
        circle.makeVisible();
    }

    /**
     * Devuelve la posición X del agujero.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Devuelve la posición Y del agujero.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Devuelve la cantidad de partículas que aún puede absorber.
     */
    public int getRemainingCapacity() {
        return capMax - current;
    }

    /**
     * Devuelve `true` si el agujero aún puede absorber partículas.
     */
    public boolean canAbsorbMore() {
        return current < capMax;
    }

    /**
     * Absorbe una partícula si hay capacidad disponible, al llenarse se cambia de color.
     */
    public void absorbParticle() {
        if (current < capMax) {
            current++;
            if (current >= capMax) {
                circle.changeColor("black");
            }
        }
    }
    
    /**
     * Devuelve la capacidad maxima del agujero negro
     */
    public int getCapacity(){
        return this.capMax;
    }
    
        /**
     * hace visible
     */
    public void makeVisible(){
        circle.makeVisible();
    }
    
    /**
     * hace invisible
     */
    public void makeInvisible(){
        circle.makeInvisible();
    }
    
    /**
     * Absorbe particulas en el contenedor.
     */
    public boolean absorbInContainer(Particle p, Iterator<Particle> iterator) {
        if (Math.abs(this.getXPosition() - p.getX()) <= 10 && Math.abs(this.getYPosition() - p.getY()) <= 10) {
            if (this.canAbsorbMore()) { 
                this.absorbParticle();  
                p.erase();
                iterator.remove(); 
                System.out.println("Partícula absorbida");
                return true; 
            }
        }
        return false;
    }
}
