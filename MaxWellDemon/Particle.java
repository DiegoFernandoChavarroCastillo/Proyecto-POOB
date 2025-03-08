/**
 * Clase Particle que representa una partícula en la simulación.
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
     * Constructor para objetos de la clase Particle.
     * @param xPos Posición X inicial.
     * @param yPos Posición Y inicial.
     * @param color Color de la partícula.
     * @param speedX Velocidad en X.
     * @param speedY Velocidad en Y.
     */
    public Particle(String color,int xPos, int yPos, boolean isRed, int speedX, int speedY) {
        super();
        changeColor(color);
        this.isRed = isRed;
        super.moveTo(xPos,yPos);
        //moveHorizontal(xPos);
        //moveVertical(yPos);
        this.color = color;
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
    public String getColor(){
    return this.color;
    }
    
    public void setVelocityX(int vx){
    this.speedX = vx;
    }
    public void setVelocityY(int vy){
    this.speedY = vy;
    }
    
    public int getVelocityX(){
    return this.speedX;
    }
    public int getVelocityY(){
    return this.speedY;
    }
    public Circle getCircle() {
    return this;
    }

}
