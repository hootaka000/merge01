
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
  $('#menu_belongs').parent().attr('class','active');

  $('.list-group-item').click(function(){
	  $('#belongs_model_id').val($(this).attr('val_id'));
	  $('#form-belongs-list').submit();
  });
  
  //所属社員追加ボタン
  $('#employee-add-button').click(function() {
      $("#belongs_emp_add_id").val($('.active_belongs').attr('val_id'));

      $.ajax({
          type: "POST",
          url: $("#form-belongs-employee-add").attr('action'),
          async: true,
          data: {
        	  belongId: $("#belongs_emp_add_id").val()
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
	  param['belongId'] = $('#belongs_emp_add_id').val();
	  
      $.ajax({
          type: "POST",
          url: $("#belongs-employee-add-update").attr('action'),
          data: param,
          success: function (data) {
        	  $('#employee-add-dialog').dialog('close');
        	  $('#form-belongs-list').submit();
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
  
  //所属社員削除ボタン
  $('.employee-remove-button').click(function() {
    var tr = $(this).parents("tr");
    var number = tr.find("#num");
    var name = tr.find("#name");
    var employee = number.text() + ":" + name.text();
    $('#employee-remove-dialog-message').text(employee + "を削除しますか？");
    $('#del_belongs_emp_id').val($(this).attr('val_id'));
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
          url: $("#form-belongs-employee-delete").attr('action'),
          data: {
        	  id: $('.active_belongs').attr('val_id'),
        	  employeeId: $("#del_belongs_emp_id").val()
          },
          success: function (data) {
        	  $('#employee-remove-dialog').dialog('close');
        	  $('#form-belongs-list').submit();
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
  
  //所属名変更
  $('#belongs-name-edit-button').click(function() {
	$('#upd_belong_id').val($('.active_belongs').attr('val_id'));
	$('#upd_belong_name').val($('.active_belongs').html());
    $('#belongs-name-edit-dialog').dialog('open');
  });
  $('#belongs-name-edit-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#belongs-name-edit-dialog-update').click(function() {
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });
	  param['belong.id'] = $('#upd_belong_id').val();
	
	  $.ajax({
	      type: "POST",
	      url: $("#form-belongs-update").attr('action'),
	      data: param,
	      success: function (data) {
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#belongs-name-edit-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $("#belongs-name-edit-dialog").dialog("close");
	        	  location.href = "belongs-list";
	          }
	      },
	      error: function () {
	    	  $('#belongs-name-edit-dialog').dialog('close');
	    	  location.href = "/ams/admin/error";
	      }
	  });

    return false;
  });
  $('#belongs-name-edit-dialog-cancel').click(function() {
    $('#belongs-name-edit-dialog').dialog('close');
    return false;
  });
  
  //所属名追加ボタン
  $('#belongs-add-button').click(function() {
    $('#belongs-add-dialog').dialog('open');
  });
  $('#belongs-add-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#belongs-add-dialog-add').click(function() {

	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });
	
	  $.ajax({
	      type: "POST",
	      url: $("#form-belongs-add").attr('action'),
	      data: param,
	      success: function (data) {
	    	  console.log(data);
	          if(data){
	              var ret = $.parseHTML(data);
	              $("#belongs-add-dialog").find('.dialog_input').html(ret);
	          }else{
	        	  $('#belongs-add-dialog').dialog('close');
	        	  location.href = "belongs-list";
	          }
	      },
	      error: function () {
	    	  $('#belongs-add-dialog').dialog('close');
	    	  location.href = "/ams/admin/error";
	      }
	  });

	return false;
  });
  $('#belongs-add-dialog-cancel').click(function() {
    $('#belongs-add-dialog').dialog('close');
    return false;
  });
  
  //所属名削除ボタン
  $('#belongs-remove-button').click(function() {
	$('#del_belongs_id').val($('.active_belongs').attr('val_id'));
	$("#belongs-remove-dialog-message").text($('.active_belongs').html() + "を削除しますか？");
    $('#belongs-remove-dialog').dialog('open');
  });
  $('#belongs-remove-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#belongs-remove-dialog-remove').click(function() {
      $.ajax({
          type: "POST",
          url: $("#form-belongs-delete").attr('action'),
          data: {
        	  id: $("#del_belongs_id").val()
          },
          success: function (data) {
        	  $('#belongs-remove-dialog').dialog('close');
        	  location.href = "belongs-list";
          },
          error: function () {
        	  $('#belongs-remove-dialog').dialog('close');
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  $('#belongs-remove-dialog-cancel').click(function() {
    $('#belongs-remove-dialog').dialog('close');
    return false;
  });
  $('#belongs-integrate-button').click(function() {
    $('#belongs-integrate-dialog').dialog('open');
  });
  $('#belongs-integrate-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#belongs-integrate-dialog-integrate').click(function() {
    $('#belongs-integrate-dialog').dialog('close');
    return false;
  });
  $('#belongs-integrate-dialog-cancel').click(function() {
    $('#belongs-integrate-dialog').dialog('close');
    return false;
  });
  $('#belongs-integrate-dialog-base').change(function() {
    var base = $(this).val();
    var tds = $("#belongs-integrate-dialog-list").find("td");
    jQuery.each(tds, function(i, td) {
      var check = $(td).find("input");
      if (base == $(check).val()) {
        $(check).prop("checked", true);
        $(check).prop("disabled", true);
      } else {
        $(check).prop("disabled", false);
        $(check).prop("checked", false);
      }
    });
  });
  $(".belongs-integrate-dialog-check").change(function() {
    var tr = $(this).parents("tr");
    if ($(this).is(":checked")) {
      tr.css("background-color", "beige");
    } else {
      tr.css("background-color", "");
    }
  });
  $('#belongs-separate-button').click(function() {
    $('#belongs-separate-dialog').dialog('open');
  });
  $('#belongs-separate-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#belongs-separate-dialog-separate').click(function() {
    $('#belongs-separate-dialog').dialog('close');
    return false;
  });
  $('#belongs-separate-dialog-cancel').click(function() {
    $('#belongs-separate-dialog').dialog('close');
    return false;
  });
  $('#belongs-separate-dialog-employee-list').filterByText($('#belongs-separate-dialog-employee-list-filter'));
  $('#belongs-separate-dialog-employee-add').click(function() {
    var options = $('#belongs-separate-dialog-employee-list option:selected');
    var list = $('#belongs-separate-dialog-employee-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#belongs-separate-dialog-new-employee-list').append($(option).clone());
      jQuery.each(list, function(i, data) {
        if ($(data).text() == $(option).text()) {
          list.splice(i, 1);
        }
      });
    });
    $('#belongs-separate-dialog-employee-list').data('options', list);
    $('#belongs-separate-dialog-employee-list option:selected').remove();
  });
  $('#belongs-separate-dialog-employee-remove').click(function() {
    var options = $('#belongs-separate-dialog-new-employee-list option:selected');
    var list = $('#belongs-separate-dialog-employee-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#belongs-separate-dialog-employee-list').append($(option).clone());
      list.push($(option).clone());
    });
    $('#belongs-separate-dialog-employee-list').data('options', list);
    $('#belongs-separate-dialog-new-employee-list option:selected').remove();
  });
});
