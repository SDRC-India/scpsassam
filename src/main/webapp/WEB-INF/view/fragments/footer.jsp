<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container-fluid footer footer-bottom">
	<div class="copyright-wrapper">
		<div class="row">
			<div class="col-md-12">
				<div class="col-md-3 col-sm-4 col-xs-12 copyright-txtt">
					<p class="footer-unicef">
						Supported by &nbsp<a href="http://www.unicef.org" target="_blank"><img
							src="resources/images/unicef-white.png"
							alt="UNICEF"></a>
					</p>

				</div>
				<div class="col-md-7 col-sm-4 col-xs-12 copyright-txtt"
					style="text-align: center;">
					<p>
						© 2017 State Child Protection Society.<a
							href="https://scpsassam.org/terms-of-use/"> Terms of Use</a> | <a
							href="https://scpsassam.org/disclaimer/">Disclaimer</a> | <a
							href="https://scpsassam.org/privacy-policy/">Privacy Policy</a> |
							<a href="https://scpsassam.org/sitemap/">Sitemap</a>
					</p>
				</div>

<!-- 				<div -->
<!-- 					class="col-md-3 col-sm-12 col-xs-12 copyright-txtt copyright-txttpadding" -->
<!-- 					style="text-align: center;"> -->
						<!--Visitor Count:-->
						<!-- Start of SimpleHitCounter Code -->
<!-- 					<div align="center" class="hitcounter" -->
<!-- 						style="font-size: 12px !important; color: #fff;margin-top: 2px;"> -->
<!-- 						<a href="http://www.freecounterstat.com" title="web hit counter"><img -->
<!-- 							src="http://counter3.01counter.com/private/freecounterstat.php?c=ec99fd8e2055981622b127cd29bbeb5f" -->
<!-- 							border="0" title="web hit counter" alt="web hit counter" -->
<!-- 							style="height: 25px;"></a><br>Visit Counter -->
<!-- 					</div> -->
					<!-- End of SimpleHitCounter Code -->
<!-- 				</div> -->

				<div class="col-md-2 col-sm-4 col-xs-12 copyright-txtt">
					<p class="footer-sdrc">
						Powered by<a href="http://www.sdrc.co.in" target="_blank"
							style="font-size: 14px; text-decoration: none; font-weight: 400; color: #fec02c;">&nbsp;SDRC</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>


<!-- Copyright Section End-->
<script>
$("#main-menu > li:not(.open)").click(function(){
	if(!$(this).hasClass("open")){
		$(this).siblings().removeClass('open');
		$(this).siblings().find('a[area-expanded]').attr("area-expanded", "false");
		$(this).siblings().find('a.highlighted[area-expanded]').removeClass("highlighted");
		$(this).siblings().find('ul.dropdown-menu').css({"display": "none"});
		$(this).siblings().find('ul.dropdown-menu').attr({"area-expanded": "false", "area-hidden": "true"});
	}
})

</script>
<!-- <script type="text/javascript"> -->
<!-- //  var $body = $('body:not(.menu-toggle)'); -->
<!-- // $body.on('click', function(event) { -->
<!-- //   if ($('#cssmenu').hasClass('in')) { -->
<!-- //     $('#cssmenu').removeClass('in'); -->
<!-- //     $('#cssmenu').attr('aria-expanded','false'); -->
<!-- //   } -->
<!-- // }) -->
<!-- </script> -->
<!--  


<!-- jquery-ui.js file is really big so we only load what we need instead of loading everything -->
<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>

<script src="resources/js/topojson.v1.min.js"></script>

<script src="resources/js/html2canvas.js"></script>

<!--  Site specific scripts down bellow -->

<script src="resources/js/sdrc.export.js"></script>

<script src="resources/js/bootstrap-datepicker.js"></script>

<script src="resources/js/core.js"></script>
<script src="resources/js/ganalytics.js"></script>

<script src="resources/js/blueimp.gallery.min.js"></script>
<script src="resources/js/bootstrapimage.gallery.js"></script>

 <script type="text/javascript" src="resources/js/jquery.smartmenus.js"></script>

    <!-- SmartMenus jQuery Bootstrap Addon -->
    <script type="text/javascript" src="resources/js/jquery.smartmenus.bootstrap.js"></script>
<!--     <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script> -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function() {
    // this will get the full URL at the address bar
    var url = window.location.href;

    // passes on every "a" tag
    $("#cssmenu a").each(function() {
        // checks if its the same on the address bar
        if (url == (this.href)) {
            $(this).closest("li").addClass("activePage");
            //for making parent of submenu active
           $(this).closest("li").parent().parent().addClass("activePage");
        }
    });
});        
</script>
