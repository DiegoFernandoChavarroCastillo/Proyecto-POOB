import java.util.*;

/**
 * MaxwellContainer representa el contenedor donde se desarrolla la simulación.
 * Permite añadir partículas, demonios y agujeros, así como controlar la visualización y la animación.
 * El contenedor es un rectángulo con una división en la mitad.
 * 
 * @author Diego chavarro, Juan Diego
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
     * Inicializa el contenedor con sus dimensiones, la división en el medio, y las listas de objetos.
     * @param width Ancho del contenedor.
     * @param height Alto del contenedor.
     */
    public MaxwellContainer(int height, int width) {
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
        container.setPos(0, 0);
        division = new Rectangle();
        division.changeSize(height, width / 80);
        division.setPos(width / 2, 0);
        division.changeColor("black");
        this.demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();
        visible = true;
        this.makeVisible();
        Canvas.getCanvas(width, height);
    }
    
    /**
     * Agrega un demonio en una posición específica dentro del contenedor.
     * @param d Posición en el eje Y donde se agregará el demonio.
     */
    public void addDemon(int d) {
        if (isInside(width / 2, d)) {
            Demon dem = new Demon(d);
            demons.add(dem);
            if (visible) {
                dem.makeVisible();
            }
        }
    }
    
    /**
     * Elimina un demonio de la lista según su posición en el eje Y.
     * @param d Posición en el eje Y del demonio a eliminar.
     */
    public void delDemon(int d) {
        if (!demons.isEmpty()) {
            boolean removed = demons.removeIf(dem -> dem.getYPosition() == d);
            if (removed) {
                System.out.println("Demonio eliminado con d: " + d);
            } else {
                System.out.println("No se encontró el demonio d: " + d);
            }
        } else {
            System.out.println("No hay demonios");
        }
    }
    
    /**
     * Verifica si una coordenada (x, y) está dentro de los límites del contenedor.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @return true si la posición está dentro del contenedor, false si está fuera de los límites.
     */
    private boolean isInside(int x, int y) {
        if (x <= width && y <= height) {
            return true;
        } else {
            System.out.println("Fuera de límites");
            return false;
        }
    }
    
    /**
     * Hace visible el contenedor, la división y todas las partículas.
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
     * Hace invisible el contenedor, la división y todas las partículas.
     */
    public void makeInvisible() {
        container.makeInvisible();
        division.makeInvisible();
        for (Particle p : particles) {
            p.makeInvisible();
        }
        visible = false;
    }
    
    /**
     * Agrega una partícula al contenedor en una posición dada.
     * @param color Color de la partícula.
     * @param isRed Indica si la partícula es roja.
     * @param px Posición X de la partícula.
     * @param py Posición Y de la partícula.
     * @param vx Velocidad en X.
     * @param vy Velocidad en Y.
     */
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy) {
        if (isInside(px, py)) {
            Particle p = new Particle(color, px, py, isRed, vx, vy);
            particles.add(p);
            if (visible) {
                p.makeVisible();
            }
        }
    }
    
    /**
     * Elimina una partícula según su color.
     * @param color Color de la partícula a eliminar.
     */
    public void delParticle(String color) {
        if (!particles.isEmpty()) {
            boolean removed = particles.removeIf(p -> p.getColor().equals(color));
            if (removed) {
                System.out.println("Partícula eliminada con el color: " + color);
            } else {
                System.out.println("No se encontró partícula con el color: " + color);
            }
        } else {
            System.out.println("No hay partículas");
        }
    }
    
    /**
     * Agrega un agujero en una posición específica dentro del contenedor.
     * @param xPosition Coordenada X.
     * @param yPosition Coordenada Y.
     * @param particles Cantidad de partículas que puede contener el agujero.
     */
    public void addHole(int xPosition, int yPosition, int particles) {
        if (isInside(xPosition, yPosition)) {
            Hole h = new Hole(xPosition, yPosition, particles);
            holes.add(h);
            if (visible) {
                h.makeVisible();
            }
        }
    }
    
    /**
     * Inicia la simulación moviendo las partículas por un número determinado de ticks.
     * También maneja las colisiones con los bordes del contenedor.
     * @param ticks Número de iteraciones de la simulación.
     */
    public void start(int ticks) {
        for (int i = 0; i < ticks; i++) {
            for (Particle p : particles) {
                int newX = p.getCircle().getX() + p.getVelocityX();
                int newY = p.getCircle().getY() + p.getVelocityY();
                
                // Colisiones con los bordes
                if (newX <= 0 || newX >= width) {
                    p.setVelocityX(-p.getVelocityX());
                }
                if (newY <= 0 || newY >= height) {
                    p.setVelocityY(-p.getVelocityY());
                }
                
                p.getCircle().moveTo(newX, newY);
            }
            
            // Pequeña pausa para la animación
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static int getWidth() { return width; }
    public static int getHeight() { return height; }
}
