<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body onload='document.f.j_username.focus();'>
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div style="border: 1px solid black; width: 300px; padding-top: 10px;">
			<br /> <h3>Invite someone</h3> <br /> <span
				style="color: red">${message}</span> <br />
	

	<c:if test="${not empty error}">
		<div class="errorblock">
			Sending reset code was not successful.<br />Cause:
				${exceptionMessage}
		</div>
	</c:if>

	<form name='f' action="<c:url value='resetPwdStepTwo' />"
		method='POST'>

		<table>
			<tr>
				<td>User Id:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr height='60' valign="middle">
				<td><input name="submit" type="submit" value="submit" /></td>
				<td align="right"><input name="reset" type="reset" /></td>
			</tr>
		</table>

	</form>
	</div>
</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>