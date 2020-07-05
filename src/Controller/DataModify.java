package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import Model.Buy_Data;
import Model.Sell_Data;
import Model.Shop_Data;
import Model.User_Data;

//這支Data_Modify程式負責處理商品資料的修改

@WebServlet("/DataModify")
public class DataModify extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request,
			                      HttpServletResponse response)
			                      throws ServletException, IOException {
		
		 //建立一個新的變數session利用getSession()方法取得HttpSession物件
		 HttpSession session=request.getSession();
		
		 //int all_items;
		 //int i;
	     //String item_NO;
	     String data;
	     String data2;
	     String total_data="";
	     String user_buy_data="";
	     Date d = new Date(); 
		 //得到商品總數量 
	     int all_items=(int)session.getAttribute("all_items"); 
	     //得到是否有上架商品的資訊 
	     String listed=(String)request.getAttribute("listed");
	     
	     //now_user是從Login那裡getAttribute來的
	     User_Data user=(User_Data)session.getAttribute("now_user");
	          
	     
	  	 @SuppressWarnings("unchecked")
		 HashMap<String, Shop_Data> Now_Map  = (HashMap<String, Shop_Data>)getServletContext().getAttribute("Now_Map");
		 @SuppressWarnings("unchecked")
		 HashMap<String, Buy_Data>  Buy_Map  = (HashMap<String, Buy_Data> )getServletContext().getAttribute("Buy_Map");
		 @SuppressWarnings("unchecked")
		 HashMap<String, Sell_Data> Sell_Map = (HashMap<String, Sell_Data>)getServletContext().getAttribute("Sell_Map");
		 
		 //如果有上架商品
		 if("listed".equals(listed)){
			
			//從Now_Map拿出對應編號的商品資料
	    	Sell_Data sell_data=Sell_Map.get(user.getAccountName());
			 
	    	total_data=sell_data.getItem_name()+"/"+sell_data.getItem_sell_number()+"/"+sell_data.getItem_discription()+"/"
   	                  +sell_data.getItem_seller()+"/"+sell_data.getItem_cost()+"\r\n";
	    	//覆蓋掉原始的data資料
			FileWriter fw = new FileWriter("D:/data.txt",true);
			fw.write(total_data); 
			fw.close();
		 }
		 		 
		 //如果沒有上架商品，就是有買商品
		 else{ 
	     //從第一個商品開始，分別把它的所有資訊顯示出來，直到最後一個
	     for (int i=1 ; i<all_items ; i++){ 	          
	    	  //將"第幾個"商品轉成字串形式
	    	  String item_NO = Integer.toString(i);
	    	  //從Now_Map拿出對應編號的商品資料
	    	  Shop_Data now_data=Now_Map.get(item_NO);
	    	  //從Buy_Map拿出對應編號的商品資料
	    	  Buy_Data buy_data=Buy_Map.get(item_NO);
	    	  
	    	  //如果使用者有買到現在這個商品
	    	  if(buy_data!=null){

	    		  //將原始商品的剩餘數量減去使用者購買數量
	    		  int k=Integer.parseInt(now_data.getItem_number())-Integer.parseInt(buy_data.getItem_buy_number());
	    		  
	    		  
	    		  int total_cost=Integer.parseInt(buy_data.getItem_buy_number())*Integer.parseInt(buy_data.getItem_cost());
	    		  //將商品資料打入data2字串
	    	      data2=user.getAccountName()+"/"+now_data.getItem_name()+"/"+buy_data.getItem_buy_number()+"/"
	    	           +d.toString()+"/"+now_data.getItem_seller()+"/"+Integer.toString(total_cost)+"/"+"\r\n";
	    	           //將data字串存入total_data字串
	   	               user_buy_data=user_buy_data+data2;
	   	               
	    		  //如果減完還有剩	   	               
	    		  if(k>0){
	    		     //將商品資料打入data字串
	    	         data=now_data.getItem_name()+"/"+Integer.toString(k)+"/"+now_data.getItem_description()+"/"
	    	             +now_data.getItem_seller()+"/"+now_data.getItem_cost()+"\r\n";
	    	         //將data字串存入total_data字串
	   	             total_data=total_data+data;

	    		  }
	    		  //如果減完沒了
	    	      else{
	    	      //就不把資料放進去data裡
	    	      }
	    	  }
	    	  //如果使用者沒有買現在這個商品
	    	  else{
	    		  //將商品資料打入data字串
		          data=now_data.getItem_name()+"/"+now_data.getItem_number()+"/"+now_data.getItem_description()+"/"
		              +now_data.getItem_seller()+"/"+now_data.getItem_cost()+"\r\n";
		          //將data字串存入total_data字串
		          total_data=total_data+data;
	    	  }       
	     }
	     
	    //將購買記錄寫入user_buy_data資料
		FileWriter ubd = new FileWriter("D:/user_buy_data.txt",true);
		ubd.write(user_buy_data); 
		ubd.close();
	    //覆蓋掉原始的data資料
		FileWriter fw = new FileWriter("D:/data.txt");
		fw.write(total_data);
		fw.close();
	}
		
		//讓使用者導向Home.java	
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
