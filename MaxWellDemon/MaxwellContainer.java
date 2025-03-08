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
        if (w > 500) w = 500;
        if (h < 2) h = 2;
        if (h > 500) h = 500;
    
        this.width = w;
        this.height = h;
        
        container = new Rectangle();
        container.changeSize(h, w);
        container.setPos(0, 0);
        
        division = new Rectangle();
        division.changeSize(h, Math.max(1, w / 80));
        division.setPos((w / 2)-(Math.max(1, w / 80)), 0);
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

            addParticle(r, b, particles);
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

    // Método para agregar múltiples partículas
    public void addParticle(int r, int b, int[][] particlesData) {
        if (particlesData.length != r + b) {
            System.out.println("Error: Se esperaban " + (r + b) + " partículas, pero se recibieron " + particlesData.length);
            setIsOk(false);
            return;
        }
    
        for (int i = 0; i < r + b; i++) {
            int px = particlesData[i][0];
            int py = particlesData[i][1];
            int vx = particlesData[i][2];
            int vy = particlesData[i][3];
    
            boolean isRed = (i < r); // Los primeros r son rojos
            String color = isRed ? "red" : "blue";
    
            // Validar si la posición está dentro del contenedor
            if (!isInside(px, py)) {
                setIsOk(false);
                continue;
            }
    
            // Validar velocidad
            if (vx <= -width || vx >= width || vy <= -height || vy >= height || (vx == 0 && vy == 0)) {
                System.out.println("Error: Velocidad de partícula no válida. Ignorada.");
                setIsOk(false);
                continue;
            }
    
            particles.add(new Particle(color, px, py, isRed, vx, vy));
            System.out.println("Partícula agregada: Color=" + color + ", Pos=(" + px + "," + py + "), Vel=(" + vx + "," + vy + ")");
        }
    }


    public void delParticle(String color) {
        // Método vacío
    }

    public void addHole(int px, int py, int particles) {
        // Método vacío
    }

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
    
    private boolean isInside(int x, int y) {
        if (x <= width && y <= height) {
            return true;
        } else {
            System.out.println("Fuera de límites");
            return false;
        }
    }

}





