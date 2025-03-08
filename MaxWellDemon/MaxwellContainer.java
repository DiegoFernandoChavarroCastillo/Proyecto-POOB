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
        
        if (w < 150) w = 150;
        if (w > 500) w = 500;
        if (h < 150) h = 150;
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
        
        Canvas.getCanvas(2*w, 2*h);
        
        this.makeVisible();
        setIsOk(true);
    }

    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particles) {
        this(h, w);
        
        if (ok()) {
            addDemon(d);
            addParticle(r, b, particles);
            System.out.println("MaxwellContainer creado con éxito.");
        } else {
            System.out.println("Error en la inicialización del contenedor.");
            setIsOk(false);
        }
    }

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
    
            boolean isRed = (i < r);
            String color = isRed ? "red" : "blue";
    
            if (!isInside(px, py)) {
                System.out.println("Error: Partícula fuera de los límites seguros. Ignorada.");
                setIsOk(false);
                continue;
            }
    
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

    public void addHole(int px, int py, int maxParticles) {
        if (isInside(px, py)) {
            Hole hole = new Hole(px, py, maxParticles);
            holes.add(hole);
            if (!visible) {
                hole.makeVisible();
            }
        }
    }

    public void start(int ticks) {
        for (int i = 0; i < ticks; i++) {
            for (Particle p : particles) {
                int newX = p.getCircle().getX() + p.getVelocityX();
                int newY = p.getCircle().getY() + p.getVelocityY();
                
                if (newX <= 10 || newX >= width - 10) {
                    p.setVelocityX(-p.getVelocityX());
                }
                if (newY <= 10 || newY >= height - 10) {
                    p.setVelocityY(-p.getVelocityY());
                }
                
                p.getCircle().moveTo(newX, newY);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isGoal() {
        return false;
    }

    public int[] demons() {
        return new int[0];
    }

    public int[][] particles() {
        return new int[0][0];
    }

    public int[][] holes() {
        return new int[0][0];
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

    private void setIsOk(boolean success) {
        this.isOk = success;
    }
    
    public static int getWidth() { return width; }
    public static int getHeight() { return height; }
    
    private boolean isInside(int x, int y) {
        if (x >= 10 && x <= width - 10 && y >= 10 && y <= height - 10) {
            return true;
        } else {
            System.out.println("Error: Partícula fuera de los límites seguros en (" + x + "," + y + ").");
            return false;
        }
    }

}
