<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ingresar</title>
</head>
<body onload='document.loginForm.j_username.focus();'>

		<div class="main-container container-fluid ">
			<div class="main-content center">
				<div class="row-fluid">
					<div class="span12">
						<div class="login-container center login">
							<div class="row-fluid">
								<div class="center">
									<h1>
										<i class="icon-book blue"></i>
										<span class="grey">Gesti&oacute;n de Inventarios </span>
										<span class="grey">Arcosa</span>
									</h1>
									<h4 class="blue"><img class="nav-user-photo" src="resources/img/logo-arcosa.png" alt="Arcosa" /></h4>
								</div>
							</div>

							<div class="space-6"></div>

							<div class="row-fluid">
								<div class="position-relative">
									<div id="login-box" class="login-box visible widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header blue lighter bigger">
													<i class="icon-cog blue"></i>
													Por favor ingresa tu informaci&oacute;n.
												</h4>

												<div class="space-6"></div>

												<form class="form-signin" role="form" id="UserLoginForm" name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input type='text'  class="span12" placeholder="Usuario" name='j_username' value='' required autofocus>
																<i class="icon-user"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input type='password' class="span12" placeholder="Contrase&ntilde;a" name='j_password' class="form-control" required>
																<i class="icon-lock"></i>
															</span>
														</label>

														<div class="space"></div>

														<div class="clearfix">
															<label class="inline">
															<div  class="text-error">
        	<%
         String errorString = (String)request.getAttribute("error");
         if(errorString != null && errorString.trim().equals("true")){
            out.println("Usuario o contrase&ntilde;a incorrecta. Por favor intentarlo nuevamente.");
         }
         String errorSession = (String)request.getParameter("session");
                if(errorSession != null && errorSession.trim().equals("expired")){
                    out.println("Su sesi&oacute;n ha expirado ingrese nuevamente.");
                }
    	%>
    	</div>
															</label>
															<button class="width-35 pull-right btn btn-small btn-primary">
																<i class="icon-key"></i>
																Ingresar
															</button>
														</div>

														<div class="space-4"></div>
													</fieldset>
												</form>

											</div><!--/widget-main-->

										</div><!--/widget-body-->
									</div><!--/login-box-->
								</div><!--/position-relative-->
							</div>
						</div>
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div>
		</div>		
				
</body>
</html>