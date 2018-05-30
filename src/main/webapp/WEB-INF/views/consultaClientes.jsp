<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<h1><fmt:message key="consultas.clientes.heading"/></h1>
<form:form method="post" commandName="formCliente">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"></td>
      <td align="left" width="20%" colspan="5">CLIENTE</td>
    </tr>
    <tr>
      <td align="right" width="20%"></td>	  
      <td align="center" width="20%">Ubicaci&oacute;n</td>
      <td align="center" width="20%">Telefono</td>
      <td align="center" width="20%">Contacto</td>
      <td align="center" width="20%">Email</td>
      <td align="center" width="20%">RFC</td>
    </tr>
	<c:forEach items="${clientes}" var="c">
     <tr>
      <td align="center" width="20%"><input type="radio" name="idCliente" value="${c.idCliente}"></td>
      <td align="left" width="20%" colspan="5">${c.nombreCliente}</td>
     </tr>
     <tr> 
      <td align="right" width="20%"></td>
      <td align="right" width="20%">${c.calle}</td>
      <td align="right" width="20%">${c.telefono1}</td>
      <td align="right" width="20%">${c.contacto1}</td>
      <td align="right" width="20%">${c.email1}</td>
      <td align="right" width="20%">${c.rfc}</td>
    </tr>
    </c:forEach>
       <tr>
    <td colspan="2" align="center"><input type="submit" value="Consultar"></td>
    <td colspan="2" align="center"><input type="submit" value="Modificar"></td>
    <td colspan="2" align="center"><input type="submit" value="Eliminar"></td>
    </tr>
  </table>
  <br>
 
</form:form>
<a href="<c:url value="home"/>">Home</a>
</body>
</html>