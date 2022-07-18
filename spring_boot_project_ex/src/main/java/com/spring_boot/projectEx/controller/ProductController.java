package com.spring_boot.projectEx.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring_boot.projectEx.model.ProductVO;
import com.spring_boot.projectEx.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService prdService;
	
	// 카테고리별 상품 조회 요청 처리
	
	@RequestMapping("/product/productListCtg/{ctgId}")
	public String viewProductCtgList(@PathVariable String ctgId, Model model) {
		ArrayList<ProductVO> prdList = prdService.listCtgProduct(ctgId);
		model.addAttribute("prdList", prdList);
		return "product/productListCtgView";
		
	}	
	
	// 상품 상세 정보 조회
	@RequestMapping("/product/productDetailView/{prdNo}")
	public String detailviewProduct(@PathVariable String prdNo, Model model){
		ProductVO prd = prdService.detailViewProduct(prdNo);
		model.addAttribute("prd", prd);
		return "product/productDetailView";
	}
}	
