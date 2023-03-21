package com.sw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sw.command.*;
import com.sw.dto.*;
import java.util.Random;

/**
 * Servlet implementation class MainController
 */
@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length()); //~~.do
		
		String viewPage = null;
	    String address=null;
	    String id=null;
	    String nickname=null;
	    int phone;
	    String pw=null;
	    String name=null;
	    int result=0;
	    //--------------------------------------
		String pname;
		int price;
		String img;
		int views;
		String size=null;
		int count;
		String kind = null;
		String pname1= "pname";
		
	    MemberDto mdto = new MemberDto(); //회원정보 dto
	    ProductDto pdto = new ProductDto();  //상품정보 dto
	    CommentDto cdto = new CommentDto();
	    Command newcommand = null;
	      

		
		HttpSession session = request.getSession();
		System.out.println("actionDo - "+ command);
		
		//-----------------------------------------------------메인 홈페이지
		if(command.equals("/home.do")) {   			
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			
			newcommand = new Command();
			for(int i = 1; i <=6; i++) {  //메인 홈페이지에 도서, 의류, 음식 별로 조회수가 높은 순으로 2가지 상품의 정보를 가져오기위한 반복,조건문 
				if(i<=2) {
					kind = "도서";
					newcommand.home(pdto, kind);
					
					pname = pdto.getPname();
					price = pdto.getPrice();
					img = pdto.getImg();
					views = pdto.getViews();
					session.setAttribute(pname1+1, pname);
					session.setAttribute("price1", price);
					session.setAttribute("img1", img);
					session.setAttribute("views1", views);
				
					pname = pdto.getPname2();
					price = pdto.getPrice2();
					img = pdto.getImg2();
					views = pdto.getViews2();
					session.setAttribute("pname2", pname);
					session.setAttribute("price2", price);
					session.setAttribute("img2", img);
					session.setAttribute("views2", views);
				}
				else if(i<=4) {
					kind = "의류";
					newcommand.home(pdto, kind);
					
					pname = pdto.getPname();
					price = pdto.getPrice();
					img = pdto.getImg();
					views = pdto.getViews();
					session.setAttribute("pname3", pname);
					session.setAttribute("price3", price);
					session.setAttribute("img3", img);
					session.setAttribute("views3", views);
				
					pname = pdto.getPname2();
					price = pdto.getPrice2();
					img = pdto.getImg2();
					views = pdto.getViews2();
					session.setAttribute("pname4", pname);
					session.setAttribute("price4", price);
					session.setAttribute("img4", img);
					session.setAttribute("views4", views);
				}
				else {
					kind = "음식";
					newcommand.home(pdto, kind);
					
					pname = pdto.getPname();
					price = pdto.getPrice();
					img = pdto.getImg();
					views = pdto.getViews();
					session.setAttribute("pname5", pname);
					session.setAttribute("price5", price);
					session.setAttribute("img5", img);
					session.setAttribute("views5", views);
				
					pname = pdto.getPname2();
					price = pdto.getPrice2();
					img = pdto.getImg2();
					views = pdto.getViews2();
					session.setAttribute("pname6", pname);
					session.setAttribute("price6", price);
					session.setAttribute("img6", img);
					session.setAttribute("views6", views);
				}
				
				
				
			
			}
			
			viewPage = "HomePage.jsp";
		}
		//-----------------------------------------------------로그인
		else if(command.equals("/login.do")) {  
	         id = request.getParameter("id");
	         pw = request.getParameter("pw");
	         mdto.setId(id);
	         mdto.setPw(pw);
	         newcommand=new Command();
	         result=newcommand.login(mdto);
	         if(result==1)               //리턴값이 1이라면 비밀번호가 일치. (로그인 성공)
	         {	
	        	 nickname = mdto.getNickname();
	        	 session.setAttribute("ID", id);
	        	 session.setAttribute("nickname",nickname);
	        	 viewPage="home.do";
	         }
	         else {
	        	 viewPage="login.jsp";
	         }

			
		}
		//-----------------------------------------------------회원가입
		else if(command.equals("/join.do")) {   
			
			 id = request.getParameter("id");		
	         pw = request.getParameter("pw");
	         name = request.getParameter("name");
	         address = request.getParameter("address");
	         nickname = request.getParameter("nickname");
	         phone = Integer.parseInt(request.getParameter("phone"));
	         
	         mdto.setAddress(address);
	         mdto.setPw(pw);
	         mdto.setName(name);
	         mdto.setPhone(phone);
	         mdto.setNickname(nickname);
	         mdto.setId(id);
	         System.out.println(mdto.getAddress());
	         newcommand = new Command();
	         result=newcommand.insert(mdto);

			
			viewPage = "login.jsp";
		}
		
		//-----------------------------------------------------마이페이지 (내정보 및 주문내역 확인)
		else if(command.equals("/MyPage.do")) {   
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			 id = (String) session.getAttribute("ID");
			 newcommand = new Command();
			 newcommand.inform(mdto, id);
			 
			 address = mdto.getAddress();
			 pw = mdto.getPw();
			 name = mdto.getName();
			 nickname = mdto.getNickname();
			 phone = mdto.getPhone();
			 
			 session.setAttribute("pw", pw);
			 session.setAttribute("name", name);
			 session.setAttribute("nickname", nickname);
			 session.setAttribute("address", address);
			 session.setAttribute("phone", phone);
			 
			 viewPage="MyPage.jsp";
	         

			
		}
		//-----------------------------------------------------도서 카테고리 웹페이지
		else if(command.equals("/book.do")) {   
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			int i = 0;
			String product_name = "bname";    //setAttribute()를 상품 갯수만큼 반복해야하기 때문에 속성값의 공통적인 부분을 변수화함.
			String product_price = "bprice";
			String product_img = "bimg";
			String product_bviews = "bviews";
			
			String bname = null;
			int bprice;
			String bimg = null;
			int bviews;
			
			newcommand = new Command();
			i = newcommand.count(pdto, "도서","");   //도서 카테고리의 상품의 전체 갯수
			session.setAttribute("count", i);
			for(int j = 1; j<= i; j++) {
				newcommand.ProductInfo(pdto, "도서", j-1,"");
				
				bname = pdto.getPname();
				bprice = pdto.getPrice();
				bimg = pdto.getImg();
				bviews = pdto.getViews();
				
				session.setAttribute(product_name+j, bname);  //속성값의 공통적인 부분을 변수화하고, 갯수만큼 세션에 저장되게함.
				session.setAttribute(product_price+j, bprice);
				session.setAttribute(product_img+j, bimg);
				session.setAttribute(product_bviews+j, bviews);
				
			}
			
			
			viewPage="book.jsp";
		}
		//-----------------------------------------------------의류 카테고리 페이지
		else if(command.equals("/cloth.do")) {
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			int i = 0;
			String product_name = "cname";
			String product_price = "cprice";
			String product_img = "cimg";
			String product_bviews = "cviews";
			
			String cname = null;
			int cprice;
			String cimg = null;
			int cviews;
			
			newcommand = new Command();
			i = newcommand.count(pdto, "의류","");
			session.setAttribute("count", i);
			for(int j = 1; j<= i; j++) {
				newcommand.ProductInfo(pdto, "의류", j-1,"");
				
				cname = pdto.getPname();
				cprice = pdto.getPrice();
				cimg = pdto.getImg();
				cviews = pdto.getViews();
				
				session.setAttribute(product_name+j, cname);
				session.setAttribute(product_price+j, cprice);
				session.setAttribute(product_img+j, cimg);
				session.setAttribute(product_bviews+j, cviews);
				
			}
	
			viewPage="cloth.jsp";
		}
		//-----------------------------------------------------식품 카테고리 페이지
		else if(command.equals("/food.do")) {
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			int i = 0;
			String product_name = "fname";
			String product_price = "fprice";
			String product_img = "fimg";
			String product_bviews = "fviews";
			
			String fname = null;
			int fprice;
			String fimg = null;
			int fviews;
			
			newcommand = new Command();
			i = newcommand.count(pdto, "음식","");
			session.setAttribute("count", i);
			for(int j = 1; j<= i; j++) {
				newcommand.ProductInfo(pdto, "음식", j-1,"");
				
				fname = pdto.getPname();
				fprice = pdto.getPrice();
				fimg = pdto.getImg();
				fviews = pdto.getViews();
				
				session.setAttribute(product_name+j, fname);
				session.setAttribute(product_price+j, fprice);
				session.setAttribute(product_img+j, fimg);
				session.setAttribute(product_bviews+j, fviews);
				
			}
		
			viewPage="food.jsp";
		}
		//-----------------------------------------------------상품 검색 결과
		else if(command.equals("/search.do")) {
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			String search_text = request.getParameter("search");
			session.setAttribute("search", search_text);
			int i = 0;
			String product_name = "sname";
			String product_price = "sprice";
			String product_img = "simg";
			String product_bviews = "sviews";
			
			String sname = null;
			int sprice;
			String simg = null;
			int sviews;
			
			newcommand = new Command();
			i = newcommand.count(pdto, "*", search_text);
			session.setAttribute("count", i);
			if(i != 0) {
				for(int j = 1; j<= i; j++) {
					newcommand.ProductInfo(pdto, "*", j-1,search_text);
					
					sname = pdto.getPname();
					sprice = pdto.getPrice();
					simg = pdto.getImg();
					sviews = pdto.getViews();
					System.out.println(sname);
					session.setAttribute(product_name+j, sname);
					session.setAttribute(product_price+j, sprice);
					session.setAttribute(product_img+j, simg);
					session.setAttribute(product_bviews+j, sviews);
					
				}
				viewPage="Searchresult.jsp";
			}
			else {
				viewPage="SearchNotFound.jsp";
			}
			
		}
		//-----------------------------------------------------결제 및 장바구니
		else if(command.equals("/payment.do")) {
			int ret;
			String alert= null;
			int cartcount;
			nickname = (String) session.getAttribute("nickname");
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			if(request.getParameter("how").equals("결제")) {		
				pname = (String) session.getAttribute("pn");
				int qua = Integer.parseInt(request.getParameter("qua"));
                name = request.getParameter("name");
                address = request.getParameter("address");
                         
				
				newcommand = new Command();
				ret = newcommand.payment(pdto,pname ,nickname,name,qua, address);
				if(ret != 0) {
					session.setAttribute("count", ret);
				}
			}
			else {
				pname = (String) session.getAttribute("pn");
				nickname = (String) session.getAttribute("nickname");
				System.out.println(pname);
				newcommand = new Command();
				newcommand.cart(pdto, pname, nickname);
				
	            cartcount = newcommand.cartcount(pdto, nickname);
				session.setAttribute("quantity", cartcount);
			}
			
			viewPage="Item.jsp";
			
			
		}
		
		//-----------------------------------------------------장바구니 페이지
		else if(command.equals("/cart.do")) {
			int cartcount=0;
			nickname = (String) session.getAttribute("nickname");
			String product_name = "name";
			String product_price = "price";
			String product_img = "img";
			String product_views = "count";
			
			
			newcommand = new Command();
			cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			for(int j = 1; j<= cartcount; j++) {
				
				newcommand.viewcart(pdto, nickname, j-1);
				
				name = pdto.getPname();
				price = pdto.getPrice();
				img = pdto.getImg();
				count = pdto.getCount();
				
				session.setAttribute(product_name+j, name);
				session.setAttribute(product_price+j, price);
				session.setAttribute(product_img+j, img);
				session.setAttribute(product_views+j, count);
				System.out.println(name);
			}		
			viewPage="cart.jsp";
		}
		
		//-----------------------------------------------------회원정보 변경 페이지
		else if (command.equals("/modify.do")) {
            pw = request.getParameter("pw");
            address = request.getParameter("address");
            phone = Integer.parseInt(request.getParameter("phone"));
            id = (String) session.getAttribute("ID");
            newcommand = new Command();
            newcommand.modify(pw,address,phone,id);
            
            session.removeAttribute("pw");
            session.removeAttribute("address");
            session.removeAttribute("phone");
            
            session.setAttribute("adress", address);
            session.setAttribute("pw", pw);
            session.setAttribute("phone", phone);
            
            viewPage = "MyPage.jsp";
         }
		
		//-----------------------------------------------------상품 상세 정보 페이지
		else if(command.equals("/item.do")) {
            pname = request.getParameter("pname");
            newcommand = new Command();
            nickname = (String) session.getAttribute("nickname");
            int cartcount = newcommand.cartcount(pdto, nickname);
			session.setAttribute("quantity", cartcount);
			
			
            newcommand.product(pdto, pname);
            kind = pdto.getKind();
            price = pdto.getPrice();
            count = pdto.getCount();
            size = pdto.getSize();
            views = pdto.getViews();
            img = pdto.getImg();
            
            session.setAttribute("pn", pname);
            session.setAttribute("kind", kind);
            session.setAttribute("price", price);
            session.setAttribute("count", count);
            session.setAttribute("views", views);
            session.setAttribute("img", img);
            session.setAttribute("size", size);
            
            viewPage = "Item.jsp";
         }

		//-----------------------------------------------------회원탈퇴
         else if (command.equals("/delete.do")) {
           String conpw = request.getParameter("password");
           String realpw = (String) session.getAttribute("pw");
           
           if(conpw.equals(realpw)) {
           id = (String) session.getAttribute("ID");
           newcommand = new Command();
           newcommand.delete(id); 
           viewPage = "logout.jsp";
           }
           
           else
              viewPage = "Delete.jsp";
         }

		//-----------------------------------------------------댓글 작성
		else if(command.equals("/comment.do")) {
            String cid = (String) session.getAttribute("ID");
            nickname = (String) session.getAttribute("nickname");
            pname = (String) session.getAttribute("pn");
            String comment = request.getParameter("commentText");
            
            
            cdto = new CommentDto(0, comment, cid, pname, null, nickname);
            newcommand = new Command();
            newcommand.comment(cdto);
            
            viewPage = "Item.jsp";
         }

		//-----------------------------------------------------댓글 수정
         else if(command.equals("/commentUpdate.do")) {
            int tempid = (int) session.getAttribute("CommentUpdate_id");
            String newcom = request.getParameter("upcom");
            newcommand = new Command();
            newcommand.commentupdate(tempid,newcom);
            
            viewPage = "Item.jsp";
         }
		
		//-----------------------------------------------------댓글 삭제
         else if(command.equals("/commentDelete.do")) {
            int tempid = (int) session.getAttribute("CommentUpdate_id");
            newcommand = new Command();
            newcommand.commentdelete(tempid);
            
            viewPage = "Item.jsp";
         }
		
		//-----------------------------------------------------장바구니에 담은 상품 삭제
         else if(command.equals("/cartdelete.do")) {
             nickname = (String) session.getAttribute("nickname");
             pname = request.getParameter("ppname");
             pdto = new ProductDto();
             
             newcommand = new Command();
             newcommand.cartdelete(pname, nickname);
           
             int cartcount = newcommand.cartcount(pdto, nickname);
             session.setAttribute("quantity", cartcount);
              
             viewPage = "cart.do";
         }
		
		//-----------------------------------------------------결제 취소
        	else if(command.equals("/orderdelete.do")) {
             nickname = (String) session.getAttribute("nickname");
             pname = request.getParameter("p_name");
             pdto = new ProductDto();
             
             newcommand = new Command();
             newcommand.orderdelete(pname, nickname);
           
             int ordercount = newcommand.ordercount(pdto, nickname);
             session.setAttribute("ordercount", ordercount);
              
             viewPage = "order.do";
         }
		
		//-----------------------------------------------------주문 내역 페이지
        	else if(command.equals("/order.do")) {
        		newcommand = new Command();
           		int ordercount=0;
                nickname = (String) session.getAttribute("nickname");
                String p_name = "p_name";
                String p_price = "p_price";
                String p_img = "p_img";
                String o_quantity = "o_quantity";
                String o_date = "o_date";
                String o_address = "o_address";
                pdto = new ProductDto();
                OrderDto odto = new OrderDto();
                
                ordercount = newcommand.ordercount(pdto, nickname);
                session.setAttribute("ordercount", ordercount);
                
                for(int j = 1; j<= ordercount; j++) {
                   
                	newcommand.vieworder(pdto, nickname, j-1);
                	newcommand.order(odto, nickname, j-1);
                   
                   name = pdto.getPname();
                   price = pdto.getPrice();
                   img = pdto.getImg();
                   int q = odto.getQuantity();
                   Date d = odto.getDate();
                   String ad = odto.getShipping();
                   
                   session.setAttribute(p_name+j, name);
                   session.setAttribute(p_price+j, price);
                   session.setAttribute(p_img+j, img);
                   session.setAttribute(o_quantity+j, q);
                   session.setAttribute(o_date+j, d);
                   session.setAttribute(o_address+j, ad);
                }
                
                viewPage="Order.jsp";
           	}
	
	
		else {}
		
		response.sendRedirect(viewPage);
	}

}
