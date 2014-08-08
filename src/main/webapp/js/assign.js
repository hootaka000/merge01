jQuery.fn.filterByText = function(textbox) {
  return this.each(function() {
    var select = this;
    var options = [];
    $(select).find('option').each(function(){
      options.push($(this).clone());
    });
    $(select).data('options', options);

    $(textbox).bind('change keyup', function() {
      var options = $(select).empty().data('options');
      var search = $.trim($(this).val());
      var regex = new RegExp(search, "gi");

      $.each(options, function(i) {
        var option = options[i];
        if (option.text().match(regex) != null) {
          $(select).append(option.clone());
        }
      });
    });
  });
};
jQuery(function($) {

  //メニュー
  $('#menu_assign').parent().attr('class','active');

  $(".employee-list-check").change(function() {
    var tr = $(this).parents("tr");
    if ($(this).is(":checked")) {
      tr.css("background-color", "beige");
    } else {
      tr.css("background-color", "");
    }
  });
  $('.bulk-assign-button').click(function() {
	var ids = [];
	$(".employee-list-check").each(function() {
		if ($(this).is(":checked")) {
			ids.push($(this).attr('data-employee-id'));
		}
	});
	if (ids.length == 0) {
		return false;
	}
	$.ajax({
		type: "post",
		url: "/ams/admin/assign/employee-setting-dialog",
		async: true,
		traditional: true,
		data: {
			employeeIds: ids
		},
		success: function(data) {
			if(data) {
				$('#assign-dialog').html($.parseHTML(data));
				$('#customer-list').filterByText($('#customer-list-filter'));
				$('#assign-dialog').dialog('open');
			}
		},
		error: function() {
			location.href = "/ams/admin/error";
		}
	})
  });
  $('.assign-button').click(function() {
	var id = [];
	id.push($(this).attr('data-employee-id'));
	$.ajax({
		type: "post",
		url: "/ams/admin/assign/employee-setting-dialog",
		async: true,
		traditional: true,
		data: {
			employeeIds: id
		},
		success: function(data) {
			if(data) {
				$('#assign-dialog').html($.parseHTML(data));
				$('#customer-list').filterByText($('#customer-list-filter'));
				$('#assign-dialog').dialog('open');
			}
		},
		error: function() {
			location.href = "/ams/admin/error";
		}
	})
  });
  $('#assign-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#group-assign-button').on("click", function() {
	$.ajax({
		type: "post",
		url: "/ams/admin/assign/group-setting-dialog",
		async: true,
		traditional: true,
		success: function(data) {
			if(data) {
				$('#group-assign-dialog').html($.parseHTML(data));
				$('#group-assign-dialog-customer-list').filterByText($('#group-assign-dialog-customer-list-filter'));
				$('#group-assign-dialog').dialog('open');
			}
		},
		error: function() {
			location.href = "/ams/admin/error";
		}
	})
  });
  $('#group-assign-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#assign-import-button').click(function() {
    $('#assign-import-dialog').dialog('open');
  });
  $('#assign-import-dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: "auto",
    height: "auto",
    modal: true
  });
  $('#assign-import-upload').click(function() {
    $('#assign-import-dialog').dialog('close');
    return false;
  });
  $('#assign-import-cancel').click(function() {
    $('#assign-import-dialog').dialog('close');
    return false;
  });
  $('#employee-filter').bind('change keyup', function(){
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
  $('#assign-dialog').on('click', '#customer-add', function() {
    var options = $('#customer-list option:selected');
    var list = $('#customer-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#assign-list').append($(option).clone());
      jQuery.each(list, function(i, data) {
        if ($(data).text() == $(option).text()) {
          list.splice(i, 1);
        }
      });
    });
    $('#customer-list').data('options', list);
    $('#customer-list option:selected').remove();
    return false;
  });
  $('#assign-dialog').on('click', '#customer-remove', function() {
    var options = $('#assign-list option:selected');
    var list = $('#customer-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#customer-list').append($(option).clone());
      list.push($(option).clone());
    });
    $('#customer-list').data('options', list);
    $('#assign-list option:selected').remove();
    return false;
  });
  $('#assign-dialog').on('click', '#assign-setting', function() {
	$('#assign-list option').each(function() {
		console.log($(this));
		$(this).attr('selected', true);
	});
    $('#assign-setting-dialog-form').submit();
  });
  $('#assign-dialog').on('click', '#assign-cancel', function() {
    $('#assign-dialog').dialog('close');
    return false;
  });
  $('#group-assign-dialog').on('click', '#group-assign-dialog-customer-add', function() {
    var options = $('#group-assign-dialog-customer-list option:selected');
    var list = $('#group-assign-dialog-customer-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#group-assign-dialog-assign-list').append($(option).clone());
      jQuery.each(list, function(i, data) {
        if ($(data).text() == $(option).text()) {
          list.splice(i, 1);
        }
      });
    });
    $('#group-assign-dialog-customer-list').data('options', list);
    $('#group-assign-dialog-customer-list option:selected').remove();
    return false;
  });
  $('#group-assign-dialog').on('click', '#group-assign-dialog-customer-remove', function() {
    var options = $('#group-assign-dialog-assign-list option:selected');
    var list = $('#group-assign-dialog-customer-list').data('options');
    jQuery.each(options, function(i, option) {
      $('#group-assign-dialog-customer-list').append($(option).clone());
      list.push($(option).clone());
    });
    $('#group-assign-dialog-customer-list').data('options', list);
    $('#group-assign-dialog-assign-list option:selected').remove();
    return false;
  });
  $('#group-assign-dialog').on('click', '#group-assign-dialog-setting', function() {
	$('#group-assign-dialog-assign-list option').each(function() {
		console.log($(this));
		$(this).attr('selected', true);
	});
    $('#group-assign-dialog-form').submit();
  });
  $('#group-assign-dialog').on('click', '#group-assign-dialog-cancel', function() {
    $('#group-assign-dialog').dialog('close');
  });
  $('#group-assign-dialog').on('change', '#group-assign-dialog-group-select', function() {
	var id = $(this).val();
	$.ajax({
		type: "post",
		url: "/ams/admin/assign/group-setting-dialog",
		async: true,
		traditional: true,
		data: {
			groupId: id
		},
		success: function(data) {
			if(data) {
				$('#group-assign-dialog').html($.parseHTML(data));
				$('#group-assign-dialog-customer-list').filterByText($('#group-assign-dialog-customer-list-filter'));
			}
		},
		error: function() {
			location.href = "/ams/admin/error";
		}
	})
  });
});
