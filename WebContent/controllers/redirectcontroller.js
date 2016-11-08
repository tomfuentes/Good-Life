forceForGood.controller('RedirectController', ['$scope', '$log', '$location', '$http',
	function($scope, $log, $location, $http) {

		$http.post('getRole', {}).
			success(function(data, status, headers, config) {
				$log.log("Success");
				var userInfo = data;

				if(userInfo.roleTypeCode == "S") {
					$location.path("/curriculum/"+userInfo.userId);
				} else if(userInfo.roleTypeCode == "F" || userInfo.roleTypeCode == "M") {
					$location.path("/instructorHome");
				} else if(userInfo.roleTypeCode == "A") {
					$location.path("/adminConsole");
				}
			}).
			error(function(data, status, headers, config) {
				$log.log("error");
				$log.log(data);
			});


	}]);