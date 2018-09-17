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
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amount = 0;
		try {
			Account account = (Account) request.getSession().getAttribute("loggedAccount");
			
			amount = Double.parseDouble(request.getParameter("amount").toString());
			int fromAccount = account.getAccountNumber();
			
			TransactionDAO td = new TransactionDAO();
			Transaction transaction = new Transaction(fromAccount, amount);
			int result = td.withdraw(transaction);
			if(result == -2) {
				request.getSession().setAttribute("wthFail", "Invalid Amount");
			} else if(result == -1) {
				request.getSession().setAttribute("wthFail", "Insufficient balance");
			} else {
				account.setBalance(account.getBalance() - amount);
				request.getSession().setAttribute("loggedAccount", account);
				request.getSession().setAttribute("wthPass", "Withdraw succesful");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("wthFail", "Invalid Amount");
		}
		response.sendRedirect(request.getContextPath() + "/Withdraw.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
