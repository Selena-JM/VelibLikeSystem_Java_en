package main;

import balance_update_from_log.UserBalance;

/**
 * This class describes a user of our system.
 */
public class Users {
	/**
	 * The unique ID of the user
	 */
	private int ID;
	/**
	 * The name of the user
	 */
	private String name;
	/**
	 * The coordinates of the user
	 */
	private Coordinates coordinates;
	/**
	 * The credit card attached to the user
	 */
	private CreditCard credit_card;
	/**
	 * The subscribing option of the user: no card, Vlibre or Vmax
	 */
	private String type_subscribing; //no card, Vlibre or Vmax
	/**
	 * The balance of the user
	 */
	private UserBalance user_balance;
	
	/**
	 * The first unique ID
	 */
	protected static int uniqid = 0; 
	/**
	 * Generating a unique ID for the users
	 * @return the unique ID
	 */
	protected static int getuniqID() {
		return ++uniqid;
	}
	
	/*
	 * Generating Getters and Setters
	 */
	
	/**
	 * Gets the ID
	 * 
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Gets the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name
	 * 
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the coordinates
	 * 
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * Sets the coordinates
	 * 
	 * @param coordinates the coordinates
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * Gets the credit card
	 * 
	 * @return the credit card
	 */
	public CreditCard getCredit_card() {
		return credit_card;
	}
	
	/**
	 * Sets the credit card
	 * 
	 * @param credit_card the credit card
	 */
	public void setCredit_card(CreditCard credit_card) {
		this.credit_card = credit_card;
	}

	/** 
	 * Gets the type of subscribing
	 * 
	 * @return the type of subscribing
	 */
	public String getType_subscribing() {
		return type_subscribing;
	}

	/**
	 * Sets the type of subscribing
	 * 
	 * @param type_subscribing the type of subscribing
	 */
	public void setType_subscribing(String type_subscribing) {
		this.type_subscribing = type_subscribing;
	}
	
	/**
	 * Gets the user balance
	 * 
	 * @return the user balance
	 */
	public UserBalance getUser_balance() {
		return user_balance;
	}

	/**
	 * Sets the user balance
	 * 
	 * @param user_balance the user balance
	 */
	public void setUser_balance(UserBalance user_balance) {
		this.user_balance = user_balance;
	}
	
	/**
     * Instantiates a new user
     *
     * @param name the name of the user
     * @param coordinates the coordinates of the user
     * @param credit_card the credit card of the user
     * @param type_subscribing the type of subscribing of the user
     */
	public Users(String name, Coordinates coordinates, CreditCard credit_card, String type_subscribing) {
		super();
		this.ID= Users.getuniqID();
		this.name = name;
		this.coordinates = coordinates;
		this.credit_card = credit_card;
		this.type_subscribing = type_subscribing;
	}
	
	/**
     * Instantiates a new user with no coordinates
     *
     * @param name the name of the user
     * @param credit_card the credit card of the user
     * @param type_subscribing the type of subscribing of the user
     */
	public Users(String name, CreditCard credit_card, String type_subscribing) {
		super();
		this.ID = getuniqID();
		this.name = name;
		this.credit_card = credit_card;
		this.type_subscribing = type_subscribing;
	}
	
	/**
	 * Gets the ID, name, coordinates, credit_card ID and type of subscribing of the user
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "User : (ID = " + ID + ", name = " + name + ", credit card ID = " + credit_card.getID()
				+ ", type of subscribing = " + type_subscribing + ")";
	}

	/**
	 * Method to compare users properly
	 * 
	 * @param ob object (supposed to be a Users object)
	 */
	public boolean equals(Object ob){
		if(ob instanceof Users) {
			Users user1 = (Users) ob;
		return (user1.getID() == this.ID);
		}
		return false;
		}

}
