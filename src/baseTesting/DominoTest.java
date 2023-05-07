package baseTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import base.Domino;

public class DominoTest {
    private Domino domino;

    @Before
    public void setUp() {
        domino = new Domino(5, 3);
    }

    @Test
    public void testPlace() {
        domino.place(1, 2, 3, 4);
        assertTrue(domino.placed);
        assertEquals(1, domino.hx);
        assertEquals(2, domino.hy);
        assertEquals(3, domino.lx);
        assertEquals(4, domino.ly);
    }

    @Test
    public void testToString() {
        assertEquals("[53]unplaced", domino.toString());

        domino.place(1, 2, 3, 4);
        assertEquals("[53](2,3)(4,5)", domino.toString());
    }

    @Test
    public void testInvert() {
        domino.place(1, 2, 3, 4);
        domino.invert();
        assertEquals(3, domino.hx);
        assertEquals(4, domino.hy);
        assertEquals(1, domino.lx);
        assertEquals(2, domino.ly);
    }

    @Test
    public void testIsDominoHorizontal() {
        domino.place(1, 2, 1, 4);
        assertTrue(domino.isDominoHorizontal());

        domino.place(1, 2, 3, 4);
        assertFalse(domino.isDominoHorizontal());
    }

    @Test
    public void testCompareTo() {
        Domino smaller = new Domino(3, 1);
        assertTrue(domino.compareTo(smaller) > 0);

        Domino larger = new Domino(5, 2);
        assertTrue(domino.compareTo(larger) < 0);

        Domino equalHigh = new Domino(5, 4);
        assertTrue(domino.compareTo(equalHigh) < 0);

        Domino equalBoth = new Domino(5, 3);
        assertEquals(0, domino.compareTo(equalBoth));
    }
}