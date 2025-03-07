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
    private Rectangle container;
    private Rectangle division;
    private List<Particle> particles;
    private boolean visible;
    private static int width;
    private static int height;

    /**
     * Constructor de la clase MaxwellContainer.
     * @param width Ancho del contenedor.
     * @param height Alto del contenedor.
     */
    public MaxwellContainer(int width, int height) {
        Canvas.reset(); // Reinicia cualquier canvas anterior
        this.width = width;
        this.height = height;
        container = new Rectangle();
        container.changeSize(height, width);
        container.setPos(0,0);
        division = new Rectangle();
        division.changeSize(height, 5);
        division.setPos(width/2,0);
        division.changeColor("black");
        particles = new ArrayList<>();
        visible = true;
        this.makeVisible();
        Canvas.getCanvas(width,height);

    }

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

    /**
     * Añade una partícula al contenedor.
     * @param particle La partícula a añadir.
     */
    public void addParticle(Particle particle) {
        particles.add(particle);
        if (visible) {
            particle.makeVisible();
        }
    }

    /**
     * Consulta la cantidad de partículas en el contenedor.
     * @return Número de partículas.
     */
    public int getParticleCount() {
        return particles.size();
    }

    /**
     * Termina la simulación limpiando todas las partículas.
     */
    public void finish() {
        makeInvisible();
        particles.clear();
    }
    
    public void createParticle(int x, int y, String color, int speedX, int speedY) {
    if (isInside(x, y)) {
        Particle p = new Particle(x, y, color, speedX, speedY);
        particles.add(p);
        if (visible) {
            p.makeVisible();
        }
    }
    }

    private boolean isInside(int x, int y) {
    return x >= 50 && x <= (50 + width) && y >= 50 && y <= (50 + height);
    }
    
    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

}
