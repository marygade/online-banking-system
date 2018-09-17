package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransactionDAO;
import model.Account;
import model.Transaction;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amount = 0;
		boolean validAmount = false;
		try {
			Account account = (Account) request.getSession().getAttribute("loggedAccount");
			
			amount = Double.parseDouble(request.getParameter("amount").toString());
			validAmount = true;
			int toAccount = Integer.parseInt(request.getParameter("to").toString());
			int fromAccount = account.getAccountNumber();
			
			TransactionDAO td = new TransactionDAO();
			Transaction transaction = new Transaction(fromAccount, toAccount, amount);
			int result = td.transfer(transaction);
			if(result == -3) {
				request.getSession().setAttribute("traFail", "Invalid Amount");
			} else if(result == -2) {
				request.getSession().setAttribute("traFail", "Insufficient balance");
			} else if(result == -1) {
				request.getSession().setAttribute("traFail", "Invalid To account number");
			} else {
				account.setBalance(account.getBalance() - amount);
				request.getSession().setAttribute("loggedAccount", account);
				request.getSession().setAttribute("traPass", "Transfer succesful");
			}
		} catch (Exception e) {
			if(!validAmount) {
				request.getSession().setAttribute("traFail", "Invalid Amount");
			} else {
				request.getSession().setAttribute("traFail", "Invalid to account number");
			}
		}
		response.sendRedirect(request.getContextPath() + "/Transfer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
