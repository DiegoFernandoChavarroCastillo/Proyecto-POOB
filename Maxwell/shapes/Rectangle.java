package shapes;

import maxwell.MaxwellContainer;
import java.awt.*;

/**
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
 * Clase que representa un rectángulo dibujable sobre el lienzo.
 * Hereda las funcionalidades comunes de la clase Figure.
 */
public class Rectangle extends Figure {
=======
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle{
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java

    /**
     * Número de bordes del rectángulo.
     */
    public static int EDGES = 4;
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java

    /**
     * Altura del rectángulo.
     */
=======
    
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
    private int height;

    /**
     * Ancho del rectángulo.
     */
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
     * Crea un rectángulo con valores predeterminados.
     */
    public Rectangle() {
=======
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "yellow";
        isVisible = false;
    }
    

    /**
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
     * Establece una nueva posición para el rectángulo.
     *
     * @param xPos nueva coordenada X
     * @param yPos nueva coordenada Y
     */
    public void setPos(int xPos, int yPos) {
=======
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
     * Cambia el tamaño del rectángulo.
     *
     * @param newHeight nueva altura en píxeles
     * @param newWidth nuevo ancho en píxeles
=======
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
    
    
    public void setPos(int xPos,int yPos){
    erase();
    this.xPosition = xPos;
    this.yPosition = yPos;
    draw();
    }
    /**
     * Slowly move the rectangle horizontally.
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
     * Slowly move the rectangle vertically.
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
     * @param newWidht the new width in pixels. newWidth must be >=0.
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
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
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
     * Dibuja el rectángulo sobre el lienzo.
     */
    @Override
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
            canvas.draw(this, color, new java.awt.Rectangle(xPosition, yPosition, width, height));
=======
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
            canvas.wait(10);
        }
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Rectangle.java
     * Borra el rectángulo del lienzo.
     */
    @Override
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(), MaxwellContainer.getHeight());
=======
     * Erase the rectangle on screen.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Rectangle.java
            canvas.erase(this);
        }
    }
}

