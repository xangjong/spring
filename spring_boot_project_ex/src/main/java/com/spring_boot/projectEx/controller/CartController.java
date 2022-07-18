package com.spring_boot.projectEx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot.projectEx.model.CartVO;
import com.spring_boot.projectEx.model.MemberVO;
import com.spring_boot.projectEx.model.OrderInfoVO;
import com.spring_boot.projectEx.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	
	// 장바구니에 추가 요청 처리
	@RequestMapping("/product/insertCart")
	public String insertCart(CartVO vo, HttpSession session) {
		// 로그인 성공 후 설정한 세션 sid 값이 가져오기
		String memId = (String)session.getAttribute("sid");
		// VO에 memId 값 설정
		vo.setMemId(memId);
		
		// (1) 동일 상품이 존재하는지 확인
		int count = cartService.checkPrdInCart(vo.getPrdNo(), memId);
		
		if(count == 0) { // 동일 상품이 존재하지 않으면
			cartService.insertCart(vo); // (2) 장바구에 새로 추가
		} else { // (3) 존재하면 장바구니에서 해당 상품의 주문수량 변경
			cartService.updateQtyInCart(vo);
		}
		
		return "redirect:/product/cartList"; // 뷰페이지 이름 아님. 
		//포워딩 :  @RequestMapping("/product/cartList") 요청으로 보내는 것
	}
	
	//장바구니 목록 보기 요청 처리
	@RequestMapping("/product/cartList")
	public String cartList(HttpSession session, Model model) {
		// cart 테이블에서 현재 memId에 해당되는 내용만 출력
		String memId = (String)session.getAttribute("sid");
		ArrayList<CartVO> cartList = cartService.cartList(memId);
		model.addAttribute("cartList", cartList);
		
		return "product/cartListView";
	}
	
	// 장바구니 삭제
	// @RequestParam("chkArr[]") ArrayList<String> chkArr)
	@ResponseBody
	@RequestMapping("/product/deleteCart")
	public int deleteCart(@RequestParam("chkArr[]") ArrayList<String> chkArr) {
		int result = 0;

		// 서비스 클래스의 deleteCart() 메소드 호출하면서 carNo 전송하고 이 번호에 해당되는 상품 삭제
		if(chkArr != null) {			
			for(String cartNo : chkArr )
				cartService.deleteCart(cartNo);
			
			result = 1;
		}

		return result;		
	}
	
	// 주문서 열기 요청 처리
	// 장바구니 목록에서 보낸 데이터 받기 : 배열로 처리
	@RequestMapping("/product/orderForm")
	public String orderForm(@RequestParam String[] memId,
											  @RequestParam int[] cartNo, 
											  @RequestParam int[] cartQty,
											  Model model) {
		
		// 주문서에서 변경된 주문수량을 적용하기 위한 update 수행
		for(int i=0; i<cartNo.length; i++) {
			CartVO vo = new CartVO();
			vo.setCartNo(cartNo[i]);
			vo.setCartQty(cartQty[i]);
			cartService.updateCart(vo);
		}		
		
		// 주문서에 출력할 정보 가져오기
		// 회원 정보 가져오기 : memId[0]
		MemberVO memInfo = cartService.getMemberInfo(memId[0]);
		// 전화번호 split
		String[] memHP = memInfo.getMemHP().split("-");
		
		// 장바구니 목록 가져오기 
		ArrayList<CartVO> cartList = cartService.cartList(memId[0]);		
		
		// Model 설정
		model.addAttribute("memInfo", memInfo);
		model.addAttribute("memHP1", memHP[0]);
		model.addAttribute("memHP2", memHP[1]);
		model.addAttribute("memHP3", memHP[2]);
		model.addAttribute("cartList", cartList);
		
		return "product/productOrderForm";
	}
	
	// 주문 완료 처리  
	@RequestMapping("/product/orderComplete")
	public String orderComplete(OrderInfoVO ordInfoVo,
													  @RequestParam String hp1,
													  @RequestParam String hp2, 
													  @RequestParam String hp3,
													  Model model) {
		// 전화번호 설정
		String hp = hp1 + "-" + hp2 + "-" + hp3;
		ordInfoVo.setOrdRcvPhone(hp);
		
		// 주문번호 생성 : 오늘날짜시분초_랜덤숫자4개
		long timeNum = System.currentTimeMillis(); // 1657868983637
		// 날짜 시간 형태로 변경 : 20200715161515_2345
		// 날짜 시간 포맷 : SimpleDateFormat 클래스 사용
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");  // 포맷 지정
		String strTime = dayTime.format(new Date(timeNum)); 
		
		// 참고로 출력
		System.out.println(timeNum);
		System.out.println(strTime);
		
		// 랜덤 숫자 4개 생성
		String rNum = "";
		for(int i=1; i<=4; i++)
				rNum += (int)(Math.random()*10);
		
		// 주문번호
		String ordNo = strTime + "_" + rNum;
		
		// 주문번호 설정
		ordInfoVo.setOrdNo(ordNo);
		
		// 주문 내역 저장 (주문 상품 정보는 장바구니에서 바로 주문 테이블로 입력 : 필요한 정보 memId)
		cartService.insertOrder(ordInfoVo);
		
		// 결과 출력 뷰 페이지에 주문번호 출력하기 위해 Model 설정
		model.addAttribute("ordNo", ordNo); 
		
		return "product/orderCompleteView";
	}
}






