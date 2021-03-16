package edu.unca.csci202;

/**
 * StoreDriver creates a instances of GroceryStore, runs the simulation, and displays
 * the results.
 * @author Jordan Satterfield
 */
public class StoreDriver {

	public static void main(String[] args) {
		
		GroceryStore g1 = new GroceryStore(5);
		
		g1.run(3600, 0.1, 7, 10); // time measured in seconds, total is one hour; the right number of checkouts
		
		System.out.println("========= Grocery Store 1 Stats =========");
		g1.printData();
		System.out.println();
		
		GroceryStore g2 = new GroceryStore(9);
		
		g2.run(36000, 0.18, 5, 20); // times measured in seconds, total is 10 hours; too few checkouts
		
		System.out.println("========= Grocery Store 2 Stats =========");
		g2.printData();
		System.out.println();
		
		GroceryStore g3 = new GroceryStore(7);
		
		g3.run(18000, 0.04, 6, 7); // times measured in seconds, total is 5 hours; too many checkouts
		
		System.out.println("========= Grocery Store 3 Stats =========");
		g3.printData();
	}

}
