package com.sw.command;
import com.sw.dto.*;
public interface Service {
	public int login(MemberDto mdto);
	void modify(String pw, String address, int phone, String id);
	void delete(String id);
	public void inform(MemberDto mdto, String id);
	void home(ProductDto pdto, String kind);
	ProductDto getproduct(String pn);
	int count(ProductDto pdto, String kind, String search);
    void ProductInfo(ProductDto pdto, String kind, int offset, String search);
	void product(ProductDto pdto, String name);
	int payment(ProductDto pdto, String pname, String nickname,String name, int count, String address);
	void cart(ProductDto pdto, String pname, String nickname);
	void viewcart(ProductDto pdto, String nickname,int cartcount);
	int cartcount(ProductDto pdto, String nickname);
	void comment(CommentDto cdto);
	void commentupdate(int id, String newcomment);
	void commentdelete(int id);
	void cartdelete(String pname, String nickname);
	int ordercount(ProductDto pdto, String nickname);
	void vieworder(ProductDto pdto, String nickname,int ordercount);
	void order(OrderDto odto, String nickname,int ordercount);
	void orderdelete(String pname, String nickname);
}
