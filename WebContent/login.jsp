<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">

	<title>The Good Life</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/GLO_logo_blue.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class=container-fluid">
	<form class="wrap" action="<c:url value='j_spring_security_check' />" method='POST' >
  		
  		<div class="logo">
  			The Good Life
  		</div>
		
		<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Cause:
-				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_blue.png">
		</div>
		
		<input type="text" name="j_username" id="top" placeholder="Username" required>
		
<!-- 		<div class="bar"> -->
<!-- 			<i></i> -->
<!-- 		</div> -->
		
		<input type="password" name="j_password" id="bot" placeholder="Password" required>
		
		<a href="resetPwdStepOne" class="forgot_link">Forgot?</a>
		<button type="submit">Sign in</button>
    	<button onclick="location.href='signup.jsp'">Register</button>
    	
	</form>
</div>
</body>

</html>