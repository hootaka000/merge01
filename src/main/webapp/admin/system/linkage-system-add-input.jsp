<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-linkage-system-add" action="linkage-system-add">
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <table class="table">
        <tr><th>社員番号</th><td><input type="text" name="linkageNumber" class="form-control" id="linkage-system-add-dialog-number" value="${requestScope.linkageNumber}"></td></tr>
        <tr><th>システム名</th><td><input type="text" name="linkageName" class="form-control" id="linkage-system-add-dialog-name" value="${requestScope.linkageName}"></td></tr>
    </table>
</s:form>
