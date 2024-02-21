package junit_tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.*;

public class CoordinatesTest {
    
    @Test
    public void testGetX() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        assertEquals(1.0, coordinates.getX());
    }
    
    @Test
    public void testSetX() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        coordinates.setX(3.0);
        assertEquals(3.0, coordinates.getX());
    }
    
    @Test
    public void testGetY() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        assertEquals(2.0, coordinates.getY());
    }
    
    @Test
    public void testSetY() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        coordinates.setY(4.0);
        assertEquals(4.0, coordinates.getY());
    }
    
    @Test
    public void testToString() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        String expected = "Coordinates : [x=1.0, y=2.0]";
        assertEquals(expected, coordinates.toString());
    }
    
    @Test
    public void testDistance() {
        Coordinates c1 = new Coordinates(1.0, 2.0);
        Coordinates c2 = new Coordinates(4.0, 6.0);
        double expected = 5.0;
        assertEquals(expected, Coordinates.distance(c1, c2));
    }
    
    @Test
    public void testEquals() {
        Coordinates c1 = new Coordinates(1.0, 2.0);
        Coordinates c2 = new Coordinates(1.0, 2.0);
        Coordinates c3 = new Coordinates(3.0, 4.0);
        
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }
}

