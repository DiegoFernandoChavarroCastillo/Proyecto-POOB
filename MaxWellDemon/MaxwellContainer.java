import java.util.*;
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
    private boolean isRunning;
    private Thread simulationThread;

    /**
     * Constructor de la clase MaxwellContainer.
     * Inicializa el contenedor con las dimensiones especificadas.
     * 
     * @param h La altura del contenedor.
     * @param w El ancho del contenedor.
     */
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
        isRunning = false;

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

    /**
     * Añade un demonio en la posición especificada.
     * 
     * @param d La posición del demonio.
     */
    public void addDemon(int d) {
        if (d < 0 || d > height) {
            System.out.println("Error: Posición del demonio fuera de rango.");
            setIsOk(false);
            return;
        }
        demons.add(new Demon(d));
        System.out.println("Demonio agregado en la posición " + d);
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
            System.out.println("Todos los demonios en la posición " + d + " han sido eliminados.");
        } else {
            System.out.println("No se encontraron demonios en la posición " + d);
        }
    }

    /**
     * Añade partículas al contenedor.
     * 
     * @param r El número de partículas rojas.
     * @param b El número de partículas azules.
     * @param particlesData Los datos de las partículas.
     */
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

    /**
     * Elimina todas las partículas del color especificado.
     * 
     * @param color El color de las partículas a eliminar.
     */
    public void delParticle(String color) {
        if (particles.isEmpty()) {
            System.out.println("No hay partículas para eliminar.");
            return;
        }

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
    }

    /**
     * Añade un agujero en la posición especificada.
     * 
     * @param px La posición x del agujero.
     * @param py La posición y del agujero.
     * @param maxParticles La capacidad máxima de partículas del agujero.
     */
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
     * Inicia la simulación con el número de ticks especificado.
     * 
     * @param ticks El número de ticks de la simulación.
     */
    public void start(int ticks) {
        int divisionX = ((width / 2) - (Math.max(1, width / 80))); 
        Random random = new Random();
    
        for (int i = 0; i < ticks; i++) {
            Iterator<Particle> particleIterator = particles.iterator(); 
            
            while (particleIterator.hasNext()) {
                Particle p = particleIterator.next();
                int oldX = p.getCircle().getX();
                int oldY = p.getCircle().getY();
                int newX = oldX + p.getVelocityX();
                int newY = oldY + p.getVelocityY();
    
                boolean absorbed = false;
                for (Hole h : holes) {
                    if (Math.abs(h.getXPosition() - newX) <= 10 && Math.abs(h.getYPosition() - newY) <= 10) {
                        if (h.canAbsorbMore()) { 
                            h.absorbParticle();  
                            p.erase();           
                            particleIterator.remove(); 
                            absorbed = true;
                            System.out.println("💥 Partícula absorbida en hole (" + h.getXPosition() + ", " + h.getYPosition() + ")");
                            break; 
                        }
                    }
                }
                if (absorbed) continue; 
    
                if (!isInside(newX, newY)) {
                    if (newX < 10) {
                        newX = 10;
                        p.setVelocityX(-p.getVelocityX());
                    } else if (newX > width - 10) {
                        newX = width - 10;
                        p.setVelocityX(-p.getVelocityX());
                    }
    
                    if (newY < 10) {
                        newY = 10;
                        p.setVelocityY(-p.getVelocityY());
                    } else if (newY > height - 10) {
                        newY = height - 10;
                        p.setVelocityY(-p.getVelocityY());
                    }
                }
    
                if ((oldX < divisionX && newX >= divisionX) || (oldX > divisionX && newX <= divisionX)) {
                    boolean demonioPresente = false;
                    for (Demon d : demons) {
                        if (Math.abs(d.getYPosition() - newY) <= 10) { 
                            demonioPresente = true;
                            break;
                        }
                    }
    
                    if (demonioPresente) {
                        int chance = random.nextInt(2); 
                        if (chance == 1) {
                            p.setVelocityX(-p.getVelocityX());
                            newX = oldX; 
                        } 
                    } else {
                        p.setVelocityX(-p.getVelocityX());
                        newX = oldX;
                    }
                }
                p.getCircle().moveTo(newX, newY);
            }
    
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


    /**
     * Verifica si se ha alcanzado el objetivo de la simulación.
     * 
     * @return true si se ha alcanzado el objetivo, false en caso contrario.
     */
    public boolean isGoal() {
        System.out.println("Simulacion Terminada");
        return false;
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

        return demonPositions;
    }

    /**
     * Devuelve las posiciones y velocidades de las partículas en el contenedor.
     * 
     * @return Una matriz con los datos de las partículas.
     */
    public int[][] particles() {
        if (particles == null || particles.isEmpty()) {
            return new int[0][0];
        }

        int[][] particleMatrix = new int[particles.size()][4];

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            particleMatrix[i][0] = p.getXPosition();
            particleMatrix[i][1] = p.getYPosition();
            particleMatrix[i][2] = p.getVelocityX();
            particleMatrix[i][3] = p.getVelocityY();
        }

        Arrays.sort(particleMatrix, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            return Integer.compare(a[3], b[3]);
        });

        return particleMatrix;
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
    }

    /**
     * Finaliza la simulación.
     */
    public void finish() {
        isRunning = false;
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
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
        int margin = 10; // bordes
        int divisionMargin = 20; // división
        int divisionX = (width / 2) - (Math.max(1, width / 80));

        if (x < margin || x > width - margin || y < margin || y > height - margin) {
            return false;
        }

        if (Math.abs(x - divisionX) < divisionMargin) {
            return false;
        }

        return true;
    }
}