<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>
<style> 


html,body       {height:100%;
                 background-image:url(http://imgur.com/D16oARz.jpg);
                 background-size:auto;
                 font-family: Microsoft JhengHei;
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
              
</style> 
</head>

<body id="content_body">
	<div>
		
			<div id="content_div1">歡迎來到 NTU &lt; Buy ● Sell &gt; 上架商品頁面-Page1&nbsp;&nbsp;<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
	<br>
		<form name="form1" method="post" action="Sell">
							
				<div align="left">商品名稱：
				<input name="name" type="text" id="name" required><br><br>
				</div>
			
				<div align="left">上架數量：
			    <input name="number" type="text" id="number" required><br><br>
				</div>
			
				<div align="left">商品描述：
			    <input name="discription" type="text" id="discription" required><br><br>
				</div>
			
				<div align="left">單價金額：
		        <input name="cost" type="text" id="cost" required>
				</div>
								 			    
			    <%String message=(String)request.getAttribute("message");%>
			    <%-- 顯示message的訊息 --%>
                <h3 style="color:red"><%=message%></h3>   
			    
			<p>
				<label> 
				<input  type="reset" name="reset" value="重設">&nbsp;&nbsp;
			    <input  type="submit"name="page" value="上架商品" onclick="return confirm
			    ('確定上架嗎？\n\nNTU &lt; Buy ‧ Sell &gt;提醒您：\n\n商品上架後即無法再做更改！\n\n日後如遇買賣糾紛請自行處理，謝謝！');" >
				</label>
				
			</p>
		</form>
	</div>
</body>
</html>
