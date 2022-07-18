<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 상세 정보 조회</title>
		<script>
			var qty =1;
			
			// 주문 수량 증감하는 함수
			function qtyChange(num){
				qty = qty + num;
				if(qty <1) qty =1;
				calAmount(); // 주문액 계산하는 함수 호출
				
			}
			
			// 주문액 계산하는 함수
			function calAmount(){
				var cartQty = document.getElementById("cartQty");
				var amount = document.getElementById("amount");
				
				var total = qty * ${prd.prdPrice};
				
				// 결과 값 반영
				cartQty.value = qty;
				amount.innerHTML = total.toLocaleString();
			}
		</script>
		
		<style>
		table { margin:0 auto; width:100%;}
			tr { width:100%; }
		</style>
	</head>
	<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/layout/top.jsp"/>
		
	<section>
		<form method="post" action="<c:url value='/product/insertCart'/>"> 
		<h3>상품 상세 정보 조회</h3>
		<table border="1" width="500">
			<tr><td rowspan="9"><img src="<c:url value='/images/${prd.prdImg}'/>" width='300' height='250'></td></tr>
		
			<tr>
				<td>상품번호</td>
				<td>
					<input type="hidden" name="prdNo" value="${prd.prdNo }">${prd.prdNo}
				</td>
				
			</tr>
			
			<tr>
				<td>상품명</td><td>${prd.prdName}</td>
			</tr>
			
			<tr>
				<td>가격</td>
				<td><fmt:formatNumber value="${prd.prdPrice}" pattern="#,###"/>원</td>
			</tr>
			
			<tr>
				<td>제조사</td><td>${prd.prdCompany}</td>
			</tr>
			<tr>
				<td>상품설명</td><td>${prd.prdDescript}</td>
			</tr>
			
			<tr>
				<td>주문 수량</td>
				<td>
					<input type="button" value="-" onClick="qtyChange(-1)">
					<input type="text" id="cartQty" name="cartQty" value="1" size="2" readonly>
					<input type="button" value="+" onClick="qtyChange(1)">개
				</td>
			</tr>
			
			
			<tr>
				<td>구매 예정 금액</td>
				<td>
					<span id="amount"><fmt:formatNumber value="${prd.prdPrice}" pattern="#,###"/></span>원
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<!-- 로그인 하지 않은 경우에는 [로그인] 버튼 출력 -->
					<c:if test="${empty sessionScope.sid }">
						<button><a href="<c:url value='/loginForm'/>">로그인</a></button>
						
					</c:if>
					<!-- 로그인 한 경우에는 [장바구니] [주문] 버튼 출력 -->
					 <c:if test="${not empty sessionScope.sid }">
					 	<input type="submit" id="insertCart" value="장바구니">
					 	<input type="button" id="insertOrder" value="바로 주문하기">
					</c:if> 
				</td>
			</tr>
			
		</table>
		</form>
		<a href="<c:url value='/product/productListCtg/${prd.ctgId}'/>"><button>상품목록</button></a>
		
		<br><br>
		<a href="<c:url value='/'/>">메인 화면으로 이동</a><br><br> 
		<a href="<c:url value='/product/productAllList'/>">전체 상품 조회</a><br><br>
		
		</section>
			<c:import url="/WEB-INF/views/layout/bottom.jsp"/>
		</div>
	</body>
</html>