/**
SubChapter view
*/

forceForGood.directive('subchapterView', ['$log', 'student', '$compile', '$http', '$upload',
	function($log, student, $compile, $http, $upload) {

		var getTemplate = function(contentType) {
			$log.log(contentType);
			var templateUrl = '';
			switch(contentType) {
				case 's':
					templateUrl = 'partials/subchapter-s.html';
					break;
				case 'm':
					templateUrl = 'partials/subchapter-m.html';
					break;
				case 'u':
					templateUrl = 'partials/subchapter-u.html';
					break;
			}
			return templateUrl;
		}
		
		var linker = function($scope,element,attrs) {
			$scope.$watch('subchapterType', function(subchapterType) {
				if(subchapterType && subchapterType.length) {
					$scope.dynamicUrl = getTemplate(subchapterType);
				}
			});
			
		};

		var controllerFunc = function($scope) {
			$scope.postShortAns = function(submitWork) {
				$log.log("Got Here to submitPost for Short Answer");
				if (!submitWork) {
					angular.forEach($scope.subchapterForm, function(subChapterElement) {
						student.updateShortAns($scope.userId, subChapterElement.saQId, subChapterElement.userAnswer, submitWork);
					});
					$("#confirmation-success-s").get(0).innerHTML = 
						$("#confirmation-success-s").get(0).innerHTML.replace("submitted", "saved");
					$scope.showConfirmation("success", "shortanswer");
					$("#confirmation-success-s").show().delay(4000).fadeOut(200);
				} else {
					var i = 0;
					var failed = false;
					while ($scope.subchapterForm[i] != undefined) {
						var subChapterElement = $scope.subchapterForm[i];
						// submit is not acceptable if answer is less than minimum length.
						if (subChapterElement.userAnswer == undefined ||
							subChapterElement.userAnswer.length < 150) {
							failed = true;
							$log.log(i, " failed.");
							$scope.showConfirmation("fail", "shortanswer");
							$("#confirmation-fail-s").show().delay(4000).fadeOut(200);
							break;
						}
						$log.log(i, " good.");
						i += 1;
					}
					if (!failed) {
						$("#confirmation-success-s").get(0).innerHTML = 
							$("#confirmation-success-s").get(0).innerHTML.replace("saved", "submitted");
						$scope.showConfirmation("success", "shortanswer");
						$("#confirmation-success-s").show().delay(4000).fadeOut(200);
					}
				}
			};

			$scope.submitPostMultiChoice = function() {
				$log.log("Got Here to submitPost for Multiple Choice");
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					student.updateMultiChoice($scope.userId, subChapterElement.multiQuesId, subChapterElement.userAnswer)
				});
				$scope.showConfirmation("success", "multichoice");
				$("#confirmation-success-m").show().delay(4000).fadeOut(200);
			};

			$scope.uploadQues = function() {
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					if (subChapterElement.files && subChapterElement.files.length) {
						$scope.upload($scope.userId, subChapterElement.uploadQuesId, subChapterElement.files);
					} else {
						$log.log("no file selected");					
					    $scope.showConfirmation("fail", "upload");
					    $("#confirmation-fail-u").show().delay(4000).fadeOut(200);
					}
					
				});
			}

			$scope.upload = function(user, questId, files) {
				$log.log("Got to upload!");
				$log.log(files);
				if (files && files.length) {
					for (var i = 0; i < files.length; i++) {
						var file = files[i];
						$log.log("Uploading");
						$upload.upload({
							url: "student/updateuploadeduseranswer",
							fields: {'userId': user, 'uploadQuesId': questId, 'mediaTypeId': 1},
							file:file,
							headers: {'enctype': 'multipart/form-data'} // only for html5
						}).progress(function(evt) {
							var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
							$log.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
						}).success(function (data, status, headers, config) {
							$log.log('file ' + config.file.name + 'uploaded. Response: ' + data);
						});
					}
					$scope.showConfirmation("success", "upload");
					$("#confirmation-success-u").show().delay(4000).fadeOut(200);
				}
			};
			
			// show confirmation per type of question
		    $scope.showConfirmation = function(flag, quesType){
		    	$scope.successM = false;
		    	$scope.successS = false;
		    	$scope.successU = false;
			    $scope.failureM = false;
			    $scope.failureS = false;
			    $scope.failureU = false;
			    
			    if (quesType == "multichoice") {
			    	if (flag == "success"){
				    	  $scope.successM = true;
				          $scope.failureM = false;
			    	}
			    	else if (flag == "fail"){
				    	  $scope.failureM = true;
				          $scope.successM = false;
			    	}
			    } else if (quesType == "shortanswer") {
			    	if (flag == "success"){
				    	  $scope.successS = true;
				          $scope.failureS = false;
			    	}
			    	else if (flag == "fail"){
				    	  $scope.failureS = true;
				          $scope.successS = false;
			    	}
			    } else if (quesType == "upload") {
			    	if (flag == "success"){
				    	  $scope.successU = true;
				          $scope.failureU = false;
			    	}
			    	else if (flag == "fail"){
				    	  $scope.failureU = true;
				          $scope.successU = false;
			    	}
			    }
		    };
		};

		return {
			restrict: 'E',
			scope: {
				userId: '=userid',
				subchapter: '=subchapter',
				subchapterType: '=subchapterType',
				subchapterForm: '=subchapterForm'
			},
			link: linker,
			template: '<ng-include src="dynamicUrl"></ng-include>',
			controller: controllerFunc
		};

	}]);