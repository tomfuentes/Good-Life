<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<BR>

<a href="login">login</a>
<BR><BR><BR><BR><BR>

<table>
    <tr>
        <td></td>
        <td align="center"><img src="<spring:url value="${request.contextPath}/resources/images/TheGoodLifeLogo.jpg" htmlEscape="true"/>"
                               alt="blah blah"   height="294" width="789"/></td>
    </tr>
</table>

</body>
<jsp:include page="../fragments/footer.jsp"/>
</html>