package main;

import balance_update_from_log.BalanceObserver;
import pricing.Pricing;

/**
 * This class describes a credit card, knowing that every user has a credit card.
 */
public class CreditCard implements BalanceObserver{
	/**
	 * The user possessing the credit card
	 */
	private Users user;
	/**
	 * The balance of the credit card
	 */
	private double balance;
	/**
	 * The unique ID of the credit card
	 */
	private int ID;
	
	/**
	 * The first unique ID
	 */
	protected static int uniqid = 0;
	/**
	 * Generating a unique ID
	 * @return the unique ID
	 */
	protected static int getuniqID() {
		return ++uniqid;
	}
	
	
	/**
	 * Gets the user of the credit card
	 * 
	 * @return the user of the credit card
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Sets the user of the credit card
	 * 
	 * @param user the user of the credit card
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Gets the balance of the credit card
	 * 
	 * @return the balance of the credit card
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Sets the balance of the credit card
	 * 
	 * @param balance the balance of the credit card
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Gets the ID of the credit card
	 * 
	 * @return the ID of the credit card
	 */
	public int getID() {
		return ID;
	}
	
	
	/**
	 * Instantiates a credit card
	 * 
	 * @param balance the initial balance
	 */
	public CreditCard(double balance) {
		super();
		this.balance = balance;
		this.ID = getuniqID();
	}
	
	/**
	 * Instantiates a credit card with no balance
	 */
	public CreditCard() {
		super();
		this.ID = getuniqID();
	}

	/**
	 * Updates the balance of the user regarding its trip
	 * 
	 * @param trip the trip of the user possessing the credit card
	 */
	@Override
	public void update(Trip trip) {
		// updates the credit-card balance with the observer pattern
		if(trip.getUser() == this.user) {
			this.balance = this.balance - Pricing.setStrategy(trip).setPrice(trip).get(0);
		}
	}

}
