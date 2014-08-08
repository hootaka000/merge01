jQuery(function($) {
	
  //メニュー
  $('#menu_system').parent().attr('class','active');

  //管理者追加ボタン
  $('#admin-add-button').click(function() {
	$('#admin-add-dialog').dialog('open');
  });
  $('#admin-add-dialog').dialog({
    autoOpen: false,
    resizable: true,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#admin-add-dialog-update').click(function() {
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });

	  $.ajax({
	      type: "POST",
	      url: $("#form-admin-add").attr('action'),
	      data: param,
	      success: function (data) {
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#admin-add-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $('#admin-add-dialog').dialog('close');
	        	  location.href = "setting";
	          }
	      },
	      error: function () {
	    	  $('#admin-add-dialog').dialog('close');
	    	  location.href = "/ams/admin/error";
	      }
	  });

	return false;
  }); 
  $('#admin-add-dialog-cancel').click(function() {
	    $('#admin-add-dialog').dialog('close');
	    return false;
  });
  
  //管理者削除ボタン
  $('.admin-remove-button').click(function() {
	    var tr = $(this).parents("tr");
	    var name = tr.find("#name");
	    $('#admin-remove-dialog-message').text(name.text() + "を削除しますか？");
	    $('#del_id').val($(this).attr('data-user-id'));
	    $('#admin-remove-dialog').dialog('open');
  });
  $('#admin-remove-dialog').dialog({
    autoOpen: false,
    resizable: true,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#admin-remove-dialog-remove').click(function() {
		$.ajax({
			type: "post",
			url: $("#form-admin-delete").attr('action'),
			async: true,
			traditional: true,
			data: {
				id: $("#del_id").val()
			},
			success: function(data) {
	        	$('#admin-remove-dialog').dialog('close');
	        	location.href = "setting";
			},
			error: function() {
	        	$('#admin-remove-dialog').dialog('close');
				location.href = "/ams/admin/error";
			}
		})
    return false;
  });
  $('#admin-remove-dialog').on('click', '#admin-remove-dialog-cancel', function() {
    $('#admin-remove-dialog').dialog('close');
    return false;
  });
  
  //アクセスキー追加ボタン
  $('#accesskey-add-button').click(function() {
    $('#accesskey-add-dialog').dialog('open');
  });
  $('#accesskey-add-dialog').dialog({
    autoOpen: false,
    resizable: true,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#linkage-system-add-dialog-add-button').click(function() {
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });

	  $.ajax({
	      type: "POST",
	      url: $("#form-linkage-system-add").attr('action'),
	      data: param,
	      success: function (data) {
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#accesskey-add-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $('#accesskey-add-dialog').dialog('close');
	        	  location.href = "setting";
	          }
	      },
	      error: function () {
	    	  $('#accesskey-add-dialog').dialog('close');
	    	  location.href = "/ams/admin/error";
	      }
	  });

	return false;
  });
  $('#linkage-system-add-dialog-cancel-button').click(function() {
    $('#accesskey-add-dialog').dialog('close');
    return false;
  });
  
  //アクセスキー削除ボタン
  $('.accesskey-remove-button').click(function() {
    var tr = $(this).parents("tr");
    var employee_name = tr.find("#employee-name");
    var system_name = tr.find("#system-name");
    var name = employee_name.text() + ":" + system_name.text();
    $('#linkage-system-remove-dialog-message').text(name + "を削除しますか？");
    $('#del_linkage_id').val($(this).attr('data-user-id'));
    $('#accesskey-remove-dialog').dialog('open');
  });
  $('#accesskey-remove-dialog').dialog({
    autoOpen: false,
    resizable: true,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#linkage-system-remove-dialog-remove').click(function() {
		$.ajax({
			type: "post",
			url: $("#form-linkage-system-delete").attr('action'),
			async: true,
			traditional: true,
			data: {
				id: $("#del_linkage_id").val()
			},
			success: function(data) {
				$('#accesskey-remove-dialog').dialog('close');
	        	location.href = "setting";
			},
			error: function() {
				$('#accesskey-remove-dialog').dialog('close');
				location.href = "/ams/admin/error";
			}
		})
    return false;
  });
  $('#linkage-system-remove-dialog-cancel').click(function() {
    $('#accesskey-remove-dialog').dialog('close');
    return false;
  });
});
