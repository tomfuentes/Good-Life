forceForGood.controller('StudentHome', ['$scope', '$log', 'student', '$routeParams', '$location',
                                        
      function($scope, $log, student, $routeParams, $location){
			$scope.userId = $routeParams.userId;
			$log.log("I got here!");
			
			//Retrieve Progress
			
			student.getProgress($scope.userId).then(function(result){
				$scope.progressValue = result;
				$log.log($scope.progressValue);
			});
	
}]);