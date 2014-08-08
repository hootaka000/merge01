<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="belongs-remove-dialog-message"></div>
<s:form id="form-belongs-delete" action="belongs-delete"><input type="hidden" id="del_belongs_id" value=""></s:form>
<div>
      <button class="btn btn-sm btn-danger" id="belongs-remove-dialog-remove">削除</button>
      <button class="btn btn-sm btn-primary" id="belongs-remove-dialog-cancel">キャンセル</button>
</div>
