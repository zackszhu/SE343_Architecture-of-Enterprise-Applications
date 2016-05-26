<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="materialize/css/materialize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to My Bookstore - Stat Page</title>
</head>
<body class="grey lighten-5">
	<jsp:include page="sideNav.jsp"></jsp:include>
	<div class="container" style="margin-left: 300px">
	    <div class="row">
	        <div class="grey lighten-5 z-depth-1 col m12" style="padding:0">
	            <p class="blue white-text" style="font-size: 2em;margin:0; padding: 10px;"> Top Books</p>
	            <table class="centered hoverable" style="border-collapse: separate;">
	                <thead>
	                <tr>
	                    <th data-field="name">Book Name</th>
	                    <th data-field="count">Author Name</th>
	                </tr>
	                </thead>
	                <tbody id="books">
	                </tbody>
	            </table>
	        </div>
	    </div>
	    <div class="row">
	        <div class="grey lighten-5 z-depth-1 col m12" style="padding:0">
	            <p class="blue white-text" style="font-size: 2em;margin:0; padding: 10px;">Recent Orders</p>
	            <table class="centered hoverable" style="border-collapse: separate;">
	                <thead>
	                <tr>
	                    <th >User ID</th>
	                    <th >Book ID</th>
	                    <th >Create Time</th>
	                </tr>
	                </thead>
	                <tbody id="orders">
	                </tbody>
	            </table>
	        </div>
	    </div>   
    </div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script>
	$(document).ready(function() {
		$.get("StatServlet", function(data) {
			$.each(data.books, function(key, value) {
				$('#books').append(
					'<tr>'+
                        '<td>'+value.Name+
                        '</td>'+
                        '<td>'+
                                value.Author+
                        '</td>'+
                    '</tr>');
			});
			$.each(data.orders, function(key, value) {
				$('#orders').append(
					'<tr>'+
                        '<td>'+value.UserID+
                        '</td>'+
                        '<td>'+
                                value.BookID+
                        '</td>'+
                        '<td>'+
                        		value.CreateTime+
                        '</td>'+
                    '</tr>');
			});
		});
		
	})
</script>
</html>