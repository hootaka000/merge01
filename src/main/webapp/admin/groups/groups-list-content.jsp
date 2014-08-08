<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <div class="container">
      <!-- group list -->
      <div class="row">
        <div class="col-sm-3">
          <div class="container">
            <div class="row">
              <div class="col-sm-3">
                <div class="list-group">
                <s:set var="req_id" value="id" />
                <s:iterator value="groups" status="stts" var="checked">
                  <a class="list-group-item <s:if test="%{#req_id==#checked.id}">active active_group</s:if> "
                  		val_id="${id}" group_id="${groupId}">${name}</a>
                </s:iterator>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block " id="group-name-edit-button">グループ名変更</button>
                <button class="btn btn-sm btn-primary btn-block " id="group-add-button">グループ追加</button>
                <button class="btn btn-sm btn-primary btn-block " id="group-remove-button">グループ削除</button>
                <s:form id="form-group-list" action="groups-list">
                    <input type="hidden" name="id" id="group_model_id" value="${req_id}">
                </s:form>
              </div>
            </div>
          </div>
        </div>

        <!-- member list -->
        <div class="col-sm-9">
          <div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block " id="employee-add-button">社員追加</button>
              </div>
              <div class="col-sm-2">
              </div>
              <div class="col-sm-2">
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
                  <th>社員番号</th>
                  <th>氏名</th>
                  <th>役職</th>
                  <th>メール</th>
                  <th>削除</th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="employees" status="stts">
                <tr>
                  <td id="num">${number}</td>
                  <td id="name">${name}</td>
                  <td id="grade">${grade.name}</td>
                  <td><a href="mailto:yamada_taro@cyberagent.local">${email}</a></td>
                  <td><button class="btn btn-sm btn-primary btn-block employee-remove-button" val_id="${id}">削除</button></td>
                </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
        </div>
      </div> <!-- row -->
    </div><!-- /.container -->


    <!-- employee add dialog -->
    <div id="employee-add-dialog" title="社員追加" style="display:none;">
	<%@ include file="groups-employee-add-input-container.jsp" %>
    </div>

    <!-- employee remove dialog -->
    <div id="employee-remove-dialog" title="社員削除" style="display:none;">
    <%@ include file="groups-employee-delete-input-container.jsp" %>
    </div>
    
    <!-- group name edit dialog -->
    <div id="group-name-edit-dialog" title="グループ名変更" style="display:none;">
	<%@ include file="groups-update-input-container.jsp" %>
    </div>

    <!-- group add dialog -->
    <div id="group-add-dialog" title="グループ追加" style="display:none;">
	<%@ include file="groups-add-input-container.jsp" %>
    </div>
    
    <!-- group remove dialog -->
    <div id="group-remove-dialog" title="グループ削除" style="display:none;">
	<%@ include file="groups-delete-input-container.jsp" %>
    </div>

