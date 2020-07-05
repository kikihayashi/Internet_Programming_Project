<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<style> 

html,body,table {
                 height:100%;
                 background-image:url(http://imgur.com/D16oARz.jpg);
                 background-size:auto;
                 font-family: Microsoft JhengHei;
                }     

#content_body   {
                 background-image:url(http://i.imgur.com/KraXrV5.png);
                 background-attachment:scroll;
                 background-position: right bottom;
                 background-repeat: no-repeat;
                 background-size:220px 220px;
                }

#content_div1   {
                 text-align:center;                 
                 color:white;font-size:1.5em;
                 background-color:#1B0052;                
                 border-style:double;
                 border-color:white
                } 
                           
#content_th     {
                 color:black;
                 text-align:left;
                }


</style> 

</head>

<body id="content_body">
	<div>
	
			<div id="content_div1">歡迎來到NTU &lt; Buy ● Sell &gt;請輸入您的資料來登入&nbsp;&nbsp;<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
		
		  <form name="form1" method="post" action="Login">
		
		  <%-- login_message是從Login.java那裡getAttribute來的 --%>
		  <% String login_message=(String)request.getAttribute("login_message"); %>
		  
		  <%-- 顯示login_message的訊息 --%>
          <h3 style="color:red"><%=login_message %></h3>
			
					<div id="content_th">帳號：
                    <input name="ID" type="text" id="ID"></div>
		  <br>
					<div id="content_th">密碼：
                    <input name="PWD" type="password" id="PWD"></div>
						
			<p>
				<label id="content_th">自動登入
				<input type="checkbox" name="auto_set" value="auto">&nbsp;&nbsp;
				
				<input id="content_th" type="submit" name="page" value="登入">
				<br><br>
				<a id="content_th" href='AddNewUser'>註冊新帳號</a>&nbsp;&nbsp;	
				</label>
			</p>
		</form>
	</div>
</body>
</html>
