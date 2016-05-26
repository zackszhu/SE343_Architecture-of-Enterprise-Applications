<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="materialize/css/materialize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to My Bookstore - BookShelf Page</title>
</head>
<body class="grey lighten-5">
	<jsp:include page="sideNav.jsp"></jsp:include>
	<div class="container" style="margin-left: 300px">
	    <div class="row" id="books">
	    </div>
    </div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script>
	$(document).ready(function() {
		$.post("BookListServlet", {"Place": "MyBooks"}, function(data) {
			$.each(data.books, function(key, value) {
				$('#books').append(
					'<div class="col s12 m5">'+
		                '<div class="card grey lighten-4 z-depth-1">'+
		                    '<div class="card-content black-text">'+
		                        '<div class="card-title blue lighten-1 white-text" style="margin:-20px -20px 10px -20px; padding:10px 20px 5px 20px">'+
		                        value.Name+'</div>'+	                        
		                        '<p>'+
		                            'The book is written by <b>'+value.Author+'</b>.<br>'+
		                            'The publisher <b>'+value.Publisher+'</b> published it.<br>'+
		                            'The ISBN code of this book is <b>'+value.ISBN+'</b>.<br>'+
		                            'The price is <b>'+value.Price+'</b>.<br>'+
		                        '</p>'+
		                    '</div>'+
		                    '<div class="card-action right-align" style="padding:10px">'+
		                        '<a href="#">Download</a>'+
		                    '</div>'+
		                '</div>'+
		            '</div>');
			})
		})
	})
</script>
</html>