package shapes;

import maxwell.MaxwellContainer;
import java.awt.*;

/**
<<<<<<< HEAD:Maxwell/shapes/Triangle.java
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
=======
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    public static int VERTICES=3;
    
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Triangle.java
    private int height;

    /**
     * Base del triángulo.
     */
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
<<<<<<< HEAD:Maxwell/shapes/Triangle.java
     * Crea un triángulo con los valores especificados.
     *
     * @param height altura del triángulo
     * @param width base del triángulo
     * @param xPosition posición inicial en X
     * @param yPosition posición inicial en Y
     * @param color color del triángulo
     */
    public Triangle(int height, int width, int xPosition, int yPosition, String color) {
=======
     * Create a new triangle at default position with default color.
     */
    public Triangle(int height, int width, int xPosition, int yPosition, String color){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Triangle.java
        this.height = height;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        isVisible = true;
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Triangle.java
     * Cambia el tamaño del triángulo.
     *
     * @param newHeight nueva altura en píxeles
     * @param newWidth nuevo ancho en píxeles
=======
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the triangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the triangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the triangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the triangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Triangle.java
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Triangle.java
     * Dibuja el triángulo sobre el lienzo.
     */
    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            int[] xpoints = { xPosition, xPosition + (width / 2), xPosition - (width / 2) };
=======
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Triangle.java
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Triangle.java
     * Borra el triángulo del lienzo.
     */
    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
=======
     * Erase the triangle on screen.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Triangle.java
            canvas.erase(this);
        }
    }
}
