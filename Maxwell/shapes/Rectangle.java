package shapes;
import maxwell.MaxwellContainer;

import java.awt.*;

public class Rectangle extends Figure {

    public static int EDGES = 4;
    private int height;
    private int width;

    public Rectangle() {
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "yellow";
        isVisible = false;
    }

    public void setPos(int xPos, int yPos) {
        erase();
        this.xPosition = xPos;
        this.yPosition = yPos;
        draw();
    }

    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.draw(this, color, new java.awt.Rectangle(xPosition, yPosition, width, height));
            canvas.wait(10);
        }
    }

    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.erase(this);
        }
    }
}
