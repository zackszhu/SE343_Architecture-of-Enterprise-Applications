<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="materialize/css/materialize.min.css">
<title>Welcome to My Bookstore - Login Page</title>
</head>
<body class="grey lighten-5">
	<div class="container" style="margin-top: 5%;">
    <div class="row">
        <div class=" col s2 offset-s5" style="margin: 0; padding: 0;">
            <img src="img/bookstore.jpg" class="z-depth-2" style="border: solid 5px #f5f5f5; border-radius: 50%; float: left">
        </div>
        <div class="col s6 white z-depth-1 offset-s3" style="margin-top: -20px;">
            <form id="form" method=POST action="login">
                <div class="row" style="margin-left: 0; margin-right:0;">
                	<div class="col s6" style="margin: 1.75rem 0 0.5rem 0;">
                        <%=(String)request.getAttribute("Time") %>
                    </div>
                	<div class="col s6 right-align " style="margin: 1.75rem 0 0.5rem 0;">
                        <a href="index?locale=en">English</a>
                        /
                        <a href="index?locale=zh">中文</a>
                    </div>
                    <div class="col s12" style="margin: 1.75rem 0 0.5rem 0;">
                        <span class="blue-text text-accent-4">
                        <%=(String)request.getAttribute("login") %>
                        </span>
                        <hr>
                    </div>
                    <% Object id = request.getSession().getAttribute("ErrorMsg");
                    	if (id == null) {
                    		id = 0;
                    	}
                    	if (id.equals(-1)) { %>
                    <div class="col s12 red lighten-5" style="margin-top: 1rem; padding: 0.5rem">
                        <span class="red-text text-accent-3">
                        <%=(String)request.getAttribute("error") %>
                        </span>
                    </div>
                    <% }%>
                    <div class="input-field col s12">
                        <input id="Username" type="text" class="validate" name="username">
                        <label for="Username">
                        <%=(String)request.getAttribute("username") %>
                        </label>
                    </div>
                    <div class="input-field col s12">
                        <input id="Password" type="password" class="validate" name="password">
                        <label for="Password">
                        <%=(String)request.getAttribute("password") %>
                        </label>
                    </div>
                </div>
                <div class="row" style="margin-left: 0; margin-right:0;">
                    <button class="btn waves-effect col" type="submit" name="action" style="margin:0 0.75rem;">
                    	<%=(String)request.getAttribute("submit") %>
                        <i class="mdi-content-send right"></i>
                    </button>
                    <a class="col s6" style="line-height: 36px;" id="signup" href="registerPage.jsp">
						<%=(String)request.getAttribute("signin") %>
					</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
</body>
</html>