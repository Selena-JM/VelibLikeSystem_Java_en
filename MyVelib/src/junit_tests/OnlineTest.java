package junit_tests;

import clui.*;
import main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class OnlineTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        DockingStations.resetuniqID();
    }

    @Test
    public void testSetStationOnline_ValidInput() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "MyVelib Network", "2"};
        Online.main(args);

        assertEquals("Station n° : 2 put online", outContent.toString().trim());
        assertTrue(stations.get(1).getState().equals("online"));
    }

    @Test
    public void testSetStationOnline_InvalidNetworkName() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "Invalid Network", "2"};
        Online.main(args);

        assertEquals("No myVelib network with this name existing", errContent.toString().trim());
    }

    @Test
    public void testSetStationOnline_InvalidStationID() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "MyVelib Network", "4"};
        Online.main(args);

        assertEquals("No station with such id", errContent.toString().trim());
    }

    @Test
    public void testSetStationOnline_InsufficientArguments() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0"};
        Online.main(args);

        assertEquals("Please enter the name of the myVelib network and the station id", errContent.toString().trim());
    }
}
