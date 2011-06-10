<%@ page language="java" pageEncoding="utf-8"
	contentType="application/json; charset=utf-8"%>
<%
	response.setStatus(403);
	String message = (String) request.getAttribute("message");
	if (message != null)
		response.getWriter().print("message:" + message);
%>