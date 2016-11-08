<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
	function checkPass() {
		//Store the password field objects into variables ...
		var pass1 = document.getElementsByClassName("pass1");
		var pass2 = document.getElementsByClassName("pass2");
		//Set the colors we will be using ...
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		//Compare the values in the password field 
		//and the confirmation field
		if (pass1[0].value == pass2[0].value) {
			//The passwords match. 
			//Set the color to the good color and inform
			//the user that they have entered the correct password 
			pass2[0].style.backgroundColor = goodColor;
		} else {
			//The passwords do not match.
			//Set the color to the bad color and
			//notify the user.
			pass2[0].style.backgroundColor = badColor;
		}
	}
</script>
<head>

  <meta charset="UTF-8">

	<title>The Good Life</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/GLO_logo_grey.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
	<div class=container-fluid">
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your signup attempt was not successful, try again.<br /> Cause:
			${exceptionMessage}
		</div>
	</c:if>

	<form class="wrap" action="<c:url value='finishUserSignUp' />" method='POST'>
  		<div class="logo">
  			The Good Life
  		</div>
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_blue.png">
		</div>
		<input type="Email" name="email" id="top" placeholder="Email" required>
		<input type="text" name="firstname" id="mid" placeholder="First Name" required>
		<input type="text" name="lastname" id="mid" placeholder="Last Name" required>
		<input type="text" name="username" id="mid" placeholder="User Name" required>

		<input type="password" name="pass1" id="mid" class="pass1" placeholder="Password" required onkeyup="checkPass(); return false;">

        <input type="password" name="pass2" id="mid" class="pass2" placeholder="Confirm Password" required onkeyup="checkPass(); return false;">

        <input type="text" name="token" id="bot" placeholder="Invite Code" required>
		<a href="requestInvitationCode" class="forgot_link">misplaced?</a>
		<button type="submit">Sign Up</button>
	</form>
	</div>
</body>
</html>