<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="dialog_content">
  <div class="col-sm-6 col-sm-offset-6">
    <input type="text" class="form-control" id="assign-list-dialog-filter" placeholder="フィルター(所属名、社員番号、氏名)">
  </div>
  <div class="dialog_input">
    <%@ include file="customer-employee-acl-input.jsp" %>
  </div>
  <div>
    <button class="btn btn-sm btn-primary" id="assign-list-dialog-setting-button">アクセス権設定</button>
    <button class="btn btn-sm btn-warning" id="assign-list-dialog-cancel-button">キャンセル</button>
  </div>
</div>
