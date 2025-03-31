package maxwell;

/**
 * Agujero negro que se mueve en el contenedor con velocidad constante (1,1),
 * rebotando en los bordes como las partículas.
 */
public class MovingHole extends Hole {

    private int speedX = 1;
    private int speedY = 1;

    /**
     * Crea un agujero móvil con una posición inicial y capacidad de absorción.
     *
     * @param xPos posición inicial en X
     * @param yPos posición inicial en Y
     * @param capMax capacidad máxima de partículas que puede absorber
     */
    public MovingHole(int xPos, int yPos, int capMax) {
        super(xPos, yPos, capMax);
    }

    /**
     * Mueve el agujero dentro del contenedor, rebotando en los bordes.
     *
     * @param width ancho del contenedor
     * @param height alto del contenedor
     */
    public void moveInContainer(int width, int height) {
        int newX = getXPosition() + speedX;
        int newY = getYPosition() + speedY;

        if (newX < 10 || newX > width - 10) {
            speedX = -speedX;
            newX = Math.max(10, Math.min(newX, width - 10));
        }
        if (newY < 10 || newY > height - 10) {
            speedY = -speedY;
            newY = Math.max(10, Math.min(newY, height - 10));
        }

        super.moveTo(newX, newY);
    }
}
