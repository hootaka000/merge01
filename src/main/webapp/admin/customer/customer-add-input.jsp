<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="form-customer-add" action="customer-add">
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <table class="table">
        <tr><th>CAON ID</th><td><input type="text" name="customer.caonId" class="form-control" id="customer-add-dialog-caon-id" value="${requestScope.customer.caonId}"></td></tr>
        <tr><th>企業名</th><td><input type="text" name="customer.name" class="form-control" id="customer-add-dialog-customer-name" value="${requestScope.customer.name}"></td></tr>
        <tr><th>事業部名</th><td><input type="text" name="customer.devision" class="form-control" id="customer-add-dialog-division-name" value="${requestScope.customer.devision}"></td></tr>
        <tr><th>サービス名</th><td><input type="text" name="customer.service" class="form-control" id="customer-add-dialog-service-name" value="${requestScope.customer.service}"></td></tr>
    </table>
</s:form>
