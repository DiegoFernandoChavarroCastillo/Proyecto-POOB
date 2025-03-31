package maxwell;

/**
 * Partícula de tipo rotator.
 * Intercambia sus velocidades Vx y Vy en cada colisión con los bordes o con la división central.
 * Tiene un tamaño más pequeño que las partículas normales para facilitar su identificación visual.
 */
public class RotatorParticle extends Particle {

    /**
     * Crea una nueva partícula rotator.
     *
     * @param color color de la partícula
     * @param xPos posición inicial en X
     * @param yPos posición inicial en Y
     * @param isRed indica si la partícula es roja
     * @param speedX velocidad inicial en X
     * @param speedY velocidad inicial en Y
     */
    public RotatorParticle(String color, int xPos, int yPos, boolean isRed, int speedX, int speedY) {
        super(color, xPos, yPos, isRed, speedX, speedY);
        setDiameter(MaxwellContainer.getWidth() / 80);
    }

    /**
     * Mueve la partícula dentro del contenedor.
     * En cada colisión con los bordes o la división central, intercambia sus velocidades.
     * Los signos de las velocidades se ajustan para evitar que atraviese los límites del contenedor.
     *
     * @param width ancho del contenedor
     * @param height alto del contenedor
     */
    @Override
    public void moveInContainer(int width, int height) {
        int vx = getVelocityX();
        int vy = getVelocityY();

        int newX = getX() + vx;
        int newY = getY() + vy;

        boolean reboteLateral = false;
        boolean reboteVertical = false;
        boolean reboteDivision = false;

        if (newX < 0 || newX > width) {
            reboteLateral = true;
        }
        if (newY < 0 || newY > height) {
            reboteVertical = true;
        }

        int divisionX = (width / 2) - (Math.max(1, width / 80));
        boolean cruzoDivision = (getX() < divisionX && newX >= divisionX) || (getX() > divisionX && newX <= divisionX);
        if (cruzoDivision) {
            reboteDivision = true;
        }

        if (reboteLateral || reboteVertical || reboteDivision) {
            int newVX = Math.abs(vy);
            int newVY = Math.abs(vx);

            if (reboteLateral) {
                newVX *= (vx > 0) ? -1 : 1;
                newVY *= (vy >= 0) ? 1 : -1;
            } else if (reboteVertical) {
                newVX *= (vx >= 0) ? 1 : -1;
                newVY *= (vy > 0) ? -1 : 1;
            } else if (reboteDivision) {
                if (vx > 0) {
                    newVX = -Math.abs(newVX);
                } else {
                    newVX = Math.abs(newVX);
                }
                newVY = (vy >= 0) ? Math.abs(newVY) : -Math.abs(newVY);
            }

            vx = newVX;
            vy = newVY;
        }

        setVelocityX(vx);
        setVelocityY(vy);
        moveTo(getX() + vx, getY() + vy);
    }
}
