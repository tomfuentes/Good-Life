var facilitatorServices = angular.module('facilitatorServices', []);

facilitatorServices.factory('facilitator', function($http, $log) {
	return({
		getProfile : getProfile,
		editProfile: editProfile
	});

	/*
	Get Profile for Facilitator
	*/
	function getProfile(userId) {

	}

	/*
	Edit profile for Facilitator
	*/
	function editProfile (userId) {
		// body...
	}

	
});