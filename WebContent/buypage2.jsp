<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Shop_Data,Model.Buy_Data,java.util.HashMap"%>

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
	
			<div id="content_div1">商品確認，請確認購買的商品及數量是否正確！
			<a style=color:white href='Home'>( 回首頁 )</a>
			<a style=color:white href='UserClear'>( 登出 )<br><br></a></div>
	<br>
        <form name="form" method="post" action="Buy">
		<table id="content_table">
		 <tr>
             <th id="content_th">&nbsp;商品名稱&nbsp;</th>
             <th id="content_th">&nbsp;單價金額&nbsp;</th>
             <th id="content_th">&nbsp;購買數量&nbsp;</th>
         </tr>
         
         <%--以下設定一些初始參數 --%>
		 <%! 
		     String[] buy_number_memory;
		 %>
		 <%--以下將商品資料，以迴圈方式輸入到表格上 --%>     
		 <%
		     //這是要存每個商品的單價所設置的	 
		     String money=",";
		     @SuppressWarnings("unchecked")
		     //這是從Home.java那裡得到的物件
		     HashMap<String, Shop_Data> Now_Map = (HashMap<String, Shop_Data>)getServletContext().getAttribute("Now_Map");
		     //得到商品總數量 
		     int all_items=(int)session.getAttribute("all_items");		 
		     //得到使用者選擇的商品 
	         String[] choose=(String[])session.getAttribute("choose");
	         //得到使用者選擇"幾樣"商品 
	         int size=java.lang.reflect.Array.getLength(choose);	         
	         //基本上使用者最少會選擇一個商品，所以一定會執行以下程式
	         
	         String color;
	         if (choose!=null) {
	        	 
	        	 //choose[i]代表使用者之前選過的商品編號
	        	 for (int i=0;i<size;i++){ 	    
			          //從Buy_Map拿出使用者選的那個商品資料
			    	  Shop_Data Now_Buy=Now_Map.get(choose[i]);	        	       	      
	        	      //將使用者選的那個商品單價存到money，以逗號分開
	        	      money=money+Now_Buy.getItem_cost()+",";	
	        	      
	        	      if((i&1)==0){
	  		    		color="#FAFAFA";
	  		    	  }
	                    else{
	                      color="#E3E3E3";
	                    }
	        	      
	        	      
	     %>  		
	             <tr>
	                  <%------------------------商品名稱---------------%>
		              <td><div style="background-color:<%=color%>" align="center"><%=Now_Buy.getItem_name()%></div></td>
		              <%------------------------單價金額---------------%>
		              <td><div style="background-color:<%=color%>" align="center"><%=Now_Buy.getItem_cost()%>元</div></td>
		              <%------------------------購買數量---------------%>
		              <td><div style="background-color:<%=color%>" align="center"><select name="buy">
		                          <option value="0">0</option>	
		 <%           
		              //這裡在確定使用者之前是否有填過商品資料
		              buy_number_memory=(String[])session.getAttribute("buy_number_memory");
		              //如果沒有
		              if(buy_number_memory==null){		  
		            	//以下得到使用者選的那個商品的剩餘數量
			              for(int j=1 ; j<=Integer.parseInt(Now_Buy.getItem_number()) ; j++){
			        	      //將整數值轉成字串
			        	      String buy_NO=Integer.toString(j); 
		 %>          
			                	  <%--顯示下拉式選單--%>      
	　                                                        <option><%=buy_NO%></option>
		 <%             	  
	                      }
		              }	
		              //如果有
		              else{
		                  //以下得到使用者選的那個商品的剩餘數量
		                  for(int j=1 ; j<=Integer.parseInt(Now_Buy.getItem_number()) ; j++){
		        	         //將整數值轉成字串
		        	         String buy_NO=Integer.toString(j);
		        	         //如果數到和使用者之前選的購買數量一樣
		        	         if(buy_number_memory[i].equals(buy_NO)){
		 %>
		                      <%--顯示下拉式選單(預設值)--%>      
　                                                        <option selected><%=buy_NO%></option>
		 <%             	  
                             }
		        	         else{
		 %>
		                	  <%--顯示下拉式選單--%>      
　                                                         <option><%=buy_NO%></option>
		 <%             	  	             
                             }
	   		              }            
                      }
	     %>        
                              </select></div></td> </tr>
         <% 	
  		          } 	    
             }   	
	           //將單價金額setAttribute，等下Buy.java會用到
	           session.setAttribute("money", money);
	     %>      
	    	   </table>
	 	 <%    	     
	           if(buy_number_memory!=null){	
                  //得到Buy.java的資訊
                  String message=(String)request.getAttribute("message"); 
         %>         
                  <%-- 顯示message的訊息 --%>
                  <h3 style="color:red"><%=message%></h3>       
         <%
	   		   } 
	        	 
	     %>        
		 <p>
				<label> 
					    <input type="submit"name="page" value="金額試算">
					    <input type="submit"name="page" value="確定購買" onclick="return confirm
					    ('確定購買嗎？\n\nNTU &lt; Buy ‧ Sell &gt;提醒您：\n\n確定購買後即無法再做更改！\n\n如需更改請自行連繫賣家，謝謝！');" >					    				    
				</label>			
	     </p>
	   </form>
     </div>
   </body>
 </html>

