<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>GIA</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/views/template/estilos.jsp" %>
</head>
<body id="page1">
		<div class="extra">
			<div class="main">
<!--==============================header=================================-->
				<header>
					<tiles:insertAttribute name="header" />
				</header>
<!--==============================content================================-->
				<section id="content">
					<!-- div class="ic"></div-->
						<!-- div class="container_12"-->
							<tiles:insertAttribute name="body" />
						<!-- /div-->
					<!-- div class="block"><tiles:insertAttribute name="body" /></div-->
				</section>
			</div>
		</div>
<!--==============================footer=================================-->
		<footer>
			<div class="main">
				<div class="inner">
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</footer>
	</body>
</html>