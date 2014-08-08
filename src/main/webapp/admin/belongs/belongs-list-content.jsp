<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <div class="container">
      <!-- belongs list -->
      <div class="row">
        <div class="col-sm-3">
          <div class="container">
            <div class="row">
              <div class="col-sm-3">
                <div class="list-group">
                <s:set var="req_id" value="id" />
                <s:iterator value="belongs" status="stts" var="checked">
                  <a class="list-group-item <s:if test="%{#req_id==#checked.id}">active active_belongs</s:if> "
                  		val_id="${id}" belongs_id="${belongsId}">${name}</a>
                </s:iterator>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block " id="belongs-name-edit-button">所属名変更</button>
                <button class="btn btn-sm btn-primary btn-block " id="belongs-add-button">所属追加</button>
                <button class="btn btn-sm btn-primary btn-block " id="belongs-remove-button">所属削除</button>
                <button class="btn btn-sm btn-primary btn-block " id="belongs-integrate-button">所属統合</button>
                <button class="btn btn-sm btn-primary btn-block " id="belongs-separate-button">所属分離</button>
                <s:form id="form-belongs-list" action="belongs-list">
                    <input type="hidden" name="id" id="belongs_model_id" value="${req_id}">
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
	<%@ include file="belongs-employee-add-input-container.jsp" %>
    </div>

    <!-- employee remove dialog -->
    <div id="employee-remove-dialog" title="社員削除" style="display:none;">
    <%@ include file="belongs-employee-delete-input-container.jsp" %>
    </div>
    
    <!-- group name edit dialog -->
    <div id="belongs-name-edit-dialog" title="所属名変更" style="display:none;">
	<%@ include file="belongs-update-input-container.jsp" %>
    </div>

    <!-- group add dialog -->
    <div id="belongs-add-dialog" title="所属名追加" style="display:none;">
	<%@ include file="belongs-add-input-container.jsp" %>
    </div>
    
    <!-- group remove dialog -->
    <div id="belongs-remove-dialog" title="所属名削除" style="display:none;">
	<%@ include file="belongs-delete-input-container.jsp" %>
    </div>

    <!-- belongs integrate dialog -->
    <div id="belongs-integrate-dialog" title="所属統合" style="display:none;">
      <form>
        <div class="form-group">
            <div><label>統合のベースとなる所属を選択してください。</label></div>
          <select class="form-control" id="belongs-integrate-dialog-base">
            <option value="1">１局</option>
            <option value="2">２局</option>
            <option value="3">３局</option>
            <option value="4">４局</option>
            <option value="5">５局</option>
          </select>
          <table class="table" id="belongs-integrate-dialog-list">
            <tr><th>統合する所属を選択してください。</th></tr>
            <tr><td><label class="checkbox-inline"><input type="checkbox" class=" belongs-integrate-dialog-check" value="1">１局</label></td></tr>
            <tr><td><label class="checkbox-inline"><input type="checkbox" class=" belongs-integrate-dialog-check" value="2">２局</label></td></tr>
            <tr><td><label class="checkbox-inline"><input type="checkbox" class=" belongs-integrate-dialog-check" value="3">３局</label></td></tr>
            <tr><td><label class="checkbox-inline"><input type="checkbox" class=" belongs-integrate-dialog-check" value="4">４局</label></td></tr>
            <tr><td><label class="checkbox-inline"><input type="checkbox" class=" belongs-integrate-dialog-check" value="5">５局</label></td></tr>
          </table>
        </div>
        <div class="form-group">
          <button class="btn btn-sm btn-danger" id="belongs-integrate-dialog-integrate">統合</button>
          <button class="btn btn-sm btn-primary" id="belongs-integrate-dialog-cancel">キャンセル</button>
        </div>
      </form>
    </div>

    <!-- belongs separate dialog -->
    <div id="belongs-separate-dialog" title="所属分離" style="display:none;">
      <div class="container">
        <div class="row">
          <div class="col-sm-5">
            <label>分離する所属</label>
            <select class="form-control">
              <option value="1">１局</option>
              <option value="2">２局</option>
              <option value="3">３局</option>
              <option value="4">４局</option>
              <option value="5">５局</option>
            </select>
          </div>
          <div class="col-sm-5 col-sm-offset-2">
            <label>新しい所属</label><input type="text" class="form-control" placeholder="所属名を入力してください">
          </div>
        </div>
        <div class="row">
          <div class="col-sm-2">
            <label>社員一覧</label>
          </div>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="belongs-separate-dialog-employee-list-filter" placeholder="フィルター">
          </div>
          <div class="col-sm-2 col-sm-offset-2">
            <label>社員一覧</label>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-5">
            <select class="form-control" id="belongs-separate-dialog-employee-list" multiple size="20">
              <option>A12345 山田 太郎</option>
              <option>A12346 山田 二郎</option>
              <option>A12347 山田 三郎</option>
              <option>A12348 山田 四郎</option>
              <option>A12349 山田 五郎</option>
              <option>A12350 山田 六郎</option>
            </select>
          </div>
          <div class="col-sm-2 center-block">
            <button class="btn btn-sm btn-primary btn-block" id="belongs-separate-dialog-employee-add">追加 ＞＞</button>
            <button class="btn btn-sm btn-warning btn-block" id="belongs-separate-dialog-employee-remove">＜＜ 削除</button>
          </div>
          <div class="col-sm-5">
            <select class="form-control" id="belongs-separate-dialog-new-employee-list" multiple size="20">
            </select>
          </div>
        </div> <!-- row -->
        <div class="row">
          <div class="col-sm-2 col-sm-offset-4">
            <button class="btn btn-sm btn-danger btn-block" id="belongs-separate-dialog-separate">分離</button>
          </div>
          <div class="col-sm-2">
            <button class="btn btn-sm btn-primary btn-block" id="belongs-separate-dialog-cancel">キャンセル</button>
          </div>
        </div> <!-- row -->
      </div> <!-- container -->
    </div>
