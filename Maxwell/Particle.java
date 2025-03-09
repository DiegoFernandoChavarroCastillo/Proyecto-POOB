import java.util.*;
/**
 * La clase Particle representa una partícula en la simulación del contenedor de Maxwell.
 * Cada partícula tiene una posición, velocidad, color y puede moverse dentro del contenedor.
 * 
 * @author Diego
 * @version 1.0
 */
class Particle extends Circle {

    private int speedX;
    private int speedY;
    private String color;
    private boolean isRed;

    /**
     * Constructor de la clase Particle.
     * Inicializa la partícula con una posición, color, velocidad y la hace visible.
     * 
     * @param color El color de la partícula.
     * @param xPos La posición inicial en el eje X.
     * @param yPos La posición inicial en el eje Y.
     * @param isRed Indica si la partícula es roja.
     * @param speedX La velocidad inicial en el eje X.
     * @param speedY La velocidad inicial en el eje Y.
     */
    public Particle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY) {
        super();
        changeColor(color);
        this.isRed = isRed;
        super.moveTo(xPos, yPos);
        this.color = color;
        this.speedX = speedX;
        this.speedY = speedY;
        super.setDiameter(MaxwellContainer.getWidth() / 30);
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
     * 
     * @param newSpeedX La nueva velocidad en el eje X.
     * @param newSpeedY La nueva velocidad en el eje Y.
     */
    public void setSpeed(int newSpeedX, int newSpeedY) {
        this.speedX = newSpeedX;
        this.speedY = newSpeedY;
    }

    /**
     * Devuelve el color de la partícula.
     * 
     * @return El color de la partícula.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Establece la velocidad en el eje X.
     * 
     * @param vx La nueva velocidad en el eje X.
     */
    public void setVelocityX(int vx) {
        this.speedX = vx;
    }

    /**
     * Establece la velocidad en el eje Y.
     * 
     * @param vy La nueva velocidad en el eje Y.
     */
    public void setVelocityY(int vy) {
        this.speedY = vy;
    }

    /**
     * Devuelve la velocidad en el eje X.
     * 
     * @return La velocidad en el eje X.
     */
    public int getVelocityX() {
        return this.speedX;
    }

    /**
     * Devuelve la velocidad en el eje Y.
     * 
     * @return La velocidad en el eje Y.
     */
    public int getVelocityY() {
        return this.speedY;
    }

    /**
     * Devuelve el círculo que representa visualmente la partícula.
     * 
     * @return El círculo de la partícula.
     */
    public Circle getCircle() {
        return this;
    }

    /**
     * Devuelve la posición en el eje X de la partícula.
     * 
     * @return La posición en el eje X.
     */
    public int getXPosition() {
        return super.getX();
    }

    /**
     * Devuelve la posición en el eje Y de la partícula.
     * 
     * @return La posición en el eje Y.
     */
    public int getYPosition() {
        return super.getY();
    }
    
    /**
     * Devuelve el valor de color de la particula.
     * 
     * @return isRed booleano
     */
    public boolean getRed(){
        return isRed;
    }
    
    /**
     * Determina en qué lado del tablero está la partícula.
     * @return `true` si está en la mitad izquierda, `false` si está en la mitad derecha.
     */
    public boolean isOnLeftSide() {
        return getXPosition() < (MaxwellContainer.getWidth() / 2);
    }

}