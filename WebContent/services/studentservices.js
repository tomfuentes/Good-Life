var studentServices = angular.module('studentServices', ['ngResource']);

studentServices.factory('student', ['$http', '$log', '$resource', function($http, $log, $resource){
	return ({
		//API Calls
		getAllowedChapters : getAllowedChapters,
		getSubChapter : getSubChapter,
		getSubChapterForm : getSubChapterForm,
		getMultChoiceOption : getMultChoiceOption,
		getMultiUserAnswer : getMultiUserAnswer,
		getShortAnsUserAnswer : getShortAnsUserAnswer,
		getUploadedAns : getUploadedAns,
		updateMultiChoice : updateMultiChoice,
		updateShortAns : updateShortAns,
		getAllowedCurriculum : getAllowedCurriculum,
		getProgress : getProgress
	});
	
	/**
	Get student progress
	*/
	function getProgress(userId) {
		$log.log("Got to get student progress");
		$log.log("Userid: "+userId);
		var promise = $http.get('student/getprogress', { params:{userId:userId} } ).then(function(response) {
			$log.log(response);
			return response.data;
		});
		return promise;
	}

	/**
	Get allowed curriculum
	*/
	function getAllowedCurriculum(userId) {
		$log.log("Got to getAllowedCurriculum");
		return $resource('student/getallowedcurriculum', {userId:userId}, {
			queryCurr:{method:'GET', isArray:true}
		}).queryCurr();
		
	}
	
	/**
	Get all the chapters for the user
	*/
	function getAllowedChapters(userId) {
		$log.log("Got to getAllowedChapters");
		return chapters =  $resource('student/getallowedchapters', {userId:userId}, {
			queryChap:{method:'GET', isArray:true}
		}).queryChap();
	}

	/**
	Get all the subchapter for that chapter
	*/
	function getSubChapter(chapterId) {
		$log.log("Got to get subchapter");
		return $resource('student/getsubchapsbychapter', {chapId:chapterId}, {
			querySubChap:{method:'GET', isArray:true}
		}).querySubChap();
	}

	/**
	Get the subchapter form for the subchapter
	*/
	function getSubChapterForm(subChapId) {
		//var data = [[{"multiQuesId":1,"quesText":"MULTIPLE CHOICE QUESTION 1","helpText":"THIS IS HELP TEXT","correctAnswer":1,"subChapId":1,"orderId":1,"published":true},{"multiQuesId":2,"quesText":"MULTIPLE CHOICE QUESTION 2","helpText":"THIS IS HELP TEXT","correctAnswer":1,"subChapId":1,"orderId":2,"published":true}],"m"];
		// var data = [[{"saQId":1,"subChapId":5,"question":"Write about the good life.","helpText":"No help text required.","orderId":1,"published":true},{"saQId":2,"subChapId":5,"question":"Write about yourself.","helpText":"No help text required.","orderId":2,"published":true}],"s"];
		// return data;
		$log.log("Got to get getSubChapterForm");
		return $resource('student/getsubchapform', {subChapId:subChapId}, {
			querySubChapForm:{method:'GET', isArray:true}
		}).querySubChapForm();
	}

	/**
	Get the multile choice question options
	*/
	function getMultChoiceOption(multiQuestId) {
		// if(multiQuestId == 1)
		// 	var data = [{"optionId":1,"multiQuesId":1,"choiceText":"OPTION 1","published":true},{"optionId":2,"multiQuesId":1,"choiceText":"OPTION 2","published":true}];
		// else
		// 	var data = [{"optionId":3,"multiQuesId":2,"choiceText":"OPTION 1","published":true}];
		// return data;
		$log.log("Got to get getMultChoiceOption");
		return $resource('student/getmultichoiceoptions', {multiQuesId:multiQuestId}, {
			queryMultiChoiceQuest:{method:'GET', isArray:true}
		}).queryMultiChoiceQuest();
	}

	/**
	Get the user answer for multiple choice
	*/
	function getMultiUserAnswer(userId, multiQuestId) {
		// if(multiQuestId == 1)
		// 	var data = 1;
		// else
		// 	var data = 3;
		// return data;
		$log.log("Got to get getMultiUserAnswer");
		return $resource('student/getmultichoiceuseranswer', {userId:userId, multiQuesId:multiQuestId}, {
			queryMultiUserAns:{method:'GET', isArray:false}
		}).queryMultiUserAns();
	}

	/**
	Get the user answer for short answer
	*/
	function getShortAnsUserAnswer(userId, shortAnsId) {
		// var data = {"subChapId":5,"saQId":1,"userAnswer":"This is my answer.","userId":1,"aprvd":false};
		// return data.userAnswer;
		$log.log("Got to get getShortAnsUserAnswer");
		return $resource('student/getshortansweruseranswer', {userId:userId, shortAnsId:shortAnsId}, {
			queryShortAns:{method:'GET'}
		}).queryShortAns();
	}

	/**
	Get the user answer for short answer
	*/
	function getUploadedAns(userId, uploadQuesId) {
		var promise = $http.get('student/getuploadedans', { 
			params: {userId: userId, uploadQuesId:uploadQuesId}} ).then(function(response) {
				return response.data;
			});
		return promise;
	}

	/**
	Update user multi answer
	*/
	function updateMultiChoice(userId, multiQuesId, userAnswer) {
		$log.log("Got to get updateMultiChoice");
		$log.log("Userid: "+userId);
		$log.log("MultiQuestId: "+multiQuesId);
		$log.log("UserAnswer: "+userAnswer);
		return $resource('student/updatemultichoice', {userId:userId, multiQuesId:multiQuesId, userAnswer:userAnswer}, {
			updateMultiChoice:{method:'GET'}
		}).updateMultiChoice();

	}

	/**
	Update user short answer
	*/
	function updateShortAns(userId, shortAnswerId, userAnswer, submitted) {
		$log.log("Got to get updateShortAns");
		$log.log("Userid: "+userId);
		$log.log("Short Answer Id: "+shortAnswerId);
		$log.log("UserAnswer: "+userAnswer);
		return $resource('student/updateshortanswer', {userId:userId, saQId:shortAnswerId, 
			userAnswer:userAnswer, submitted:submitted}, 
			{updateShortAns:{method:'GET'}
		}).updateShortAns();
	}
	
	
	
	

}]);