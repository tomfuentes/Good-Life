'use strict';

var forceForGood = angular.module('forceForGood', [
	'ngRoute',
	'ui.bootstrap',
	'userManagement',
	'curriculum',
	'ngTable',
	'adminCenter',
	'multi-select',
	'adminFilter',
	'adminService',
	'chapterServices',
	'studentServices',
	'facilitatorServices',
	'angularFileUpload',
	'ngAnimate',
	'toastr']);

forceForGood.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/searchUsers', {
				templateUrl:'admin/search.html',
				controller: 'AdminSearch'
			}).
			when('/adminConsole', {
				templateUrl: 'admin/adminConsole.html',
				controller: 'AdminConsole'
			}).
			when('/userinvite', {
				templateUrl: 'admin/userinvite.html',
				controller: 'UserInvite'
			}). 
			when('/chapterBuilder', {
				templateUrl: 'partials/chapterBuilder.html',
				controller: 'ChapterBuilder'
			}).
			when('/curriculum/:userId', {
				templateUrl: 'partials/curriculum.html',
				controller: 'StudentCurriculumView'
			}).
			when('/studentHome/:userId', {
				templateUrl: 'partials/studentHome.html',
				controller: 'StudentHome'
			}).
			/*when('/instructorHome', {
				templateUrl: 'partials/instructorHome.html',
				controller: 'RedirectController'
			}).*/
			when('/facilitator/home/:userId', {
				templateUrl: 'facilitator/homepage.html',
				controller: 'FacHomePage'
			}).
			when('/home', {
				templateUrl: 'partials/selectFromMany.html',
				controller: 'RedirectController'
			}). 
			otherwise({
				redirectTo: '/home'
			});
	}]);
