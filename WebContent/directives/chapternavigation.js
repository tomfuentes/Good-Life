/**
Directive is for chapter navigation
*/

forceForGood.directive('chapterNavigation', [ '$log', 'student', '$compile',
	function($log, student, $compile) {

	return {
		restrict: 'E',
		scope: {
			userId: '=userId',
			returnValue: '=returnValue',
		},
		templateUrl:'partials/chapter-navigation.html',
		controller: function($scope) {

			//Initalized Variables
			$scope.chapterInfo = [];
			$scope.chapterexpanded = false;


			//Update chapters for the user
			student.getAllowedChapters($scope.userId).$promise.then(function(result) {
				var chapterList = angular.copy(result);
				angular.forEach(chapterList, function(chapterObj) {
					student.getSubChapter(chapterObj.chapId).$promise.then(function(subChapList) {
						chapterObj.subchapters = [];
						chapterObj.subchapters = chapterObj.subchapters.concat(subChapList);
					});
				});
				$scope.chapterInfo = $scope.chapterInfo.concat(chapterList);
			});

			//Loads the subchapter content for that chapter
			$scope.loadSubChapter =  function(chapter) {
				$log.log("Got to load subchapter");
				chapter.subchapters = student.getSubChapter(chapter.chapId);
			};

			//Pass the clicked value to the form
			$scope.passSubChapter = function(subChapter) {
				$scope.chapterexpanded = false;
				$scope.returnValue(subChapter);
			};

			//expand view
			$scope.expand = function(chapter) {
				chapter.show = !chapter.show;
				$scope.chapterexpanded = true;
			};


		},
		link: function($scope, elm, attr) {
			// $scope.$watch('chapterInfo', function(newValue, oldValue) {
			// 	$log.log("Value changed in navigation");
			// 	$compile(elm.contents())($scope);
			// });
		}
	};

}]);