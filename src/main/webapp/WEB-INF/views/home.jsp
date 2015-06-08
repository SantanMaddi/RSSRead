<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>RSS Read</title>
	<meta charset="utf-8">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.1.min.js"> </script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"> </script>
	
	<script type="text/javascript">
	function postnewfeed(){
		$.ajax({
			url : "newfeed",
			type : "POST",
			data : {"xmlpath" : $("#xmlpath").val()},
			success : function(data){
				//$("#xmlpath").reset();
				$("#rssfeeds").DataTable().ajax.reload();
			}
		});
	}
	
	$(document).ready(function() {
		initTable();
	} );
	
	function initTable(){
		$('#rssfeeds').dataTable( {
			"ordering" : true,
			"pagingType" : "simple",
			"lengthChange" : false,
			"searching" : false,
			"draw" : 1,
			"start" : 0,
			"length" : 10,
			"processing": true,
			"serverSide": true,
			"ajax": {
				"url": "paginationdatasource",
				"type": "POST"
			},
			"columns": [
			            { "data": "subject", "orderable" : true },
			            { "data": "description", "orderable" : false },
			            { "data": "link", "orderable" : false },
			            { "data": "pubDate", "orderable" : false }
			            ]
		} );
	}
	</script>
	
</head>

<body>
	<div>
		<jsp:include page="/WEB-INF/views/header.jsp" />
	</div>
	<div>
		<h2>RSSFeed Reader</h2>
	</div>
	<P></P>

	<div align="center">
	<form>
		<input type="text" id="xmlpath" name="xmlpath" required="required" size="50" 
		placeholder="Input RSS/XML link"/> 
	</form>
	<input type="submit" value="Read" onclick="postnewfeed()"/>
	</div>
	<br>
	<div class="col-sm-12">
		<table id="rssfeeds" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="sorting_asc">Subject</th>
					<th>Description</th>
					<th>Link</th>
					<th>Published On</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
