package refactoring.day1.practice09.Etc.tdd;

public class Account {
	private int balance;
	
	public Account(int money) {
		this.balance = money;
	}

	public void deposit(int money){
		this.balance += money;
	}
	
	public void withdraw(int money){
		this.balance -= money;
	}
	
	public int getBalance(){
		return this.balance;
	}
	
	public double getCompoundInterest( int year, double interest){
		return 0;
	}
}
