<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">

	<title>The Good Life - Reset Password</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/GLO_logo_grey.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
<div class=container-fluid">
	<form class="wrap" action="<c:url value='resetPwdStepTwo' />" method='POST' >
  		
  		<div class="logo">
  			The Good Life
  		</div>
		<c:if test="${not empty error}">
			<div class="errorblock">
			Sending reset code was not successful.<br />Cause:
				${exceptionMessage}
			</div>
		</c:if>
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_blue.png">
		</div>
		
		<input type="email" name="email" id="solo" placeholder="Email" required>
		<button type="submit">Submit</button>
	</form>
</div>
</body>
</html>