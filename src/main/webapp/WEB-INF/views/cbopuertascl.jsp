<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

  <div class="form-group">
   	 <label for="idPuerta">PUERTA</label>
    	<select id="idPuerta" name="idPuerta" value='' class="form-control" required>
    		<option value="" > <fmt:message key="entradas.select"/> </option>
        		<c:forEach items="${puertas}" var="p">
      				<option value="${p.idCatalogo}"> <c:out value="${p.descripcion}"/></option>
    			</c:forEach>
    	</select>
  	</div>
  </div>