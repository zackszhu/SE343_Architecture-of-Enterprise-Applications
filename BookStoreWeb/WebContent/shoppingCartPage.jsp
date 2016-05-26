<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to My Bookstore - Shopping Cart</title>
   <link rel="stylesheet" type="text/css"
         href="materialize/css/materialize.min.css">
   <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
</head>
<body class="grey lighten-5">
<jsp:include page="sideNav.jsp"></jsp:include>
<div class="container" style="margin-left: 300px">
    <div class="row">
        <form method="post" action="BookActionServlet">
            <input type="hidden" name="action" value="buy"/>
            <ul class="collection with-header grey lighten-4 z-depth-1 input-field">
                <li class="collection-header blue lighten-1">
                    <h4 class="white-text">Shopping Cart</h4>
                </li>
                <button class="btn-floating btn-large waves-effect waves-light red right"
                        style="margin-top: -27px; margin-right:100px;" type="submit" name="buy">Buy
                </button>
                <span id="list"></span>
            </ul>
        </form>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script>
$(document).ready(function() {
	$.get("ShoppingCartServlet", function(data) {
		$.each(data.books, function(key, value) {
			$('#list').append(
				'<li class="collection-item grey lighten-4" style="position: relative; padding-bottom: 1.5rem">'+
                    '<input id="'+key+'" type="checkbox" name="bookID" value="'+value.ID+'"/>'+
                    '<label for="'+key+'" style="font-size: 1.5em; line-height:24px"'+
                           'class="grey-text text-darken-3">'+value.Name+
                        '<span style="line-height:24px; margin-left: 1rem;"'+
                              'class="grey-text"> ¥'+value.Price+'</span>'+
                    '</label>'+
                '</li>'			
			);
		})
	})
})
</script>
</body>
</html>