forceForGood.controller('UserInvite', ['$log', '$scope', 'userService',
	function($log, $scope, userService) {
		
		$scope.invite = function(user) {
			$log.log(user);
			userService.inviteuser(user).then( handleSuccess, handleError );
		};

		var handleSuccess = function( response ) {
			$scope.successInv = true;
			$scope.failureInv = false;
			$("#confirmation-success-inv").show().delay(4000).fadeOut(200);
		};

		var handleError = function( response ) {
			$scope.successInv = false;
			$scope.failureInv = true;
			$("#confirmation-failure-inv").show().delay(4000).fadeOut(200);
		};


	}]);