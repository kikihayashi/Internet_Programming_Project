<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<style> 

html,body       {height:100%;
                 font-family: Microsoft JhengHei;
                 background-image:url(http://imgur.com/D16oARz.jpg);
                 background-size:auto;
                }  

#content_body   {
                 background-image:url(http://i.imgur.com/KraXrV5.png);
                 background-attachment:scroll;
                 background-position: right bottom;
                 background-repeat: no-repeat;
                 background-size:220px 220px;}

#content_div1   {text-align:center; 
                 width:100%;height:10%;
                 color:white;font-size:1.5em;
                 background-color:#1B0052;
                 border-style:double;
                 border-color:white;
                } 
                           
#content_th     {
                 color:black;
                 text-align:left;
                }

</style> 

</head>
<body id="content_body">
	<div>
	
			<div id="content_div1">現在來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page2&nbsp;&nbsp;<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
	<br>
		<form name="form1" method="post" action="AddNewUser">

          <%--這裡顯示使用者名稱是否被註冊的結果--%>
          <% String register_message=(String)request.getAttribute("register_message"); %>
          
          <%-- 顯示register_message的訊息 --%>
          <h3 style="color:red"><%=register_message %></h3>
			
				<div id="content_th" align="left">設定登入帳號：
                <input name="ID" type="text" id="ID" required></div>
		<br>	
				<div id="content_th" align="left">設定登入密碼：
                <input name="PWD" type="text" id="PWD" required></div>
	
			<p>
				<label> 
				    <input id="content_th" type="reset" name="reset" value="重設">&nbsp;&nbsp;
					<input id="content_th" type="submit" name="page" value="送出"><br><br>
				</label>				
			</p>
		</form>
	</div>
</body>
</html>
