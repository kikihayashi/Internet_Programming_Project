package Controller;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.ItemCheck;
import Model.Buy_Data;

//這支Buy程式扮演Controller的角色，負責處理使用者購買的工作
 
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	
		protected void processRequest(HttpServletRequest request,
			                          HttpServletResponse response)
			                          throws ServletException, IOException {
			
		//建立一個新的AccountCheck，變數check_model指向AccountCheck
		ItemCheck model = new ItemCheck();				
        //設定請求回應編碼為UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		//設定頁面導向設置
		String jspPageToForward =null;
		//設定顯示試算金額訊息或結帳錯誤訊息
		String message;	
		
		//建立一個新的變數session利用getSession()方法取得HttpSession物件
		HttpSession session = request.getSession();
		//得到homepage2的money字串資訊
		String money=(String)session.getAttribute("money");
		
		HashMap<String, Buy_Data> Buy_Map=new HashMap<String, Buy_Data>();	
		//得到使用者在buypage2點了哪個按鈕資訊
		String page_information=request.getParameter("page");		
		//得到homepage2的buy字串陣列資訊
		String[] buy_number=request.getParameterValues("buy");
		//記憶使用者之前填的個別商品數量
		String[] buy_number_memory=buy_number;
				
		if     ("金額試算".equals(page_information)){
			    //利用逗號分開得到個別商品單價
			    String[] item_money=money.split(",");
		        //計算總金額
		        message=model.check_items_money(buy_number,item_money);
		        //將使用者導向buypage2.jsp	
		        jspPageToForward="buypage2.jsp";			
		}
		
		else if("確定購買".equals(page_information)){       		
		        //得到結帳訊息
		        boolean checkout_message=model.checkout(buy_number_memory);
		        //如果有買東西		
		        if  (checkout_message){
		        	 //利用逗號分開得到個別商品單價
		    		 String[] item_money=money.split(",");
			         //計算總金額
			         message=model.check_items_money(buy_number,item_money);    
	                 //將使用者導向buypage3.jsp	
		             jspPageToForward="buypage3.jsp";
		        } 
		        //如果沒買東西
		        else{
			         message="請至少選擇一項商品購買";
			         //將使用者導向buypage2.jsp	
			         jspPageToForward="buypage2.jsp";	
		        }			
		}
		
		//第一次執行程式會導向這裡		
		else{
	            //得到homepage1的choose字串陣列資訊
		        String[] choose=request.getParameterValues("choose");
		        
		        if(choose!=null){
		        //將該字串陣列資訊setAttribute，homepage2.jsp要用到
		        session.setAttribute("choose",choose);	
		        //這是要做之前購買數量記憶用所設置的，homepage2.jsp要用到
		        buy_number_memory=null;
		        //第一次進來此程式不需要顯示東西
		        message="";
		        //將使用者導向buypage2.jsp
		        jspPageToForward="buypage2.jsp";
		        }
  
		        else{
		        	message="請至少選擇一樣商品！";
		        	//將使用者導向buypage1.jsp
			        jspPageToForward="buypage1.jsp";		        		        	
		            }
		        
		}
		
		//這是要做之前購買數量記憶用所設置的，homepage2.jsp要用到
		session.setAttribute("buy_number_memory",buy_number_memory);
		//顯示試算金額訊息或結帳錯誤訊息
		request.setAttribute("message",message);
		//導向jspPageToForward的地方
		RequestDispatcher dispatcher=request.getRequestDispatcher(jspPageToForward);
		dispatcher.forward(request, response);	
	    }

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
