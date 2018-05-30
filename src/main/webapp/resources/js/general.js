$(document).ready(function(){
	selectall = function(){
		if($("#selecttodo").is(":checked")){
			$("input[type=checkbox]").prop('checked', true);
		}else{
			$("input[type=checkbox]").prop('checked', false);
		}
		
	};

});
