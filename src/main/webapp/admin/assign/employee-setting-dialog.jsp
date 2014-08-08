<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

      <div class="container">
        <s:form id="assign-setting-dialog-form" action="employee-setting-update">
        <s:iterator value="employeeIds" var="id">
        <s:hidden name="employeeIds" value="%{id}"/>
        </s:iterator>
        <div class="row">
          <div class="col-sm-2">
            <label>顧客一覧</label>
          </div>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="customer-list-filter" placeholder="フィルター">
          </div>
          <div class="col-sm-2 col-sm-offset-2">
            <label>アクセス権一覧</label>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-5">
            <select class="form-control" id="customer-list" multiple size="20">
              <s:iterator value="customers" var="customer">
              <option value="<s:property value="id"/>"><s:property value="name"/></option>
              </s:iterator>
            </select>
          </div>
          <div class="col-sm-2 center-block">
            <button class="btn btn-sm btn-primary btn-block" id="customer-add">追加 ＞＞</button>
            <button class="btn btn-sm btn-warning btn-block" id="customer-remove">＜＜ 削除</button>
          </div>
          <div class="col-sm-5">
            <select class="form-control" name="customerIds" id="assign-list" multiple size="20">
              <s:iterator value="employee.customers" var="customer">
              <option value="<s:property value="id"/>"><s:property value="name"/></option>
              </s:iterator>
            </select>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-2 col-sm-offset-4">
            <button class="btn btn-sm btn-primary btn-block" id="assign-setting">設定</button>
          </div>
          <div class="col-sm-2">
            <button class="btn btn-sm btn-warning btn-block" id="assign-cancel">キャンセル</button>
          </div>
        </div> <!-- row -->
        </s:form>
      </div> <!-- container -->
