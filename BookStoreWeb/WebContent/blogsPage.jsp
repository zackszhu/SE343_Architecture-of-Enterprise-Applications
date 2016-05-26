<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="materialize/css/materialize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to My Bookstore - My Posts Page</title>
</head>
<body class="grey lighten-5">
	<jsp:include page="sideNav.jsp"></jsp:include>
	<div class="container" style="margin-left: 300px">
	    <div class="row" id="blogs">
	    </div>
	    <div class="row">
	    	<div class="col s12 m6">
	    		<div class="card grey lighten-4 z-depth-1">
		    		<form action="BlogAddServlet" method="post" id="addForm">
			    		<div class="card-content black-text row" style="margin-bottom: 0">
			    			<div class="card-title blue lighten-1 white-text" style="margin:-20px -20px 10px -20px; padding:10px 20px 5px 20px">
			    				<input type="text" id="title" class="validate" name="title">
			    			</div>
			    			<p>
			    				<input type="text" id="content" class="validate" name="content">
			    			</p>
			    		</div>
			    		<div class="card-action right-align" style="padding:10px">
	                        <a href="javascript: return false;" onclick="$('#addForm').submit()">Add</a>
	                    </div>
	                </form>
	    		</div>
	    		
	    	</div>
	    </div>
    </div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script>
	$(document).ready(function() {
		$.get("BlogListServlet", function(data) {
			$.each(data.blogs, function(key, value) {
				$('#blogs').append(
					'<div class="col s12 m6">'+
		                '<div class="card grey lighten-4 z-depth-1">'+
		                    '<div class="card-content black-text">'+
		                        '<div class="card-title blue lighten-1 white-text" style="margin:-20px -20px 10px -20px; padding:10px 20px 5px 20px">'+
		                        value.Title+'</div>'+	                        
		                        '<p>'+
		                            value.Content+'<br>'+value.Time+
		                        '</p>'+
		                    '</div>'+
		                '</div>'+
		            '</div>');
			})
		})
	})
</script>
</html>