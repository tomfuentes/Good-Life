var curriculum = angular.module('curriculum', []);

/*
	Chapter Builder page controller
 */
curriculum.controller('ChapterBuilder', ['$scope', '$log', '$filter', 'ngTableParams', 'listChapters','$modal',
                                         function($scope, $log, $filter, ngTableParams, listChapters, modal) {

	//Table Information
	var data = [];

	$scope.chapterTable = new ngTableParams({
		//Settings
		page: 1,
		count: 10,
		filter: {

			chapTitle: ''       // initial filter

		}
	}, {
		total: data.length,
		//groupBy: 'data.curriculumTreeList.objR.chapId',
		getData: function($defer, params) {

			var page = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
			$scope.data=page;
			$defer.resolve(page);

		}
	});


	var handleSuccess = function (response) {
		$log.log("Successful");
		data = response.data;
		//alert(JSON.stringify(data));
		$scope.structureData(data);
		//alert(JSON.stringify($scope.chapterData[0].exer[0]));
		data = $scope.chapterData;
		$scope.chapterTable .reload();
	};

	var handleError = function (response) {
		$log.log("Error");
		data = [{}];
		$scope.chapterTable .reload();
	};

	// Structure Table Data	
	$scope.structureData  = function(data){
		$scope.chapterData = [];
		var count = 0;
		angular.forEach(data, function(root) {
			angular.forEach(root, function(item) {
				$scope.chapterData.push( {"chap": item.data, "exer": null});
				$scope.exerciseData = [];

				count++;
				if (item.numberOfChildren > 0){

					angular.forEach(item.children, function(exercise) {

						$scope.exerciseData.push(exercise.data);
						$scope.chapterData[count-1].exer = $scope.exerciseData;

					});


				}
				//alert(JSON.stringify($scope.chapterData[count-1]));

			});
		});
	}

	//Add Chapter
	$scope.addRow = function(title, desc, addChapterFm) {

		var id = addChapterFm.chapId.$viewValue.id;
		var title = addChapterFm.chapTitle.$viewValue;
		var desc = addChapterFm.chapDescr.$viewValue;


		var confirmAdd = function (response) {
			$scope.chapterData.push({"chap":{"chapId":response.data,"chapDescr":desc,"chapTitle":title,"orderId":id,"published":false},"exer":[]});
			$scope.showConfirmation("success", "Chapter titled " + "'"+title +"' was added!");

		} 

		var failAdd = function (response) {
			$scope.showConfirmation("fail", "Chapter titled " + "'"+title +"' was not added! Please try again" );
		} 

		if (addChapterFm.$valid){
			listChapters.addChapter(title, desc, id).then(confirmAdd, failAdd);
		}

	};

	$scope.orderOptions = [{id: 1, label: 1 }, {id: 2, label: 2}, {id: 3, label: 3  } , {id: 4, label: 4 }, {id: 5 , label: 5}];

	// Update Chapter
	$scope.editRow = function(form){

		var id = $scope.chapIdNum;
		var title = form.chapTitle.$viewValue;
		var desc = form.chapDescr.$viewValue; 
		var order = form.chapId.$viewValue.id;

		var index = -1;		
		var comArr = eval( data );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].chap.chapId === id ) {
				index = i;
				break;
			}
		}


		var confirmAdd = function (response) {
			$scope.children = angular.copy($scope.chapterData[index].exer);
			$scope.chapterData.splice(index, 1,  {"chap":{"chapId":id,"chapDescr":desc,"chapTitle":title,"orderId":order,"published":false},"exer":$scope.children});
			$scope.showConfirmation("success", "Chapter titled " + "'"+title +"' was modifed!");

		} 

		var failAdd = function (response) {
			$scope.showConfirmation("fail", "Chapter titled " + "'"+title +"' was not modified! Please try again" );
		} 			 			 
		if (form.$valid){
			listChapters.updateChapter(id, title, desc, order, false).then(confirmAdd, failAdd);
		}
	}

	//Delete Chapter
	$scope.removeRow = function(){		
		var chapId = $scope.chapIdNum;
		var index = -1;		
		var comArr = eval( data );
		//	alert(JSON.stringify(comArr[0].objR));
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].chap.chapId === chapId ) {
				index = i;
				break;
			}
		}

		if( index === -1 ) {
			$scope.showConfirmation("fail", "Chapter" + "#"+chapId +" was not deleted!");
		}
		data.splice( index, 1 );		
		listChapters.deleteChapter(chapId).then( handleSuccess, handleError );
		$scope.showConfirmation("success", "Chapter" + " #"+chapId +" has been deleted!");
		$scope.chapterTable .reload();
		init();

	};


	$scope.hideRows = function(){
		show = false;
	};

	$scope.showRows = false;

	$scope.showRow = function(val){

		if(typeof val == 'undefined'){
			// alert('false');
			return false;

		}
		else{
			return true;
		}
	};

	// Dialog Modal
	var myModal;
	$scope.addExerciseData = {};

	$scope.openDialog = function(url, formData, size){
		$scope.addExerciseData = formData;
		myModal = modal.open({templateUrl: url, scope: $scope, size: size
		});
	}

	$scope.closeDialog = function(addExerciseFm){
		myModal.close();
		$scope.deleteExercise(addExerciseFm.subChapTitle.$viewValue);

	}

	$scope.closeChapDialog = function(){
		myModal.close();
		$scope.removeRow();

	}

	$scope.cancelDialog = function() {
		myModal.dismiss('cancel');
	};

	$scope.listClick = function(event, rowInfo){
		$scope.currentChap = rowInfo;

		if(event.target.className.indexOf("exerciseTitle") > 0){
			$scope.exerciseClick(rowInfo, event);

		}
		else{
			$scope.chapterClick(rowInfo);
		}
		//alert("check"+JSON.stringify(rowInfo));

	}

	// View Row Details from Table
	$scope.chapterClick = function(rowData){
		$scope.editChapterFlag = true;
		$scope.addChapterFlag = false;


		var chapTitle = rowData.chap.chapTitle;
		var chapDesc = rowData.chap.chapDescr;
		$scope.chapIdNum = rowData.chap.chapId;
		$scope.showPane("chapter", chapTitle, "", chapDesc);

	}


	//Show Chapter and Exercise (Subchapter) View
	$scope.showChapterPane = false;
	$scope.showChapterEdit = false;
	$scope.showSubChapPane = false;
	$scope.showSubChapEdit = false;
	$scope.editChapterFlag = false;
	$scope.addChapterFlag = false;
	$scope.editExFlag = false;
	$scope.addExFlag = true;
	$scope.chapIdNum = "";
	$scope.chapTitle = "";
	$scope.chapDescr = "";
	$scope.exTitle = "";
	$scope.exDescr = "";
	$scope.currentChap = "";

	$scope.resetPaneValues = function(){
		$scope.chapIdNum = "";
		$scope.chapTitle = "";
		$scope.chapDescr = "";
		$scope.exTitle = "";
		$scope.exDescr = "";
	}

	//Get Row Data
	$scope.getRowByID = function(chapID){
		var comArr = eval( data );

		var row;

		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].chap.chapId === chapID ) {
				row = comArr[i];
				break;
			}
		}

		return row;


	}

	$scope.showPane = function(paneType, chap, subChap, chapDesc, mode){
		//  $scope.resetPaneValues();

		if (paneType == "subchapter"){
			if(mode == "add"){
				$scope.selectedExercise = null;
				$scope.editExFlag = false;
				$scope.addExFlag = true;
			}
			$scope.showChapterPane = false;
			$scope.showSubChapPane = true;
			$scope.chapTitle = chap;
			$scope.exTitle = subChap;
		}
		else if (paneType == "chapter"){


			$scope.showChapterPane = true;
			$scope.showSubChapPane = false;
			$scope.chapTitle = chap;
			$scope.chapDescr = chapDesc;

		}


	};

	$scope.showSuccess = false;
	$scope.showFailure = false;
	$scope.confirmMsg = "";

	$scope.showConfirmation = function(flag, message){
		if (flag == "success"){
			$scope.showSuccess = true;
			$scope.showFailure = false;
		}
		else if (flag == "fail"){
			$scope.showFailure = true;
			$scope.showSuccess = false;
		}

		$scope.confirmMsg = message;


	};

	// Add Questions to Exercise
	var questions = {};
	questions.data = [{
		id: "1",
		text: "How has the Good Life impacted you?"
	},
	{
		id: "2",
		text: "Where do you plan to be in 5 years?"
	}];

	$scope.questions = questions;

	$scope.deleteItem = function (index) {
		questions.data.splice(index, 1);
	}
	$scope.addItem = function (question) {
		questions.data.push({
			id: questions.data.length + 1,
			text: question
		});
	}


	//Carousel
	$scope.myInterval = 5000;
	var slides = $scope.slides = [];
	$scope.addSlide = function() {
		var newWidth = 600 + slides.length + 1;
		slides.push({
			image: 'http://placekitten.com/' + newWidth + '/300',
			text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
			['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
		});
	};
	for (var i=0; i<4; i++) {
		$scope.addSlide();
	}

	$scope.fileURL = "";
	$scope.openCarousel = function(url, pdf){
		//alert(pdf);
		$scope.fileURL = pdf;
		$scope.viewPage(url, pdf);
	}
	//View Page Request

	$scope.viewPage = function(url, pdf){

		var confirmAdd = function (response) {
			/*var file = new Blob([response], {type: 'application/pdf'});
			    var fileURL = URL.createObjectURL(file);
			    $scope.content = $sce.trustAsResourceUrl(fileURL);*/

			myModal = modal.open({templateUrl: url, scope: $scope

			});
		} 

		var failAdd = function (response) {
			$scope.showConfirmation("fail", "Please try again" );
		} 

		listChapters.viewPage(pdf).then( confirmAdd, failAdd );
	}

	$scope.refresh = function(){
		init();
	}

	//Initial Home Search
	var init = function () {
		listChapters.search().then( handleSuccess, handleError );

	};
	init();

}]);