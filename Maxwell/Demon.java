import java.util.*;
/**
 * La clase Demon representa un demonio en el contenedor de Maxwell.
 * El demonio tiene una posición en el eje Y y está representado visualmente por dos triángulos:
 * uno para el cuerpo y otro para el ojo.
 * 
 * @author Diego chavarro, Diego Rodriguez
 * @version 08/03/25
 */
public class Demon {
    private int yPosition;
    private Triangle body;
    private Triangle eye;

    /**
     * Constructor de la clase Demon.
     * Inicializa el demonio en la posición especificada y lo hace visible.
     * 
     * @param yPosition La posición en el eje Y donde se ubicará el demonio.
     */
    public Demon(int yPosition) {
        this.yPosition = yPosition;
        body = new Triangle(MaxwellContainer.getHeight() / 20, MaxwellContainer.getWidth() / 20, 
                             MaxwellContainer.getWidth() / 2 - (Math.max(1, MaxwellContainer.getWidth() / 100)), 
                             yPosition, "green");
        body.makeVisible();
        eye = new Triangle(MaxwellContainer.getHeight() / 40, MaxwellContainer.getWidth() / 40, 
                           MaxwellContainer.getWidth() / 2 - (Math.max(1, MaxwellContainer.getWidth() / 100)), 
                           yPosition + (MaxwellContainer.getHeight() / 60), "magenta");
        eye.makeVisible();
    }

    /**
     * Hace visible el demonio, mostrando tanto el cuerpo como el ojo.
     */
    public void makeVisible() {
        body.makeVisible();
        eye.makeVisible();
    }

    /**
     * Hace invisible el demonio, ocultando tanto el cuerpo como el ojo.
     */
    public void makeInVisible() {
        body.makeInvisible();
        eye.makeInvisible();
    }

    /**
     * Elimina el demonio del contenedor, borrando tanto el cuerpo como el ojo.
     */
    public void erase() {
        body.erase();
        eye.erase();
    }

    /**
     * Devuelve la posición en el eje Y del demonio.
     * 
     * @return La posición en el eje Y del demonio.
     */
    public int getYPosition() {
        return this.yPosition;
    }
    
    /**
     * decide si dejar pasar o no a la particula.
     */
    public boolean interactInContainer(Particle p, int oldX, int oldY, int newX, int newY) {
        if (Math.abs(this.getYPosition() - oldY) <= 5) {
            if ((p.getRed() && p.getVelocityX() > 0) || (!p.getRed() && p.getVelocityX() < 0)) {
                p.setVelocityX(-p.getVelocityX()); 
                p.moveTo(oldX, oldY);
                return true;
            } else if (p.getRed() && p.getVelocityX() < 0 || !p.getRed() && p.getVelocityX() > 0) {
                p.moveTo(newX, newY);
                return true;
            }
        }
        return false;
    }
}