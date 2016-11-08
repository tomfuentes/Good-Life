<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="../fragments/headTag.jsp" />
<script>
	function checkPass() {
		//Store the password field objects into variables ...
		var pass1 = document.getElementById('pass1');
		var pass2 = document.getElementById('pass2');
		//Store the Confimation Message Object ...
		var message = document.getElementById('confirmMessage');
		//Set the colors we will be using ...
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		//Compare the values in the password field 
		//and the confirmation field
		if (pass1.value == pass2.value) {
			//The passwords match. 
			//Set the color to the good color and inform
			//the user that they have entered the correct password 
			pass2.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Passwords Match!"
		} else {
			//The passwords do not match.
			//Set the color to the bad color and
			//notify the user.
			pass2.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Passwords Do Not Match!"
		}
	}
</script>
<body onload='document.f.j_username.focus();'>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div style="border: 1px solid black; width: 600px; padding-top: 10px;">
		<br />
		<h3>Signup for an account with the email and token provided.</h3>
		<br /> <span style="color: red">${message}</span> <br />


		<c:if test="${not empty error}">
			<div class="errorblock">
				Your signup attempt was not successful, try again.<br /> Cause:
				${exceptionMessage}
			</div>
		</c:if>

		<form name='userData' action="<c:url value='finishUserSignUp' />"
			method='POST'>

			<table>
				<tr>
					<td>Email Address:</td>
					<td><input type='email' name='email' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="pass1" id="pass1"></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><input type="password" name="pass2" id="pass2" onkeyup="checkPass(); return false;"> 
					    <span id="confirmMessage" class="confirmMessage"></span>
					</td>
				</tr>
				<tr>
					<td>Email Token:</td>
					<td><input type='text' name='token' id='token'/></td>
					<BR />
				</tr>
				<tr height='60' valign="middle">
					<td><input name="submit" type="submit" value="submit" /></td>
					<td align="right"><input name="reset" type="reset" /></td>
				</tr>
			</table>
		</form>
	</div>
	<center>
		<a href="requestInvitationCode">Request a misplaced invite code</a>
	</center>
</body>
<jsp:include page="../fragments/footer.jsp" />
</html>