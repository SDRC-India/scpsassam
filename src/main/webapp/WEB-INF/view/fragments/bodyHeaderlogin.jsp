<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <% --%>
<!-- 	UserDetailsModel user = null; -->
<!-- %> -->
<%-- <% --%>
<!-- 	user = (UserDetailsModel) request.getSession().getAttribute( -->
<!-- 			Constants.USER_PRINCIPAL); -->
<!-- %> -->

<!-- Top bar -->

<header class="navbar-static-top bar" id="head1">

	<!-- logo row starts -->
	<div class="container-fluid header_bg headerbackground">
		<div class="container scpsLogoHeadInfo">
			<div class="col-md-2 col-sm-2 col-xs-12 textAligncenter">
				<a href="https://scpsassam.org/" target="_blank"><img class="scpsassamLogo"
					src="resources/images/SCPS_LOGO.png"
					alt="SCPS Assam"></a>
			</div>
			<div class="col-md-8 col-sm-8 col-xs-12 siteHeading">
				<h3 class="fontcolorDark">State Child Protection Society</h3>
				<h4 class="fontcolorDark">Social Welfare Department</h4>
				<h4 class="fontcolorDark">Government of Assam</h4>
			</div>

			<div class="col-md-2 col-sm-2 col-xs-12 logoAssam">
				<div class="headerinfo">
					<a href="http://assam.gov.in/" target="_blank"><img
						class="assamLogo"
						src="resources/images/logo_assam.png"
						alt="Assam Government"></a>
					<div class="clear"></div>
				</div>
			</div>
			<!-- logo row ends -->
</div>
</div>

</header>
<!-- END Top Bar	 -->

<div class="navbar navbar-default navbar-static-top bar menu" id="head2">
	<div class="container-fluid menutoggle">
		<jsp:include page="newmenuforlogin.jsp" />
	</div>
</div>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
 <script src="resources/js/angular.min.js"></script>
	<script src="resources/js/angular-animate.min.js"></script>
<spring:url
	value="/webjars/angular-loading-bar/0.4.3/loading-bar.min.js"
	var="loadingbarmin" />
<script src="${loadingbarmin}" type="text/javascript"></script>

<script src="resources/js/d3.min.js"></script>

<script type="text/javascript">
</script>

<script type="text/javascript">

		function login() {
			$('#loginModal').modal('show');
		}
		function logout() {
			$('#logoutModal').modal('show');
		}
		
		$(window).scroll(function(){
			if($(window).scrollTop()>100)
			$('#head2').addClass('nav-fixed');
			
			else
				$('#head2').removeClass('nav-fixed');
		})
		
		 
</script>
<!-- 	<script src="resources/js/angcontrollers/factSheetController.js"></script> -->

