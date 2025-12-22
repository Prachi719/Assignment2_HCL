package Assignment2;

public class BankTest {

//	Design a Java program where a bank account class implements multiple interfaces to perform 
//	banking and customer-related operations. The program should handle exceptions as required. 
	
	
	
//5️⃣ Test Cases (Important for Exams)
//	Test Case	Input	Expected Output
//	TC1	Deposit ₹2000	Balance increases successfully
//	TC2	Withdraw ₹1500	Balance decreases successfully
//	TC3	Withdraw more than balance	Exception: Insufficient balance
//	TC4	Deposit ₹0	Exception: Invalid amount
//	TC5	Withdraw negative amount	Exception: Invalid amount
//	TC6	Update mobile number	Mobile number updated
//	TC7	Display customer details	All details printed correctly
	
	public static void main(String args[]){
		//Bank(String name,	int acc,	int pin,	double amount,long no)
		
		Bank b = new Bank("Samiksha Mahale",9999,9090,1500,930373999);
		try {
		b.deposit(500, 9999); //2000
		b.deposit(0, 9999);
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			b.withdraw(9999, 9099, 300);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			b.withdraw(9999, 9090, 3000);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			b.withdraw(9999, 9090, -24);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			double amount = 300.89;
			b.deposit(amount,9999);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		b.displayDetails(9999, 900);
		
		System.out.println();
		b.updateDetails(9999, 9090,"Khushi Mahale", 810392765);
		System.out.println();
		b.displayDetails(9999, 9090);
	}
	
}

class ZeroRupeeException extends Exception{
	public ZeroRupeeException(String msg) {
		System.out.println(msg);
	}
}
class NegativeAmountException extends Exception{
	public NegativeAmountException(String msg) {
		System.out.println(msg);
	}
}
class InsufficientBalanceException extends Exception{
	public InsufficientBalanceException(String msg) {
		System.out.println(msg);
	}
}

interface BankingOperation{
	void deposit(double amount,int account) throws ZeroRupeeException; //done
	void withdraw(int acc,int pin,double amount) throws NegativeAmountException, InsufficientBalanceException ;
	void getBalance(int acc);
}

interface CustomerOperation{
	void updateDetails(int acc,int pin,String name, long no);
	void displayDetails(int acc,int pin);
}

class Bank implements BankingOperation,CustomerOperation{
	String name;
	int acc;
	int pin;
	double amount;
	long no;
	Bank(String name,	int acc,	int pin,	double amount,long no){
		this.acc = acc;
		this.pin = pin;
		this.name = name;
		this.amount = amount;
		this.no = no;
		System.out.println("Account Created Successfully for "+name);
	}
	public void deposit(double amount,int account) throws ZeroRupeeException{
		if(amount ==0) {
			throw new ZeroRupeeException("0 is not sufficient balance to deposit");
		} else if(account == this.acc) {
			this.amount += amount;
			System.out.println("Deposited Successfuly. Total Amount "+this.amount);
		} else {
			System.out.println("Account Number is not Correct");
		}
	}
	
	public void withdraw(int acc,int pin,double amount) throws NegativeAmountException, InsufficientBalanceException {
		if(acc==this.acc && pin == this.pin) {
			if(amount <0) throw new  NegativeAmountException("Exception : Negative Amount can't be withdrawn.");
			else if(amount<=this.amount) {
				this.amount -=amount; 
				System.out.println("Amount Deducted "+amount+ " Total amount : "+this.amount);
			} else {
				throw new InsufficientBalanceException("Exception : Insufficient Balance");
			}
		} else {
			System.out.println("You Entered wrong PIN or Account Number. Try Again!");
		}
	}
	public void getBalance(int acc,int pin) {
		if(acc==this.acc && pin == this.pin) {
			System.out.println("Total Amount : "+this.amount);
		} else {
			System.out.println("You Entered wrong PIN or Account Number. Try Again!");
		}
	}
	
	public void updateDetails(int acc,int pin,String name, long no) {
		if(acc==this.acc && pin == this.pin) {
			this.name = name;
			this.no = no;
			System.out.println("Details Updated");
		} else {
			System.out.println("You Entered wrong PIN or Account Number. Try Again!");
		}
	}
	@Override
	public void displayDetails(int acc, int pin) {
		if(acc==this.acc && pin == this.pin) {
		System.out.println("Customer Name : "+name);
		System.out.println("Amount : "+amount);
		System.out.println("Mobile Number : "+no);
		} 
		else {
			System.out.println("You Entered wrong PIN or Account Number. Try Again!");
		}
	}
	@Override
	public void getBalance(int acc) {
		if(acc==this.acc && pin == this.pin) {
			System.out.println("Total Amount : "+amount);
		}
		else {
			System.out.println("You Entered wrong PIN or Account Number. Try Again!");
		}
		
	}
	
}
