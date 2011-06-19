<%@ page language="java" pageEncoding="utf-8"
	import="monopoly.impl.controllers.response.BaseResponse"
contentType="text/plain; charset=utf-8"%>
<%
	//response.setStatus(403);

	BaseResponse result = (BaseResponse) request
			.getAttribute("result");
	if (result != null)
		response.getWriter().print(result);
	System.out.println(result);
%>