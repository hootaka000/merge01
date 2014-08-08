<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div id="employee-remove-dialog-message"></div>
<s:form id="form-belongs-employee-delete" action="belongs-employee-delete"><input type="hidden" id="del_belongs_emp_id" value=""></s:form>
<div>
	<button class="btn btn-sm btn-danger" id="employee-remove-dialog-remove">削除する</button>
	<button class="btn btn-sm btn-primary" id="employee-remove-dialog-cancel">キャンセル</button>
</div>
