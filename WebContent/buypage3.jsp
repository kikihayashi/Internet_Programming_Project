<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Shop_Data,Model.Buy_Data,Model.ItemCheck,Model.User_Data,java.util.HashMap"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>
<style> 

table,th,td     {border: 0.6px solid black;} 

html,body,table {height:100%;
                 background-image:url(http://imgur.com/D16oARz.jpg);
                 background-size:auto;
                }    

#content_body   {
                 background-image:url(http://i.imgur.com/KraXrV5.png);
                 background-attachment:scroll;
                 background-position: right bottom;
                 background-repeat: no-repeat;
                 background-size:400px 400px;}

#content_div1   {text-align:center; 
                 width:100%;height:10%;
                 color:white;font-size:1.5em;
                 background-color:#1B0052;
                 font-family:Microsoft JhengHei;
                 border-style:double;
                 border-color:white}
                 
#content_div2   {width:100%;height:150%;
                 background-color:#FFF7E8;}    
                           
#content_th     {background-color:#1B0052;
                 font-family: Microsoft JhengHei;
                 color:white}

#content_table  {
                 background-color:white;
                 cellpadding:10;
                 font-family: Microsoft JhengHei;}

</style> 
</head>

<body id="content_body">
	<div>
	
			 <div id="content_div1">恭喜您買到本商品，我們會盡速聯絡賣家發貨！<br><br></div>
	<br>
        <form name="form" method="post" action="Buy">
		<table id="content_table">
		 <tr>
             <th id="content_th">商品名稱</th>
             <th id="content_th">賣家名稱</th>
             <th id="content_th">購買數量</th>
         </tr>

		 <%--以下將商品資料，以迴圈方式輸入到表格上 --%>     
		 <%	
		         //建立一個新的BuyCheck，變數model指向BuyCheck
		         ItemCheck model = new ItemCheck();		    
		         @SuppressWarnings("unchecked")
		         //這是從Home.java那裡得到的物件
	             HashMap<String, Shop_Data> Now_Map = (HashMap<String, Shop_Data>)getServletContext().getAttribute("Now_Map");
	             //now_user是從Login那裡getAttribute來的
	    	     User_Data user=(User_Data)session.getAttribute("now_user");
	             //建立一個新的Buy_Map變數指向HashMap<String, Buy_Data>物件，覆蓋掉先前的記錄
	             HashMap<String, Buy_Data> Buy_Map=new HashMap<String, Buy_Data>();	        
	             //得到使用者選擇的商品 
	             String[] choose=(String[])session.getAttribute("choose");
		         //得到使用者選擇的商品的購買數量 
		         String[] buy_number_memory=(String[])session.getAttribute("buy_number_memory");
	             //得到使用者選擇"幾樣"商品 
	             int size=java.lang.reflect.Array.getLength(choose);
	             
	             String color;
	        	 //choose[i]代表使用者之前選過的商品編號
	        	 for (int i=0 ; i<size ; i++){ 	        
			          //從Buy_Map拿出使用者選的那個商品資料
			    	  Shop_Data Now_Buy=Now_Map.get(choose[i]);
			          //如果使用者後來沒有選擇這項商品(購買數量=0)		          
	        	      if("0".equals(buy_number_memory[i])){
	        	         //不做任何事
	        	      }
			          //如果有選(購買數量>0)	
	        	      else{
	        	    	  
	        	    	  if((i&1)==0){
	  	  		    		color="#FAFAFA";
	  	  		    	  }
	  	                    else{
	  	                      color="#E3E3E3";
	  	                    }
	        	    	  	    	 	        	    	  
	        	    	   //將使用者購買資料存入buy_data物件裡
	     	    	       Buy_Data buy_data=model.user_buy(Now_Buy.getItem_NO(),Now_Buy.getItem_name(),buy_number_memory[i],Now_Buy.getItem_cost(),user.getAccountName(),Buy_Map);	     	    	       
	     %>  		
	                       <tr>
	                          <%------------------------商品名稱---------------%>
		                      <td><div style="background-color:<%=color%>" align="center"><%=Now_Buy.getItem_name()%></div></td>
		                      <%------------------------賣家名稱---------------%>
		                      <td><div style="background-color:<%=color%>" align="center"><%=Now_Buy.getItem_seller()%></div></td>	
		                      <%------------------------購買數量---------------%>
		                      <td><div style="background-color:<%=color%>" align="center"><%=buy_number_memory[i]%></div></td>			 
                           </tr>
         <% 
                      }   
                 }
	        	  //將Buy_Map物件以getServletContext().setAttribute，之後Login需要用到
				  getServletContext().setAttribute("Buy_Map",Buy_Map);	        	 
         %>
         </table>
                  <%-- 從Buy.java那裡得到的物件 --%>
                  <% String message=(String)request.getAttribute("message"); %>              
                  <%-- 顯示message的訊息 --%>
                  <h3 style="color:red">本次購買<%=message%></h3>    
		 <p>
				<label> 
					   <a style="color:black;font-family: Microsoft JhengHei" href='DataModify'>回首頁繼續購物！</a>
				</label>			
	     </p>
	   </form>
     </div>
   </body>
 </html>

