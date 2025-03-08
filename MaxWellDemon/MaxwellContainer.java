import java.util.*;

/**
 * MaxwellContainer representa el contenedor donde se desarrolla la simulación.
 * Permite añadir partículas y controlar la visualización.
 * El contenedor es un rectángulo con una división en la mitad.
 * 
 * @author Diego, Juan Diego
 * @version 1.0
 */
public class MaxwellContainer {
    private boolean visible;
    private static int width;
    private static int height;
    private Rectangle container;
    private Rectangle division;
    private List<Demon> demons;
    private List<Particle> particles;
    private List<Hole> holes;

    /**
     * Constructor de la clase MaxwellContainer.
     * @param width Ancho del contenedor.
     * @param height Alto del contenedor.
     */
    public MaxwellContainer(int width, int height) {
        Canvas.reset(); 
        if (width < 4) {
        width = 4;
        }
        if (height < 200) {
            height = 200;
        }
        this.width = width;
        this.height = height;
        container = new Rectangle();
        container.changeSize(height, width);
        container.setPos(0,0);
        division = new Rectangle();
        division.changeSize(height, width/80);
        division.setPos(width/2,0);
        division.changeColor("black");
        this.demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();
        visible = true;
        this.makeVisible();
        Canvas.getCanvas(width,height);

    }
    
    public void addDemon(int height){
            if (isInside(width/2, height)) {
            Demon d = new Demon(height);
            demons.add(d);
        if (visible) {
            d.makeVisible();
        }
    }
    }
    
    public void delDemon(int yPosition){
    if (!demons.isEmpty()){
        for (int i = 0; i < demons.size(); i++){
            Demon demon = demons.get(i);
            if (demon.getYPosition() == yPosition){
                demon.erase(); 
                demons.remove(i); 
                return; 
            }
        }
        System.out.println("No se encontró demonio en la posición " + yPosition);
    } else {
        System.out.println("No hay demonios");
    }
}

    
    private boolean isInside(int x, int y) {
    if (x <= (width) && y <= (height)){
        return x <= (width) && y <= (height);
    } else {
        System.out.println("Fuera de limites");
        return false;
    }}
    
    /**
     * Hace visible el contenedor con la división y todas sus partículas.
     */
    public void makeVisible() {
        container.makeVisible();
        division.makeVisible();
        for (Particle p : particles) {
            p.makeVisible();
        }
        visible = true;
    }

    /**
     * Hace invisible el contenedor con la división y todas sus partículas.
     */
    public void makeInvisible() {
        container.makeInvisible();
        division.makeInvisible();
        for (Particle p : particles) {
            p.makeInvisible();
        }
        visible = false;
    }

    public void addParticle(String color,boolean isRed,  int px, int py, int speedX, int speedY) {
    if (isInside(px, py)) {
        Particle p = new Particle(color,px, py, isRed, speedX, speedY);
        particles.add(p);
        if (visible) {
            p.makeVisible();
        }
    }
    }

    public void delParticle(String color){
    if (!particles.isEmpty()){
        for (int i = 0; i < particles.size(); i++){
            Particle particle = particles.get(i);
            if (particle.getColor().equals(color)){ // Comparamos el color
                particle.erase(); // Se borra visualmente
                particles.remove(i); // Se elimina de la lista
                return; // Sale para no seguir buscando
            }
        }
        System.out.println("No se encontró partícula con el color: " + color);
    } else {
        System.out.println("No hay partículas");
    }
}

    
    public void addHole(int xPosition, int yPosition, int particles){
        if (isInside(xPosition, yPosition)) {
            Hole h = new Hole(xPosition, yPosition, particles);
            holes.add(h);
            if (visible) {
                h.makeVisible();
            }
    }
        
    }
    
    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

}
