<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주문 폼</title>
		<style>
			h3 { margin-top:20px;}
			table { width:900px; margin-top:20px; border-collapse: collapse;}
			table caption { font-size: 20px; font-weight: 600; margin-bottom: 10px;}	
			table tr { height:30px; }
			table th, td { border:solid gray 1px; padding:10px;}
			table th { background-color: darkgray; color: #fff; width: 100px; }	
			table td { text-align: left; width: 300px; }	
			input, select { text-align:left; height: 20px; margin: 2px 0;}
			.order_list td { text-align: center; }
		</style>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/views/layout/top.jsp"></c:import>
			
			<form method="post"  action="<c:url value='/product/orderComplete'/>">
			
					<table border="1">
						<caption>주문자 정보</caption>
						<tr>
							<th>주문자</th>
							<td>${memInfo.memName}</td>
							<th>연락처</th>
							<td>${memInfo.memHP}</td>
						</tr>
					</table>
					
					<table border="1">
						<caption>수령인 정보</caption>
						<tr>
							<th>수령인</th>
							<td><input type="text" value="${memInfo.memName}">
									 <input type="hidden"  name="memId"   value="${memInfo.memId}"></td>
							<th>연락처</th>
							<td><input type="text"    name="hp1"   value="${memHP1}" size="3"> 
				                    - <input type="text"   name="hp2"  value="${memHP2}" size="4">
				                    - <input type="text"   name="hp3"  value="${memHP3}" size="4"></td>
						</tr>
						<tr>
							<th>배송지 주소</th>
							<td colspan="3">
								<input type="text" id="zipcode" name="ordRcvZipcode" value="${memInfo.memZipCode}" size="7" readonly>
			            		<input type="button" id="searchZipBtn" name="searchZipBtn" value="우편번호 찾기"><br>
			            		<input type="text" id="ordAddress1" name="ordRcvAddress1" value="${memInfo.memAddress1}" placeholder="주소 입력" readonly size="80"><br>
			            		<input type="text" id="ordAddress2" name="ordRcvAddress2" value="${memInfo.memAddress2}" placeholder="상세 주소 입력" size="80">
		            		</td>
						</tr>
						<tr>
							<th>배송메세지</th>
							<td colspan="3">
								<select>
									<option>배송 전 연락주세요</option>
									<option>부재 시 문 앞에 놔주세요</option>
									<option>문 앞에 놓고 벨 눌러주세요</option>
								</select>
							</td>
						</tr>
					</table>
					
					<table border="1" class="order_list">
						<caption>주문 상품 목록</caption>
						<tr>
							<th>상품번호</th>
							<th>사진</th>
							<th>상품명</th>
							<th>가격</th>
							<th>주문수량</th>
							<th>구매예정금액</th>
						</tr>
						<c:forEach items="${cartList}" var="cart">
							<tr>
								<td>${cart.prdNo}</td>
								<td><img src="<c:url value='/images/${cart.prdImg}'/>" width="50" height="40"></td>
								<td>${cart.prdName}</td>
								<td align="right"><fmt:formatNumber value="${cart.prdPrice}" pattern="#,###"/>원</td>
								<td><input type="text" value="${cart.cartQty}" size="3"></td>
								<td align="right">
									<c:set var="amount" value="${cart.prdPrice * cart.cartQty}"/>
									<c:set var="sum" value="${sum + amount}"/>
									<fmt:formatNumber value="${amount}" pattern="#,###"/>원
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5">총 구매 예정 금액</td>
							<td align="right">
								<fmt:formatNumber value="${sum}" pattern="#,###"/>원
							</td>
						</tr>
					</table>
					
					<table>
						<caption>결제 정보</caption>
						<tr>
							<th>결제 방법</th>
							<td>
								<input type="radio" id="card" name="pay" value="신용카드" checked><label for="card">신용카드</label>
								<input type="radio" id="account" name="pay" value="신용카드"><label for="account">실시간 계좌 이체</label>
							</td>
						</tr>
					</table><br><br>
			
				<input type="submit" value="완료">
			</form>
			<c:import url="/WEB-INF/views/layout/bottom.jsp"></c:import>
		</div>
	</body>
</html>