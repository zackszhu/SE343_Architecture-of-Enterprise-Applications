<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<div id="danmu"></div>
<ul id="slide-out" class="side-nav fixed z-depth-2" style="width: 240px;">
    <li class="logo" style="text-align: center; margin-top: 32px; margin-bottom: 16px; border-bottom: 1px solid #ddd">
        <img src="img/bookstore.jpg"
             style="width:100%; height:auto">
        <p style="font-size: 1.5em;padding-left: 15px" class="left-align grey-text text-darken-3">${sessionScope.currentUser}</p>
    </li>
    <li style="border-bottom: 1px solid #ddd">
    	<input type="text" id="danmutext">
    	<a href="#" onclick="sendMessage()">Send Danmu</a>
    </li>
    <li><a href="welcome?position=store">Store Page</a></li>
    <% if (request.isUserInRole("ADMIN")) {%>
    <li><a href="welcome?position=users">Users</a></li>
    <li><a href="welcome?position=stat">Statistics</a></li>
    <%} else {%>
    <li><a href="welcome?position=myBooks">My Books</a></li>
    <li>
        <a href="shoppingCartPage.jsp">Shopping Cart</a>
    </li>
    <li>
    	<a href="blogsPage.jsp"> My Posts </a>
    </li>
    <%}%>
    <li style="margin-top: 16px; margin-bottom: 32px; border-top: 1px solid #ddd">
        <a href="logout">Log Out</a>
    </li>
</ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i class="mdi-navigation-menu"></i></a>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script src="js/jquery.danmu.js"></script>
<script src="js/sideNav.js"></script>
</body>
</html>