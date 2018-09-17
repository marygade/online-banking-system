<%
	request.getSession().invalidate();
	response.sendRedirect("Login.jsp");
	return;
%>