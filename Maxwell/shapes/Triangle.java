package shapes;

import maxwell.MaxwellContainer;
import java.awt.*;

/**
 * Clase que representa un triángulo dibujable sobre el lienzo.
 * Hereda las funcionalidades comunes de la clase Figure.
 */
public class Triangle extends Figure {

    /**
     * Número de vértices del triángulo.
     */
    public static int VERTICES = 3;

    /**
     * Altura del triángulo.
     */
    private int height;

    /**
     * Base del triángulo.
     */
    private int width;

    /**
     * Crea un triángulo con los valores especificados.
     *
     * @param height altura del triángulo
     * @param width base del triángulo
     * @param xPosition posición inicial en X
     * @param yPosition posición inicial en Y
     * @param color color del triángulo
     */
    public Triangle(int height, int width, int xPosition, int yPosition, String color) {
        this.height = height;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        isVisible = true;
    }

    /**
     * Cambia el tamaño del triángulo.
     *
     * @param newHeight nueva altura en píxeles
     * @param newWidth nuevo ancho en píxeles
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Dibuja el triángulo sobre el lienzo.
     */
    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            int[] xpoints = { xPosition, xPosition + (width / 2), xPosition - (width / 2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /**
     * Borra el triángulo del lienzo.
     */
    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.erase(this);
        }
    }
    
}
