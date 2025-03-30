package shapes;

import maxwell.MaxwellContainer;
import java.awt.*;

/**
 * Clase abstracta que representa una figura geométrica básica que puede ser dibujada en un lienzo.
 * Proporciona funcionalidades comunes como movimiento, cambio de color y visibilidad.
 */
public abstract class Figure {

    /**
     * Posición en el eje X de la figura.
     */
    protected int xPosition;

    /**
     * Posición en el eje Y de la figura.
     */
    protected int yPosition;

    /**
     * Color actual de la figura.
     */
    protected String color;

    /**
     * Indica si la figura es visible en el lienzo.
     */
    protected boolean isVisible;

    /**
     * Hace visible la figura en el lienzo.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Hace invisible la figura en el lienzo.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Mueve la figura 20 píxeles a la derecha.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Mueve la figura 20 píxeles a la izquierda.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Mueve la figura 20 píxeles hacia arriba.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Mueve la figura 20 píxeles hacia abajo.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Mueve la figura horizontalmente la distancia especificada.
     *
     * @param distance distancia en píxeles (positiva hacia la derecha, negativa hacia la izquierda)
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Mueve la figura verticalmente la distancia especificada.
     *
     * @param distance distancia en píxeles (positiva hacia abajo, negativa hacia arriba)
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Mueve la figura lentamente en dirección horizontal.
     *
     * @param distance distancia en píxeles a recorrer
     */
    public void slowMoveHorizontal(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for (int i = 0; i < Math.abs(distance); i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Mueve la figura lentamente en dirección vertical.
     *
     * @param distance distancia en píxeles a recorrer
     */
    public void slowMoveVertical(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for (int i = 0; i < Math.abs(distance); i++) {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Cambia el color de la figura.
     *
     * @param newColor nuevo color de la figura (por ejemplo: "red", "blue", "green", etc.)
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Dibuja la figura en el lienzo. Debe ser implementado por las subclases.
     */
    public abstract void draw();

    /**
     * Borra la figura del lienzo. Debe ser implementado por las subclases.
     */
    public abstract void erase();
}
