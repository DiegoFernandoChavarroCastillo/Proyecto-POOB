package maxwell;

/**
 * Demonio de tipo weak.
 * Solo actúa una vez: después de permitir el paso de una partícula, se elimina.
 */
public class WeakDemon extends Demon {

    private boolean eliminado;
    private MaxwellContainer container;

    /**
     * Crea un demonio débil en una posición dada.
     *
     * @param yPos posición vertical del demonio
     * @param container referencia al contenedor para poder eliminarse
     */
    public WeakDemon(int yPos, MaxwellContainer container) {
        super(yPos);
        this.container = container;
        this.eliminado = false;
        super.setBodyColor("gray");
    }

    /**
     * Interactúa con una partícula cruzando la división. Solo actúa si no ha sido eliminado.
     * Después de dejar pasar o rebotar una partícula, se elimina.
     *
     * @param p partícula que intenta cruzar
     * @param oldX posición anterior en X
     * @param oldY posición anterior en Y
     * @param newX nueva posición en X
     * @param newY nueva posición en Y
     * @return true si actúa y debe detener el movimiento automático
     */
    @Override
    public boolean interactInContainer(Particle p, int oldX, int oldY, int newX, int newY) {
        if (eliminado) return false;

        if (Math.abs(this.getYPosition() - oldY) > 5) {
            p.moveTo(oldX, oldY);
            return false;
        }

        boolean reboto = false;

        if ((p.getVelocityX() > 0 && p.getX() < oldX) || (p.getVelocityX() < 0 && p.getX() > oldX)) {
            p.setVelocityX(-p.getVelocityX());
            reboto = true;
        } else {
            p.moveTo(newX, newY);
        }

        container.removeDemon(this);
        makeInVisible();
        erase();
        eliminado = true;
        return true;
    }
}
