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
import Model.Sell_Data;
import Model.User_Data;

//這支Sell程式扮演Controller的角色，負責處理使用者上架的工作
 
@WebServlet("/Sell")
public class Sell extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	
		protected void processRequest(HttpServletRequest request,
			                          HttpServletResponse response)
			                          throws ServletException, IOException {
			
        //設定請求回應編碼為UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		//建立一個新的HashMap，變數Map_login指向HashMap
		HashMap<String, Sell_Data> Sell_Map=new HashMap<String, Sell_Data>();	
		//建立一個新的AccountCheck，變數check_model指向AccountCheck
		ItemCheck model = new ItemCheck();
		//設定頁面導向設置
		String PageToForward =null;
		String message="";
		//建立一個新的變數session利用getSession()方法取得HttpSession物件
		HttpSession session = request.getSession();
			
		String page_information = request.getParameter("page");		
		String sell_name        = request.getParameter("name");
		String sell_number      = request.getParameter("number");
		String sell_discription = request.getParameter("discription");
		String sell_cost        = request.getParameter("cost");
	    //如果使用者已按下"上架商品"
		if   ("上架商品".equals(page_information)){	
			  //判斷單價金額與上架數量是否為正整數
			  boolean sell_message=model.check_sell(sell_number,sell_cost);			
			  //如果是
			  if(sell_message){	
			  //now_user是從Login那裡getAttribute來的
		      User_Data user=(User_Data)session.getAttribute("now_user");
			  //將使用者上架資料存入sell_data物件裡
	          Sell_Data sell_data=model.user_sell(sell_name,sell_number.replaceFirst("^0*", "") ,sell_discription,
	        		                              user.getAccountName(),sell_cost.replaceFirst("^0*", ""),Sell_Map);	 
	          //將Map物件以getServletContext().setAttribute，之後Login需要用到
        	  getServletContext().setAttribute("Sell_Map",Sell_Map);
        	  
        	  request.setAttribute("listed","listed");      	  
		      //將使用者導向buypage2.jsp	
		      PageToForward="DataModify";
			  }
			  //如果不是
			  else{
				   message="單價金額與上架數量必須是正整數";
				   //將使用者導向sellpage1.jsp	
				   PageToForward="sellpage1.jsp";				  
			  }
			  
		    }
		//第一次執行本程式會導向這裡
		else{       		
			  //將使用者導向sellpage1.jsp	
			  PageToForward="sellpage1.jsp";	
		    }
        request.setAttribute("message",message);
		//導向PageToForward的地方
		RequestDispatcher dispatcher=request.getRequestDispatcher(PageToForward);
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
