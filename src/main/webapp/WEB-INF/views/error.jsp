<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/header.jsp" />
	</div>
	<div>
		<h2>RSSFeed Reader</h2>
	</div>

	<div>
		<h3>Error Occurred</h3>
		<p>Please refer below for more details</p>
	</div>
	<P>${error}.</P>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>