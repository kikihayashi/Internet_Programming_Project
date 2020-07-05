package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//這支UserClear程式扮演使用者登出的角色

@WebServlet("/UserClear")
public class UserClear extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request,
			                      HttpServletResponse response)
			                      throws ServletException, IOException {
		
		//建立一個新的變數session利用getSession()方法取得HttpSession物件
		HttpSession session=request.getSession();
        //刪除session會話
		session.invalidate();
		
		//刪除cookie資料
		Cookie cookie0=new Cookie("login_set","");
		Cookie cookie1=new Cookie("ID","");
	    Cookie cookie2=new Cookie("PWD","");
	    cookie0.setMaxAge(0);
	    cookie1.setMaxAge(0);
	    cookie2.setMaxAge(0);
	    response.addCookie(cookie0);
	    response.addCookie(cookie1);
	    response.addCookie(cookie2);
				
		//讓使用者導向Login.java	
	    response.sendRedirect("Home");
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
