<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title> 
		<script src="<c:url value='/js/jquery-3.6.0.min.js' />"></script>
		<script src="<c:url value='/js/login.js' />"></script>
		<style>
			section { margin:50px; }
		</style>
	</head>
	<body>
		<div id="wrap"> 
			 <!-- TOP으로 이동 -->
          <!-- TOP 포함 -->
       <%-- <jsp:include page="/WEB-INF/views/layout/top.jsp" flush="true" /> --%>
        <c:import url="/WEB-INF/views/layout/top.jsp"/> 
			<!--  로그인 폼  -->
			<section>
		        <h1 id="title">로그인</h1>
		        <form id="loginForm" name="loginForm" method="post"  action="<c:url value='/login'/>">
		          <table>
		            <tr><th> ID</th><td><input type="text" id="id" name="id" ></td></tr>
		            <tr><th>비밀번호</th><td><input type="password" id="pwd" name="pwd"></td></tr>
		             <tr>
		                <td colspan="2" align="center" id="button">
		                    <br><input type="submit" value="로그인">
		                    <input type="reset" value="취소">
		                </td>
		            </tr>             
		            </table>
		        </form>	
	        </section>
	   		     <!-- BOTTOM으로 이동 -->
             <!-- BOTTOM 포함 -->
        <%--   <jsp:include page="/WEB-INF/views/layout/bottom.jsp" flush="true" /> --%>
            <c:import url="/WEB-INF/views/layout/bottom.jsp"/>  
	      </div>
	</body>
</html>


