<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-groups-update" action="groups-update">
    <div id="groups-update-dialog-message"></div>
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <label>グループ名を入力してください</label>
      <input type="text" class="form-control" name="group.name" id="upd_grp_name" value="${requestScope.group.name}">
    </div>
    <input type="hidden" name="group.id" id="upd_id" value="${requestScope.group.id}">
</s:form>
