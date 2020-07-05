package Controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.User_Data;
import Model.AccountCheck;

//這支AddNewUser程式扮演Controller的角色，負責處理使用者註冊的工作

@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	    
	    //建立一個新的AccountCheck，變數check_model指向AccountCheck
	    private Model.AccountCheck model= new AccountCheck();
	    
	    //建立一個新的HashMap，變數Map_login指向HashMap
	    HashMap<String, User_Data> Map=new HashMap<String, User_Data>();
	    
	    protected void processRequest(HttpServletRequest request,
			                          HttpServletResponse response) 
			                          throws ServletException, IOException {
				
		//設定請求回應編碼為UTF-8 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		//建立一個新的變數session利用getSession()方法取得HttpSession物件
		HttpSession session = request.getSession();
		
		//建立一個導向設定，隨著程式條件而改變
		String jspPageToForward = null;
		//建立一個註冊訊息設定，隨著程式條件而改變
		String register_message = "";
		
		//得到目前是到註冊頁面的哪裡
		String nowpage = request.getParameter("page"); 
		
	   //如果在註冊介面Page1已按"下一頁"，就設定導向註冊介面Page2
	   if  ("下一頁".equals(nowpage)){
			//得到註冊介面Page1的資訊
			String name_show =request.getParameter("name");	
			String sid_show  =request.getParameter("sid");	
			String phone_show=request.getParameter("phone");
			String mail_show =request.getParameter("mail");
			String edu_show  =request.getParameter("edu");
			String grade_show=request.getParameter("grade");
			
			//將以上物件setAttribute
			session.setAttribute("name_show",name_show);
			session.setAttribute("sid_show",sid_show);
			session.setAttribute("phone_show",phone_show);
			session.setAttribute("mail_show",mail_show);
			session.setAttribute("edu_show",edu_show);
			session.setAttribute("grade_show",grade_show);
			
            //設定導向註冊介面Page2
		    jspPageToForward ="addUserPage2.jsp";		     
		   }
		
	   //不是以上情況，就設定導向註冊介面Page1
	   else{
			//設定導向註冊介面Page1
		    jspPageToForward ="addUserPage1.jsp";	
		   }
		
	   //如果在註冊頁面Page2已按"送出"，就進行帳號登錄的邏輯檢核相關動作
	   if  ("送出".equals(nowpage)){
			//得到使用者名稱
			String ID =request.getParameter("ID");
			String PWD =request.getParameter("PWD");
			 
			//進行使用者名稱邏輯判斷，看是否已有人使用
			boolean message=model.checkAccountNameExistence(ID,Map);
			 
			//如果是true代表使用者名稱已經被使用，就設定導向註冊頁面Page2重新換一個帳號
			if (message){				 
				register_message="所輸入的帳戶名稱已經有人使用，請輸入另一個帳戶名稱！";
			    jspPageToForward ="addUserPage2.jsp";				
			   }
			
			//如果是false代表使用者名稱還沒有被使用，就設定導向註冊介面Page3
			else{
				 //還沒有被使用就不用輸入訊息
				 register_message = "";
				 String name =(String)session.getAttribute("name_show");
				 String sid  =(String)session.getAttribute("sid_show");
				 String phone=(String)session.getAttribute("phone_show");
				 String mail  =(String)session.getAttribute("mail_show");
				 String edu  =(String)session.getAttribute("edu_show");
				 String grade  =(String)session.getAttribute("grade_show");
				 
				 //將使用者註冊資料存入newuser物件裡
				 User_Data new_user=model.addNewUser(name,sid,phone,mail,edu,grade,ID,PWD,Map);
				 //將新註冊使用者的new_user物件setAttribute
				 session.setAttribute("new_user",new_user);  
				 //將Map物件以getServletContext().setAttribute，之後Login需要用到
				 getServletContext().setAttribute("Map",Map);
				 //設定導向註冊介面Page3				 				 
			     jspPageToForward ="addUserPage3.jsp";		     
			    }
			
		     }
		        //將register_message物件setAttribute，註冊頁面Page2要用到
		        request.setAttribute("register_message",register_message);
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
