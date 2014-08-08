<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-belongs-update" action="belongs-update">
    <div id="belongs-update-dialog-message"></div>
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <label>所属名を入力してください</label>
      <input type="text" class="form-control" name="belong.name" id="upd_belong_name" value="${requestScope.belong.name}">
    </div>
    <input type="hidden" name="belong.id" id="upd_belong_id" value="${requestScope.belong.id}">
</s:form>
