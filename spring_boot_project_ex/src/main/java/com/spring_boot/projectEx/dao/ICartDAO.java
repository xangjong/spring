package com.spring_boot.projectEx.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring_boot.projectEx.model.CartVO;
import com.spring_boot.projectEx.model.MemberVO;
import com.spring_boot.projectEx.model.OrderInfoVO;

public interface ICartDAO {
	void insertCart(CartVO vo); // 장바구니에 추가
	int checkPrdInCart(HashMap<String, Object> map); // 동일 상품 존재 여부 확인
	void updateQtyInCart(CartVO vo); // 동일 상품이 존재하는 경우 수량만 변경
	ArrayList<CartVO> cartList(String memId);
	void deleteCart(String cartNo); // 장바구니에서 cartNo에 해당되는 것 삭제
	
	// 주문 처리 작업
	void updateCart(CartVO vo);
	MemberVO getMemberInfo(String memId); // 주문서에 출력할 회원정보 알아오기
	
	// 주문 내역 저장
	void insertOrderInfo(OrderInfoVO ordInfoVO); // 주문 관련 정보 저장
	void insertOrderProduct(HashMap<String, Object> map); // 주문 상품 내용 저장
	void deleteCartAfterOrder(String memId); // 주문 후 장바구니 비우기
}
