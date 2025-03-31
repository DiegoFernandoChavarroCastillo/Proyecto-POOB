package maxwell;

/**
 * Representa una partícula efímera en el experimento del demonio de Maxwell.
 * <p>
 * Esta partícula tiene las siguientes características especiales:
 * <ul>
 *   <li>Reduce su velocidad en 1 unidad en cada eje cuando colisiona con los bordes</li>
 *   <li>Se autodestruye cuando su velocidad llega a cero en ambos ejes</li>
 *   <li>Se visualiza más pequeña que las partículas normales</li>
 * </ul>
 * 
 * @see Particle
 * @see MaxwellContainer
 * @version 1.1
 * @author Diego Chavarro y Diego Rodriguez
 */
public class EphemeralParticle extends Particle {

    private MaxwellContainer container;

    /**
     * Construye una nueva partícula efímera con las características especificadas.
     *
     * @param color el color visual de la partícula (ej. "red", "blue")
     * @param xPos la posición horizontal inicial en píxeles
     * @param yPos la posición vertical inicial en píxeles
     * @param isRed indica si la partícula es de tipo rojo (para interacción con el demonio)
     * @param speedX velocidad inicial en el eje horizontal (píxeles por movimiento)
     * @param speedY velocidad inicial en el eje vertical (píxeles por movimiento)
     * @param container referencia al contenedor donde existe la partícula
     * @throws IllegalArgumentException si las coordenadas están fuera del contenedor
     * @throws NullPointerException si el contenedor es nulo
     */
    public EphemeralParticle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY, MaxwellContainer container) {
        super(color, xPos, yPos, isRed, speedX, speedY);
        this.container = container;
        setDiameter(MaxwellContainer.getWidth() / 60);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Comportamiento específico para partículas efímeras:
     * <ul>
     *   <li>Reduce la velocidad en 1 unidad al rebotar en bordes</li>
     *   <li>Se elimina automáticamente al detenerse completamente</li>
     *   <li>Mantiene el rebote normal con la división central</li>
     * </ul>
     * 
     * @param width el ancho del contenedor en píxeles
     * @param height la altura del contenedor en píxeles
     */
    @Override
    public void moveInContainer(int width, int height) {
        int vx = getVelocityX();
        int vy = getVelocityY();
    
        int newX = getX() + vx;
        int newY = getY() + vy;
    
        if (newX < 0 || newX > width) {
            vx = adjustVelocity(vx * -1);
        }
    
        if (newY < 0 || newY > height) {
            vy = adjustVelocity(vy * -1);
        }
    
        int divisionX = (width / 2) - (Math.max(1, width / 80));
        if ((getX() < divisionX && newX >= divisionX) || (getX() > divisionX && newX <= divisionX)) {
            vx = -vx;
            newX = getX();
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

    /**
     * Ajusta la velocidad reduciéndola en 1 unidad hacia cero.
     * 
     * @param v la velocidad actual a ajustar
     * @return la nueva velocidad después del ajuste
     */
    private int adjustVelocity(int v) {
        if (v > 0) return v - 1;
        if (v < 0) return v + 1;
        return 0;
    }
}