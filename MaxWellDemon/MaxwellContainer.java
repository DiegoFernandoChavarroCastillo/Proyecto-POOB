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

    /**
     * Constructor de la clase MaxwellContainer.
     * @param width Ancho del contenedor.
     * @param height Alto del contenedor.
     */
    public MaxwellContainer(int width, int height) {
        container = new Rectangle();
        container.changeSize(height, width);
        container.moveHorizontal(50);
        container.moveVertical(50);
        division = new Rectangle();
        division.changeSize(height, 5);
        division.moveHorizontal(50 + width / 2);
        division.moveVertical(50);
        division.changeColor("black");
        particles = new ArrayList<>();
        visible = false;
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
}
