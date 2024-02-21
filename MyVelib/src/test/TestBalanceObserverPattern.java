package test;

import balance_update_from_log.LogFinishedTrips;
import balance_update_from_log.UserBalance;
import main.Bicycles;
import main.Coordinates;
import main.CreditCard;
import main.MyVelib;
import main.Trip;
import main.Users;

public class TestBalanceObserverPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyVelib myvelib = new MyVelib();
		
		myvelib.myVelibSetUp(10, 10, 70, 4); //10 stations with 10 slots each, 70 bikes in total, it is a square of 4 km and it has 20 users
		LogFinishedTrips log_finished_trips = new LogFinishedTrips();
		
		// To get random users 
		/*
		Users user1 = MyVelib.getList_users().get(0);
		System.out.println("User 1 :" + user1);
		
		Users user2 = MyVelib.getList_users().get(1);
		System.out.println("User 2 :" + user2);
		*/
		
		CreditCard john_card = new CreditCard(100);
		Users user1 = new Users("John", john_card, "no card");
		john_card.setUser(user1);
		MyVelib.addUser(user1);
		UserBalance user1_balance = new UserBalance(user1, 0, 0, 0, 0);
		user1.setUser_balance(user1_balance);
		
		CreditCard patrick_card = new CreditCard(50);
		Users user2 = new Users("Patrick", patrick_card, "Vlibre");
		patrick_card.setUser(user2);
		MyVelib.addUser(user2);
		UserBalance user2_balance = new UserBalance(user2, 10, 100, 20, 10);
		user2.setUser_balance(user2_balance);
		
		CreditCard sandra_card = new CreditCard(50);
		Users user3 = new Users("Sandra", sandra_card, "Vmax");
		sandra_card.setUser(user3);
		MyVelib.addUser(user3);
		UserBalance user3_balance = new UserBalance(user3, 20, 200, 20, 5);
		user3.setUser_balance(user3_balance);
		
		
		log_finished_trips.registerObserver(user1_balance);
		log_finished_trips.registerObserver(user2_balance);
		log_finished_trips.registerObserver(user3_balance);
		
		// Testing the updating of the user balance
		Coordinates c1 = MyVelib.getList_stations().get(0).getCoordinates();
		Coordinates c2 = MyVelib.getList_stations().get(1).getCoordinates();
		Coordinates c3 = new Coordinates(Math.random()*10, Math.random()*10);
		Bicycles bike1 = new Bicycles("electrical", c1, "available");
		Bicycles bike2 = new Bicycles("mechanical", c1, "available");
		
		
		Trip trip1 = new Trip(c1, c2, user1, bike1, 20);
		LogFinishedTrips.addTrip(trip1);
		
		System.out.println(user1_balance);
		
		Trip trip2 = new Trip(c1, c2, user1, bike2, 20);
		LogFinishedTrips.addTrip(trip2);
		
		
		Trip trip3 = new Trip(c1, c3, user3, bike1, 20);
		LogFinishedTrips.addTrip(trip3);
		
		Trip trip4 = new Trip(c1, c3, user2, bike1, 220);
		LogFinishedTrips.addTrip(trip4);
		
		System.out.println("nombre de trips : " + LogFinishedTrips.getTrips().size());
		
		System.out.println(user1_balance);
		System.out.println(user2_balance);
		System.out.println(user3_balance);
		
		System.out.println(MyVelib.getList_stations().get(0).getStation_balance());
		System.out.println(MyVelib.getList_stations().get(1).getStation_balance());
	}

}
