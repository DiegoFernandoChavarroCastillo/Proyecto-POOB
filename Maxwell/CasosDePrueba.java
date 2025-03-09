import java.util.Random;

public class CasosDePrueba {

    /**
     * Caso de prueba 1: Contenedor con un demonio y algunas partículas.
     * Se agregan partículas rojas y azules con velocidades aleatorias.
     */
    public void casoPrueba1() {
        System.out.println("Ejecutando Caso de Prueba 1...");
        MaxwellContainer container = new MaxwellContainer(300, 400);

        container.addDemon(150);

        int[][] particlesData = generarParticulas(5, 5, 400, 300);
        container.addParticle(5, 5, particlesData);

        container.addHole(200, 150, 3);

        container.start(100);
    }

    /**
     * Caso de prueba 2: Contenedor con múltiples demonios y agujeros.
     * Se agregan varios demonios y agujeros estratégicamente.
     */
    public void casoPrueba2() {
        System.out.println("Ejecutando Caso de Prueba 2...");
        MaxwellContainer container = new MaxwellContainer(400, 500);

        container.addDemon(100);
        container.addDemon(200);
        container.addDemon(300);

        container.addHole(100, 100, 3);
        container.addHole(250, 200, 5);
        container.addHole(400, 300, 4);

        int[][] particlesData = generarParticulas(10, 10, 500, 400);
        container.addParticle(10, 10, particlesData);

        container.start(150);
    }

    /**
     * Caso de prueba 3: Contenedor con múltiples partículas y obstáculos.
     * Se agregan partículas con velocidades elevadas y varios demonios.
     */
    public void casoPrueba3() {
        System.out.println("Ejecutando Caso de Prueba 3...");
        MaxwellContainer container = new MaxwellContainer(500, 500);

        container.addDemon(50);
        container.addDemon(250);
        container.addDemon(450);

        container.addHole(200, 100, 3);
        container.addHole(350, 300, 5);

        int[][] particlesData = generarParticulas(15, 15, 500, 500);
        container.addParticle(15, 15, particlesData);

        container.start(200);
    }

    /**
     * Genera datos de partículas con posiciones y velocidades aleatorias.
     * 
     * @param rojas Número de partículas rojas.
     * @param azules Número de partículas azules.
     * @param maxX Límite del eje X.
     * @param maxY Límite del eje Y.
     * @return Matriz con los datos de las partículas.
     */
    private int[][] generarParticulas(int rojas, int azules, int maxX, int maxY) {
        Random rand = new Random();
        int total = rojas + azules;
        int[][] particlesData = new int[total][4];

        for (int i = 0; i < total; i++) {
            int x = rand.nextInt(maxX - 20) + 10;
            int y = rand.nextInt(maxY - 20) + 10;

            int vx = rand.nextInt(10) - 5; 
            int vy = rand.nextInt(10) - 5;

            if (vx == 0) vx = 1;
            if (vy == 0) vy = 1;

            particlesData[i][0] = x;
            particlesData[i][1] = y;
            particlesData[i][2] = vx;
            particlesData[i][3] = vy;
        }

        return particlesData;
    }
}
