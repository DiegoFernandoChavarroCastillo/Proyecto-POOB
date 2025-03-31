package maxwell;

import java.util.Random;

/**
 * Partícula que cambia de color en cada movimiento.
 */
public class ColorChangingParticle extends Particle {

    private static final String[] COLORS = {
        "red", "blue", "green", "yellow", "magenta", "black", "cyan", "orange"
    };

    private Random random;

    /**
     * Crea una partícula que cambia de color en cada movimiento.
     *
     * @param color color inicial (será reemplazado al moverse)
     * @param xPos posición inicial en X
     * @param yPos posición inicial en Y
     * @param isRed indica si es roja (relevante para demonios)
     * @param speedX velocidad en X
     * @param speedY velocidad en Y
     */
    public ColorChangingParticle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY) {
        super(color, xPos, yPos, isRed, speedX, speedY);
        this.random = new Random();
    }

    @Override
    public void moveInContainer(int width, int height) {
    
        String newColor = COLORS[random.nextInt(COLORS.length)];
        super.changeColor(newColor);
        
        super.moveInContainer(width, height);
    }
}
