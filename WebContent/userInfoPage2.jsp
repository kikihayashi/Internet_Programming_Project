<%@ page import="Model.*" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.User_Data,java.util.HashMap,java.io.*"%>
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
                 background-position:right bottom;  
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

#content_table  {width:100%;height:100%;
                 background-color:#FFF7E8;
                 cellpadding:10;
                 font-family: Microsoft JhengHei;}

</style> 

</head>

<body id="content_body">
	<div>

		<%--now_user是從Login那裡getAttribute來的 --%>
		<%
			User_Data user = (User_Data) session.getAttribute("now_user");
		%>
		<div id="content_div1">
			現在來到&nbsp;NTU &lt; Buy ● Sell &gt;&nbsp;<%=user.getAccountName()%>
			的個人資料區&nbsp; <a style="color: white" href='UserClear'>( 登出 )</a>
			<a style="color: white" href='Home'>( 回首頁 )<br>
			<br></a>
		</div>

		<h3>您的註冊資料：</h3>
		<table>
			<tr>

				<th id="content_th">&nbsp;姓名&nbsp;</th>
				<th id="content_th">&nbsp;學號&nbsp;</th>
				<th id="content_th">&nbsp;手機&nbsp;</th>
				<th id="content_th">&nbsp;信箱&nbsp;</th>
				<th id="content_th">&nbsp;學院&nbsp;</th>
				<th id="content_th">&nbsp;學歷&nbsp;</th>
			</tr>
			<tr>
				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getName()%>&nbsp;
					</div></td>

				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getSid()%>&nbsp;
					</div></td>

				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getPhone()%>&nbsp;
					</div></td>

				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getMail()%>&nbsp;
					</div></td>

				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getEdu()%>&nbsp;
					</div></td>

				<td><div style="background-color: #FAFAFA" align="center">
						&nbsp;<%=user.getGrade()%>&nbsp;
					</div></td>
			</tr>

		</table>

		<h3>您的購買記錄：</h3>
		<table>
			<tr>
				<th id="content_th">&nbsp;購買時間&nbsp;</th>
				<th id="content_th">&nbsp;商品名稱&nbsp;</th>
				<th id="content_th">&nbsp;購買數量&nbsp;</th>
				<th id="content_th">&nbsp;賣家名稱&nbsp;</th>
				<th id="content_th">&nbsp;總共花費&nbsp;</th>
			</tr>

			<%
	try{
		 //讀取商品資料
		 BufferedReader br = new BufferedReader(new FileReader("D:/user_buy_data.txt"));
	     //位於D槽	    	     
		 File file = new File("D:/user_buy_data.txt"); 
		 FileInputStream fis = new FileInputStream(file);
		 //獲得檔案大小
		 float size = (float)fis.available();
		 int all_items = 1;
		 String color;
		 //如果大小等於0(沒任何資料)  		
		 if((int)size==0){	    	    		
		 //不做任何事		    	
	     }
		 
		 //如果不等於0
		 else{
		 String line=null;
		
	     while( (line = br.readLine()) != null){
	    	 
	        //用/當分隔符號，解開每個部分
	        String[] lineTokes = line.split("/"); 
	        //買家名稱
	        String item_buyer_name = lineTokes[0];
	        //商品名稱
	        String item_name = lineTokes[1];
 	        //購買數量
	        String item_buy_number = lineTokes[2];
	        //購買時間
	        String item_time = lineTokes[3];
	        //賣家名稱
	        String item_seller = lineTokes[4];
	        //總共花費
	        String item_total_cost = lineTokes[5];	 
	        
	        
	        
	        if(user.getAccountName().equals(item_buyer_name)){
	        	
	        	
	        	if((all_items&1)==0){
		    		color="#E3E3E3";
		    		//商品總數加1
			        all_items=all_items+1;	
		    	  }
                  else{
                    color="#FAFAFA";
                    //商品總數加1
    		        all_items=all_items+1;	
                  }
	        	
	 %>
			<tr>
				<td><div style="background-color:<%=color%>" align="center">
						&nbsp;<%=item_time%>&nbsp;
					</div></td>

				<td><div style="background-color:<%=color%>" align="center"><%=item_name%></div></td>

				<td><div style="background-color:<%=color%>" align="center"><%=item_buy_number%></div></td>

				<td><div style="background-color:<%=color%>" align="center"><%=item_seller%></div></td>

				<td><div style="background-color:<%=color%>" align="center"><%=item_total_cost%>元
					</div></td>
			</tr>
			<%	   
	 
	        }	         
	     }
         }	 
	     //關閉txt檔
	     br.close();	
	     fis.close();
	     
	     }
	     //如果檔案不存在
	     catch(FileNotFoundException fnf){   	     
	           FileWriter fw = new FileWriter("D:/user_buy_data.txt");
		       fw.close();		        
	     }
	     //如果檔案讀取錯誤
	     catch(IOException fnf){
	    	   FileWriter fw = new FileWriter("D:/user_buy_data.txt");
			   fw.close();			      	  
	     }
	
	%>
		</table>

	</div>
</body>
</html>

