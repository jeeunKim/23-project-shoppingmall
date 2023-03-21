package com.sw.command;


import javax.servlet.ServletException;

import com.sw.dao.*;
import com.sw.dto.*;

public class Command implements Service {
	
	public int insert(MemberDto mdto) throws ServletException {
		Dao sdao = new DaoImpl();
		int retVal = sdao.insertMember(mdto);
		
		return retVal;
	}
	public int login(MemberDto mdto) {
		// TODO Auto-generated method stub
		int ret = 0;
		Dao sdao = new DaoImpl();
		String id = mdto.getId();
		String pw = mdto.getPw();
		
		String dbpw = sdao.loginMember(mdto, id);
		System.out.println(dbpw);
		if(pw.equals(dbpw)) {
			ret = 1;
		}else {
			ret = 0;
		}
		
		return ret;
	}
	public void inform(MemberDto mdto, String id) {
		Dao sdao = new DaoImpl();
		sdao.mypage(mdto, id);
	}
	public void home(ProductDto pdto, String kind) {
		Dao sdao = new DaoImpl();
		sdao.homepage(pdto, kind);
	}
	public int count(ProductDto pdto, String kind, String search) {
		Dao sdao = new DaoImpl();
		int ret = sdao.count(pdto, kind, search);
		return ret;
	}
	public void ProductInfo(ProductDto pdto, String kind, int offset, String search) {
		Dao sdao = new DaoImpl();
		sdao.productInfo(pdto, kind, offset, search);
	}
	public void product(ProductDto pdto, String name) {
		Dao sdao = new DaoImpl();
		sdao.product(pdto, name);
	}
	public int payment(ProductDto pdto, String pname, String nickname,String name, int count, String address) {
		Dao sdao = new DaoImpl();
		int ret = sdao.payment(pdto, pname, nickname,name, count,address);
		if(ret >=0) {
			
		}else {
			ret = 0;
		}
		
		return ret;
	}
	public void cart(ProductDto pdto, String pname, String nickname) {
		Dao sdao = new DaoImpl();
		sdao.cart(pdto, pname, nickname);
	}
	public void viewcart(ProductDto pdto, String nickname,int cartcount) {
		Dao sdao = new DaoImpl();
		sdao.viewcart(pdto, nickname,cartcount);
	}
	public int cartcount(ProductDto pdto, String nickname) {
		Dao sdao = new DaoImpl();
		int ret = sdao.cartcount(pdto,  nickname);
		
		return ret;
	}
	public void modify(String pw, String address, int phone, String id) {
		Dao sdao = new DaoImpl();
		sdao.modify(pw,address,phone,id);
	}

	public void delete(String id) {
		Dao sdao = new DaoImpl();
		sdao.delete(id);
	}
	public void comment(CommentDto cdto) {
	    Dao sdao = new DaoImpl();
	    sdao.comment(cdto);
	}
	   
	public void commentupdate(int id, String newcomment) {
	    Dao sdao = new DaoImpl();
	    sdao.commentupdate(id,newcomment);
	}
	   
	public void commentdelete(int id) {
	    Dao sdao = new DaoImpl();
	    sdao.commentdelete(id);
	}
	public ProductDto getproduct(String pn) {
      Dao sdao = new DaoImpl();
      ProductDto pdto = sdao.getproduct(pn);
      return pdto;
    }
	public void cartdelete( String pname, String nickname) {
	    Dao sdao = new DaoImpl();
	    sdao.cartdelete(pname, nickname);
    }
	public int ordercount(ProductDto pdto, String nickname) {
		Dao sdao = new DaoImpl();
	     int ret = sdao.ordercount(pdto,  nickname);    
	     return ret;
	}
	public void vieworder(ProductDto pdto, String nickname,int cartcount) {
		 Dao sdao = new DaoImpl();
	     sdao.vieworder(pdto, nickname,cartcount);
	}
	public void order(OrderDto odto, String nickname,int ordercount) {
		 Dao sdao = new DaoImpl();
	     sdao.order(odto, nickname,ordercount);
	}
	public void orderdelete(String pname, String nickname) {
		 Dao sdao = new DaoImpl();
	     sdao.orderdelete(pname,nickname);
	}
	
	



}
