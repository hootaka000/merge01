jQuery(function($) {

  //メニュー
  $('#menu_customers').parent().attr('class','active');
  
  //顧客追加ボタン
  $("#customer-add-button").click(function() {
	$('.alert-danger').remove();
    $("#customer-add-dialog").dialog('open');
  });
  
  //顧客追加->追加ボタン
  $("#customer-add-dialog-add-button").click(function() {
	  
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  var id = $(this).attr('name');
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });

      $.ajax({
          type: "POST",
          url: $("#form-customer-add").attr('action'),
          data: param,
          success: function (data) {
              if(data){
                  var ret = $.parseHTML(data);
                  $("#customer-add-dialog").find('.dialog_input').html(ret);
              }else{
            	  $("#customer-add-dialog").dialog("close");
            	  location.href = "customer-list";
              }
          },
          error: function () {
              $("#customer-add-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  
  //顧客追加->キャンセルボタン
  $("#customer-add-dialog-cancel-button").click(function() {
    $("#customer-add-dialog").dialog("close");
    return false;
  });
  
  //顧客追加ダイアログ
  $("#customer-add-dialog").dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  
  //インポートボタン
  $('#customer-import-button').click(function() {
	$('.alert-danger').remove();
    $('#customer-import-dialog').dialog('open');
  });
  
  //インポートダイアログ
  $('#customer-import-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  
  //インポート->アップロードボタン
  $('#customer-import-upload').click(function() {

    var form = $('#form-customer-import').get(0);
    var formData = new FormData(form);
  
    $.ajax({
        type: "POST",
        url: $("#form-customer-import").attr('action'),
        contentType: false,
        processData: false,
        data: formData,
        success: function (data) {
            if(data){
                var ret = $.parseHTML(data);
                $("#customer-import-dialog").find('.dialog_input').html(ret);
            }else{
          	  $("#customer-import-dialog").dialog("close");
          	  location.href = "customer-list";
            }
        },
        error: function () {
            $("#customer-import-dialog").dialog("close");
      	    location.href = "/ams/admin/error";
        }
    });
    return false;
  });
  
  //インポート->キャンセルボタン
  $('#customer-import-cancel').click(function() {
    $('#customer-import-dialog').dialog('close');
    return false;
  });
  
  //エクスポートボタン
  $('#customer-export-button').click(function() {
	  $("#form-customer-export").submit();
	  return false;
  });
  
  //更新ボタン
  $(".customer-update-button").click(function() {
	$("#upd_id").val($(this).attr("val_id"));
    var tr = $(this).parents("tr");
    var customer = tr.find("#name");
    var division = tr.find("#division");
    var service = tr.find("#service");
    var name = customer.text();
    if (division.text()) {
      name += " " + division.text();
    }
    if (service.text()) {
      name += " " + service.text();
    }
    $("#customer-update-dialog-message").text(name + "を更新しますか？");
	$('.alert-danger').remove();
    $("#customer-update-dialog").dialog('open');
  });
  
  //更新->更新ボタン
  $("#customer-update-dialog-update-button").click(function() {
	  
	  var param = {};
	  $('.dialog_input .form-control').each(function(){
		  if($(this).val()){
			  param[$(this).attr('name')] = $(this).val();
		  }
	  });
	  param['customer.id'] = $('#upd_id').val();

      $.ajax({
          type: "POST",
          url: $("#form-customer-update").attr('action'),
          data: param,
          success: function (data) {
              if(data){
                  var ret = $.parseHTML(data);
                  $("#customer-update-dialog").find('.dialog_input').html(ret);
              }else{
            	  $("#customer-update-dialog").dialog("close");
            	  location.href = "customer-list";
              }
          },
          error: function () {
              $("#customer-update-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  
  //更新->キャンセルボタン
  $("#customer-update-dialog-cancel-button").click(function() {
    $("#customer-update-dialog").dialog("close");
    return false;
  });
  
  //更新ダイアログ
  $("#customer-update-dialog").dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  
  //削除ボタン
  $(".customer-remove-button").click(function() {
	$("#del_id").val($(this).attr("val_id"));
    var tr = $(this).parents("tr");
    var customer = tr.find("#name");
    var division = tr.find("#division");
    var service = tr.find("#service");
    var name = customer.text();
    if (division.text()) {
      name += " " + division.text();
    }
    if (service.text()) {
      name += " " + service.text();
    }
    $("#customer-remove-dialog-message").text(name + "を削除しますか？");
	$('.alert-danger').remove();
    $("#customer-remove-dialog").dialog('open');
  });
  
  //削除->削除ボタン
  $("#customer-remove-dialog-remove-button").click(function() {

      $.ajax({
          type: "POST",
          url: $("#form-customer-delete").attr('action'),
          data: {
        	  id: $("#del_id").val()
          },
          success: function (data) {
        	  $("#customer-remove-dialog").dialog("close");
        	  location.href = "customer-list";
          },
          error: function () {
              $("#customer-remove-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  
  //削除->キャンセルボタン
  $("#customer-remove-dialog-cancel-button").click(function() {
    $("#customer-remove-dialog").dialog("close");
  });
  
  //削除ダイアログ
  $("#customer-remove-dialog").dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });

  //社員リンク
  $(".assign-num").click(function() {
      $("#emp_acl_id").val($(this).attr("val_id"));

      $.ajax({
          type: "POST",
          url: $("#form-customer-employee-acl").attr('action'),
          async: true,
          data: {
        	  customerId: $("#emp_acl_id").val()
          },
          success: function (data) {
              if(data){
                  var ret = $.parseHTML(data);
                  $("#assign-list-dialog").find('.dialog_input').html(ret);
                  $("#assign-list-dialog").dialog("open");
            	  coloring_table('.assign-list-dialog-check');
              }
          },
          error: function () {
              $("#assign-list-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
	  
  });
  
  //社員リンク->アクセス権設定ボタン
  $("#assign-list-dialog-setting-button").click(function() {

	  var param = {};
	  $('.assign-list-dialog-check:checked').each(function(){
		  param[$(this).attr('name')] = $(this).val();
	  });
	  param['customerId'] = $('#emp_acl_id').val();
	  
      $.ajax({
          type: "POST",
          url: $("#customer-employee-acl-update").attr('action'),
          data: param,
          success: function (data) {
        	  $("#assign-list-dialog").dialog("close");
        	  location.href = "customer-list";
          },
          error: function () {
        	  $("#assign-list-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
	  
  });
  
  //社員リンク->キャンセルボタン
  $("#assign-list-dialog-cancel-button").click(function() {
    $("#assign-list-dialog").dialog("close");
  });
  
  //社員リンクダイアログ->チェック
  $("#assign-list-dialog").on('change', '.assign-list-dialog-check', function() {
	  coloring_table('.assign-list-dialog-check');
  });

  //チェックレコード着色
  var coloring_table = function(target) {
    $(target).each(function(){
        var tr = $(this).parents("tr");
        if ($(this).is(":checked")) {
          tr.css("background-color", "beige");
        } else {
          tr.css("background-color", "");
        }   	
    });
  }
  
  //社員リンクダイアログ
  $("#assign-list-dialog").dialog({
    autoOpen: false,
    resizable: true,
    width: "800px",
    height: "600",
    modal: true
  });
  
  //グループリンク
  $(".group-num").click(function() {
      $("#grp_acl_id").val($(this).attr("val_id"));

      $.ajax({
          type: "POST",
          url: $("#form-customer-group-acl").attr('action'),
          async: true,
          data: {
        	  customerId: $("#grp_acl_id").val()
          },
          success: function (data) {
              if(data){
                  var ret = $.parseHTML(data);
                  $("#assign-group-dialog").find('.dialog_input').html(ret);
                  $("#assign-group-dialog").dialog("open");
            	  coloring_table('.assign-group-dialog-check');
              }
          },
          error: function () {
              $("#assign-group-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
  });
  
  //グループリンク->アクセス権設定ボタン
  $("#assign-group-dialog-setting-button").click(function() {
 	  var param = {};
	  $('.assign-group-dialog-check:checked').each(function(){
		  param[$(this).attr('name')] = $(this).val();
	  });
	  param['customerId'] = $('#grp_acl_id').val();
	  
      $.ajax({
          type: "POST",
          url: $("#customer-group-acl-update").attr('action'),
          data: param,
          success: function (data) {
        	  $("#assign-group-dialog").dialog("close");
        	  location.href = "customer-list";
          },
          error: function () {
        	  $("#assign-group-dialog").dialog("close");
        	  location.href = "/ams/admin/error";
          }
      });
      return false;
  });
  
  //グループリンク->キャンセルボタン
  $("#assign-group-dialog-cancel-button").click(function() {
    $("#assign-group-dialog").dialog("close");
  });
  
  //グループリンクダイアログ->チェック
  $("#assign-group-dialog").on('change', '.assign-group-dialog-check', function() {
	  coloring_table('.assign-group-dialog-check');
  });
  
  //グループリンクダイアログ
  $("#assign-group-dialog").dialog({
    autoOpen: false,
    resizable: true,
    width: "800px",
    height: "600",
    modal: true
  });
  
  //前ページ
  $('.prev').click(function(){
	  $('#currentNumber').val(Number($('#current_page').html()) - 1);
	  $("#form-customer-list").submit();
  });
  
  //次ページ
  $('.next').click(function(){
	  $('#currentNumber').val(Number($('#current_page').html()) + 1);
	  $("#form-customer-list").submit();
  });
  
  //フィルター
  $("#customer-filter").bind("change keyup", function(e) {
    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
  	  $('#currentNumber').val(Number($('#current_page').html()));
	  $("#form-customer-list").submit();
	}
  });
  
  $("#assign-list-dialog-filter").bind("change keyup", function() {
    var filter = $(this);
    var trs = $('#assign-list-dialog-employee-list > tbody > tr');
    var search = $.trim(filter.val());
    var regex = new RegExp(search, "gi");
    jQuery.each(trs, function(i, tr) {
      var belongs = $(tr).find("#belongs");
      var number = $(tr).find("#number");
      var name = $(tr).find("#name");
      if (belongs.text().match(regex) != null) {
        $(tr).show();
        return;
      }
      if (number.text().match(regex) != null) {
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
  
  $("#assign-list-dialog-allcheck").change(function() {
    var trs = $("#assign-list-dialog-employee-list > tbody > tr")
    var check = $(this).is(":checked");
    jQuery.each(trs, function(i, tr) {
      var checkbox = $(tr).find(".assign-list-dialog-check");
      if (checkbox.is(":visible")) {
        checkbox.prop("checked", check);
        if (check) {
          $(tr).css("background-color", "beige");
        } else {
          $(tr).css("background-color", "");
        }
      }
    });
  });
});
