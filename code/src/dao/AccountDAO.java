package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	private Connection connection;

	public AccountDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Bank", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean createAccount(Account account) {
		boolean result = false;
		String sp = "{call CREATE_ACCOUNT(?,?,?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall(sp);
			callableStatement.setString(1, account.getName());
			callableStatement.setString(2, account.getEmail());
			callableStatement.setString(3, account.getPassword());
			callableStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Account login(Account account) {
		String sp = "{call login(?,?,?,?,?,?,?)}";
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall(sp);
			callableStatement.setString(1, account.getEmail());
			callableStatement.setString(2, account.getPassword());
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
			callableStatement.registerOutParameter(6, java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter(7, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();

			int status = callableStatement.getInt(7);
			if (status == 1) {
				account.setId(callableStatement.getInt(3));
				account.setName(callableStatement.getString(4));
				account.setAccountNumber(callableStatement.getInt(5));
				account.setBalance(callableStatement.getDouble(6));
			} else {
				account = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			account = null;
		}
		return account;
	}
}