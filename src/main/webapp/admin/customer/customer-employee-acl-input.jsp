<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="customer-employee-acl-update" action="customer-employee-acl-update">
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <table class="table" id="assign-list-dialog-employee-list">
        <thead>
          <tr><th></th><th>社員番号</th><th>社員名</th><th>メール</th></tr>
        </thead>
        <tbody>
          <s:iterator value="allEmployees" status="st" var="employee">
              <tr><td><input type="checkbox" name="selectEmployeesId[${st.index}]" value="${id}" 
                  id="emp_check_${id}" class="assign-list-dialog-check" 
                        <s:iterator value="customer.employees" status="st2" var="checked">
                            <s:if test="%{#employee.id==#checked.id}">checked=checked</s:if>
                        </s:iterator>
                  ></td>
                  <td id="number">${number}</td><td id="name">${name}</td><td>${email}</td></tr>
          </s:iterator>
        </tbody>
    </table>
    <input type="hidden" id="emp_acl_id" value="${requestScope.customerId}">
</s:form>
<s:form id="form-customer-employee-acl" action="customer-employee-acl">
</s:form>
