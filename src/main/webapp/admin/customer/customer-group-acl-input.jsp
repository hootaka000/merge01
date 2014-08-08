<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="customer-group-acl-update" action="customer-group-acl-update">
<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
  <table class="table">
    <thead>
      <tr><th></th><th>グループ番号</th><th>グループ名</th></tr>
    </thead>
    <tbody>

  <s:iterator value="allGroups" status="st" var="group">
      <tr><td><input type="checkbox" name="selectGroupsId[${st.index}]" value="${id}" 
          id="grp_check_${id}" class="assign-group-dialog-check" 
                <s:iterator value="customer.groups" status="st2" var="checked">
                    <s:if test="%{#group.id==#checked.id}">checked=checked</s:if>
                </s:iterator>
          ></td>
          <td>${id}</td><td>${name}</td></tr>
  </s:iterator>

    </tbody>
  </table>
<input type="hidden" id="grp_acl_id" value="${requestScope.customerId}">
</s:form>
<s:form id="form-customer-group-acl" action="customer-group-acl">
</s:form>

