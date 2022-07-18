<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>주문 완료</title>
	</head>
	<body>
		<div id="wrap">
			 <c:import url="/WEB-INF/views/layout/top.jsp"/>
			<section>
			주문 완료!<br>
			주문 번호 : ${ordNo }
			</section>
			 <c:import url="/WEB-INF/views/layout/bottom.jsp"/>
		</div>
	</body>
</html>