package junit_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.*;
import clui.*;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SetUpTest {
	
	@BeforeEach
    public void setUp() {
        MyVelib.setName(null);
        MyVelib.setList_stations(new ArrayList<>());
        MyVelib.setList_users(new ArrayList<>());
        MyVelib.setList_bicycle(new ArrayList<>());
    }
	
    @Test
    public void testSetUpWithTwoArguments() {
        String[] arguments = {"setup", "MySystem"};
        
        SetUp.main(arguments);
        
        assertEquals("MySystem", MyVelib.getName());
        assertEquals(10, MyVelib.getList_stations().size());
        assertEquals(70, MyVelib.getList_bicycle().size());
        assertEquals(0, MyVelib.getList_users().size());
    }
    
    @Test
    public void testSetUpWithSixArguments() {
        String[] arguments = {"setup", "MySystem", "2", "5", "10", "50"};
        
        SetUp.main(arguments);
        
        assertEquals("MySystem", MyVelib.getName());
        assertEquals(2, MyVelib.getList_stations().size());
        assertEquals(50, MyVelib.getList_bicycle().size());
        assertEquals(0, MyVelib.getList_users().size());
    }
    
    @Test
    public void testSetUpWithSevenArguments() {
        String[] arguments = {"setup", "MySystem", "2", "5", "10", "50", "3"};
        
        SetUp.main(arguments);
        
        assertEquals("MySystem", MyVelib.getName());
        assertEquals(2, MyVelib.getList_stations().size());
        assertEquals(50, MyVelib.getList_bicycle().size());
        assertEquals(3, MyVelib.getList_users().size());
    }
    
    @Test
    public void testSetUpWithInvalidArguments() {
        String[] arguments = {"setup", "MySystem", "something"};
        
        // Capture the error message printed to the error stream
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errStream));
        
        SetUp.main(arguments);
        
        // Restore the error stream
        System.setErr(System.err);
        
        String errorMessage = errStream.toString().trim();
        assertTrue(errorMessage.startsWith("Try with only the name"));
    }
}
