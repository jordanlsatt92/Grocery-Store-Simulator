package edu.unca.csci202;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/**
 * GroceryStore contains the variables and methods to simulate simulate lines in a grocery store.
 * @author Jordan Satterfield
 */
public class GroceryStore {

	private Random ran = new Random();
	//instance variables
	private ArrayList<Queue<Customer>> lines = new ArrayList<Queue<Customer>>();
	private ArrayList<Integer> maxSizeOfLines = new ArrayList<Integer>();
	private int numOfLines;
	private int numOfTimeSteps;
	private double probability;
	private int timeToScan;
	private int maxNumOfItems;
	private boolean linesEmpty = true;
	private boolean linesEqual = false;
	private int customersServed = 0;
	
	public GroceryStore(int numOfLines) {
		super();
		this.numOfLines = numOfLines;
		
		for (int i = 0; i < this.numOfLines; i++) {
			Queue<Customer> deque = new ArrayDeque<Customer>();
			this.lines.add(deque);
			this.maxSizeOfLines.add(0);
		}
	}
	
	/**
	 * Returns the queue (line) with the lowest size, or random queue if the lines are empty.
	 * @return the queue with the lowest size, or random queue if the lines are empty.
	 */
	private Queue<Customer> getShortestLine() {
		Queue<Customer> shortest = this.lines.get(0);
		for (int i = 0; i < this.lines.size(); i ++) {
			if (this.lines.get(i).size() == 0) {
				this.linesEmpty = true;
			}else {
				this.linesEmpty = false;
				break;
			}
		}
		if (this.linesEmpty) {
			return this.lines.get(ran.nextInt(lines.size()));
		}
		for (int i = 1; i < this.lines.size(); i++) {
			if (shortest.size() == this.lines.get(i).size()) {
				this.linesEqual = true;
			}else {
				this.linesEqual = false;
				break;
			}
		}
		if(this.linesEqual) {
			return shortest;
		}
		for (int i = 0; i < this.lines.size(); i++) {
			if (this.lines.get(i).size() < shortest.size()) {
				shortest = this.lines.get(i);
			}
		}
		return shortest;
	}

	/**
	 * Runs the simulation of the grocery store.
	 * Loops through the number of time steps, adds customers to lines.
	 * @param numOfTimeSteps - the number of steps run through the simulation
	 * @param probability - the chance a customer will appear in that time step.
	 * @param timeToScan - the maximum number of time it takes to scan and bag an item.
	 * @param maxNumOfItems - the maximum number of items each customer can purchace.
	 */
	public void run(int numOfTimeSteps, double probability, int timeToScan, int maxNumOfItems) {
		this.numOfTimeSteps = numOfTimeSteps;
		this.probability = probability;
		this.timeToScan = timeToScan;
		this.maxNumOfItems = maxNumOfItems;
		
		for (int i = 0; i < this.numOfTimeSteps; i++) {
			for (int j = 0; j < lines.size(); j++) {
				if (!lines.get(j).isEmpty()) {
					Customer current = lines.get(j).peek();
					current.decrementTime();
					if (current.getRemainingTime() == 0) {
						lines.get(j).remove();
						this.customersServed ++;
					}
				}
			}
			if (ran.nextDouble() < this.probability) {
				Customer customer = new Customer(ran.nextInt(this.maxNumOfItems) + 1, this.timeToScan);
				this.getShortestLine().add(customer);
			}
			for (int k = 0; k < this.maxSizeOfLines.size(); k ++) {
				if (this.maxSizeOfLines.get(k) < this.lines.get(k).size()) {
					this.maxSizeOfLines.set(k, this.lines.get(k).size());
				}
			}
		}
		
	}
	/**
	 * Prints the data from the grocery store after the simulation.
	 */
	public void printData() {
		System.out.println("Number of time steps: " + this.numOfTimeSteps);
		System.out.println("Number of lines: " + this.numOfLines);
		System.out.println("Probability of customer arrival: " + this.probability);
		System.out.println("Time to scan item: " + this.timeToScan);
		System.out.println("Maximum number of items: " + this.maxNumOfItems);
		String ret1 = "Maximum length of each checkout: ";
		String ret2 = "Number of customers left in each checkout: ";
		for (int i = 0; i < this.numOfLines; i++) {
			if (i == this.numOfLines - 1) {
				ret1 += this.maxSizeOfLines.get(i);
				ret2 += this.lines.get(i).size();
				break;
			}
			ret1 += this.maxSizeOfLines.get(i) + ", ";
			ret2 += this.lines.get(i).size() + ", ";
		}
		System.out.println(ret1);
		System.out.println(ret2);
		System.out.println("Number of customers served: " + this.customersServed);
	}
	
	
}
