package test;
import main.MyVelib;

public class TestSetUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyVelib myvelib = new MyVelib();
		
		myvelib.myVelibSetUp(10, 10, 70, 5);
		System.out.println("c'est ok " + MyVelib.getList_stations().get(5));
		
		myvelib.myVelibSetUp(10, 10, 70, 5, 10);
		System.out.println("c'est ok bis" + MyVelib.getList_users().size());
		System.out.println("c'est ok bis" + MyVelib.getList_stations().size());
	}

}
