package maxwell;

/**
 * Partícula de tipo ephemeral.
 * En cada colisión con los bordes del contenedor pierde 1 unidad de velocidad en cada dirección.
 * Si su velocidad llega a 0 en ambos ejes, se borra visualmente y se elimina del contenedor.
 */
public class EphemeralParticle extends Particle {

    private MaxwellContainer container;

    /**
     * Crea una nueva partícula ephemeral.
     *
     * @param color color de la partícula
     * @param xPos posición inicial en X
     * @param yPos posición inicial en Y
     * @param isRed indica si la partícula es roja
     * @param speedX velocidad inicial en X
     * @param speedY velocidad inicial en Y
     * @param container contenedor al que pertenece
     */
    public EphemeralParticle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY, MaxwellContainer container) {
        super(color, xPos, yPos, isRed, speedX, speedY);
        this.container = container;
        setDiameter(MaxwellContainer.getWidth() / 60); // más pequeñas que las normales
    }


    @Override
    public void moveInContainer(int width, int height) {
        int vx = getVelocityX();
        int vy = getVelocityY();
    
        int newX = getX() + vx;
        int newY = getY() + vy;
    
        // Rebote horizontal (izquierda/derecha)
        if (newX < 0 || newX > width) {
            vx = adjustVelocity(vx * -1); // rebota y reduce
        }
    
        // Rebote vertical (techo/suelo)
        if (newY < 0 || newY > height) {
            vy = adjustVelocity(vy * -1); // rebota y reduce
        }
    
        // Rebote con división central
        int divisionX = (width / 2) - (Math.max(1, width / 80));
        if ((getX() < divisionX && newX >= divisionX) || (getX() > divisionX && newX <= divisionX)) {
            vx = -vx;
            newX = getX(); // no cruza
        }
    
        setVelocityX(vx);
        setVelocityY(vy);
    
        moveTo(getX() + vx, getY() + vy);
    
        if (vx == 0 && vy == 0) {
            makeInvisible();
            erase();
            container.removeParticle(this);
        }
    }


    private int adjustVelocity(int v) {
        if (v > 0) return v - 1;
        if (v < 0) return v + 1;
        return 0;
    }
}
