package test;

import maxwell.MaxwellContest;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxwellContestTest {

    @Test
    public void shouldReflectCorrectlyWithinBounds() {
        double reflected = MaxwellContest.reflect(3.0, 10.0);
        assertEquals(3.0, reflected, 1e-9);
    }

    @Test
    public void shouldReflectCorrectlyWhenOverBounds() {
        double reflected = MaxwellContest.reflect(17.0, 10.0);  
        assertEquals(3.0, reflected, 1e-9);
    }

    @Test
    public void shouldReflectNegativeCorrectly() {
        double reflected = MaxwellContest.reflect(-3.0, 10.0);
        assertEquals(3.0, reflected, 1e-9);
    }

    @Test
    public void shouldReturnInfinityIfNoFlipPossible() {
        double t = MaxwellContest.findMinFlipTime(0, 0, 10, 0, 10, 10, 9); 
        assertEquals(Double.POSITIVE_INFINITY, t, 1e-9);
    }

    @Test
    public void shouldReturnImpossibleIfNoFlipCanBeDone() {
        int[][] particles = {
            {0, 0, 1, 0},   
            {10, 0, 1, 0}  
        };
        String result = MaxwellContest.solve(10, 10, 9, 1, 1, particles);
        assertEquals("impossible", result);
    }

}
