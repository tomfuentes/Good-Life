	/* Make the control draggable */
	function makeDraggable() {
		$(".selectorField").draggable({ helper: "clone",stack: "div",cursor: "move", cancel: null  });
	}

	var _ctrl_index = 1001;
	function docReady() {
		console.log("document ready");
		compileTemplates();
		
		makeDraggable();
		
		$( ".droppedFields" ).droppable({
			  activeClass: "activeDroppable",
			  hoverClass: "hoverDroppable",
			  accept: ":not(.ui-sortable-helper)",
			  drop: function( event, ui ) {
				//console.log(event, ui);
				var draggable = ui.draggable;				
				draggable = draggable.clone();
				draggable.removeClass("selectorField");
				draggable.addClass("droppedField");
				draggable[0].id = "CTRL-DIV-"+(_ctrl_index++); // Attach an ID to the rendered control
				draggable.appendTo(this);				
				

				/* Once dropped, attach the customization handler to the control */
				draggable.click(function () {
										// The following assumes that dropped fields will have a ctrl-defined. 
										//   If not required, code needs to handle exceptions here. 
										var me = $(this)
										var ctrl = me.find("[class*=ctrl]")[0];
										var ctrl_type = $.trim(ctrl.className.match("ctrl-.*")[0].split(" ")[0].split("-")[1]);
										customize_ctrl(ctrl_type, this.id);
										//window["customize_"+ctrl_type](this.id);
								});

				makeDraggable();
			}
		});		

		/* Make the droppedFields sortable and connected with other droppedFields containers*/
		$( ".droppedFields" ).sortable({
										cancel: null, // Cancel the default events on the controls
										connectWith: ".droppedFields"
									}).disableSelection();
	}
	

	/*
		Preview the customized form 
			-- Opens a new window and renders html content there.
	*/
	function preview() {
		console.log('Preview clicked');
		
		// Sample preview - opens in a new window by copying content -- use something better in production code, just one way I did it

		
		var selected_content = $("#selected-content").clone();
		selected_content.find("div").each(function(i,o) {
								var obj = $(o)
								obj.removeClass("draggableField ui-draggable well ui-droppable ui-sortable");
							});
		var legend_text = $("#form-title")[0].value;
		
		if(legend_text=="") {
			legend_text="Form builder demo";
		}
		selected_content.find("#form-title-div").remove();
		
		var selected_content_html = selected_content.html();
		
		var dialogContent  ='<!DOCTYPE HTML>\n<html lang="en-US">\n<head>\n<meta charset="UTF-8">\n<title></title>\n';
		dialogContent+= '<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">\n';
		dialogContent+='<style>\n'+$("#content-styles").html()+'\n</style>\n';
		dialogContent+= '</head>\n<body>';
		dialogContent+= '<legend>'+legend_text+'</legend>';
		dialogContent+= selected_content_html;
		dialogContent+= '\n</body></html>';

        // For testing
		//dialogContent+='<br/><br/><b>Source code: </b><pre>'+$('<div/>').text(dialogContent).html();+'</pre>\n\n';

		//dialogContent = dialogContent.replace('\n</body></html>','');
		dialogContent+= '\n</body></html>';
		
		

		var win = window.open("about:blank");
		win.document.write(dialogContent);
	}
		
	if(typeof(console)=='undefined' || console==null) { console={}; console.log=function(){}}
	
	/* Delete the control from the form */
	function delete_ctrl() {
		if(window.confirm("Are you sure about this?")) {
			var ctrl_id = $("#theForm").find("[name=forCtrl]").val()
			console.log(ctrl_id);
			$("#"+ctrl_id).remove();
		}
	}
	
	/* Compile the templates for use */
	function compileTemplates() {
		window.templates = {};
		window.templates.common = Handlebars.compile($("#control-customize-template").html());
		
		/* HTML Templates required for specific implementations mentioned below */
		
		// Mostly we donot need so many templates
		
		window.templates.textbox = Handlebars.compile($("#textbox-template").html());
		window.templates.passwordbox = Handlebars.compile($("#textbox-template").html());
		window.templates.combobox = Handlebars.compile($("#combobox-template").html());
		window.templates.selectmultiplelist = Handlebars.compile($("#combobox-template").html());
		window.templates.radiogroup = Handlebars.compile($("#combobox-template").html());
		window.templates.checkboxgroup = Handlebars.compile($("#combobox-template").html());
		
	}
	
	// Object containing specific "Save Changes" method
	save_changes = {};
	
	// Object comaining specific "Load Values" method. 
	load_values = {};
	
	
	/* Common method for all controls with Label and Name */
	load_values.common = function(ctrl_type, ctrl_id) {
		var form = $("#theForm");
		var div_ctrl = $("#"+ctrl_id);
		
		form.find("[name=label]").val(div_ctrl.find('.control-label').text())
		var specific_load_method = load_values[ctrl_type];
		if(typeof(specific_load_method)!='undefined') {
			specific_load_method(ctrl_type, ctrl_id);		
		}
	}
	
	
	
	/* Specific method to load values from a textbox control to the customization dialog */
	load_values.textbox = function(ctrl_type, ctrl_id) {
		var form = $("#theForm");
		var div_ctrl = $("#"+ctrl_id);
		var ctrl = div_ctrl.find("input")[0];
		form.find("[name=name]").val(ctrl.name)		
		form.find("[name=placeholder]").val(ctrl.placeholder)		
	}
	
	// Passwordbox uses the same functionality as textbox - so just point to that
	load_values.passwordbox = load_values.textbox;

	
	/* Specific method to load values from a combobox control to the customization dialog  */
	load_values.combobox = function(ctrl_type, ctrl_id) {
		var form = $("#theForm");
		var div_ctrl = $("#"+ctrl_id);
		var ctrl = div_ctrl.find("select")[0];
		form.find("[name=name]").val(ctrl.name)
		var options= '';
		$(ctrl).find('option').each(function(i,o) { options+=o.text+'\n'; });
		form.find("[name=options]").val($.trim(options));
	}
	// Multi-select combobox has same customization features
	load_values.selectmultiplelist = load_values.combobox;
	
	
	/* Specific method to load values from a radio group */
	load_values.radiogroup = function(ctrl_type, ctrl_id) {
		var form = $("#theForm");
		var div_ctrl = $("#"+ctrl_id);
		var options= '';
		var ctrls = div_ctrl.find("div").find("label");
		var radios = div_ctrl.find("div").find("input");
		
		ctrls.each(function(i,o) { options+=$(o).text()+'\n'; });
		form.find("[name=name]").val(radios[0].name)
		form.find("[name=options]").val($.trim(options));
	}
	
	// Checkbox group  customization behaves same as radio group
	load_values.checkboxgroup = load_values.radiogroup;
	
	/* Specific method to load values from a button */
	load_values.btn = function(ctrl_type, ctrl_id) {
		var form = $("#theForm");
		var div_ctrl = $("#"+ctrl_id);
		var ctrl = div_ctrl.find("button")[0];
		form.find("[name=name]").val(ctrl.name)		
		form.find("[name=label]").val($(ctrl).text().trim())		
	}
	
	/* Common method to save changes to a control  - This also calls the specific methods */
	
	save_changes.common = function(values) {
		var div_ctrl = $("#"+values.forCtrl);
		div_ctrl.find('.control-label').text(values.label);
		var specific_save_method = save_changes[values.type];
		if(typeof(specific_save_method)!='undefined') {
			specific_save_method(values);		
		}
	}
	
	/* Specific method to save changes to a text box */
	save_changes.textbox = function(values) {
		var div_ctrl = $("#"+values.forCtrl);
		var ctrl = div_ctrl.find("input")[0];
		ctrl.placeholder = values.placeholder;
		ctrl.name = values.name;
		//console.log(values);
	}

	// Password box customization behaves same as textbox
	save_changes.passwordbox= save_changes.textbox;

	/* Specific method to save changes to a combobox */
	save_changes.combobox = function(values) {
		console.log(values);
		var div_ctrl = $("#"+values.forCtrl);
		var ctrl = div_ctrl.find("select")[0];
		ctrl.name = values.name;
		$(ctrl).empty();
		$(values.options.split('\n')).each(function(i,o) {
			$(ctrl).append("<option>"+$.trim(o)+"</option>");
		});
	}
	
	/* Specific method to save a radiogroup */
	save_changes.radiogroup = function(values) {
		var div_ctrl = $("#"+values.forCtrl);
		
		var label_template = $(".selectorField .ctrl-radiogroup label")[0];
		var radio_template = $(".selectorField .ctrl-radiogroup input")[0];
		
		var ctrl = div_ctrl.find(".ctrl-radiogroup");
		ctrl.empty();
		$(values.options.split('\n')).each(function(i,o) {
			var label = $(label_template).clone().text($.trim(o))
			var radio = $(radio_template).clone();
			radio[0].name = values.name;
			label.append(radio);
			$(ctrl).append(label);
		});
	}
	
	/* Same as radio group, but separated for simplicity */
	save_changes.checkboxgroup = function(values) {
		var div_ctrl = $("#"+values.forCtrl);
		
		var label_template = $(".selectorField .ctrl-checkboxgroup label")[0];
		var checkbox_template = $(".selectorField .ctrl-checkboxgroup input")[0];
		
		var ctrl = div_ctrl.find(".ctrl-checkboxgroup");
		ctrl.empty();
		$(values.options.split('\n')).each(function(i,o) {
			var label = $(label_template).clone().text($.trim(o))
			var checkbox = $(checkbox_template).clone();
			checkbox[0].name = values.name;
			label.append(checkbox);
			$(ctrl).append(label);
		});
	}
	
	// Multi-select customization behaves same as combobox
	save_changes.selectmultiplelist = save_changes.combobox;
	
	/* Specific method for Button */
	save_changes.btn = function(values) {
		var div_ctrl = $("#"+values.forCtrl);
		var ctrl = div_ctrl.find("button")[0];
		$(ctrl).html($(ctrl).html().replace($(ctrl).text()," "+$.trim(values.label)));
		ctrl.name = values.name;
		//console.log(values);
	}

	
	/* Save the changes due to customization 
		- This method collects the values and passes it to the save_changes.methods
	*/
	function save_customize_changes(e, obj) {
		//console.log('save clicked', arguments);
		var formValues = {};
		var val=null;
		$("#theForm").find("input, textarea").each(function(i,o) {
			if(o.type=="checkbox"){
				val = o.checked;
			} else {
				val = o.value;
			}
			formValues[o.name] = val;
		});
		save_changes.common(formValues);
	}
	
	/*
		Opens the customization window for this
	*/
	function customize_ctrl(ctrl_type, ctrl_id) {
		console.log(ctrl_type);
		var ctrl_params = {};

		/* Load the specific templates */
		var specific_template = templates[ctrl_type];
		if(typeof(specific_template)=='undefined') {
			specific_template = function(){return ''; };
		}
		var modal_header = $("#"+ctrl_id).find('.control-label').text();
		
		var template_params = {
			header:modal_header, 
			content: specific_template(ctrl_params), 
			type: ctrl_type,
			forCtrl: ctrl_id
		}
		
		// Pass the parameters - along with the specific template content to the Base template
		var s = templates.common(template_params)+"";
		
		
		$("[name=customization_modal]").remove(); // Making sure that we just have one instance of the modal opened and not leaking
		$('<div id="customization_modal" name="customization_modal" class="modal hide fade" />').append(s).modal('show');
		
		setTimeout(function() {
			// For some error in the code  modal show event is not firing - applying a manual delay before load
			load_values.common(ctrl_type, ctrl_id);
		},300);
    }
	
	function addChapterDB(dataInput){
		var success = false;

	    $("#response").html("");
        
        $.ajax({
            url: "../chapterlookup/addchapter",
            type: 'POST',
            data: dataInput,
            success: function(data, textStatus, xhr) {
            	addDiv();
            	$("#response").html("Chapter successfully added - ID# "+data);
            },
            error: function(xhr, textStatus, errorThrown) {
            		alert(textStatus);
            }
        });
	}
	
	function addDiv(className, url, title){
        var newSub = document.createElement('div');
        newSub.className = 'chapter';

        var newLink = document.createElement('a');
        newLink.href = 'chapterbuilder.html';
        newLink.innerHTML = "New Chapter";

        var deletebtn = document.createElement("img");
        deletebtn.id = "deletebtn";
        deletebtn.src = "../resources/images/button_delete.png";
        deletebtn.onclick = function goaway() {
            if (window.confirm("Are you sure you want to delete this item?"))
                this.parentNode.remove();
            };

        newSub.appendChild(newLink);
        newSub.appendChild(deletebtn);
        
        document.getElementById('listOfFields').appendChild(newSub);
	}

    // Dynamically add tabs for new (sub)chapters.
    function addChapter(desc, title, id) {
        console.log("add chapter");
      
        var input = {chapTitle: title, orderId: id, chapDescr: desc};
        
        //Make database call;

        addChapterDB(input);
        	
	    //Database call complete;  receive confirmation; create div

        
	}
    
    function addSubchapter() {
        //TODO: prompt for type of subchapter and choose among the 3. 
        console.log("add subchap");
      
        var newSub = document.createElement('div');
        newSub.className = 'subchapter';

        var newLink = document.createElement('a');
        newLink.href = 'subchapterbuilder.html';
        newLink.innerHTML = "New Subchapter";

        var deletebtn = document.createElement("img");
        deletebtn.id = "deletebtn";
        deletebtn.src = "../resources/images/button_delete.png";
        deletebtn.onclick = function goaway() {
            if (window.confirm("Are you sure you want to delete this item?"))
                this.parentNode.remove();
            };
        
        newSub.appendChild(newLink);
        newSub.appendChild(deletebtn);
        
        document.getElementById('listOfFields').appendChild(newSub);
	}

    function deleteButton(e) {
        if (window.confirm("Are you sure you want to delete this item?")) {
            e.parentNode.remove();
        }
    }
