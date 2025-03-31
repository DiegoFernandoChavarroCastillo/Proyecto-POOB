package maxwell;

import java.util.Random;

/**
 * Clase que contiene múltiples casos de prueba para simular el funcionamiento del contenedor de Maxwell.
 * Cada método representa un escenario diferente con demonios, partículas y agujeros.
 */
public class CasosDePrueba {
    
    /**
     * Caso de prueba 1: Contenedor con múltiples demonios y agujeros.
     * Se agregan varias partículas de ambos colores.
     */
    public void casoPrueba1() {
        MaxwellContainer container = new MaxwellContainer(400, 500);

        container.addDemon(100);
        container.addDemon(200);
        container.addDemon(300);

        container.addHole(100, 100, 3);
        container.addHole(250, 200, 5);
        container.addHole(400, 300, 4);

        int r = 10;
        int b = 10;
        int[][] particlesData = generarParticulas(r, b, 500, 400);
        agregarParticulas(container, r, b, particlesData);

        container.start(1500);
    }


    /**
     * Caso de prueba 2: Partículas con alta velocidad y varios demonios.
     */
    public void casoPrueba2() {
        MaxwellContainer container = new MaxwellContainer(400, 500);

        container.addDemon(100);
        container.addDemon(200);
        container.addDemon(300);

        container.addHole(100, 100, 3);
        container.addHole(250, 200, 5);
        container.addHole(400, 300, 4);

        int[][] particlesData = {{15,15, 70,15}, {30,30,15,15}, {350,70,15,15}, {380, 50, 15,1}};
        agregarParticulas(container, 3, 1, particlesData);

        container.start(1500);
    }
    
    /**
     * Caso de prueba 3: Escenario imposible de resolver.
     * Utiliza velocidades y posiciones que impiden cumplir la condición de separación.
     */
    public void casoPrueba3() {
        MaxwellContainer container = new MaxwellContainer(40, 40);

        container.addDemon(10);

        int[][] particlesData = {{70,10,20,20}, {20, 30, -20,-10}, {70,20,10,-20}, {60,20,20,20}};
        agregarParticulas(container, 2, 2, particlesData);

        container.start(1500);
    }

    /**
     * Caso de prueba 4: Contenedor con pocas partículas y un solo demonio.
     * Se utiliza para validar comportamientos básicos.
     */
    public void casoPrueba4() {
        MaxwellContainer container = new MaxwellContainer(140, 40);

        container.addDemon(10);

        int[][] particlesData = {{60,10,40,10},{10,10,20,0}};
        agregarParticulas(container, 1, 1, particlesData);

        container.start(1000);
    }
    
        /**
     * Caso de prueba 5: Contenedor con partículas de todos los tipos.
     * Incluye normales, ephemeral, flying y rotator. También se incluyen agujeros negros para verificar que las flying no sean absorbidas.
     */
    public void casoPrueba5() {
        MaxwellContainer container = new MaxwellContainer(400, 500);

        container.addDemon(100);
        container.addDemon(200);
        container.addDemon(300);

        container.addHole(150, 200, 2);
        container.addHole(250, 200, 2);
        container.addHole(350, 250, 2);

        container.addParticle("normal", "red", true, 30, 50, 10, 5);

        container.addParticle("ephemeral", "magenta", true, 60, 80, 2, 2);

        container.addParticle("flying", "green", false, 50, 180, 5, 0);
        container.addParticle("flying", "green", false, 360, 240, -5, 0);

        container.addParticle("rotator", "orange", true, 70, 70, 5, 5);
        container.addParticle("rotator", "orange", false, 370, 370, -6, -3);

        container.start(2000);
    }
    
    /**
     * Caso de prueba 6: Verificación del demonio azul (BlueDemon).
     * Solo permite el paso de partículas azules, las rojas son devueltas.
     */
    public void casoPrueba6() {
        MaxwellContainer container = new MaxwellContainer(400, 500);
    
        // Agregamos un BlueDemon a la altura media
        container.addDemon("blue", 250);
    
        // Partícula azul intentando cruzar desde la izquierda hacia la derecha → debe pasar
        container.addParticle("normal", "blue", false, 100, 250, -15, 0);
    
        // Partícula azul intentando cruzar desde la derecha hacia la izquierda → debe pasar
        container.addParticle("normal", "blue", false, 350, 250, -15, 0);
    
        // Partícula roja intentando cruzar desde la izquierda hacia la derecha → debe ser devuelta
        container.addParticle("normal", "red", true, 100, 250, 15, 0);
    
        // Partícula roja intentando cruzar desde la derecha hacia la izquierda → debe ser devuelta
        container.addParticle("normal", "red", true, 350, 250, 15, 0);
    
        container.start(2000);
    }



    /**
     * Genera partículas con posiciones y velocidades aleatorias.
     */
    private int[][] generarParticulas(int r, int b, int maxX, int maxY) {
        Random rand = new Random();
        int total = r + b;
        int[][] particlesData = new int[total][4];

        for (int i = 0; i < total; i++) {
            int x = rand.nextInt((2 * maxX) - 20) + 10;
            int y = rand.nextInt(maxY - 20) + 10;

            int vx = rand.nextInt(20);
            int vy = rand.nextInt(20);

            if (vx == 0) vx = 30;
            if (vy == 0) vy = 30;

            particlesData[i][0] = x;
            particlesData[i][1] = y;
            particlesData[i][2] = vx;
            particlesData[i][3] = vy;
        }

        return particlesData;
    }

    /**
     * Agrega partículas al contenedor usando el método addParticle.
     * Asigna color rojo a las primeras r partículas, azul a las b siguientes.
     *
     * @param container Instancia del contenedor
     * @param r Cantidad de partículas rojas
     * @param b Cantidad de partículas azules
     * @param particlesData Datos de posición y velocidad de las partículas
     */
    private void agregarParticulas(MaxwellContainer container, int r, int b, int[][] particlesData) {
        for (int i = 0; i < r + b; i++) {
            int px = particlesData[i][0];
            int py = particlesData[i][1];
            int vx = particlesData[i][2];
            int vy = particlesData[i][3];
            boolean isRed = i < r;
            String color = isRed ? "red" : "blue";
            container.addParticle(color, isRed, px, py, vx, vy);
        }
    }
}
