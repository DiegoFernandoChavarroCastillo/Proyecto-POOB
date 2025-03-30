package shapes;

import maxwell.MaxwellContainer;
import java.awt.geom.*;

/**
 * Clase que representa un círculo dibujable sobre el lienzo.
 * Hereda las funcionalidades comunes de la clase Figure.
 */
public class Circle extends Figure {

    /**
     * Constante para el valor de PI.
     */
    public static final double PI = 3.1416;

    /**
     * Diámetro del círculo.
     */
    private int diameter;

    /**
     * Construye un círculo con valores predeterminados.
     */
    public Circle() {
        diameter = 10;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }

    /**
     * Establece el diámetro del círculo.
     *
     * @param diameter nuevo diámetro del círculo
     */
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    /**
     * Cambia el tamaño del círculo.
     *
     * @param newDiameter nuevo diámetro en píxeles
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Mueve el círculo a una posición específica.
     *
     * @param x nueva posición en X
     * @param y nueva posición en Y
     */
    public void moveTo(int x, int y) {
        erase();
        this.xPosition = x;
        this.yPosition = y;
        draw();
    }

    /**
     * Obtiene la posición X del círculo.
     *
     * @return posición X
     */
    public int getX() {
        return this.xPosition;
    }

    /**
     * Obtiene la posición Y del círculo.
     *
     * @return posición Y
     */
    public int getY() {
        return this.yPosition;
    }

    /**
     * Dibuja el círculo sobre el lienzo.
     */
    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, diameter, diameter));
            canvas.wait(10);
        }
    }

    /**
     * Borra el círculo del lienzo.
     */
    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.erase(this);
        }
    }
}
