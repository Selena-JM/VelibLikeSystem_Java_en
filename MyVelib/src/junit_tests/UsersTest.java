package junit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class UsersTest {

	@Test
	void testUniqueID() {
		Coordinates coordinates = new Coordinates(1.0,2.0);
		CreditCard credit_card = new CreditCard(200.0);
		Users user1 = new Users("Alice", coordinates, credit_card, "Vmax");
		Users user2 = new Users("Alice", coordinates, credit_card, "Vmax");
		assertTrue(user1.getID()!=user2.getID());
	}
	@Test
    public void testConstructorWithCoordinates() {
        String name = "John";
        Coordinates coordinates = new Coordinates(10.0, 20.0);
        CreditCard creditCard = new CreditCard(100.0);
        String typeSubscribing = "Vlibre";

        Users user = new Users(name, coordinates, creditCard, typeSubscribing);

        assertEquals(name, user.getName());
        assertEquals(coordinates, user.getCoordinates());
        assertEquals(creditCard, user.getCredit_card());
        assertEquals(typeSubscribing, user.getType_subscribing());
    }

    @Test
    public void testConstructorWithoutCoordinates() {
        String name = "Marie";
        CreditCard creditCard = new CreditCard(200.0);
        String typeSubscribing = "Vmax";

        Users user = new Users(name, creditCard, typeSubscribing);

        assertEquals(name, user.getName());
        assertNull(user.getCoordinates());
        assertEquals(creditCard, user.getCredit_card());
        assertEquals(typeSubscribing, user.getType_subscribing());
    }

    @Test
    public void testToString() {
        String name = "John";
        Coordinates coordinates = new Coordinates(10.0, 20.0);
        CreditCard creditCard = new CreditCard(100.0);
        String typeSubscribing = "Vlibre";

        Users user = new Users(name, coordinates, creditCard, typeSubscribing);

        String expectedString = "User : (ID = " + user.getID() + ", name = " + name + ", credit card ID = "
                + creditCard.getID() + ", type of subscribing = " + typeSubscribing + ")";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testEquals() {
        String name = "John";
        Coordinates coordinates = new Coordinates(10.0, 20.0);
        CreditCard creditCard = new CreditCard(100.0);
        String typeSubscribing = "Vlibre";

        Users user1 = new Users(name, coordinates, creditCard, typeSubscribing);
        Users user2 = new Users(name, coordinates, creditCard, typeSubscribing);
        Users user3 = new Users("Jane", coordinates, creditCard, typeSubscribing);
        
        assertEquals(user1,user1);
        assertNotEquals(user1, user2);
        assertNotEquals(user1, user3);
    }
}
