package com.spring_boot.projectEx.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring_boot.projectEx.dao.IProductDAO;
import com.spring_boot.projectEx.model.ProductVO;

@Service
public class ProductService implements IProductService {

	@Autowired
	@Qualifier("IProductDAO")
	IProductDAO dao;
	
	@Override
	public ArrayList<ProductVO> listCtgProduct(String ctgId) {
		return dao.listCtgProduct(ctgId);
	}

	@Override
	public ArrayList<ProductVO> listAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertProduct(ProductVO prd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProduct(ProductVO prd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProduct(String prdNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductVO detailViewProduct(String prdNo) {
		return dao.detailViewProduct(prdNo);
		 
	}

	@Override
	public String prdNoCheck(String prdNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProductVO> productSearch(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
