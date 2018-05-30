<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <div class="form-group">
   	 <label for="idPlanta">PLANTA</label>
    	<select id="idPlanta" name="idPlanta" class="form-control" required>
    		<option value=""> <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${plantas}" var="plant">
      				<option value="${plant.idPlanta}"> <c:out value="${plant.descripcion}"/></option>
    			</c:forEach>
    	</select>
 </div>
