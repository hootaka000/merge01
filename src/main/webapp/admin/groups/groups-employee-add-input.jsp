<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="groups-employee-add-update" action="groups-employee-add-update">
	<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
    <div class="form-group">
      <table class="table">
        <thead>
          <tr><th></th><th>社員番号</th><th>氏名</th></tr>
        </thead>
        <tbody>
          <s:iterator value="allEmployees" status="st" var="employee">
              <tr><td><input type="checkbox" name="selectEmployeesId[${st.index}]" value="${id}" 
                  id="emp_check_${id}" class="employee-add-dialog-check"></td>
              <td id="number">${number}</td><td id="name">${name}</td></tr>
          </s:iterator>
        </tbody>
    </table>
    <input type="hidden" id="grp_emp_add_id" value="${requestScope.groupId}">
</s:form>
<s:form id="form-groups-employee-add" action="groups-employee-add">
</s:form>

