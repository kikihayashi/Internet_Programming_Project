package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.io.*;

import Model.AccountCheck;
import Model.ItemCheck;
import Model.Shop_Data;
import Model.User_Data;

//這支Home程式扮演Controller的角色，負責處理使用者進入首頁的工作

@WebServlet("/Home")
public class Home extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	       
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    		    		    	
	    	//設定請求回應編碼為UTF-8 
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");	
			
			//建立一個新的AccountCheck，變數check_model指向AccountCheck
		    AccountCheck check_model = new AccountCheck();
		    //建立一個新的ItemCheck，變數model指向ItemCheck
		    ItemCheck model = new ItemCheck();		    
		    //建立一個新的HashMap，變數Map_login指向HashMap
			HashMap<String, Shop_Data> Now_Map=new HashMap<String, Shop_Data>();
			
			//建立一個新的變數session利用getSession()方法取得HttpSession物件
			HttpSession session = request.getSession();
						
			String line = null; 
			String home_message = "";
   	        int all_items = 1;
   	      
//以下進行cookie自動登入判斷----------------------------------------------------------------------------------
   	        
   	          //得到瀏覽器的cookie資料
			  Cookie[] cookies= request.getCookies();
   	          //如果有cookie
			  if (cookies != null){
				  //取得註冊時的資料，如果是第一次執行此程式會得到null
			      @SuppressWarnings("unchecked")
				  HashMap<String, User_Data> Map = (HashMap<String, User_Data>)getServletContext().getAttribute("Map");
				  
				  for (Cookie cookie_0 : cookies){		     		   
		     	       String name0 = cookie_0.getName();
		     	       String value0= cookie_0.getValue();
		     	            
		     	       //如果使用者之前有按"自動登入"
		     	       if ("login_set".equals(name0) && "auto".equals(value0)){
		     	            
		     	    	   //找是否有名稱為"ID"的cookie
		     	    	   for (Cookie cookie_1 : cookies){				     		   
				     	        String name1 = cookie_1.getName();
				     	        String value1= cookie_1.getValue();
				     	             
				     	        //如果有
				     	        if ("ID".equals(name1)){				     	        	
				     	        	String ID=value1;
				     	                
				     	        	//找是否有名稱為"PWD"的cookie
				     	            for (Cookie cookie_2 : cookies){								     		   
				     	                 String name2 = cookie_2.getName();
						     	         String value2= cookie_2.getValue();
						     	         	
						     	         //如果有
						     	         if ("PWD".equals(name2)){						     	        	
						     	        	 String PWD=value2;
						     	        	 
						     	        	 //檢查使用者的帳號、密碼
						     				 String login_message = (String)check_model.checkPassword(ID,PWD,Map);
						     				 
						     				 //如果帳密正確
						     				 if (login_message.equals("Yes_PWD")){						     					 
						     	        	 //得到現在要登入使用的now_user物件
						     				 User_Data now_user=Map.get(ID);
						     	             //將使用者的now_user物件setAttribute
						     	             session.setAttribute("now_user",now_user);  							     	        		
						     	             //設定一個已登入的字串，然後將它setAttribute，到buypage1.jsp用來判斷是否有登入情況
				        		             session.setAttribute("Sign_in","Sign_in");									         
						     				 }
						     				 
						     				 //如果帳密不正確
						     				 else{
						     			     //不做任何事 						     					 
						     				 }			     				 
						     				 //跳出迴圈
						     		         break;
						     		     }					     	         
				     	            }
				     	        //跳出迴圈
				     	        break;
				     	        }
		     	           }
		     	       //跳出迴圈
		     	       break;
		     	       }
		     	       
		               //如果使用者之前沒按"自動登入" 	       
		               else{
		               //不做任何事 	     	    	   
		               }    
                }
            }	
     
//以下執行商品資料載入-----------------------------------------------------------------------------------
   	        
	    	try{
	    		 //讀取商品資料
	    		 BufferedReader br = new BufferedReader(new FileReader("D:\\data.txt"));
	    	     //位於D槽	    	     
	    		 File file = new File("D:\\data.txt"); 
	    		 FileInputStream fis = new FileInputStream(file);
	    		 //獲得檔案大小
	    		 float size = (float)fis.available();
	    		 //如果大小等於0(沒任何資料)  		
	    		 if((int)size==0){	    	    		
	    			 home_message="NO_item";
	    	         Now_Map=null;
	    	    	 all_items=0;	    	    	
	    	    	}
	    		 
	    		//如果不等於0
	    		else{

	    	    while( (line = br.readLine()) != null){
	    	    	 
	    	        //用/當分隔符號，解開每個部分
	    	        String[] lineTokes = line.split("/"); 
	    	        //商品名稱
	    	        String name = lineTokes[0];
	      	        //剩餘數量
	    	        String number = lineTokes[1];
	    	        //相關描述 
	    	        String description = lineTokes[2];
	    	        //賣家名稱
	    	        String seller = lineTokes[3];
	    	        //單價金額
	    	        String cost = lineTokes[4];	    	     
                    //這是第幾個商品
	    	        String item_NO = Integer.toString(all_items);	    	        	     
	    	        //將使用者註冊資料存入now_data物件裡
	    	        Shop_Data now_data=model.add_items(item_NO,name,number,description,seller,cost,Now_Map);	     	     
	 			    //商品總數加1
	    	        all_items=all_items+1;
	    	    	}	    	     
	    	     }
	    	     //關閉txt檔
	    	     br.close();	
	    	     fis.close();
	    	     
	    	     }
	    	     //如果檔案不存在
	    	     catch(FileNotFoundException fnf){   	     
	    	           FileWriter fw = new FileWriter("D:/data.txt");
	 			       fw.close();
	 			       home_message="NO_item";
		    	       Now_Map=null;
		    	       all_items=0;		    	       
	    	    }
	    	     //如果檔案讀取錯誤
	    	     catch(IOException fnf){
	    	    	   FileWriter fw = new FileWriter("D:/data.txt");
		 			   fw.close();
		 			   home_message="NO_item";
	    	    	   Now_Map=null;
	    	    	   all_items=0;	 	    	    	  
	    	    }
	    	
	    	    //將訊息setAttribute
	    	    request.setAttribute("home_message",home_message);
	            //將訊息setAttribute
   	            request.setAttribute("message",""); 	         
   	            //將商品總數setAttribute
   	            session.setAttribute("all_items",all_items);	    	     
   	            //將Map物件以getServletContext().setAttribute，之後Login需要用到
		        getServletContext().setAttribute("Now_Map",Now_Map);
			    RequestDispatcher dispatcher=request.getRequestDispatcher("buypage1.jsp");
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
