import java.util.*;
/**
 * La clase Particle representa una partícula en la simulación del contenedor de Maxwell.
 * Cada partícula tiene una posición, velocidad, color y puede moverse dentro del contenedor.
 * 
 * @author Diego
 * @version 1.0
 */
class Particle {

    private int speedX;
    private int speedY;
    private String color;
    private boolean isRed;
    private Circle circle;

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
        this.circle = new Circle();
        circle.changeColor(color);
        circle.moveTo(xPos, yPos);
        circle.setDiameter(MaxwellContainer.getWidth() / 40);
        circle.makeVisible();

        this.color = color;
        this.speedX = speedX;
        this.speedY = speedY;
        this.isRed = isRed;
    }

    /**
     * Mueve la partícula automáticamente según su velocidad.
     */
    public void move() {
        circle.moveHorizontal(speedX);
        circle.moveVertical(speedY);
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
        return this.circle;
    }

    /**
     * Devuelve la posición en el eje X de la partícula.
     * 
     * @return La posición en el eje X.
     */
    public int getXPosition() {
        return circle.getX();
    }

    /**
     * Devuelve la posición en el eje Y de la partícula.
     * 
     * @return La posición en el eje Y.
     */
    public int getYPosition() {
        return circle.getY();
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
    
    public void erase(){
        circle.erase();
    }
    
    public void makeVisible(){
        circle.makeVisible();
    }
    
    public void makeInvisible(){
        circle.makeInvisible();
    }
    
    public int getX(){
    return circle.getX();
    }
    
    public int getY(){
    return circle.getY();
    }
    
    public void moveTo(int newX,int newY){
        circle.moveTo(newX,newY);
    }

}