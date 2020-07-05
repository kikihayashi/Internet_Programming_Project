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
		
			<div id="content_div1">歡迎來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page1&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
		<br>
		<form name="form1" method="post" action="AddNewUser">
									
                    <div id="content_th">姓名：
                    <input name="name" type="text" id="name" required></div>
		<br>		
					<div id="content_th">學號：
                    <input name="sid" type="text" id="sid" required></div>
		<br>		
					<div id="content_th">手機：
                    <input type="tel" id="phone" name="phone"  maxlength="10" 
                           pattern="[0][9][0-9]{8}" placeholder="09xxxxxxxx" required></div>
		<br>		
					<div id="content_th">信箱：
                    <input name="mail" type="email" id="mail" required></div>
		<br>		
					<div id="content_th">學院：
							<input type="radio" name="edu" value="文學院"required>文學院 
							<input type="radio" name="edu" value="工學院">工學院
							<input type="radio" name="edu" value="理學院">理學院
							<input type="radio" name="edu" value="醫學院">醫學院
							<input type="radio" name="edu" value="管理學院">管理學院
							<input type="radio" name="edu" value="法律學院">法律學院
							<input type="radio" name="edu" value="社會科學院">社會科學院							
							<input type="radio" name="edu" value="生命科學院">生命科學院
							<input type="radio" name="edu" value="電機資訊學院">電機資訊學院
							<input type="radio" name="edu" value="公共衛生學院">公共衛生學院
							<input type="radio" name="edu" value="生物資源暨農學院">生物資源暨農學院</div>
		<br>		
				    <div id="content_th">學歷：
				            <select required name="grade" >
				            <option value=""></option>
		                    <option value="大一" >大一</option>	
		                    <option value="大二">大二</option>	
		                    <option value="大三">大三</option>	
		                    <option value="大四">大四</option>	
		                    <option value="碩士">碩士</option>	
		                    <option value="博士">博士</option>	
		                    <option value="教授">教授</option>			                      
		                    </select></div>
			   
			<p>
				<label> <input id="content_th" type="reset" name="reset" value="重設">&nbsp;&nbsp;
					<input id="content_th" type="submit" name="page" value="下一頁"><br><br>
				</label>
				
			</p>
		</form>
	</div>
</body>
</html>
