<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <link href="/ams/css/assign.css" rel="stylesheet">

    <div class="container">

      <!-- belongs list -->
      <div class="row">
        <div class="col-sm-3">
          <div class="container">
            <div class="row">
              <div class="col-sm-3">
                <div class="list-group">
                  <s:set var="belongs_id" value="belongsId"/>
                  <s:iterator value="belongsList" var="b"> 
                  <!--  TODO s:ifが正しく評価されていない -->
                  <s:if test="%{#b.id==#belongs_id}"><s:a cssClass="list-group-item active" href="/ams/admin/assign/setting?belongsId=%{id}>"><s:property value="name"/></s:a></s:if>
                  <s:else><s:a cssClass="list-group-item" href="/ams/admin/assign/setting?belongsId=%{id}"><s:property value="name"/></s:a></s:else>
                  </s:iterator>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <button class="btn btn-sm btn-primary btn-block " id="group-assign-button">グループのアクセス権設定</button>
              </div>
            </div>
          </div>
        </div>

        <!-- member list -->
        <div class="col-sm-9">
          <div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block bulk-assign-button">一括設定</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block" id="assign-export-button">エクスポート</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block" id="assign-import-button">インポート</button>
              </div>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="employee-filter" placeholder="フィルター（社員番号または氏名）">
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <table class="table" id="employee-list">
              <thead>
                <tr>
                  <th></th>
                  <th>社員番号</th>
                  <th>氏名</th>
                  <th>役職</th>
                  <th>メール</th>
                  <th>設定</th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="belongs.employees" var="employee">
                <tr>
                  <td><input type="checkbox" class="employee-list-check" data-employee-id="<s:property value="id"/>"></td>
                  <td id="num"><s:property value="number"/></td>
                  <td id="name"><s:property value="name"/></td>
                  <td id="grade"><s:if test="grade != null"><s:property value="grade.name"/></s:if></td>
                  <td><s:a href="mailto:%{email}"><s:property value="email"/></s:a></td>
                  <td><button class="btn btn-sm btn-primary btn-block assign-button" data-employee-id="<s:property value="id"/>">設定</button></td>
                </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
          <div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block bulk-assign-button">一括設定</button>
              </div>
            </div>
          </div>
        </div>
      </div> <!-- row -->
    </div><!-- /.container -->

    <div id="assign-dialog" title="アクセス権設定" style="display:none;">
    </div>
    <div id="group-assign-dialog" title="グループのアクセス権設定" style="display:none;">
    </div>

