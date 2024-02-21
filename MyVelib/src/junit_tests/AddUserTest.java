package junit_tests;

import static org.junit.jupiter.api.Assertions.*;

import clui.AddUser;
import main.MyVelib;
import main.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AddUserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        MyVelib.setName(null);
        MyVelib.setList_stations(new ArrayList<>());
        MyVelib.setList_users(new ArrayList<>());
        MyVelib.setList_bicycle(new ArrayList<>());
    }

    @Test
    public void testAddUser_NoneCardType() {

        // Set the MyVelib instance as the current network
        MyVelib.setName("myVelib");

        // Execute the AddUser command with none card type
        AddUser.main(new String[]{"command", "Vincent", "none", "myVelib"});

        // Check if the user is added correctly
        Users user = MyVelib.getList_users().get(0);
        assertNotNull(user);
        assertEquals("Vincent", user.getName());
        assertEquals("no card", user.getType_subscribing());
        assertEquals("User added : " + user, outContent.toString().trim());
    }

    @Test
    public void testAddUser_VlibreCardType() {

        // Set the MyVelib instance as the current network
        MyVelib.setName("myVelib");

        // Execute the AddUser command with Vlibre card type
        AddUser.main(new String[]{"command", "Annie", "Vlibre", "myVelib"});

        // Check if the user is added correctly
        Users user = MyVelib.getList_users().get(0);
        assertNotNull(user);
        assertEquals("Annie", user.getName());
        assertEquals("Vlibre", user.getType_subscribing());
        assertEquals("User added : " + user, outContent.toString().trim());
    }

    @Test
    public void testAddUser_VmaxCardType() {

        // Set the MyVelib instance as the current network
        MyVelib.setName("myVelib");

        // Execute the AddUser command with Vmax card type
        AddUser.main(new String[]{"command", "Alice", "Vmax", "myVelib"});

        // Check if the user is added correctly
        Users user = MyVelib.getList_users().get(0);
        assertNotNull(user);
        assertEquals("Alice", user.getName());
        assertEquals("Vmax", user.getType_subscribing());
        assertEquals("User added : " + user, outContent.toString().trim());
    }

    @Test
    public void testAddUser_NonExistingNetwork() {

        // Set the MyVelib instance as the current network
        MyVelib.setName("myVelib");

        // Execute the AddUser command with a non-existing network
        AddUser.main(new String[]{"command", "Vincent", "none", "nonExistingNetwork"});

        // Check if the error message is displayed
        assertEquals("No MyVelib network with this name existing", errContent.toString().trim());
    }

    @Test
    public void testAddUser_InvalidArguments() {

        // Set the MyVelib instance as the current network
        MyVelib.setName("myVelib");

        // Execute the AddUser command with invalid arguments
        AddUser.main(new String[]{"command", "Vincent", "none"});

        // Check if the error message is displayed
        assertEquals("Please enter user name, card type and the name of the myVelib network", errContent.toString().trim());
    }
}
