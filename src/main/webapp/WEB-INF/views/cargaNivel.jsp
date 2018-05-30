<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<select id="nivel" name="nivel" value='' class="form-control" required>
	<option value="" > <fmt:message key="entradas.select"/> </option>
	<c:forEach var="i" begin="1" end="${niveles[0]}">
		<option value="${i}"> <c:out value="${i}"/></option>
	</c:forEach>
</select>
