/**
 * Representa un agujero en el contenedor que puede absorber partículas hasta una capacidad máxima.
 */
public class Hole extends Circle {

    private int xPosition;
    private int yPosition;
    private int capMax; 
    private int current;

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
        super.setDiameter(MaxwellContainer.getWidth() / 25);
        super.moveTo(xPosition, yPosition);
        super.changeColor("grey");
        this.makeVisible();
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
                super.changeColor("black");
            }
        }
    }
    
    /**
     * Devuelve la capacidad maxima del agujero negro
     */
    public int getCapacity(){
        return this.capMax;
    }
}
