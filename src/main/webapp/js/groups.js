
$("#employee-filter").bind("change keyup", function() {
    var filter = $(this);
    var trs = $('#employee-list > tbody > tr');
    var search = $.trim(filter.val());
    var regex = new RegExp(search, "gi");
    jQuery.each(trs, function(i, tr) {
      var num = $(tr).find('#num');
      var name = $(tr).find('#name');
      if (num.text().match(regex) != null) {
        $(tr).show();
        return;
      }
      if (name.text().match(regex) != null) {
        $(tr).show();
        return;
      }
      $(tr).hide();
    });
});

jQuery(function($) {

  //メニュー
  $('#menu_groups').parent().attr('class','active');

  $('.list-group-item').click(function(){
	  $('#group_model_id').val($(this).attr('val_id'));
	  $('#form-group-list').submit();
  });
  
  //グループ社員追加ボタン
  $('#employee-add-button').click(function() {
      $("#grp_emp_add_id").val($('.active_group').attr('val_id'));

      $.ajax({
          type: "POST",
          url: $("#form-groups-employee-add").attr('action'),
          async: true,
          data: {
        	  groupId: $("#grp_emp_add_id").val()
          },
          success: function (data) {
              if(data){
                  var ret = $.parseHTML(data);
                  $("#employee-add-dialog").find('.dialog_input').html(ret);
                  $('#employee-add-dialog').dialog('open');
              }
          },
          error: function () {
              $("#employee-add-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
	  
    return false;
  });
  $('#employee-add-dialog').dialog({
    autoOpen: false,
    resizable: true,
    width: "600",
    height: "400",
    modal: true
  });
  $('#employee-add-dialog-add').click(function() {

	  var param = {};
	  $('.employee-add-dialog-check:checked').each(function(){
		  param[$(this).attr('name')] = $(this).val();
	  });
	  param['groupId'] = $('#grp_emp_add_id').val();
	  
      $.ajax({
          type: "POST",
          url: $("#groups-employee-add-update").attr('action'),
          data: param,
          success: function (data) {
        	  $('#employee-add-dialog').dialog('close');
        	  $('#form-group-list').submit();
          },
          error: function () {
        	  $('#employee-add-dialog').dialog('close');
        	  location.href = "/ams/admin/error";
          }
      });
	  
    return false;
  });
  $('#employee-add-dialog-cancel').click(function() {
    $('#employee-add-dialog').dialog('close');
    return false;
  });
  $(".employee-add-dialog-check").change(function() {
    var tr = $(this).parents("tr");
    if ($(this).is(":checked")) {
      tr.css("background-color", "beige");
    } else {
      tr.css("background-color", "");
    }
  });

  //グループ社員削除ボタン
  $('.employee-remove-button').click(function() {
    var tr = $(this).parents("tr");
    var number = tr.find("#num");
    var name = tr.find("#name");
    var employee = number.text() + ":" + name.text();
    $('#employee-remove-dialog-message').text(employee + "を削除しますか？");
    $('#del_emp_id').val($(this).attr('val_id'));
    $('#employee-remove-dialog').dialog('open');
  });
  $('#employee-remove-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#employee-remove-dialog-remove').click(function() {

      $.ajax({
          type: "POST",
          url: $("#form-group-employee-delete").attr('action'),
          data: {
        	  id: $('.active_group').attr('val_id'),
        	  employeeId: $("#del_emp_id").val()
          },
          success: function (data) {
        	  $('#employee-remove-dialog').dialog('close');
        	  $('#form-group-list').submit();
          },
          error: function () {
        	  $('#employee-remove-dialog').dialog('close');
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  $('#employee-remove-dialog-cancel').click(function() {
    $('#employee-remove-dialog').dialog('close');
    return false;
  });
  
  //グループ名変更
  $('#group-name-edit-button').click(function() {
	$('#upd_id').val($('.active_group').attr('val_id'));
	$('#upd_grp_name').val($('.active_group').html());
    $('#group-name-edit-dialog').dialog('open');
  });
  $('#group-name-edit-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#group-name-edit-dialog-update').click(function() {
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });
	  param['group.id'] = $('#upd_id').val();
	
	  $.ajax({
	      type: "POST",
	      url: $("#form-groups-update").attr('action'),
	      data: param,
	      success: function (data) {
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#group-name-edit-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $("#group-name-edit-dialog").dialog("close");
	        	  location.href = "groups-list";
	          }
	      },
	      error: function () {
	          $("#group-name-edit-dialog").dialog("close");
	    	  location.href = "/ams/admin/error";
	      }
	  });

	return false;
  });
  $('#group-name-edit-dialog-cancel').click(function() {
    $('#group-name-edit-dialog').dialog('close');
    return false;
  });
  
  //グループ名追加
  $('#group-add-button').click(function() {
    $('#group-add-dialog').dialog('open');
  });
  $('#group-add-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#group-add-dialog-add').click(function() {
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });
	
	  $.ajax({
	      type: "POST",
	      url: $("#form-groups-add").attr('action'),
	      data: param,
	      success: function (data) {
	    	  console.log(data);
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#group-add-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $('#group-add-dialog').dialog('close');
	        	  location.href = "groups-list";
	          }
	      },
	      error: function () {
	    	  $('#group-add-dialog').dialog('close');
	    	  location.href = "/ams/admin/error";
	      }
	  });

	return false;
  });
  $('#group-add-dialog-cancel').click(function() {
    $('#group-add-dialog').dialog('close');
    return false;
  });
  
  //グループ削除ボタン
  $('#group-remove-button').click(function() {
	$('#del_id').val($('.active_group').attr('val_id'));
	$("#group-remove-dialog-message").text($('.active_group').html() + "を削除しますか？");
    $('#group-remove-dialog').dialog('open');
  });
  $('#group-remove-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#group-remove-dialog-remove').click(function() {
      $.ajax({
          type: "POST",
          url: $("#form-groups-delete").attr('action'),
          data: {
        	  id: $("#del_id").val()
          },
          success: function (data) {
        	  $('#station-remove-dialog').dialog('close');
        	  location.href = "groups-list";
          },
          error: function () {
        	  $('#station-remove-dialog').dialog('close');
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  $('#group-remove-dialog-cancel').click(function() {
    $('#group-remove-dialog').dialog('close');
    return false;
  });
});
