package shapes;

import maxwell.MaxwellContainer;
import java.awt.*;

/**
 * Clase que representa un rectángulo dibujable sobre el lienzo.
 * Hereda las funcionalidades comunes de la clase Figure.
 */
public class Rectangle extends Figure {

    /**
     * Número de bordes del rectángulo.
     */
    public static int EDGES = 4;

    /**
     * Altura del rectángulo.
     */
    private int height;

    /**
     * Ancho del rectángulo.
     */
    private int width;

    /**
     * Crea un rectángulo con valores predeterminados.
     */
    public Rectangle() {
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "yellow";
        isVisible = false;
    }

    /**
     * Establece una nueva posición para el rectángulo.
     *
     * @param xPos nueva coordenada X
     * @param yPos nueva coordenada Y
     */
    public void setPos(int xPos, int yPos) {
        erase();
        this.xPosition = xPos;
        this.yPosition = yPos;
        draw();
    }

    /**
     * Cambia el tamaño del rectángulo.
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
     * Dibuja el rectángulo sobre el lienzo.
     */
    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.draw(this, color, new java.awt.Rectangle(xPosition, yPosition, width, height));
            canvas.wait(10);
        }
    }

    /**
     * Borra el rectángulo del lienzo.
     */
    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.erase(this);
        }
    }
}
