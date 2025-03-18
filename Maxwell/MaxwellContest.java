import java.util.Locale;
public class MaxwellContest {

    static final double EPS = 1e-9;
    static final int MAX_ITER = 10000; 

    static double reflect(double u, double h) {
        double mod = u % (2 * h);
        if (mod < 0) mod += 2 * h;
        if (mod <= h + EPS) return mod;
        else return 2 * h - mod;
    }

    static double findMinFlipTime(double px, double py, double vx, double vy, double w, double h, double d) {
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

    public static void simulate(int w, int h, int d, int b, int r, int[][] particles) {
        int[][] fixedParticles = new int[particles.length][4];
    
        for (int i = 0; i < particles.length; i++) {
            fixedParticles[i][0] = (particles[i][0] + w)*10;
            fixedParticles[i][1] = particles[i][1]*10;
            fixedParticles[i][2] = particles[i][2]*10;
            fixedParticles[i][3] = particles[i][3]*10;
        }
        
        MaxwellContainer container = new MaxwellContainer(w*10, h*10, d*10, b, r, fixedParticles);
        Object result = solve(w, h, d, r, b, particles);
        System.out.println(result.toString());
        
        if (result instanceof String && result.equals("impossible")) {
            System.out.println("impossible");
            return;
        }

        float timeToSolve = Float.parseFloat(result.toString().replace(",", "."));

        container.start((int) Math.ceil(timeToSolve));
    }
}
