<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap2.min.css" rel="stylesheet" media="screen">
    
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/subchapterbuilder.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>	
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

    <!-- using handlebars for templating, but DustJS might be better for the current purpose
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.0-rc.3/handlebars.min.js"></script> -->

	<title>
		Chapter Builder
	</title>
	
</head>
<body>

	<!--[if lt IE 9]>
	<b class="text-error">All components may not work correctly on IE 8 or below </b><br/><br/>
	<![endif]-->
	<a href="curriculum.html" style="float:right; margin:10px 30px">back to curriculum</a>
    <legend>Chapter Builder</legend>
	
    <div id="listOfFields" class="span3 well tab-content">
        <button class="btn btn-success" onclick="addChapter('Diversity in Chicago', 'Speech', '1');" style="margin:5px 5px">Add Chapter</button>
        
        <div class="chapter">
            <a href="chapterbuilder.html">Sample Chapter</a>
            <img id="deletebtn" onclick="deleteButton(this)" src="../resources/images/button_delete.png"></img>
        </div>
    </div>
    <div id="response" class="span3">
    
    </div>

<script>
	$(document).ready(); 
</script>
</body>
</html>
