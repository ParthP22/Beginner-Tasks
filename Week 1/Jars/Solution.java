package Jars;
import java.io.IOException;

import Bank.*;
import NumberManipulation.*;

/**
 * This class provides my solution to Momo's Week 1 Beginner Task
 * 
 * @author parthpatel0426
 */
public class Solution extends NumberGenerator{

	/**
	 * a <code>private BankAccount[]</code> array that stores various bank accounts
	 */
	private BankAccount[] accounts;
	
	
	/**
	 * a <code>private int</code> variable that keeps track of the current number of accounts in the bank
	 */
	private int totalAcc;
	
	
	/**
	 * a <code>private NumberGenerator</code> object that produces random numbers based on what type and
	 * range the user would like
	 */
	private NumberGenerator randomNum;
	
	
	/**
	 * This is a <code>private</code> default constructor that is used to initialize an instance
	 * of NumberGenerator within the <code>public</code> constructor
	 */
	private Solution() {
		totalAcc = 0;
	}
	
	
	/**
	 * This <code>public</code> constructor initializes the bank so that accounts may be created and stored
	 * 
	 * @param amount <code>int</code> parameter indicating the maximum number of accounts stored in the bank
	 */
	public Solution(int amount) {
		accounts = new BankAccount[amount];
		totalAcc = 0;
		randomNum = new Solution();
	}
	
	
	/**
	 * This <code>public void</code> method fully clears the bank of all accounts and sets a new maximum amount of accounts
	 * 
	 * @param amount <code>int</code> parameter indicating the maximum number of accounts stored in the bank
	 */
	public void resetBank(int amount) {
		accounts = new BankAccount[amount];
	}
	
	
	/**
	 * This <code>public void</code> method fully, but randomly, fills the bank, with the first half being boys' accounts
	 * and the second half being girls' accounts
	 */
	public void fillBank() {
		int i = 0;
		for(i = 0; i < accounts.length/2; i++) {
			this.createBoyAccount();
		}
		for(i = accounts.length/2; i < accounts.length; i++) {
			this.createGirlAccount();
		}
		
	}
	

	/**
	 * This <code>public void</code> method creates a boy's account in the bank, and it also randomly creates 
	 * a number of accounts associated with this person
	 */
	public void createBoyAccount() {
		
		double[] tmpAccVal = null;
		int rand = randomNum.randomInt(1,4);
		tmpAccVal = new double[rand];
		for(int j = 0; j < rand; j++) {
			tmpAccVal[j] = randomNum.randomDoubleMoney(1000000);
		}
		accounts[totalAcc++] = new BankAccount(new Person(new NameGenerator().oneBoyName()), new AccountGenerator().getMultipleAccounts(rand) , tmpAccVal);
		
	}
	
	
	/**
	 * This <code>public void</code> method creates a boy's account in the bank, and it also randomly creates 
	 * a number of accounts associated with this person
	 */
	public void createGirlAccount() {
			
		double[] tmpAccVal = null;
		int rand = randomNum.randomInt(1,4);
		tmpAccVal = new double[rand];
		for(int j = 0; j < rand; j++) {
			tmpAccVal[j] = randomNum.randomDoubleMoney(1000000);
		}
		accounts[totalAcc++] = new BankAccount(new Person(new NameGenerator().oneGirlName()), new AccountGenerator().getMultipleAccounts(rand) , tmpAccVal);
		
	}
	
	
	/**
	 * This <code>private void</code> method creates a .csv file and records the information of all bank accounts;
	 * it is a helper function to <code>public void createCSV</code>
	 * 
	 * @throws IOException for errors regarding file input/output
	 */
	private void writeCSV() throws IOException{
		FileGenerator fileGenerator = new FileGenerator("accounts.csv");
		fileGenerator.createFile();
		fileGenerator.writeFile("Name, Account Num., Account 1, Account 2, Account 3");
		for(BankAccount account : accounts) {
			fileGenerator.writeFile(account.getInformation());
			
		}
		fileGenerator.closeFile();
	}
	
	/**
	 * This <code>public void</code> method allows the user to create a .csv file containing all the information
	 * of all the bank accounts, which goes as follows: Name, Account Number, Account 1, Account 2, Account 3
	 * 
	 * @throws IOException for errors regarding file input/output
	 */
	public void createCSV() throws IOException{
		this.writeCSV();
	}
	

	/**
	 * This <code>public void</code> method prints out the information of every person in the bank
	 * 
	 */
	public void printInfo(){
		int i = 0;
		for(BankAccount account : accounts) {
			System.out.println(""+(i+1)+" "+account.getInformation());
			i++;
		}
	}
	
	/**
	 * This <code>public void</code> method allows the user to perform the fillBank(), printInfo(), and createCSV()
	 * methods (all in this specific order)
	 * 
	 * @throws IOException for errors regarding file input/output
	 */
	public void run() throws IOException{
		this.fillBank();
		this.printInfo();
		this.createCSV();
	}
	

}
