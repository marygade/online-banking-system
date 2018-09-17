<%@ page import="other.Extract" %>
<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	Account account = null;
	if(session.getAttribute("loggedAccount") == null) {
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
		return;
	} else {
		try {
			account = (Account) session.getAttribute("loggedAccount");
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/Login.jsp");
			return;
		}
	}
	TransactionDAO td = new TransactionDAO();
	ArrayList<Transaction> transactions = td.getTransactions(account);
%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<header>
		<h1>Online Banking System</h1>
		<h4>Hello <%=account.getName() %></h4>
	</header>
	<nav>
		<ul>
			<li><a href="Home.jsp">Home</a></li>
			<li><a href="Withdraw.jsp">Withdraw</a></li>
			<li><a href="Deposit.jsp">Deposit</a></li>
			<li><a href="Transfer.jsp">Transfer</a></li>
			<li><a href="Transactions.jsp">Transactions</a></li>
			<li><a href="Logout.jsp">Logout</a></li>
		</ul>
	</nav>
	<div class="content">
		<table>
			<caption>Transactions</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Type</th>
					<th>To</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Transaction transaction : transactions) {
						%>
							<tr>
								<td><%=transaction.getId() %></td>
								<td><%=transaction.getType() %></td>
								<td><%=transaction.getToAccount() %></td>
								<td><%=transaction.getAmount() %></td>
							</tr>
						<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>