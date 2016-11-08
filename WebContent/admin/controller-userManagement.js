var userManagement = angular.module('userManagement', []);

/*
	Admin Seach page controller
*/
userManagement.controller('AdminSearch', ['$scope', '$log', '$filter', 'ngTableParams', 'userService', 'toastr', '$modal',
	function($scope, $log, $filter, ngTableParams, userService, toastr, $modal) {

		//Store user status in object for quicklookup
		var userStatus = {};
		$scope.seachisOpen = true;
		var searchFormValues = null;

		$scope.submitSearch = function(searchForm) {
			$log.log( 'Submitting values' );
			var idsForRoles = getIdsForRoles($scope.roleOptions);
			var parameters = buildObject(searchForm.textInput, searchForm.typeSelected.id, idsForRoles);
			$log.log(idsForRoles);
			$log.log(parameters);
			// data = userService.search(idsForRoles, parameters);
			// $scope.userTable .reload();
			userService.search(idsForRoles, parameters).then( handleSuccess, handleError );
			$scope.seachisOpen = false;
			searchFormValues = searchForm;
		};

		//Search Options for the Text Field
		$scope.searchOptions = [
			{ label:'User Name', id:"usr_nm"},
			{ label:'First Name', id:"frst_nm"},
			{ label:'Last Name', id:"lst_nm"},
			{ label:'E-mail', id:"email"}];

		//Role options
		$scope.roleOptions = [
			{ name:'Student', id:'student', ticked: true},
			{ name:'Facilitator', id:'facilitator', ticked: false},
			{ name:'Moderator', id:'moderator', ticked: false}];

		//Table Information
		var data = [];

		$scope.userTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
			sorting: {
				name: 'asc'
			}
		}, {
			total: data.length,
			getData: function($defer, params) {
				var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
				$defer.resolve(orderedData.slice((params.page()-1)*params.count(),params.page()*params.count()));
			}
		});

		var getIdsForRoles = function(roleOptions) {
			var rolechoosen = {student:0, moderator:0, facilitator:0};
			angular.forEach(roleOptions, function(value, key) {
				if( value.ticked === true ) {
					if(value.id == "student") rolechoosen.student = 1;
					if(value.id == "facilitator") rolechoosen.facilitator = 1;
					if(value.id == "moderator") rolechoosen.moderator = 1;
						
				}
			});
			return rolechoosen;
		};

		var buildObject = function(textInput, id, idsForRoles) {
			var sendObject = {};
			sendObject.input = textInput;
			sendObject.id = id;
			sendObject.sb = idsForRoles.student;
			sendObject.mb = idsForRoles.moderator;
			sendObject.fb = idsForRoles.facilitator;
			return sendObject;
		};

		var handleSuccess = function (response) {
			$log.log("Succesful search");
			data = response.data[0];
			tempStatusObjects = response.data[1];
			userStatus = {};
			for(var index=0; index<data.length; index++) {
				data[index].userStatus = tempStatusObjects[index].statusTypeCode;
				data[index].endDate = tempStatusObjects[index].endDate;
				userStatus[tempStatusObjects[index].userId] = tempStatusObjects[index]
			}
			$scope.userTable.reload();
		};

		var handleError = function (response) {
			$log.log("Error with search");
			data = [{}];
			$scope.userTable .reload();
		};

		//When user wants to change status
		$scope.changeUserStatus = function(action, userId) {
			if(action == 'a') {
				if( userStatus.hasOwnProperty(userId)) {
					//User is suspended or deleted
					$log.log("Activate User");
					userService.changeEndDate(userStatus[userId].userStatusId, $filter('date')(new Date(), 'shortDate') ).then(function(result) {
						$log.log(result);
						toastr.success('User has been activated', 'Activation Succesful');
						$scope.submitSearch(searchFormValues);
					});
				}
				else {
					//User already is active
					toastr.warning('User is already active', 'Warning');
				}
			}
			else {
				if( userStatus.hasOwnProperty(userId) ) {
					//User is already suspended or deleted
					toastr.warning('User is already suspended/deleted', 'Warning');
				}
				else {
					//User is active so we can suspend/delete them
					userService.changeUserStatus(action, userId).then(function(result) {
						$log.log(result);
						if(action == 'd') {
							toastr.success('Deletion will occur in 7 days', 'Deletion Succesful');
						} else {
							toastr.success('Suspended for 7 days', 'Suspension Succesful');
						}
						$scope.submitSearch(searchFormValues);
					});
				}
			}
		};

		//Change User Suspension/Deletion End Date
		$scope.changeEndDate = function(userId) {
			if(userStatus.hasOwnProperty(userId)) {
				//User is suspended/deleted
				var modalInstanceDate = $modal.open({
					templateUrl: 'admin/pickEndDate.html',
					controller: 'ModalEndDatePicker',
					size: 'lg',
					resolve: {
						userStatusId: function() {
							return userStatus[userId].userStatusId;
						}
					},
					keyboard: false
				});

				modalInstanceDate.result.then(function(returnValue) {
					$log.log(returnValue);
					userService.changeEndDate(returnValue.userStatusId, $filter('date')(returnValue.endDate, 'shortDate') ).then(function(result) {
						$log.log(result);
						$scope.submitSearch(searchFormValues);
						toastr.success('End Date Change successful', 'Success');
					});
				});
			}
			else {
				//Active User
				toastr.warning('User is Active', 'Warning');
			}
		};

}]);

userManagement.controller('ModalEndDatePicker', ['$scope', '$log', '$modalInstance', 'userStatusId',
	function($scope, $log, $modalInstance, userStatusId) {
		$scope.currDate = new Date();
		$scope.endDate = new Date(); //Date that user picks
		var returnValue = {};

		$scope.ok = function() {
			returnValue.endDate = $scope.endDate;
			returnValue.userStatusId = userStatusId;
			$modalInstance.close(returnValue);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}
]);