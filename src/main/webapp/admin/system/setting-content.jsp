<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <div class="container">

      <!-- system setting -->
      <div class="row">
        <!-- admin list -->
        <h2>管理者一覧</h2>
        <div class="col-sm-4">
          <div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block " id="admin-add-button">管理者追加</button>
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <table class="table" id="admin-list">
              <thead>
                <tr>
                  <th>社員番号</th>
                  <th>削除</th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="adminUsers" var="admin">
                <tr>
                  <td id="name"><s:property value="name"/></td>
                  <td><button class="btn btn-sm btn-primary btn-block admin-remove-button" data-user-id="${id}">削除</button></td>
                </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
        </div>
      </div> <!-- row -->
      <div class="row">
        <!-- admin list -->
        <h2>アクセスキー一覧</h2>
        <div class="col-sm-6">
          <div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block " id="accesskey-add-button">アクセスキーを発行する</button>
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <table class="table" id="admin-list">
              <thead>
                <tr>
                  <th>社員番号</th>
                  <th>システム名</th>
                  <th>アクセスキー</th>
                  <th>削除</th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="linkageSystems" var="system">
                <tr>
                  <td id="employee-name"><s:property value="user.name"/></td>
                  <td id="system-name"><s:property value="name"/></td>
                  <td id="accesskey"><s:property value="accessKey"/></td>
                  <td><button class="btn btn-sm btn-primary btn-block accesskey-remove-button" data-user-id="${id}">削除</button></td>
                </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
        </div>
      </div> <!-- row -->
    </div><!-- /.container -->
    <div id="admin-add-dialog" title="管理者追加" style="display:none;">
    <%@ include file="admin-add-input-container.jsp" %>
    </div>
    <div id="admin-remove-dialog" title="管理者削除" style="display:none;">
    <%@ include file="admin-delete-input-container.jsp" %>
    </div>
    <div id="accesskey-add-dialog" title="アクセスキー追加" style="display:none;">
    <%@ include file="linkage-system-add-input-container.jsp" %>
    </div>
    <div id="accesskey-remove-dialog" title="アクセスキー削除" style="display:none;">
    <%@ include file="linkage-system-delete-input-container.jsp" %>
    </div>
    <script src="/ams/js/system.js"></script>
