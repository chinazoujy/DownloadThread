<%@page import="com.dreamwin.xunlei.proccess.*"%>
<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="GB2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<script type="text/javascript">
setInterval(function () {
window.location.reload();
}, 1000);
</script>
<body>
	<table width="100%" border="1px" cellpadding="0" cellspacing="0">	
		<c:forEach items="${info}" var="item2"  varStatus="tempStatus">
			<tr>
				<c:forEach var="i" begin="0" end="3" step="1">
					<td align="center">${item2[i]}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>