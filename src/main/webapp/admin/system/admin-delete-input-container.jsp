<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="admin-remove-dialog-message"></div>
<s:form id="form-admin-delete" action="admin-delete"><input type="hidden" id="del_id" value=""></s:form>
<div>
      <button class="btn btn-sm btn-danger" id="admin-remove-dialog-remove">削除</button>
      <button class="btn btn-sm btn-primary" id="admin-remove-dialog-cancel">キャンセル</button>
</div>
