package maxwell;

/**
 * Demonio de tipo blue.
 * Solo permite el paso de partículas azules. Las partículas rojas son detenidas y devueltas.
 */
public class BlueDemon extends Demon {

    /**
     * Crea un demonio azul en una posición dada.
     *
     * @param xPos posición horizontal del demonio
     */
    public BlueDemon(int yPos) {
        super(yPos);
        super.setBodyColor("magenta");
    }

    /**
     * Interactúa con una partícula que intenta cruzar la división.
     * Solo deja pasar partículas azules.
     *
     * @param p partícula que intenta cruzar
     * @param oldX posición anterior en X
     * @param oldY posición anterior en Y
     * @param newX nueva posición en X
     * @param newY nueva posición en Y
     * @return true si el demonio actúa, false si no
     */
    @Override
    public boolean interactInContainer(Particle p, int oldX, int oldY, int newX, int newY) {
        if (Math.abs(this.getYPosition() - oldY) > 5) {
            p.moveTo(oldX, oldY);
            return false;
        }
    
        if (p.getRed()) {
            p.setVelocityX(-p.getVelocityX());
            return true;
        }
        
        p.moveTo(newX, newY);
        return true; 
    }


}
