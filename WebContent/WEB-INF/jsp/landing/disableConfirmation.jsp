<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<BR>
User - Disabled
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>