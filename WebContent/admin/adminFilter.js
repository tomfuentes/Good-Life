angular.module('adminFilter', [])
	.filter('roleType', function() {
		return function(input) {
			if(input.toLowerCase() == "s") {
				return "Student";
			}
			if(input.toLowerCase() == "a") {
				return "Admin";
			}
			if(input.toLowerCase() == "f") {
				return "Facilitator";
			}
			if(input.toLowerCase() == "m") {
				return "Moderator";
			}
			
		};
	})
	.filter('statusType', function() {
		return function(input) {
			if(input.toLowerCase() == "s") {
				return "Suspended";
			}
			if(input.toLowerCase() == "a") {
				return "Active";
			}

			if(input.toLowerCase() == "d") {
				return "Deleted";
			}
		}
	})
	;