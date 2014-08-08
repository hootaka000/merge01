<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-belongs-add" action="belongs-add">
    <div id="belongs-add-dialog-message"></div>
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <label>所属名を入力してください</label>
      <input type="text" class="form-control" name="belong.name" id="upd_belong_name" value="${requestScope.belong.name}">
    </div>
</s:form>
