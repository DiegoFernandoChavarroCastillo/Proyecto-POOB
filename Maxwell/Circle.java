import java.awt.*;
import java.awt.geom.*;

public class Circle extends Figure {

    public static final double PI = 3.1416;
    private int diameter;

    public Circle() {
        diameter = 10;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    public void moveTo(int x, int y) {
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, diameter, diameter));
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
