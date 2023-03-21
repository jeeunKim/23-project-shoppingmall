package com.sw.dao;
import com.sw.dto.*;
import java.util.ArrayList;
public interface Dao {
	public int insertMember(MemberDto sdto);   //회원가입 메소드
	public String loginMember(MemberDto mdto, String id);	//로그인 메소드
	public void mypage(MemberDto mdto, String id); //마이페이지에 회원정보를 가져오기 위한 메소드
	public int login(MemberDto dto); //로그인
	public MemberDto getDto(String id, String pw); //회원정보 가져오기
	public void modify(String pw, String address, int phone, String id);
	public void delete(String id);
	public void homepage(ProductDto pdto, String kind);
	public ProductDto getproduct(String pn);
	public int count(ProductDto pdto, String kind, String search);      //카테고리별 상품의 전체 갯수를 가져오는 메소드
    public void productInfo(ProductDto pdto, String kind, int offset,String search); //상품정보를 가져오는 메소드
    public void product(ProductDto pdto, String name);
    public int payment(ProductDto pdto, String nickname,String pname, String name, int count, String address);
    public void cart(ProductDto pdto, String pname, String nickname);
    public void viewcart(ProductDto pdto, String nickname,int cartcount);
    public int cartcount(ProductDto pdto, String nickname);
    public void comment(CommentDto cdto);
    public ArrayList<CommentDto> getList(String pname);
    public void commentupdate(int id, String newcom);
    public void commentdelete(int id);
    public void cartdelete( String pname, String nickname);
    public int ordercount(ProductDto pdto, String nickname);
    public void vieworder(ProductDto pdto, String nickname,int cartcount);
    public void order(OrderDto odto, String nickname,int ordercount);
    public void orderdelete(String pname, String nickname);
}
