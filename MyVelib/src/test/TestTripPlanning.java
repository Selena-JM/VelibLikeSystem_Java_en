package test;

import java.util.ArrayList;

import main.Coordinates;
import main.MyVelib;
import terminals.TripPlanningClient;

public class TestTripPlanning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyVelib myvelib = new MyVelib();
		
		myvelib.myVelibSetUp(10, 10, 70, 10, 20); //10 stations with 10 slots each, 70 bikes in total, it is a square of 4 km and it has 20 users
		
		ArrayList<Coordinates> start_end = TripPlanningClient.tripPlanningClient();
		System.out.println(start_end);
	}

}
