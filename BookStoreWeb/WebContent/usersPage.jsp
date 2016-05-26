<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="materialize/css/materialize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to My Bookstore - User Page</title>
</head>
<body class="grey lighten-5">
	<jsp:include page="sideNav.jsp"></jsp:include>
	<div class="container" style="margin-left: 300px">
	    <div class="row">
	        <table class="grey lighten-4 z-depth-1" style="border-spacing:2rem; border-collapse: separate;">
	            <thead>
	            <tr>
	                <th data-field="id">ID</th>
	                <th data-field="username">Name</th>
	                <th data-field="email">E-mail</th>
	                <th data-field="time">Create Time</th>
	                <th data-field="status">Status</th>
	                <th data-field="action">Action</th>
	            </tr>
	            </thead>
	            <tbody id="tableBody">
	            </tbody>
	        </table>
	    </div>
	</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script>
$(document).ready(function() {
	$.get("UserListServlet", function(data) {
		$.each(data.users, function(key, value) {
			$('#tableBody').append(
					'<tr>'+
	                    '<td>'+value.ID+'</td>'+
	                    '<td>'+value.Name+'</td>'+
	                    '<td>'+value.Email+'</td>'+
	                    '<td>'+value.CreateTime+'</td>'+
	                    '<td>'+value.Role+'</td>'+
	                    '<td>'+                     
	                        '<a href="BanUserServlet?userID='+value.ID+'">Delete</a>'+
	                    '</td>'+
                	'</tr>'
			);
		})
	})
})
</script>
</html>