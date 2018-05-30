<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <div class="form-group">
   	 <label>TIPO PRODUCTO</label>
    	<select id="tipoProducto" name="tipoProducto" required class="form-control">
    		<option value="" ><fmt:message key="entradas.select"/></option>
    		<c:forEach items="${Convenios}" var="cnv">
    		   <option value="${cnv.claveProducto}" >${cnv.tipo}</option>
    		</c:forEach>
    	</select>
 </div>
