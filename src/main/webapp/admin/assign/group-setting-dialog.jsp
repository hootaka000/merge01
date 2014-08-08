<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

      <div class="container">
        <form id="infosys.cyberagent.local" action="group-setting-update">
        <div class="row">
          <div class="col-sm-5">
            <label>アクセス権を設定するグループ</label>
            <select class="form-control" id="group-assign-dialog-group-select" name="groupId">
              <s:set var="group_id" value="groupId"/>
              <s:iterator value="groups" var="g">
              <option value="<s:property value="id"/>"<s:if test="%{#g.id==#group_id}"> selected</s:if>><s:property value="name"/></option>
              </s:iterator>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-2">
            <label>顧客一覧</label>
          </div>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="group-assign-dialog-customer-list-filter" placeholder="フィルター">
          </div>
          <div class="col-sm-2 col-sm-offset-2">
            <label>アクセス権一覧</label>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-5">
            <select class="form-control" id="group-assign-dialog-customer-list" multiple size="20">
              <s:iterator value="customers" var="c">
              <option value="<s:property value="id"/>"><s:property value="name"/></option>
              </s:iterator>
            </select>
          </div>
          <div class="col-sm-2 center-block">
            <button class="btn btn-sm btn-primary btn-block" id="group-assign-dialog-customer-add">追加 ＞＞</button>
            <button class="btn btn-sm btn-warning btn-block" id="group-assign-dialog-customer-remove">＜＜ 削除</button>
          </div>
          <div class="col-sm-5">
            <select class="form-control" id="group-assign-dialog-assign-list" name="customerIds" multiple size="20">
              <s:iterator value="group.customers" var="c">
              <option value="<s:property value="id"/>"><s:property value="name"/></option>
              </s:iterator>
            </select>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-2 col-sm-offset-4">
            <button class="btn btn-sm btn-danger btn-block" id="group-assign-dialog-setting">アクセス権設定</button>
          </div>
          <div class="col-sm-2">
            <button class="btn btn-sm btn-primary btn-block" id="group-assign-dialog-cancel">キャンセル</button>
          </div>
        </div> <!-- row -->
        </form>
      </div> <!-- container -->
