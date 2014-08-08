<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="customer-remove-dialog-message"></div>
<s:form id="form-customer-delete" action="customer-delete"><input type="hidden" id="del_id" value=""></s:form>
<div>
    <button class="btn btn-sm btn-danger" id="customer-remove-dialog-remove-button">削除する</button>
    <button class="btn btn-sm btn-primary" id="customer-remove-dialog-cancel-button">キャンセル</button>
</div>
