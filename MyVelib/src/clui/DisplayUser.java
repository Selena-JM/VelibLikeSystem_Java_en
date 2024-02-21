package clui;

import main.MyVelib;
import main.Users;
/**
 * The DisplayUser class provides functionality to display information about a specific user in the MyVelib system.
 * Users can view the balance information of a user by providing the MyVelib network name and the user ID as input.
 * The class searches for the user with the given ID and displays their balance information if found.
 */
public class DisplayUser {
	/**
	 * The main method of the DisplayUser class.
	 * It is the entry point for displaying user information in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean good_user_id = false;
		
		if (args.length == 3) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				for(Users user : MyVelib.getList_users()) {
					if(user.getID() == Integer.valueOf(args[2])) {
						good_user_id = true;
						System.out.println(user.getUser_balance());
					}
				}
				if (!good_user_id){
					System.err.println("No user with such id");
				}
			}
			else {
				System.err.println("No myVelib network with this name existing");
			}
		}
		else {
			System.err.println("Please enter a velibnetwork name and a user ID");
		}
	}
}
