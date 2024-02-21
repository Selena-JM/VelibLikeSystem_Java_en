package test;

import java.util.ArrayList;

import balance_update_from_log.UserBalance;
import main.*;
import pricing.*;

public class TestPricing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyVelib myvelib = new MyVelib();
		
		
		myvelib.myVelibSetUp(10, 10, 70, 10); //10 stations with 10 slots each, 70 bikes in total, it is a square of 4 km and it has 20 users
		
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
		
		
		// Test from a station to another station
		Coordinates c1 = MyVelib.getList_stations().get(0).getCoordinates();
		Coordinates c2 = MyVelib.getList_stations().get(1).getCoordinates();

		Bicycles bike1 = new Bicycles("electrical", c1, "available");
		
		
		Trip trip1 = new Trip(c1, c2, user1, bike1, 20);
		PricingStategy pricingstrategy = Pricing.setStrategy(trip1);
		ArrayList<Double> res = pricingstrategy.setPrice(trip1);
		
		System.out.println(res);
		
		
		// Test for an electrical bike 
		Bicycles bike2 = new Bicycles("mechanical", c1, "available");
		
		Trip trip2 = new Trip(c1, c2, user1, bike2, 20);
		PricingStategy pricingstrategy2 = Pricing.setStrategy(trip2);
		ArrayList<Double> res2 = pricingstrategy2.setPrice(trip2);
		
		System.out.println(res2);
		
		
		// Test for different users (different type of subscribing) 
		Trip trip4 = new Trip(c1, c2, user2, bike1, 20);
		PricingStategy pricingstrategy4 = Pricing.setStrategy(trip4);
		ArrayList<Double> res4 = pricingstrategy4.setPrice(trip4);
		
		System.out.println(res4);
		
		
		// Test from a station to free parking
		Coordinates c3 = new Coordinates(Math.random()*10, Math.random()*10);
		
		Trip trip3 = new Trip(c1, c3, user1, bike1, 20);
		PricingStategy pricingstrategy3 = Pricing.setStrategy(trip3);
		ArrayList<Double> res3 = pricingstrategy3.setPrice(trip3);
		
		System.out.println(res3);
		
		
		// Test from free parking to a station
		Trip trip5 = new Trip(c3, c1, user1, bike1, 20);
		PricingStategy pricingstrategy5 = Pricing.setStrategy(trip5);
		ArrayList<Double> res5 = pricingstrategy5.setPrice(trip5);
		
		System.out.println(res5);
	}

}
