curriculum.directive('exerciseBuilder', ['listChapters', 
	function(listChapters) {
	
	var main = function($scope){

		var handleSuccess = function (response) {
			$scope.chapterData = response.data;
			$scope.structureData($scope.chapterData);
		};

		var handleError = function (response) {
			$scope.chapterData = [{}];
		};
		
		// View Exercise
		$scope.exerciseClick = function(rowData, event){
			$scope.editExFlag = true;
			$scope.addExFlag = false;
			var title = event.target.textContent;
			$scope.selectedExercise = $scope.getExerciseByTitle(title);
			$scope.ex_order = 2;
			var chapId = rowData.chap.chapId;
			$scope.chapIdNum = chapId;
			var chapTitle = "";

			var comArr = eval( $scope.chapterData );


			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].chap.chapId === chapId ) {
					chapTitle = comArr[i].chap.chapTitle;
				}
			}
			$scope.showPane("subchapter", chapTitle, title, "", "");

		};

	    //Add Exercise
	    $scope.submitExercise = function(exerciseFm){
	    	
	    	var chapId = $scope.chapIdNum;
	    	var title = exerciseFm.subChapTitle.$viewValue;
	    	var exDesc = exerciseFm.subChapDescr.$viewValue;
	    	var orderId = exerciseFm.exOrder.$viewValue.id;
	    	var published = exerciseFm.chapPub.$viewValue;
	    	
	    	var confirmAdd = function (response) {
	    		
		  	var position = 0;
	    		var comArr = eval( $scope.chapterData);
		  		for( var i = 0; i < comArr.length; i++ ) {
		  			if( comArr[i].chap.chapId === chapId ) {
		  				position = i;
		  			}
		  		}
	    		
		  		//alert(position + comArr[position-1].chap.chapTitle);
		  		alert(JSON.stringify($scope.chapterData[position]));
		  		if ($scope.chapterData[position].exer != null){
		  				$scope.chapterData[position].exer.push({"subChapId":response.data,"chapId":chapId,"subChapDescr":exDesc,"subChapTitle":title,"orderId":orderId,"published":published});
			  	}
			  		else {
						$scope.chapterData[position].chap.splice(position, 0, { "chap ":{"exer":[{"subChapId":response.data,"chapId":chapId,"subChapDescr":exDesc,"subChapTitle":title,"orderId":orderId,"published":published}]}});

			  		}
				$scope.showConfirmation("success", "Exercise titled " + "'"+title +"' was added!");

			} 

			    var failAdd = function (response) {
				$scope.showConfirmation("fail", "Exercise titled " + "'"+title +"' was not added! Please try again" );
			} 

			if (exerciseFm.$valid){
				
				listChapters.addSubChapter(chapId, title, exDesc, orderId).then( confirmAdd, failAdd );

			}
	    };
	    
	    
	    //Update Exercise
	    $scope.updateExercise = function(form){
         var row = $scope.getRowByID($scope.chapIdNum);	
            
	    	var chapId = $scope.chapIdNum;
	    	var title = form.subChapTitle.$viewValue;
	    	var exDesc = form.subChapDescr.$viewValue;
	    	var orderId = form.exOrder.$viewValue.id;
	    	var published = form.chapPub.$viewValue;
	    	
	  		var chapindex = -1;		
	  		var comArr = eval( $scope.chapterData );
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chap.chapId === row.chap.chapId ) {
	  				chapindex = i;
	  				break;
	  			}
	  		}
	  		 		
	  		var exindex = -1;		

	  		for( var i = 0; i < row.exer.length; i++ ) {
	  			if( row.exer[i].subChapTitle === $scope.selectedExercise.subChapTitle) {
	  				exindex = i;
	  				break;
	  			}
	  		}
	  		
	  		if( chapindex === -1 || exindex === -1) {
				$scope.showConfirmation("fail", "Exercise " + row.exer[0].subChapTitle +" was not modified!");
	  		}
	  		var itemToModify = row.exer[exindex];
	  		alert(JSON.stringify($scope.chapterData[chapindex].exer[exindex]));
			$scope.chapterData[chapindex].exer.splice(exindex, 1, [{"subChapId":itemToModify.subChapId,"chapId":chapId,"subChapDescr":exDesc,"subChapTitle":title,"orderId":orderId,"published":false}]);
	  		
	  		if(form.$valid){
	  			listChapters.updateExercise(itemToModify.subChapId, chapId, title, exDesc, orderId, false).then( handleSuccess, handleError );
				$scope.showConfirmation("success", "Exercise" + " titled '"+ itemToModify.subChapTitle +"' has been modified!");	
				listChapters.search().then( handleSuccess, handleError );
	  		}
			
	    };
	    
	    //Delete Exercise
	    $scope.deleteExercise = function(exTitle){
	    	var row = $scope.getRowByID($scope.chapIdNum);	    	
	    	
	  		var chapindex = -1;		
	  		var comArr = eval( $scope.chapterData );
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chap.chapId === row.chap.chapId ) {
	  				chapindex = i;
	  				break;
	  			}
	  		}
	  		
	  		
	  		var exindex = -1;		

	  		for( var i = 0; i < row.exer.length; i++ ) {
	  			if( row.exer[i].subChapTitle === exTitle) {
	  				exindex = i;
	  				break;
	  			}
	  		}
	  		
	  		if( chapindex === -1 || exindex === -1) {
				$scope.showConfirmation("fail", "Chapter" + "#"+row.exer[0].subChapId +" was not deleted!");
	  		}
	  		var itemToDelete = row.exer[exindex];
	  		$scope.chapterData[chapindex].exer.splice(exindex, 1);		
			listChapters.deleteExercise(itemToDelete.subChapId).then( handleSuccess, handleError );
			listChapters.search().then( handleSuccess, handleError );
			$scope.showConfirmation("success", "Exercise" + " titled '"+ itemToDelete.subChapTitle +"' has been deleted!");
			$scope.showChapterPane = false;
	    };
	    
	    //Get Exercise By Title
	    $scope.getExerciseByTitle = function(title){
	  		
	  		var row = $scope.currentChap;
	  		var exindex = -1;		

	  		for( var i = 0; i < row.exer.length; i++ ) {
	  			if( row.exer[i].subChapTitle === title) {
	  				exindex = i;
	  				break;
	  			}
	  		}
	  		
	  		return row.exer[exindex];
	  				
	    }
	
	}

	return {
		restrict: 'AE',
		templateUrl: 'partials/forms/addExercise.html',
		scope: false,
		controller: main
		};

}]);