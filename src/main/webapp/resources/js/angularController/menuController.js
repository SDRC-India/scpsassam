



function menuController($scope, $http, $timeout){
	
	$scope.confirmLogout = function()
	{
		$scope.warningMsg = "Are you sure you want to logout ?";
		$("#confirmSaveModal").modal("show");
	}

}