<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="dialog_content">
      <div class="col-sm-6 col-sm-offset-6">
        <input type="text" class="form-control" id="assign-group-dialog-filter" placeholder="フィルター(グループ名)">
      </div>
	  <div class="dialog_input">
    	<%@ include file="customer-group-acl-input.jsp" %>
	  </div>
      <div>
    <button class="btn btn-sm btn-primary" id="assign-group-dialog-setting-button">アクセス権設定</button>
    <button class="btn btn-sm btn-warning" id="assign-group-dialog-cancel-button">キャンセル</button>
  </div>
</div>

