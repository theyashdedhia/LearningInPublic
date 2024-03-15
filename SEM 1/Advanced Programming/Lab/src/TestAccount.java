
class Account
{
	public String name;
	private String ID;
	protected double balance;
	public Account(String name, String ID, double balance) {
		this.name = name;
		this.ID = ID;
		this.balance = balance;
	}
	public double getBalance()
	{
		return balance;
	}
	public void withdraw(double amt)
	{
		if(amt <= balance) balance -= amt;	
	}
	public void deposit(double amt)
	{
		if(amt>0) balance += amt;
	}
}

class CAccount extends Account{
	private double overdraftLimit;
	private double overdraftTaken;
	public CAccount(String name, String ID, double balance, double overdraftLimit, double overdraftTaken){
		super(name, ID, balance);
		this.overdraftLimit = overdraftLimit;
		this.overdraftTaken = overdraftTaken; 
	}
	
	public void withdraw(double amt){
		if(amt <= ) balance -= amt;
	}
}
public class TestAccount{
	public static void main(String args[]){
		Account a1 = new Account("Tan A K", "S123", 24.5);
		Account a2 = new Account("Smith T", "S124", 1200.0);
		a1.deposit(100);
		a1.withdraw(2000);
		a2.deposit(120);
		a2.withdraw(80);
		System.out.println("Balance for " + a1.name + " is " + a1.getBalance());
		System.out.println("Balance for " + a2.name + " is " + a2.getBalance());
	}
}
