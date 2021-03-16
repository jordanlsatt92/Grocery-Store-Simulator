/**
 * 
 */
package edu.unca.csci202;

import java.util.Random;

/**
 * Customer contains the methods and variables necessary to simulate a customer
 * entering a line at a grocery store.
 * @author Jordan Satterfield
 */
public class Customer {

	Random ran = new Random();
	
	
	//instance variables
	private int numberOfItems;
	private int remainingTime;
	
	public Customer(int maxItems, int timeToScan) {
		super();
		this.numberOfItems = maxItems;
		this.remainingTime = this.numberOfItems * timeToScan;
	}
	
	/**
	 * Gets the number of items the customer has.
	 * @return the numberOfItems
	 */
	public int getNumberOfItems() {
		return numberOfItems;
	}

	/**
	 * Gets the customer's remaining time until the end of the transaction.
	 * @return the remainingTime
	 */ 
	public int getRemainingTime() {
		return remainingTime;
	}
	
	/**
	 * Decrements the time left during the transaction.
	 */
	public void decrementTime() {
			this.remainingTime --;
	}









	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
