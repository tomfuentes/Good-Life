<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>	
	<h3>Username : ${username}</h3>	
	
	

<sec:authorize access="isAuthenticated()">
   YES, you are logged in!<br/>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_USER">
   You are just a user so you can see this link<br/>
   <ul>
   <li/>You Can request to be upgraded to facilitator
   <li/>You can upload essays
   <li/>view ........
   </ul>
   <br/>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_FACILITATOR">
   You are Facilitator<br/>
   <ul>
   <li/>You Can Upload Videos
   <li/>You can grade Essays
   <li/>......
   </ul><br/>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
   You are a super admin<br/>
   <ul>
   <li/>You Can upgrade or downgrade user from the role of facilitator
   <li/><a href="su/disable">Disable a User (Can be enabled later)</a>
   <li/><a href="su/enable">Enable a disabled user</a>
   <li/><a href="su/delete">Deleted a User (Permanently deleted)</a>
   <li/><a href="su/SendInvite">You can Invite Someone</a>
   <li/>......
   </ul><br/>
</sec:authorize>
	
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>