
<!-- Top bar -->

<header class="navbar-static-top bar" id="head1">

	<div class="container-fluid headertop" >
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="row">
			<div class="col-md-12" style="padding: 2px;">
				<div class="headertop">
					<!-- <div class="container"> -->
					<div class="col-md-3 col-sm-3 col-xs-4 mobileNav">
						<ul class="header-links">
<!-- 							<li class="hindicls"><a href="http://scpsbihar.org">English</a></li> -->
<!-- 							<li><a href="#">অসমীয়া</a></li> -->
						</ul>
					</div>
					<div class="col-md-9 col-sm-9 col-xs-8 langOption">
						<div class="social-icons">
							<ul style="margin: 0; padding: 0;">
<!-- 								<li ><a href="javascript:void(0);" -->
<!-- 									onclick="changemysize(12);"><small>-A</small></a></li> -->
<!-- 								<li ><a href="javascript:void(0);" -->
<!-- 									onclick="changemysize(14);">A</a></li> -->
<!-- 								<li ><a href="javascript:void(0);" -->
<!-- 									onclick="changemysize(16);"><big>+A</big></a></li> -->
								<li><a href="https://scpsassam.org/help/">Help</a></li>
								<li><a href="https://scpsassam.org/faq/">FAQ</a></li>
								<li class="mblsitemap"><a href="https://scpsassam.org/contact-us-2/">Contact Us</a></li>
								<li class="mblres"><a href="#"><i
										class="fa fa-lg fa-facebook"></i></a></li>
								<li class="twt"><a href="#"><i
										class="fa fa-lg fa-twitter"></i></a></li>
							</ul>
						</div>
					</div>

					<div class="col-md-3 col-sm-3 col-xs-3 mobileSearch">
						<div class="header-search">
							<form role="search" method="get" class="search-form"
								action="https://scpsassam.org/">
								<label> <input type="search" class="search-field"
									placeholder="Search" value="" name="s">
								</label> <input type="submit" class="search-submit" value="GO">
							</form>
						</div>
					</div>

				</div>
			</div>
</div>
		
	</div>

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
		<jsp:include page="newMenu.jsp" />
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script src="resources/js/angular.min.js"></script>
	<script src="resources/js/angular-animate.min.js"></script>
<%-- <spring:url --%>
<%-- 	value="/webjars/angular-loading-bar/0.4.3/loading-bar.min.js" --%>
<%-- 	var="loadingbarmin" /> --%>
<%-- <script src="${loadingbarmin}" type="text/javascript"></script> --%>

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

