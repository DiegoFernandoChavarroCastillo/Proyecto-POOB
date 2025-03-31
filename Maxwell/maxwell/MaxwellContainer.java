package maxwell;

import java.util.*;
import shapes.Rectangle;
import shapes.Canvas;
/**
 * La clase MaxwellContainer representa un contenedor que simula el experimento del demonio de Maxwell.
 * El contenedor tiene una división central y puede contener partículas, demonios y agujeros.
 * Las partículas pueden moverse dentro del contenedor y rebotar en las paredes o interactuar con los demonios.
 * 
 * @author Diego Chavarro, Diego Rodriguez
 * @version 08/03/25
 */
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
    private Thread simulationThread;
    private volatile boolean running = false;
    private static final int MARGIN = 1; 
    private static final int DIVISIONMARGIN = 1;

    /**
     * Constructor de la clase MaxwellContainer.
     * Inicializa el contenedor con las dimensiones especificadas.
     * 
     * @param h La altura del contenedor.
     * @param w El ancho del contenedor.
     */
    public MaxwellContainer(int w, int h) {
        Canvas.reset();

        if (w < 2) w = 2;
        if (w > 500) w = 500;
        if (h < 2) h = 2;
        if (h > 500) h = 500;

        this.width = 2*w;
        this.height = h;

        container = new Rectangle();
        container.changeSize(h, 2*w);
        container.setPos(0, 0);

        division = new Rectangle();
        division.changeSize(h, Math.max(1, w / 40));
        division.setPos((w )-(Math.max(1, w / 40)), 0);
        division.changeColor("black");

        this.demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();

        visible = true;
        running = false;

        Canvas.getCanvas(2*w, 2*h);

        this.makeVisible();
        setIsOk(true);
    }

    /**
     * Constructor de la clase MaxwellContainer con parámetros adicionales.
     * Inicializa el contenedor con las dimensiones especificadas y añade demonios y partículas.
     * 
     * @param h La altura del contenedor.
     * @param w El ancho del contenedor.
     * @param d La posición del demonio.
     * @param b El número de partículas azules.
     * @param r El número de partículas rojas.
     * @param particles Los datos de las partículas.
     */
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData) {
        this(h, w);
    
        if (ok()) {
            addDemon(d);
            if (particlesData.length != r + b) {
                System.out.println("Error: Se esperaban " + (r + b) + " partículas, pero se recibieron " + particlesData.length);
                setIsOk(false);
            } else {
                setIsOk(true);
                for (int i = 0; i < r + b; i++) {
                    int px = particlesData[i][0];
                    int py = particlesData[i][1];
                    int vx = particlesData[i][2];
                    int vy = particlesData[i][3];
    
                    boolean isRed = (i < r);
                    String color = isRed ? "red" : "blue";
    
                    addParticle(color, isRed, px, py, vx, vy);
                }
            }
        } else {
            System.out.println("Error en la inicialización del contenedor.");
            setIsOk(false);
        }
    }


    /**
     * Añade un demonio en la posición especificada.
     * 
     * @param d La posición del demonio.
     */
    public void addDemon(int d) {
        if (d < 0 || d > height) {
            System.out.println("Error: Posición del demonio fuera de rango.");
            setIsOk(false);
        } else {
            demons.add(new Demon(d));
            setIsOk(true);
        }
    }
    
    /**
     * Agrega un demonio de un tipo específico en una posición dada.
     *
     * @param type tipo de demonio ("normal", "blue", "weak")
     * @param d posición en X donde se ubicará el demonio
     */
    public void addDemon(String type, int d) {
        if (d < 0 || d > height) {
            System.out.println("Error: Posición del demonio fuera de rango.");
            setIsOk(false);
            return;
        }
    
        switch (type.toLowerCase()) {
            case "normal":
                addDemon(d);
                setIsOk(true);
                break;
            case "blue":
                demons.add(new BlueDemon(d));
                setIsOk(true);
                break;
            case "weak":
                demons.add(new WeakDemon(d,this));
                setIsOk(true);
                break;
            default:
                System.out.println("Error: Tipo de demonio '" + type + "' no reconocido.");
                setIsOk(false);
                return;
        }
    
        setIsOk(true);
    }


    /**
     * Elimina todos los demonios en la posición especificada.
     * 
     * @param d La posición del demonio.
     */
    public void delDemon(int d) {
        boolean found = false;
        Iterator<Demon> iterator = demons.iterator();

        while (iterator.hasNext()) {
            Demon demon = iterator.next();
            if (demon.getYPosition() == d) {
                demon.erase();
                iterator.remove();
                found = true;
            }
        }

        if (found) {
            setIsOk(true);
            System.out.println("Todos los demonios en la posición " + d + " han sido eliminados.");
        } else {
            System.out.println("No se encontraron demonios en la posición " + d);
            setIsOk(false);
        }
    }
    
    public void removeDemon(Demon d) {
        demons.remove(d);
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
    
    /**
     * Agrega una nueva partícula al contenedor.
     *
     * @param color Color de la partícula ("red", "blue", etc.)
     * @param isRed Indica si la partícula es roja
     * @param px Posición inicial en el eje X
     * @param py Posición inicial en el eje Y
     * @param vx Velocidad en el eje X
     * @param vy Velocidad en el eje Y
     */
    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy) {
        if (!isInside(px, py)) {
            System.out.println("Error: Partícula fuera de los límites seguros. Ignorada.");
            setIsOk(false);
            return;
        }
    
        if (vx <= -width || vx >= width || vy <= -height || vy >= height || (vx == 0 && vy == 0)) {
            System.out.println("Error: Velocidad de partícula no válida. Ignorada.");
            setIsOk(false);
            return;
        }
    
        particles.add(new Particle(color, px, py, isRed, vx, vy));
        setIsOk(true);
    }

    /**
     * Agrega una nueva partícula al contenedor, permitiendo definir su tipo.
     * 
     * @param type Tipo de partícula ("normal", "ephemeral", "flying", "rotator")
     * @param color Color de la partícula
     * @param isRed true si la partícula es roja
     * @param px Posición X inicial
     * @param py Posición Y inicial
     * @param vx Velocidad en el eje X
     * @param vy Velocidad en el eje Y
     */
    public void addParticle(String type, String color, boolean isRed, int px, int py, int vx, int vy) {
        if (!isInside(px, py)) {
            System.out.println("Error: Partícula fuera de los límites seguros. Ignorada.");
            setIsOk(false);
            return;
        }
    
        if (vx <= -width || vx >= width || vy <= -height || vy >= height || (vx == 0 && vy == 0)) {
            System.out.println("Error: Velocidad de partícula no válida. Ignorada.");
            setIsOk(false);
            return;
        }
    
        switch (type.toLowerCase()) {
            case "normal":
                addParticle(color, isRed, px, py, vx, vy); 
                break;
            case "ephemeral":
                particles.add(new EphemeralParticle(color, px, py, isRed, vx, vy, this));
                setIsOk(true);
                break;
            case "flying":
                particles.add(new FlyingParticle(color, px, py, isRed, vx, vy));
                setIsOk(true);
                break;
            case "rotator":
                particles.add(new RotatorParticle(color, px, py, isRed, vx, vy));
                setIsOk(true);
                break;
            default:
                System.out.println("Advertencia: Tipo de partícula '" + type + "' no reconocido. Ignorada.");
                setIsOk(false);
                return;
        }
    
        setIsOk(true);
    }

    /**
     * Elimina todas las partículas del color especificado.
     * 
     * @param color El color de las partículas a eliminar.
     */
    public void delParticle(String color) {
        if (particles.isEmpty()) {
            System.out.println("No hay partículas para eliminar.");
            setIsOk(false);
        } else {
            Iterator<Particle> iterator = particles.iterator();
            boolean found = false;
    
            while (iterator.hasNext()) {
                Particle p = iterator.next();
                if (p.getColor().equals(color)) {
                    p.erase();
                    iterator.remove();
                    found = true;
                }
            }
    
            if (found) {
                System.out.println("Se eliminaron todas las partículas de color: " + color);
            } else {
                System.out.println("No se encontraron partículas de color: " + color);
            }
        setIsOk(found);
        }
    }
    
    /**
     * Elimina una partícula del contenedor.
     *
     * @param p Partícula a eliminar
     */
    public void removeParticle(Particle p) {
        particles.remove(p);
    }


    /**
     * Inicia la simulación con el número de ticks especificado.
     * La simulación se ejecuta en un hilo separado y se detiene automáticamente si se cumple el objetivo.
     * 
     * @param ticks El número de ticks de la simulación.
     */
    public void start(int ticks) {
        if (ticks == 0) {
            running = false;
            if (simulationThread != null) {
                try {
                    simulationThread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            setIsOk(false);
        } else if (!running) {
            running = true;
            setIsOk(true);
            simulationThread = new Thread(() -> {
                Random random = new Random();
                int divisionX = ((width / 2) - (Math.max(1, width / 80))); 
    
                for (int i = 0; i < ticks && running; i++) {
                    Iterator<Particle> particleIterator = particles.iterator();
    
                    while (particleIterator.hasNext()) {
                        Particle p = particleIterator.next();
                        int oldX = p.getX();
                        int oldY = p.getY();
                        int newX = p.getX() + p.getVelocityX();
                        int newY = p.getY() + p.getVelocityY();
                        
                        if ((oldX < divisionX && (oldX + p.getVelocityX()) >= divisionX) || 
                            (oldX > divisionX && (oldX + p.getVelocityX()) <= divisionX)) {
    
                            boolean handledByDemon = false;
                            for (Demon d : demons) {
                                if (d.interactInContainer(p, oldX, oldY, newX, newY)) {
                                    handledByDemon = true;
                                    break;
                                }
                            }
                            if (handledByDemon) continue;
                        }
                            
    
                        p.moveInContainer(width, height);
    
                        newX = p.getX();
                        newY = p.getY();
    
                        boolean absorbed = false;
                        for (Hole h : holes) {
                            if (h.absorbInContainer(p, particleIterator)){
                                absorbed = true;
                                break;
                            }
                        }
                        if (absorbed) continue;
                    }
    
                    if (isGoal()) {
                        finish();
                    }
    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                running = false;
            });
    
            simulationThread.start();
        }
    }

    /**
     * Verifica si todas las partículas rojas están en un solo lado y todas las azules en el otro,
     * o si todas las partículas son del mismo color y están en un solo lado.
     *
     * @return `true` si el objetivo se ha alcanzado, `false` en caso contrario.
     */
    public boolean isGoal() {
    
        boolean allRedLeft = true;     
        boolean allBlueRight = true; 
    
        for (Particle p : particles) {
            boolean isRed = p.getRed();
            boolean isOnLeft = p.isOnLeftSide();
        
            if (isRed && !isOnLeft) { allRedLeft = false; }
            if (!isRed && isOnLeft) { allBlueRight = false; }
        }
    
        boolean resultado = (allRedLeft && allBlueRight) ;
        return resultado;
    }


    /**
     * Devuelve las posiciones de los demonios en el contenedor.
     * 
     * @return Un array con las posiciones de los demonios.
     */
    public int[] demons() {
        int[] demonPositions = new int[demons.size()];

        for (int i = 0; i < demons.size(); i++) {
            demonPositions[i] = demons.get(i).getYPosition();
        }

        Arrays.sort(demonPositions);
        setIsOk(true);
        return demonPositions;
    }

    /**
     * Devuelve las posiciones y velocidades de las partículas en el contenedor.
     * 
     * @return Una matriz con los datos de las partículas.
     */
    public int[][] particles() {
        int[][] particlesMatrix;
        
        if (particles == null || particles.isEmpty()) {
            particlesMatrix = new int[0][0];
        } else {
            particlesMatrix = new int[particles.size()][4];
    
            for (int i = 0; i < particles.size(); i++) {
                Particle p = particles.get(i);
                particlesMatrix[i][0] = p.getXPosition();
                particlesMatrix[i][1] = p.getYPosition();
                particlesMatrix[i][2] = p.getVelocityX();
                particlesMatrix[i][3] = p.getVelocityY();
            }
    
            Arrays.sort(particlesMatrix, (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
                if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
                                  return Integer.compare(a[3], b[3]);
            });
        }
        setIsOk(true);
        return particlesMatrix;
    }
    
    /**
     * Retorna las posiciones y velocidades de las partículas del tipo dado,
     * ordenadas lexicográficamente por posición y velocidad.
     *
     * @param type tipo de partícula ("normal", "ephemeral", "flying", "rotator")
     * @return matriz con las partículas del tipo dado en formato [x, y, vx, vy]
     */
    public int[][] particles(String type) {
        List<int[]> datos = new ArrayList<>();
    
        for (Particle p : particles) {
            boolean matches = switch (type.toLowerCase()) {
                case "normal" -> p.getClass().equals(Particle.class);
                case "ephemeral" -> p instanceof EphemeralParticle;
                case "flying" -> p instanceof FlyingParticle;
                case "rotator" -> p instanceof RotatorParticle;
                default -> false;
            };
    
            if (matches) {
                datos.add(new int[]{ p.getX(), p.getY(), p.getVelocityX(), p.getVelocityY() });
            }
        }
    
        datos.sort(Comparator
            .comparingInt((int[] a) -> a[0])  
            .thenComparingInt(a -> a[1])      
            .thenComparingInt(a -> a[2])      
            .thenComparingInt(a -> a[3]));   
    
        return datos.toArray(new int[0][]);
    }


    /**
     * Devuelve las posiciones y capacidades de los agujeros en el contenedor.
     * 
     * @return Una matriz con los datos de los agujeros.
     */
    public int[][] holes() {
        int[][] holeData = new int[holes.size()][3];

        for (int i = 0; i < holes.size(); i++) {
            Hole h = holes.get(i);
            holeData[i][0] = h.getXPosition();
            holeData[i][1] = h.getYPosition();
            holeData[i][2] = h.getCapacity();
        }

        Arrays.sort(holeData, Comparator.comparingInt((int[] a) -> a[0])
                                         .thenComparingInt(a -> a[1])
                                         .thenComparingInt(a -> a[2]));
        setIsOk(true);
        return holeData;
    }

    /**
     * Hace visible el contenedor y todos sus elementos.
     */
    public void makeVisible() {
        visible = true;
        container.makeVisible();
        division.makeVisible();
        if (!demons.isEmpty()) {
            for (Demon d : demons) {
                d.makeVisible();
            }
        }
        if (!particles.isEmpty()) {
            for (Particle p : particles) {
                p.makeVisible();
            }
        }
        if (!holes.isEmpty()) {
            for (Hole h : holes) {
                h.makeVisible();
            }
        }
        Canvas.getCanvas(width, height);
        setIsOk(true);
    }

    /**
     * Hace invisible el contenedor y todos sus elementos.
     */
    public void makeInvisible() {
        visible = false;
        division.makeInvisible();
        container.makeInvisible();
        if (!demons.isEmpty()) {
            for (Demon d : demons) {
                d.makeInVisible();
            }
        }
        if (!particles.isEmpty()) {
            for (Particle p : particles) {
                p.makeInvisible();
            }
        }
        if (!holes.isEmpty()) {
            for (Hole h : holes) {
                h.makeInvisible();
            }
        }
        setIsOk(true);
    }

    /**
     * Finaliza la simulación.
     */
    public void finish() {
        running = false;
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
        setIsOk(true);
    }

    /**
     * Verifica si el contenedor está en un estado válido.
     * 
     * @return true si el contenedor está en un estado válido, false en caso contrario.
     */
    public boolean ok() {
        return isOk;
    }

    private void setIsOk(boolean success) {
        this.isOk = success;
    }

    /**
     * Devuelve el ancho del contenedor.
     * 
     * @return El ancho del contenedor.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Devuelve la altura del contenedor.
     * 
     * @return La altura del contenedor.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Verifica si una posición está dentro de los límites del contenedor.
     * 
     * @param x La coordenada x de la posición.
     * @param y La coordenada y de la posición.
     * @return true si la posición está dentro de los límites, false en caso contrario.
     */
    private boolean isInside(int x, int y) { 
        int divisionX = (width / 2) - (Math.max(1, width / 80));

        if (x < MARGIN || x > width - MARGIN || y < MARGIN || y > height - MARGIN) {
            return false;
        }

        if (Math.abs(x - divisionX) < DIVISIONMARGIN) {
            return false;
        }

        return true;
    }
}