package maxwell;

import java.util.Random;

public class CasosDePrueba {

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

    public void casoPrueba2() {
        MaxwellContainer container = new MaxwellContainer(500, 500);

        container.addDemon(50);
        container.addDemon(250);
        container.addDemon(450);

        container.addHole(200, 100, 3);
        container.addHole(350, 300, 5);

        int r = 15;
        int b = 15;
        int[][] particlesData = generarParticulas(r, b, 500, 500);
        agregarParticulas(container, r, b, particlesData);

        container.start(2000);
    }

    public void casoPrueba3() {
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

    public void casoPrueba4() {
        MaxwellContainer container = new MaxwellContainer(400, 500);

        container.addDemon(100);
        container.addDemon(200);
        container.addDemon(300);

        container.addHole(250, 200, 1);
        container.addHole(400, 300, 1);

        int[][] particlesData = {{350,70,15,15}, {380, 50, 15,1}, {15,15,70,15}, {30,30,15,15}};
        agregarParticulas(container, 3, 1, particlesData);

        container.start(1500);
    }

    public void casoPrueba5() {
        MaxwellContainer container = new MaxwellContainer(40, 40);

        container.addDemon(10);

        int[][] particlesData = {{70,10,20,20}, {20, 30, -20,-10}, {70,20,10,-20}, {60,20,20,20}};
        agregarParticulas(container, 2, 2, particlesData);

        container.start(1500);
    }

    public void casoPrueba6() {
        MaxwellContainer container = new MaxwellContainer(140, 40);

        container.addDemon(10);

        int[][] particlesData = {{60,10,40,10},{10,10,20,0}};
        agregarParticulas(container, 1, 1, particlesData);

        container.start(1000);
    }

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
