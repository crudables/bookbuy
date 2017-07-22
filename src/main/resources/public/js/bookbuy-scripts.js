$(document).ready(function(){
	var url = window.location;
	var id = "";
	var comment = "";
	$("").submit(function(event){
		event.preventDefault();
		var formid = '#' + event.target.id
		 comment = $(this).children(':first').val();
		id = $(this).children(':first').next().val();
		
		doPost();
	});
	
//	function post(){
//		url = url+id;
//		$("#fragment"+id).load(url);
//	}
	function doPost(){
		
		$.ajax({
			type:"POST",
			contentType:"application/json",
			url:url+"posts/comment/"+id+"/"+comment,
			data:{"id":id,"comment":comment},
			success:function(result,error){
				url = window.location +"/";
				if(result.status == "Done"){
					$("#fragment"+id).load(url)
				}
				
				else{
					console.log("update failed");
					console.log(error);
				}
			}
			
		});
	}
});