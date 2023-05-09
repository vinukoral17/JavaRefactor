package baseTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.SpacePlace;

class SpacePlaceTest {

	@Test
    public void testDefaultConstructor() {
        SpacePlace spacePlace = new SpacePlace();
        assertEquals(0, spacePlace.getxOrg());
        assertEquals(0, spacePlace.getyOrg());
    }

    @Test
    public void testParameterizedConstructor() {
        SpacePlace spacePlace = new SpacePlace(1.0, 2.0);
        assertEquals(1.0, spacePlace.getTheta(), 0.01);
        assertEquals(2.0, spacePlace.getPhi(), 0.01);
    }

    @Test
    public void testSettersAndGetters() {
        SpacePlace spacePlace = new SpacePlace();
        spacePlace.setxOrg(3);
        spacePlace.setyOrg(4);
        spacePlace.setTheta(5.0);
        spacePlace.setPhi(6.0);

        assertEquals(3, spacePlace.getxOrg());
        assertEquals(4, spacePlace.getyOrg());
        assertEquals(5.0, spacePlace.getTheta(), 0.01);
        assertEquals(6.0, spacePlace.getPhi(), 0.01);
    }
}
