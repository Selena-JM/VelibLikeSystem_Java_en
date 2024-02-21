package junit_tests;

import clui.*;
import main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class OfflineTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        DockingStations.resetuniqID();
    }

    @Test
    public void testSetStationOffline_ValidInput() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "MyVelib Network", "3"};
        Offline.main(args);

        assertEquals("Station n° : 3 put offline", outContent.toString().trim());
        assertTrue(stations.get(2).getState().equals("offline"));
    }

    @Test
    public void testSetStationOffline_InvalidNetworkName() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "Invalid Network", "2"};
        Offline.main(args);

        assertEquals("No myVelib network with this name existing", errContent.toString().trim());
    }

    @Test
    public void testSetStationOffline_InvalidStationID() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0", "MyVelib Network", "4"};
        Offline.main(args);

        assertEquals("No station with such id", errContent.toString().trim());
    }

    @Test
    public void testSetStationOffline_InsufficientArguments() {
        MyVelib.setName("MyVelib Network");

        ArrayList<DockingStations> stations = new ArrayList<>();
        stations.add(new DockingStations(1, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard"));
        stations.add(new DockingStations(3, new Coordinates(0.0, 0.0), "service", "standard"));

        MyVelib.setList_stations(stations);

        String[] args = {"arg0"};
        Offline.main(args);

        assertEquals("Please enter the name of the myVelib network and the station id", errContent.toString().trim());
    }
}
