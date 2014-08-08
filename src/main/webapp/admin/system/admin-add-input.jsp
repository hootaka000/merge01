<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-admin-add" action="admin-add">
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <label>社員番号を入力してください</label>
      <input type="text" class="form-control" name="employeeNumber" id="admin-add-dialog-employee-number" value="${requestScope.employeeNumber}">
    </div>
</s:form>
