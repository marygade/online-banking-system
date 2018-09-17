package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Account;
import model.Transaction;

public class TransactionDAO {
	private Connection connection;

	public TransactionDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Bank", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int withdraw(Transaction transaction) {
		int result = 0;
		String sp = "{call WITHDRAW_AMOUNT(?,?,?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall(sp);
			callableStatement.setInt(1, transaction.getFromAccount());
			callableStatement.setDouble(2, transaction.getAmount());
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();
			result = callableStatement.getInt(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deposit(Transaction transaction) {
		int result = 0;
		String sp = "{call DEPOSIT_AMOUNT(?,?,?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall(sp);
			callableStatement.setInt(1, transaction.getFromAccount());
			callableStatement.setDouble(2, transaction.getAmount());
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();
			result = callableStatement.getInt(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int transfer(Transaction transaction) {
		int result = 0;
		String sp = "{call TRANSFER_AMOUNT(?,?,?,?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall(sp);
			callableStatement.setInt(1, transaction.getFromAccount());
			callableStatement.setInt(2, transaction.getToAccount());
			callableStatement.setDouble(3, transaction.getAmount());
			callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();
			result = callableStatement.getInt(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Transaction> getTransactions(Account account) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		String sp = "select * from table (get_transactions(" + account.getAccountNumber() + "));";
		Statement statement;
		try {
			statement = connection.createStatement();
			System.out.println(sp);
			ResultSet resultSet = statement.executeQuery(sp);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int fromAccount = resultSet.getInt("from_account");
				int toAccount = resultSet.getInt("to_account");
				String type = resultSet.getString("type");
				double amount = resultSet.getDouble("amount");
				Transaction transaction = new Transaction(id, fromAccount, toAccount, type, amount);
				transactions.add(transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}
}