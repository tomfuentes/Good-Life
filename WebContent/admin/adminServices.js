var adminService = angular.module('adminService', []);

adminService.factory('userService', function( $http, $q, $log ) {
	
	return({
		//APIs
		search : search,
		inviteuser: inviteuser,
		changeUserStatus: changeUserStatus,
		changeEndDate : changeEndDate
	});

	//Function to return serach
	function search(idsForRoles, parameters) {

		var request = $http({
			method: 'POST',
			url: 'usermanagement/search',
			params: {input:parameters.input, field:parameters.id, sb:parameters.sb, mb:parameters.mb, fb:parameters.fb}
		});

		// return(request.then( handleSuccess, handleError ));
		return request;
	}

	function inviteuser(email) {

		var request = $http({
			method: 'POST',
			url: 'su/inviteuser',
			params: {email:email}
		});
		
		return request;
	}

	/**
	Suspend or delete user
	*/
	function changeUserStatus(action, userId) {
		var promise = $http({
			method: 'POST',
			url: 'usermanagement/adduserstatus',
			params: {userId:userId, statusTypeCode:action}
		}).then(function(response) {
			return response.data;
		})

		return promise;
	}

	/**
	Activate User
	*/
	function changeEndDate(userStatusId, date) {
		$log.log("Activate User");
		$log.log(date);
		var promise = $http({
			method: 'POST',
			url: 'usermanagement/changeenddate',
			params: {userStatusId:userStatusId, newDate:date}
		}).then(function(response) {
			return response.data;
		});

		return promise;
	}

});