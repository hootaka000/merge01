<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:if test="hasFieldErrors() || hasActionErrors()"><div class="alert alert-danger"><s:fielderror /><s:actionerror/></div></s:if>
<s:iterator value="loadErrors" status="st">
<ul class="errorMessage"><li><span>${location}行目：</span><span>${detail}</span></li></ul>
</s:iterator>
<s:form id="form-customer-import" action="customer-import" method="post" enctype="multipart/form-data">
	<div class="form-group">
	  <label>インポートするファイルを選択してください。</label>
	  <input type="file" class="form-control" name="csv" id="customer-import-dialog-file">
	</div>
</s:form>
