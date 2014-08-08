<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-customer-update" action="customer-update">
    <div id="customer-update-dialog-message"></div>
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <table class="table">
        <tr><th>CAON ID</th><td><input type="text" name="customer.caonId" class="form-control" id="customer-add-dialog-caon-id" value="${requestScope.customer.caonId}"></td></tr>
    </table>
    <input type="hidden" id="upd_id" value="${customer.id}">
</s:form>
