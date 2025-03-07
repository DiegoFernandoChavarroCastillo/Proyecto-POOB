/**
 * Clase Particle que representa una partícula en la simulación.
 * 
 * @author Diego
 * @version 1.0
 */
class Particle extends Circle {
    private int speedX;
    private int speedY;

    /**
     * Constructor para objetos de la clase Particle.
     * @param xPos Posición X inicial.
     * @param yPos Posición Y inicial.
     * @param color Color de la partícula.
     * @param speedX Velocidad en X.
     * @param speedY Velocidad en Y.
     */
    public Particle(int xPos, int yPos, boolean blue, int speedX, int speedY) {
        super();
        if (blue){
            changeColor("blue");
        } else {
            changeColor("red");
        }
        moveHorizontal(xPos);
        moveVertical(yPos);
        this.speedX = speedX;
        this.speedY = speedY;
        super.setDiameter(MaxwellContainer.getWidth()/30);
        this.makeVisible();
    }

    
    /**
     * Mueve la partícula automáticamente según su velocidad.
     */
    public void move() {
        moveHorizontal(speedX);
        moveVertical(speedY);
    }

    /**
     * Cambia la velocidad de la partícula.
     * @param newSpeedX Nueva velocidad en X.
     * @param newSpeedY Nueva velocidad en Y.
     */
    public void setSpeed(int newSpeedX, int newSpeedY) {
        this.speedX = newSpeedX;
        this.speedY = newSpeedY;
    }
}
