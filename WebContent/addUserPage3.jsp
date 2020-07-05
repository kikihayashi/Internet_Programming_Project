<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.User_Data"%>
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

			<div id="content_div1">現在來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page3&nbsp;&nbsp;<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
    <br>
                <%--new_user是從AddNewUser那裡getAttribute來的 --%>
                <% User_Data user=(User_Data)session.getAttribute("new_user"); %>
					
				<div id="content_th">
				帳號：<%=user.getAccountName()%><br>			
				密碼：<%=user.getPassword()%><br>			
				姓名：<%=user.getName()%><br>			
				學號：<%=user.getSid()%><br>			
				手機：<%=user.getPhone()%><br>			
				信箱：<%=user.getMail()%><br>			
				學院：<%=user.getEdu()%><br>			
				學歷：<%=user.getGrade()%>
				</div>
					
		<p>
		  <label> 
		        <a id="content_th" href='Login'>恭喜你完成註冊，按這裡登入即可開始買東西囉！</a>    
		  </label>

		</p>
	</div>
</body>
</html>

