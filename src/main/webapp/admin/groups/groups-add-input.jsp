<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-groups-add" action="groups-add">
    <div id="groups-add-dialog-message"></div>
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <label>グループ名を入力してください</label>
      <input type="text" class="form-control" name="group.name" id="upd_grp_name" value="${requestScope.group.name}">
    </div>
</s:form>
