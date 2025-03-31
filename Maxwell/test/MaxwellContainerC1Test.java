package test;

import maxwell.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxwellContainerC1Test {

    private MaxwellContainer container;

    @Before
    public void setup() {
        container = new MaxwellContainer(400, 500);
    }

    @After
    public void teardown() {
        container = null;
    }

    @Test
    public void shouldAddNormalDemonInValidPosition() {
        container.addDemon(250);
        assertTrue(container.ok());
    }

    @Test
    public void shouldntAddDemonInNegativePosition() {
        container.addDemon(-10);
        assertFalse(container.ok());
    }

    @Test
    public void shouldAddBlueDemonCorrectly() {
        container.addDemon("blue", 200);
        assertTrue(container.ok());
    }

    @Test
    public void shouldntAddUnknownDemonType() {
        container.addDemon("ghost", 200);
        assertFalse(container.ok());
    }

    @Test
    public void shouldAddHoleInValidPosition() {
        container.addHole(200, 200, 5);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddMovingHoleUsingType() {
        container.addHole("moving", 50, 50, 3);
        assertTrue(container.ok());
    }

    @Test
    public void shouldntAddHoleWithInvalidType() {
        container.addHole("wormhole", 100, 100, 1);
        assertFalse(container.ok());
    }

    @Test
    public void shouldAddNormalParticleUsingTypedMethod() {
        container.addParticle("normal", "blue", false, 100, 100, 5, 5);
        assertTrue(container.ok());
    }

    @Test
    public void shouldntAddParticleWithUnknownType() {
        container.addParticle("ghost", "red", true, 100, 100, 5, 5);
        assertFalse(container.ok());
    }

    @Test
    public void shouldAbsorbParticlesWithMovingHole() {
        container.addHole("moving", 50, 50, 5);
        container.addParticle("normal", "blue", false, 55, 55, 0, 0);
        container.start(100);
        assertEquals(0, container.particles("normal").length);
    }

    @Test
    public void shouldReturnParticlesOrderedLexicographically() {
        container.addParticle("normal", "red", true, 20, 20, 1, 1);
        container.addParticle("normal", "red", true, 10, 10, 1, 1);
        int[][] datos = container.particles("normal");
        assertEquals(10, datos[0][0]);
        assertEquals(20, datos[1][0]);
    }

    @Test
    public void shouldReturnOnlyRedParticlesIfAsked() {
        container.addParticle("normal", "red", true, 10, 10, 1, 1);
        container.addParticle("normal", "blue", false, 20, 20, 1, 1);
        int[][] normales = container.particles("normal");
        assertEquals(2, normales.length);
        assertEquals(10, normales[0][0]);
    }

    @Test
    public void shouldAddAndTrackMultipleDemonTypes() {
        container.addDemon("blue", 100);
        container.addDemon("weak", 200);
        container.addDemon(300);
        assertEquals(1, container.demons("blue").length);
        assertEquals(1, container.demons("weak").length);
        assertEquals(1, container.demons("normal").length);
    }

    @Test
    public void shouldReturnSortedHoles() {
        container.addHole("normal", 200, 200, 4);
        container.addHole("moving", 100, 100, 2);
        int[][] holes = container.holes("normal");
        assertEquals(1, holes.length);
        assertEquals(200, holes[0][0]);
    }

    @Test
    public void shouldntCrashWithEmptyParticlesQuery() {
        int[][] datos = container.particles("ephemeral");
        assertNotNull(datos);
        assertEquals(0, datos.length);
    }

    @Test
    public void shouldntCrashWithEmptyDemonQuery() {
        int[] demonios = container.demons("ghost");
        assertNotNull(demonios);
        assertEquals(0, demonios.length);
    }

    @Test
    public void shouldMoveAndAbsorbWithMixedHolesAndParticles() {
        container.addHole("normal", 300, 300, 2);
        container.addHole("moving", 50, 50, 2);
        container.addParticle("normal", "blue", false, 300, 300, 0, 0);
        container.addParticle("normal", "red", true, 55, 55, 0, 0);
        container.start(100);
        assertEquals(0, container.particles("normal").length);
    }

    @Test
    public void shouldHandleOverlappingParticlesAndDemon() {
        container.addDemon("blue", 250);
        container.addParticle("normal", "red", true, 100, 250, 15, 0);
        container.addParticle("normal", "blue", false, 300, 250, -15, 0);
        container.start(50);
        int[][] azules = container.particles("normal");
        assertTrue(azules.length > 0); 
    }

}
