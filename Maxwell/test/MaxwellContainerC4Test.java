package test;

import maxwell.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxwellContainerC4Test {

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
    public void shouldAddEphemeralParticleAndLetItVanish() {
        container.addParticle("ephemeral", "magenta", true, 50, 50, 1, 1);
        container.start(300); 
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddFlyingParticleAndNotBeAbsorbedByHole() {
        container.addHole("normal", 200, 200, 10);
        container.addParticle("flying", "green", false, 190, 200, 2, 0);
        container.start(300);
        assertTrue(container.ok());
        assertEquals(1, container.particles("flying").length);
    }

    @Test
    public void shouldAddRotatorParticleAndChangeVelocity() {
        container.addParticle("rotator", "orange", true, 100, 100, 5, 3);
        container.start(300);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddColorChangingParticleSuccessfully() {
        container.addParticle("colorchanging", "blue", false, 100, 100, 2, 1);
        container.start(200);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddBlueDemonAndBlockRedParticle() {
        container.addDemon("blue", 200);
        container.addParticle("normal", "red", true, 100, 200, 15, 0);
        container.start(300);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddBlueDemonAndLetBlueParticlePass() {
        container.addDemon("blue", 200);
        container.addParticle("normal", "blue", false, 100, 200, 15, 0);
        container.start(300);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddMovingHoleAndAbsorbParticle() {
        container.addHole("moving", 50, 50, 5);
        container.addParticle("normal", "blue", false, 55, 55, 0, 0);
        container.start(500);
        assertEquals(0, container.particles("normal").length);
        assertTrue(container.ok());
    }

    @Test
    public void shouldAddMultipleTypesAndRunWithoutError() {
        container.addDemon("blue", 200);
        container.addDemon("weak", 250);
        container.addHole("moving", 100, 100, 3);
        container.addParticle("flying", "green", false, 150, 200, -2, 0);
        container.addParticle("ephemeral", "magenta", true, 300, 300, -1, -1);
        container.addParticle("rotator", "orange", true, 100, 100, 3, 5);
        container.addParticle("colorchanging", "cyan", false, 50, 50, 1, 1);
        container.start(400);
        assertTrue(container.ok());
    }

    @Test
    public void shouldNotAbsorbFlyingParticleInMovingHole() {
        container.addHole("moving", 200, 200, 5);
        container.addParticle("flying", "green", false, 190, 200, 2, 0);
        container.start(400);
        assertEquals(1, container.particles("flying").length);
    }
}
