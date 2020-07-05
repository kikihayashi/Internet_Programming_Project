package Controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.AccountCheck;
import Model.User_Data;

//這支Login程式扮演Controller的角色，負責處理使用者登入的工作
 
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	       
		protected void processRequest(HttpServletRequest request,
			                          HttpServletResponse response)
			                          throws ServletException, IOException {
	    	
        //設定請求回應編碼為UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
					    
		//建立一個頁面導向設定，隨著程式條件而改變
		String PageToForward =null;
		//建立一個登入訊息設定，隨著程式條件而改變
		String login_message=" ";
		
		//得到登入頁面Page1的一些資訊						
		String user_ID    = request.getParameter("ID");
		String user_PWD   = request.getParameter("PWD");
		String login_set  = request.getParameter("auto_set");
		String login_page = request.getParameter("page");

		//建立一個新的變數session利用getSession()方法取得HttpSession物件
		HttpSession session=request.getSession();
		
		//建立一個新的AccountCheck，變數check_model指向AccountCheck
	    AccountCheck check_model = new AccountCheck();
		//取得註冊時的資料，如果是第一次執行此程式會得到null
	    @SuppressWarnings("unchecked")
		HashMap<String, User_Data> Map = (HashMap<String, User_Data>)getServletContext().getAttribute("Map");
		
		//看使用者是否在登入頁面Page1
        if ("登入".equals(login_page)){
        	       	    
			//如果在(已按登入)，檢查使用者的帳號、密碼
			login_message = (String)check_model.checkPassword(user_ID,user_PWD,Map);
												
				//以下進行帳密邏輯判斷，在各種不同情況下的回應
                if      (login_message.equals("Nothing")){					
                	     //輸入錯誤提示
					     login_message="請輸入帳號與密碼";
					     //將使用者導向登入頁面Page1
					     PageToForward ="userInfoPage1.jsp";					
				        }
						
                else if (login_message.equals("No_ID")){	
                	     //輸入錯誤提示
					     login_message="輸入帳號錯誤，查無此帳號";
					     //將使用者導向登入頁面Page1
					     PageToForward ="userInfoPage1.jsp";
				        }
				
				else if (login_message.equals("No_PWD")){
					     //輸入錯誤提示
					     login_message="輸入密碼錯誤";
					     //將使用者導向登入頁面Page1
					     PageToForward ="userInfoPage1.jsp";																	
				        }
				
                else if (login_message.equals("Yes_PWD")){               	
                	     //正確提示不需寫任何東西
					     login_message="";			     
					     
					     //如果有選"自動登入"
					     if ("auto".equals(login_set)){
					    	 
	                	     //設定cookie儲存使用者的帳密及自動登入資訊
					    	 Cookie cookie0=new Cookie("login_set",login_set);
	                	     Cookie cookie1=new Cookie("ID",user_ID);
	                	     Cookie cookie2=new Cookie("PWD",user_PWD);
	                	     //設定生命週期為5分鐘
	                	     cookie0.setMaxAge(5*60);
	                	     cookie1.setMaxAge(5*60);
	                	     cookie2.setMaxAge(5*60);
	                	     //填加cookie資料到瀏覽器
	                	     response.addCookie(cookie0);
	                	     response.addCookie(cookie1);
	                	     response.addCookie(cookie2);
					        }					
                	         //得到現在要登入使用的now_user物件
					         User_Data now_user=Map.get(user_ID);        		    
        		             //將使用者的now_user物件setAttribute
        		             session.setAttribute("now_user",now_user);
        		             //設定一個已登入的字串，然後將它setAttribute，到Home.java時用來判斷是否有登入情況
        		             session.setAttribute("Sign_in","Sign_in");
					         //將使用者導向登入頁面Page2
					         PageToForward ="Home";		
				            }					    		    
         }
        
         //如果不在，可能是第一次執行此程式，或是使用者之前有按自動登入        		 
         else{		             			 		 
			  //讓使用者導向登入頁面Page1
              PageToForward ="userInfoPage1.jsp";		  
		     } 
                     
		 //將此login_message物件setAttribute，登入頁面page1要使用
	     request.setAttribute("login_message",login_message);
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
