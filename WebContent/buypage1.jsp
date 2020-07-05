<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Shop_Data,Model.User_Data,Model.ItemCheck,java.util.HashMap"%>

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
                 background-position: bottom;
                 background-repeat: no-repeat;
                 background-size:500px 500px;
                }

#content_div1   {
                 text-align:center; 
                 width:100%;height:10%;
                 color:white;font-size:1.5em;
                 background-color:#1B0052;
                 font-family:Microsoft JhengHei;
                 border-style:double;
                 border-color:white
                }
                 
#content_div2   {
                 width:100%;height:150%;
                 background-color:#FFF7E8;
                }    
                           
#content_th     {
                 background-color:#1B0052;
                 font-family: Microsoft JhengHei;
                 color:white}

#content_table  {
                 width:100%;
                 height:100%;
                 background-color:#FFF7E8;
                 cellpadding:10;
                 font-family: Microsoft JhengHei;
                }

</style> 
</head>
<body id="content_body">
	 
		<%		
		      //建立一個新的BuyCheck，變數model指向BuyCheck
		      ItemCheck model = new ItemCheck();
		      //這是從Login那裡得到的訊息，用來判斷使用者是否已登入
		      String Sign_in =(String)session.getAttribute("Sign_in");	
		      //如果已登入
		      if("Sign_in".equals(Sign_in)){
		    	  //user是從Login那裡getAttribute來的 
	              User_Data user=(User_Data)session.getAttribute("now_user"); 
		%>		          		      
		      <div id="content_div1"> 
		            歡迎來到NTU &lt; Buy ● Sell &gt;這是一個給台大生自由買賣的免費平台！&nbsp;
		      <a style=color:white href='userInfoPage2.jsp'>Hello !&nbsp;<%=user.getAccountName()%>&nbsp;</a>&nbsp;&nbsp;
		      <a style=color:white href='UserClear'>( 登出 )</a></div><br>		            
              <a style="color:black;font-family:Microsoft JhengHei" href='Sell'>上架商品</a>&nbsp;&nbsp;<br>          
		<%
              }
		      //如果尚未登入  
		      else{
		%>	
		      <div id="content_div1">
		            歡迎來到NTU &lt; Buy ● Sell &gt;這是一個給台大生自由買賣的免費平台！&nbsp;
		      <a style=color:white href='Login'>( 登入 )</a>
		      <a style=color:white href='AddNewUser'>( 註冊 )</a></div><br><br> 
	    <%
              }
		%>	
             <br>
             <form name="form" method="post" action="Buy">   
             <%--以下將商品資料，以迴圈方式輸入到表格上 --%>     
		<%  
		     @SuppressWarnings("unchecked")
		     //這是從Home.java那裡得到的物件
		     HashMap<String, Shop_Data> Now_Map = (HashMap<String, Shop_Data>)getServletContext().getAttribute("Now_Map");		 
		     //得到商品總數量 
		     int all_items=(int)session.getAttribute("all_items"); 
		     //得到首頁是否有商品訊息
		     String home_message=(String)request.getAttribute("home_message");	     
		     //如果沒有任何商品了
		     if("NO_item".equals(home_message)){
		%>  	
		     <%---------顯示首頁訊息 --------%>
		     <h3 style="color:red">現在沒有任何商品了，成為第一個上架商品的賣家吧！</h3>
			 <h3 style="color:red">(PS. 越快上架，商品會排在越前面喔！)</h3>   
		<%
			 }
		     //如果還有商品 
		     else{
		%> 
		
		     <table id=content_table>  
		     <tr>		      
                <th id="content_th">&nbsp;商品編號&nbsp;</th>
                <th id="content_th"><font color="white">&nbsp;商品名稱&nbsp;</font></th>
                <th id="content_th"><font color="white">&nbsp;剩餘數量&nbsp;</font></th>
                <th id="content_th"><font color="white">&nbsp;相關描述&nbsp;</font></th>
                <th id="content_th"><font color="white">&nbsp;賣家名稱&nbsp;</font></th>
                <th id="content_th"><font color="white">&nbsp;單價金額&nbsp;</font></th>
        <% 
                //如果已登入
		        if("Sign_in".equals(Sign_in)){
		%>	
                <th id="content_th"><font color="white">&nbsp;選擇商品&nbsp;</font></th>
        <%
			    } 		  
		%>
             </tr>     
		<%   
		     //從第一個商品開始，分別把它的所有資訊顯示出來，直到最後一個
		     for (int i=1 ; i<all_items ; i++)
		     { 	          
		    	  //將"第幾個"商品轉成字串形式
		    	  String item_NO = Integer.toString(i);
		    	  //從Buy_Map拿出對應編號的商品資料
		    	  Shop_Data Now_Buy=Now_Map.get(item_NO);
		    	  //奇數
		    	  String color = ((i&1)==0)? "#E3E3E3" : "#FAFAFA";
		%> 
		      <%--以下將商品資料顯示在表格上 --%>
		      <tr>
		          <%-------------------------商品編號---------------%>
		          <td style="background-color:<%=color%>"><div align="center"><%=Now_Buy.getItem_NO()%></div></td>
		          <%-------------------------商品名稱---------------%>
		          <td style="background-color:<%=color%>"><div align="center"  ><%=Now_Buy.getItem_name()%></div></td>
		          
		<%        
		          if("1".equals(Now_Buy.getItem_number())){			
		%>
			      <%-------------------------剩餘數量---------------%>
	              <td style="background-color:<%=color%>"><div align="center" style="color:red"><%=Now_Buy.getItem_number()%></div></td>
		<%
		           }		
		          else{
		%>
		          <%-------------------------剩餘數量---------------%>
		          <td style="background-color:<%=color%>"><div align="center"><%=Now_Buy.getItem_number()%></div></td>
		<%
		          }           
		%>
		          <%-------------------------相關描述---------------%>
		          <td style="background-color:<%=color%>"><div align="left"  >&nbsp;<%=Now_Buy.getItem_description()%>&nbsp;</div></td>
		          <%-------------------------賣家名稱---------------%>		          
		          <td style="background-color:<%=color%>"><div align="center"><%=Now_Buy.getItem_seller()%></div></td>
		          <%-------------------------單價金額---------------%>
		          <td style="background-color:<%=color%>"><div align="center"><%=Now_Buy.getItem_cost()%>元</div></td>	
		<% 
		             //如果已登入
		             if("Sign_in".equals(Sign_in)){
		%>		          	          
		             <%--這裡的item_NO可以判斷使用者選了哪一些商品 --%>		          
		             <td style="background-color:<%=color%>"><div align="center"><input type="checkbox" name="choose" value=<%=item_NO%>></div></td>	
		<%	 
		             }
		%>		
	          	          
		      </tr> 	  
		<%	 
		      }
		%>		
		      </table>	
    
		<%           
		             //得到使用者是否有選商品的訊息
	                 String message=(String)request.getAttribute("message");
		%>           
		             <%-- 顯示message的訊息 --%>
                     <h3 style="color:red"><%=message%></h3>  
		<%             
		             //如果已登入
		             if("Sign_in".equals(Sign_in)){
	    %>
                <p>  
				<label> 			
				        <input style="color:black;font-family: Microsoft JhengHei" type="reset" name="reset" value="重新選擇">&nbsp;&nbsp;
					    <input style="color:black;font-family: Microsoft JhengHei" type="submit"name="page"  value="加入購物車">				    
				</label>
			   </p>   
		<%
                     }
            }
		%>		
			</form>
	
</body>

</html>
