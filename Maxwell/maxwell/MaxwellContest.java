package maxwell;


import java.util.Locale;
/**
 * Clase MaxwellContest para simular el problema del demonio de Maxwell.
 */
public class MaxwellContest {

    static final double EPS = 1e-9;
    static final int MAX_ITER = 10000; 
    
    /**
     * Refleja una posición en un eje dado un límite.
     * @param u La posición inicial.
     * @param h El límite del eje.
     * @return La posición reflejada.
     */
    public static double reflect(double u, double h) {
        double mod = u % (2 * h);
        if (mod < 0) mod += 2 * h;
        if (mod <= h + EPS) return mod;
        else return 2 * h - mod;
    }
    
    /**
     * Encuentra el tiempo mínimo en el que una partícula cambia de lado.
     * @param px Posición inicial en x.
     * @param py Posición inicial en y.
     * @param vx Velocidad en x.
     * @param vy Velocidad en y.
     * @param w Ancho del área.
     * @param h Altura del área.
     * @param d Distancia de referencia.
     * @return Tiempo mínimo para el cambio de lado.
     */
    public static double findMinFlipTime(double px, double py, double vx, double vy, double w, double h, double d) {
        double best = Double.POSITIVE_INFINITY;
        if (vx > 0) {
            int kStart = (int) Math.ceil(px / (2 * w));
            for (int k = kStart; k < kStart + MAX_ITER; k++) {
                double t = (2 * w * k - px) / vx;
                if (t < 0) continue;
                if (Math.abs(vy) < EPS) {
                    if (Math.abs(py - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                } else {
                    double y = reflect(py + vy * t, h);
                    if (Math.abs(y - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                }
            }
        } else {
            int kStart = (int) Math.floor(px / (2 * w));
            for (int k = kStart; k > kStart - MAX_ITER; k--) {
                double t = (2 * w * k - px) / vx; 
                if (t < 0) continue;
                if (Math.abs(vy) < EPS) {
                    if (Math.abs(py - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                } else {
                    double y = reflect(py + vy * t, h);
                    if (Math.abs(y - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                }
            }
        }
        return best;
    }
    
    /**
     * Resuelve el problema y calcula el tiempo mínimo para equilibrar las partículas.
     * @param w Ancho del área.
     * @param h Altura del área.
     * @param d Distancia de referencia.
     * @param r Cantidad de partículas del grupo rojo.
     * @param b Cantidad de partículas del grupo azul.
     * @param particles Matriz con los datos de las partículas.
     * @return Tiempo mínimo en el que las partículas alcanzan el equilibrio o "impossible" si no es posible.
     */
    public static String solve(int w, int h, double d, int r, int b, int[][] particles) {
        int total = r + b;
        double overallTime = 0.0;
        boolean possible = true;
        for (int i = 0; i < total; i++) {
            double px = particles[i][0];
            double py = particles[i][1];
            double vx = particles[i][2];
            double vy = particles[i][3];
            boolean inLeft = (px < 0);
            boolean needFlip = false;
            if (i < r) {
                if (!inLeft) needFlip = true;
            } else {
                if (inLeft) needFlip = true;
            }
            if (needFlip) {
                double tCandidate = findMinFlipTime(px, py, vx, vy, w, h, d);
                if (tCandidate == Double.POSITIVE_INFINITY) {
                    possible = false;
                } else {
                    overallTime = Math.max(overallTime, tCandidate);
                }
            }
        }
        if (!possible) {
            return "impossible";
        } else {
            return String.format("%.6f", overallTime);
        }
    }
    
    /**
     * Simula el proceso del demonio de Maxwell y muestra las decisiones del demonio en cada paso.
     * @param w Ancho del área.
     * @param h Altura del área.
     * @param d Distancia de referencia.
     * @param b Cantidad de partículas azules.
     * @param r Cantidad de partículas rojas.
     * @param particles Matriz con los datos de las partículas.
     */
    public static void simulate(int w, int h, int d, int b, int r, int[][] particles) {
        int[][] fixedParticles = new int[particles.length][4];
        StringBuilder log = new StringBuilder();
        for (int i = 0; i < particles.length; i++) {
            fixedParticles[i][0] = (particles[i][0] + w) * 10;
            fixedParticles[i][1] = particles[i][1] * 10;
            fixedParticles[i][2] = particles[i][2] * 10;
            fixedParticles[i][3] = particles[i][3] * 10;
        }
        
        MaxwellContainer container = new MaxwellContainer(w * 10, h * 10, d * 10, b, r, fixedParticles);
        Object result = solve(w, h, d, r, b, particles);
        
        System.out.println(result.toString());
        
        if (result instanceof String && result.equals("impossible")) {
            System.out.println("impossible");
            return;
        }

        float timeToSolve = Float.parseFloat(result.toString().replace(",", "."));
        container.start((int) Math.ceil(timeToSolve));
       
        log.append("\nRegistro de decisiones del demonio:\n");
        for (int i = 0; i < particles.length; i++) {
            boolean inLeft = (particles[i][0] < 0);
            boolean shouldBeLeft = (i < r);
            
            log.append(String.format("Partícula %d: (%.2f, %.2f) con velocidad (%.2f, %.2f)\n", 
                i, particles[i][0] * 10.0, particles[i][1] * 10.0, particles[i][2] * 10.0, particles[i][3] * 10.0));
            
            if (inLeft == shouldBeLeft) {
                log.append("   -> Toca al demonio: la hace rebotar\n");
            } else {
                log.append("   -> Toca al demonio: la deja pasar\n");
            }
        }
        
        System.out.println(log.toString());
    }
}
