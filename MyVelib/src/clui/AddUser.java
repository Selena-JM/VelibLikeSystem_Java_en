package clui;

import balance_update_from_log.LogFinishedTrips;
import main.CreditCard;
import main.MyVelib;
import main.Users;
import balance_update_from_log.UserBalance;

/**
 * The AddUser class provides a command-line interface for adding users to the MyVelib system.
 */

public class AddUser {
	/**
	 * The main method of the AddUser class.
	 * It is the entry point for the command-line interface.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length == 4) {
			if(MyVelib.getName().equalsIgnoreCase(args[3])){
				if(args[2].equalsIgnoreCase("none")) {
				CreditCard creditcard = new CreditCard();
				Users user = new Users(args[1], creditcard, "no card");
				
				UserBalance user_balance = new UserBalance(user, 0, 0, 0, 0);
				user.setUser_balance(user_balance);
				
				LogFinishedTrips logFinishedTrips = new LogFinishedTrips();
				logFinishedTrips.registerObserver(user_balance);
				
				MyVelib.addUser(user);
				System.out.println("User added : " + user );
				}

				else if (args[2].equalsIgnoreCase("Vlibre") || args[2].equalsIgnoreCase("Vmax")){
					CreditCard creditcard = new CreditCard();
					Users user = new Users(args[1], creditcard, args[2]);

					UserBalance user_balance = new UserBalance(user, 0, 0, 0, 0);
					user.setUser_balance(user_balance);
					
					LogFinishedTrips logFinishedTrips = new LogFinishedTrips();
					logFinishedTrips.registerObserver(user_balance);
					
					MyVelib.addUser(user);
					System.out.println("User added : " + user );
					}

				else {
					System.err.println("No such type of subscribing");
				}
			}
			else {
				System.err.println("No MyVelib network with this name existing");
			}
		}
		else {
			System.err.println("Please enter user name, card type and the name of the myVelib network");
		}
	}
}
