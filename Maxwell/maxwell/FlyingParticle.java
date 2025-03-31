package maxwell;

/**
 * Representa una partícula voladora en el experimento del demonio de Maxwell.
 * <p>
 * Esta partícula tiene las siguientes características especiales:
 * <ul>
 *   <li>No puede ser absorbida por los agujeros (implementado en la clase Hole)</li>
 *   <li>Se visualiza con un tamaño 50% mayor que las partículas normales</li>
 *   <li>Mantiene todos los demás comportamientos de una partícula normal</li>
 * </ul>
 * 
 * @see Particle
 * @see Hole
 * @see MaxwellContainer
 * @version 1.1
 * @author Diego Chavarro y Diego Rodriguez
 */
public class FlyingParticle extends Particle {

    /**
     * Factor de aumento de tamaño respecto a las partículas normales.
     * Valor 1.5 indica que será un 50% más grande.
     */
    private static final double SIZE_FACTOR = 1.5;

    /**
     * Construye una nueva partícula voladora con las características especificadas.
     * La partícula se crea con un tamaño un 50% mayor que las partículas normales
     * pero con el mismo comportamiento de movimiento y colisiones.
     *
     * @param color El color de la partícula (ej. "red", "blue").
     * @param xPos La posición horizontal inicial (coordenada x).
     * @param yPos La posición vertical inicial (coordenada y).
     * @param isRed Indica si la partícula es de tipo rojo (afecta las reglas del demonio).
     * @param speedX La velocidad inicial en el eje horizontal (píxeles por movimiento).
     * @param speedY La velocidad inicial en el eje vertical (píxeles por movimiento).
     * @throws IllegalArgumentException Si las coordenadas están fuera del contenedor.
     * 
     * @see MaxwellContainer#getWidth() Para el cálculo del tamaño base.
     */
    public FlyingParticle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY) {
        super(color, xPos, yPos, isRed, speedX, speedY);
        
       
        int baseSize = MaxwellContainer.getWidth() / 40; 
        int largerSize = (int)(baseSize * SIZE_FACTOR); 
        setDiameter(largerSize);
    }
    
    /**
     * Implementación específica para partículas voladoras. El movimiento es idéntico
     * al de las partículas normales pero con la particularidad de que los agujeros
     * no las absorberán (implementado en la clase Hole).
     */
    @Override
    public void moveInContainer(int width, int height) {
        super.moveInContainer(width, height);
    }
}