<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="group-remove-dialog-message"></div>
<s:form id="form-groups-delete" action="groups-delete"><input type="hidden" id="del_id" value=""></s:form>
<div>
      <button class="btn btn-sm btn-danger" id="group-remove-dialog-remove">削除</button>
      <button class="btn btn-sm btn-primary" id="group-remove-dialog-cancel">キャンセル</button>
</div>
