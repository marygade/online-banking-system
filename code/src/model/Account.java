package model;

public class Account {
	private int id, accountNumber;
	private String name, email, password;
	private double balance;

	public Account(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Account(int id, int accountNumber, String name, String email, String password, double balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.balance = balance;
	}

	public Account(int accountNumber, String name, String email, String password, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.balance = balance;
	}

	public Account(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}