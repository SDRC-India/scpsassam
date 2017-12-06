<%@ page import="org.sdrc.scpsassam.util.Constants"%>
<%@ page import="org.sdrc.scpsassam.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!--logo part end-->
<!-- spinner -->
<%
Integer role = 0;
UserModel user = null; Boolean hasLR = false;
List<String> features = new ArrayList<String>();
List<String> permissions = new ArrayList<String>();
String agencyUrl = null;

if(request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL) != null){
		user = (UserModel) request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL);
		role=user.getRoleId();
		agencyUrl = "http://www.socialdefence.tn.gov.in/";
}
%>
<script>
	var hasLR = <%=hasLR%>;
	var agencyUrl = "<%=agencyUrl%>";
</script>

      <!-- Navbar fixed top -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle menu-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"></a>
          </div>
          <div class="navbar-collapse collapse" id="cssmenu">
          
            <!-- Left nav -->
            <ul class="nav navbar-nav" id="main-menu">
             <%if (user != null) {%><li><a>Welcome<b> <%=user.getUsername()%></b></a></li><%}%>
              <li class="home-link"><a href = "https://scpsassam.org/">Home</a></li>
<!--                           <li><a  href="submissionManagement">Submission Management</a></li> -->
<!--                           <li><a  href="indicatorManagement">Indicator Management</a></li> -->
<!--                           <li><a  href="#">Logout</a></li> -->

				
				<%if (user == null) {%><li class="loginbtn"><a href="login">Login</a></li><%}%>
				<%if (user != null && user.getRoleId() != 3) {%><li><a href="dataEntry">Data Entry</a></li><%}%>
				<%if (user != null && user.getRoleId() != 3) {%><li><a href="submissionManagement">Submission Management</a></li><%}%>
				<%if (user != null && user.getRoleId() == 3) {%><li><a href="indicatorManagement">Indicator Management</a></li><%}%>
				<%if((UserModel) request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL)!=null 
						&& ((UserModel) request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL)).getRoleId()==3 ){ 
					      %>
					 <li class="nav"><a class="scroll" href="misReport" id="misReport">Report</a></li>
						
						<% } %>
				<%if (user != null) {%><li><a href="webLogout">Logout</a></li><%}%>
				
            </ul>
       
          </div><!--/.nav-collapse -->
        </div><!--/.container -->
      </div>

<div class="loaderMask" id="loader-mask" style="display: none;">
	<div class="windows8">
		<div class="wBall" id="wBall_1">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_2">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_3">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_4">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_5">
			<div class="wInnerBall"></div>
		</div>
	</div>
</div>
<div id="confirmSaveModal" class="confrirmation-modal modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead"><img alt="" src="resources/images/icons/Messages_warning_caution_icon.svg" style="width: 25px;margin-top: -5px;">&nbsp; INFO</div>
					<div class="warnbody">Are you sure you want to logout ?</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">Yes</button>
					<button type="button" class="btn errorOk" data-dismiss="modal">No</button>
				</div>
			</div>
		</div>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<!--     <script type="text/javascript" src="resources/js/jquery.smartmenus.js"></script> -->

    <!-- SmartMenus jQuery Bootstrap Addon -->
<!--     <script type="text/javascript" src="resources/js/jquery.smartmenus.bootstrap.js"></script> -->
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!-- SmartMenus jQuery plugin -->
<script type="text/javascript">
// $(document).ready(function(){
//     $(".home-link").click(function(){
//     	$("#confirmSaveModal").modal("show");
//     });
// });
  </script>