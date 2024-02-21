package junit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import main.*;


public class CreditCardTest {
    private Users user;
    private CreditCard creditCard;

    @BeforeEach
    public void setUp() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        creditCard = new CreditCard(100.0);
        user = new Users("John", coordinates, creditCard, "Vlibre");
        creditCard.setUser(user);
    }

    @Test
    public void testGetUser() {
        assertEquals(user, creditCard.getUser());
    }

    @Test
    public void testSetUser() {
        Users newUser = new Users("Alice", new Coordinates(3.0, 4.0), new CreditCard(200.0), "Vmax");
        creditCard.setUser(newUser);
        assertEquals(newUser, creditCard.getUser());
    }

    @Test
    public void testGetBalance() {
        assertEquals(100.0, creditCard.getBalance());
    }

    @Test
    public void testSetBalance() {
        creditCard.setBalance(200.0);
        assertEquals(200.0, creditCard.getBalance());
    }

    @Test
    public void testGetID() {
        assertNotNull(creditCard.getID());
    }

    @Test
    public void testCreditCardWithBalance() {
        CreditCard card = new CreditCard(50.0);
        assertEquals(50.0, card.getBalance());
        assertNotNull(card.getID());
    }

    @Test
    public void testCreditCardWithoutBalance() {
        CreditCard card = new CreditCard();
        assertEquals(0.0, card.getBalance());
        assertNotNull(card.getID());
    }

    @Test
    public void testUpdateBalanceWithDifferentUser() {
        Users otherUser = new Users("Alice", new Coordinates(3.0, 4.0), new CreditCard(200.0), "Vmax");
        Bicycles bicycle = new Bicycles("electrical", new Coordinates(5.0, 6.0), "available");
        Trip trip = new Trip(new Coordinates(1.0, 2.0), new Coordinates(3.0, 4.0), otherUser, bicycle, 60);
        creditCard.update(trip);
        assertEquals(100.0, creditCard.getBalance());
    }
}


