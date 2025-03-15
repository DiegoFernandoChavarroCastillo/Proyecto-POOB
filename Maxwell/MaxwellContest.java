import java.util.*;

/**
 * Clase MaxwellContest que resuelve el problema del demonio de Maxwell.
 */
public class MaxwellContest {

    /**
     * Verifica el tiempo mínimo necesario para ordenar las partículas.
     * Retorna "impossible" si es imposible resolver el problema.
     *
     * @param h Altura del contenedor.
     * @param w Ancho del contenedor.
     * @param d Posición en Y del demonio.
     * @param b Número de partículas azules.
     * @param r Número de partículas rojas.
     * @param particles Matriz de partículas {px, py, vx, vy}.
     * @return Tiempo mínimo para resolver el problema o "impossible" si es imposible.
     */
    public Object solve(int h, int w, int d, int b, int r, int[][] particles) {
        List<int[]> redParticles = new ArrayList<>();
        List<int[]> blueParticles = new ArrayList<>();
        
        int midX = w / 2;
        boolean impossible = false;

        for (int i = 0; i < particles.length; i++) {
            int[] p = particles[i];
            boolean isRed = i < r;
            int px = p[0], py = p[1], vx = p[2], vy = p[3];
            
            if (isRed) {
                if (px > (w/2)){
                    if ((vy == 0 && (py < d - 5 || py > d + 5)) || vx == 0) {
                    impossible = true;
                    return "impossible";
                    }
                }
                redParticles.add(p);
            } else {
                if (px < (w/2)){
                    if ((vy == 0 && (py < d - 5 || py > d + 5)) || vx == 0) {
                    impossible = true;
                    return "impossible";
                    }
                }
                blueParticles.add(p);
            }
        }

        float minTime = Float.MAX_VALUE;
        for (int[] red : redParticles) {
            for (int[] blue : blueParticles) {
                float time = Math.abs((red[0] - midX) / (float) red[2]);
                minTime = Math.min(minTime, time);
            }
        }
        return minTime == Float.MAX_VALUE ? 0 : minTime;
    }

    /**
     * Simula la solución del problema visualmente.
     *
     * @param h Altura del contenedor.
     * @param w Ancho del contenedor.
     * @param d Posición en Y del demonio.
     * @param b Número de partículas azules.
     * @param r Número de partículas rojas.
     * @param particles Matriz de partículas {px, py, vx, vy}.
     */
    public void simulate(int h, int w, int d, int b, int r, int[][] particles) {
        MaxwellContainer container = new MaxwellContainer(h, w, d, b, r, particles);
        Object result = solve(h, w, d, b, r, particles);
        
        if (result instanceof String && result.equals("impossible")) {
            System.out.println("imposible");
            return;
        }
        
        float timeToSolve = (float) result;
        container.start((int) Math.ceil(timeToSolve));
    }
}
