package shapes;

import maxwell.MaxwellContainer;
import java.awt.geom.*;

/**
<<<<<<< HEAD:Maxwell/shapes/Circle.java
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
=======
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * construye circulo predeterminado
     */
    public Circle(){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Circle.java
        diameter = 10;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
<<<<<<< HEAD:Maxwell/shapes/Circle.java

    /**
     * Establece el diámetro del círculo.
     *
     * @param diameter nuevo diámetro del círculo
     */
    public void setDiameter(int diameter) {
=======
    /**
     * cambia el daimetro
     * 
     * @param diametro el diametro del circulo
     */
    public void setDiameter(int diameter){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Circle.java
        this.diameter = diameter;
    }
    
    /**
     * hace visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * hace invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

<<<<<<< HEAD:Maxwell/shapes/Circle.java
    /**
     * Cambia el tamaño del círculo.
     *
     * @param newDiameter nuevo diámetro en píxeles
     */
    public void changeSize(int newDiameter) {
=======
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(MaxwellContainer.getWidth(),MaxwellContainer.getHeight());
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
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
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Circle.java
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
<<<<<<< HEAD:Maxwell/shapes/Circle.java
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
=======
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public void moveTo(int x, int y){
    erase(); 
    this.xPosition = x;
    this.yPosition = y;
    draw(); 
    }

    public int getX(){
    return this.xPosition;
    }
    
    public int getY(){
    return this.yPosition;
    }

>>>>>>> parent of 7e0fe1f (implementacion clase abstracta figures):Maxwell/Circle.java
}
