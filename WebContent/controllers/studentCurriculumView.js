forceForGood.controller('StudentCurriculumView', ['$scope', '$log', 'student', '$routeParams', '$location',
	function($scope, $log, student, $routeParams, $location) {
		//Preset Values
		$scope.userId = $routeParams.userId;	
		$scope.studentview = true;


		//Update form content
		$scope.subChapterClicked = function(subChapter) {
			student.getSubChapterForm(subChapter.subChapId).$promise.then(function(subChapterFormList) {
				subChapterFormTemp = subChapterFormList[0];
				subChapterTypeTemp  = subChapterFormList[1];
				if ( subChapterTypeTemp == 'm' ) {
					//Read in each multiple choice question
					listMultipleChoiceQuest = [];
					angular.forEach(subChapterFormTemp[0].root.children, function(multiQuestObj) {
						listMultipleChoiceQuest.push(multiQuestObj.data);
					});
					subChapterFormTemp = listMultipleChoiceQuest;
					$log.log("SubChapter List for MC");
					$log.log(subChapterFormTemp);
					angular.forEach(subChapterFormTemp, function(subChapQues) {
						student.getMultChoiceOption(subChapQues.multiQuesId).$promise.then(function(result) {
							subChapQues.options=result;
						});
						student.getMultiUserAnswer($scope.userId, subChapQues.multiQuesId).$promise.then(function(result) {
							subChapQues.userAnswer = result.userAnswer;
							$log.log("result of multi answer");
							$log.log(result);
						});
					});

				}
				else if ( subChapterTypeTemp == 's' ) {
					$log.log(subChapterFormTemp);
					angular.forEach( subChapterFormTemp, function(subChapQues) {
						student.getShortAnsUserAnswer($scope.userId, subChapQues.saQId).$promise.then(function(result) {
							subChapQues.userAnswer  = result.userAnswer;
							$log.log("result of short answer");
							$log.log(result);
						});
					});
				} else {
					angular.forEach( subChapterFormTemp, function(subChapQues) {
						student.getUploadedAns($scope.userId, subChapQues.uploadQuesId).then(function(result) {
							if ( angular.isUndefined(result) || result === null ) {
								subChapQues.userAnswer  = null;
							}
							else {
								subChapQues.userAnswer = result.filePath;
							}
							$log.log("result of short answer");
							$log.log(result);
						});
					});
				}
				$scope.subChapterSelected = subChapter;
				$scope.subChapterForm = subChapterFormTemp;
				$scope.subChapterType = subChapterTypeTemp;
			});
			
		};

		function handleSuccess( response ) {
			$log.log("Good call");
			$log.log(response);
			$scope. chapters = response.data;
		}

		function handleError( response ) {
			$log.log("Error with http call");
		}

	}]);