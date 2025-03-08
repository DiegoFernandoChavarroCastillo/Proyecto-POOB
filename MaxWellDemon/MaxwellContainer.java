import java.util.*;

public class MaxwellContainer {
    
    private boolean isOk;
    private static int width;
    private static int height;
    private Rectangle container;
    private Rectangle division;
    private List<Demon> demons;
    private List<Particle> particles;
    private List<Hole> holes;
    private boolean visible;
    
    public MaxwellContainer(int h, int w) {
        Canvas.reset();
        
        if (w < 2) w = 2;
        if (w > 200) w = 200;
        if (h < 2) h = 2;
        if (h > 200) h = 200;
    
        this.width = w;
        this.height = h;
        
        container = new Rectangle();
        container.changeSize(h, w);
        container.setPos(0, 0);
        
        division = new Rectangle();
        division.changeSize(h, Math.max(1, w / 80));
        division.setPos(w / 2, 0);
        division.changeColor("black");
        
        this.demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();
        
        visible = true;
        
        Canvas.getCanvas(w, h);
        
        this.makeVisible();
        setIsOk(true);
    }

    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particles) {
        this(h, w); // 🔥 Llamamos al primer constructor

        if (ok()) { // Solo si el primer constructor se ejecutó correctamente
            // 🔥 Inicializar los demonios
            addDemon(d);

            // 🔥 Inicializar las partículas (r rojas, b azules)
            for (int i = 0; i < r + b; i++) {
                int px = particles[i][0];
                int py = particles[i][1];
                int vx = particles[i][2];
                int vy = particles[i][3];
                
                boolean isRed = (i < r); 
                String color = isRed ? "red" : "blue";

                this.particles.add(new Particle(color, px, py, isRed, vx, vy));
            }
            System.out.println("MaxwellContainer creado con éxito.");
        } else {
            System.out.println("Error en la inicialización del contenedor.");
            setIsOk(false); // Si hubo un problema, marcar como no exitoso
        }
    }

    // Métodos públicos según el diagrama de clases
    public void addDemon(int d) {
        if (d < 0 || d > height) {
            System.out.println("Error: Posición del demonio fuera de rango.");
            setIsOk(false);
            return;
        }
        demons.add(new Demon(d));
        System.out.println("Demonio agregado en la posición " + d);
    }

    public void delDemon(int d) {
        // Método vacío
    }

    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy) {
        // Método vacío
    }

    public void delParticle(String color) {
        // Método vacío
    }

    public void addHole(int px, int py, int particles) {
        // Método vacío
    }

    public void start(int ticks) {
        // Método vacío
    }

    public boolean isGoal() {
        return false; // Retorno temporal
    }

    public int[] demons() {
        return new int[0]; // Retorno temporal
    }

    public int[][] particles() {
        return new int[0][0]; // Retorno temporal
    }

    public int[][] holes() {
        return new int[0][0]; // Retorno temporal
    }

    public void makeVisible() {
        // Método vacío
    }

    public void makeInvisible() {
        // Método vacío
    }

    public void finish() {
        // Método vacío
    }

    public boolean ok() {
        return isOk;
    }

    // Método auxiliar para actualizar el estado de la última acción
    private void setIsOk(boolean success) {
        this.isOk = success;
    }
    
    public static int getWidth() { return width; }
    public static int getHeight() { return height; }
}





