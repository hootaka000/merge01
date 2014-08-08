<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <div class="container">
      <!-- belongs list -->
      <div class="row">
        <!-- customer list -->
        <div class="col-sm-14">
					<div class="container">
            <div class="row">
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block" id="customer-add-button">顧客追加</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block" id="customer-export-button">エクスポート</button>
              </div>
              <div class="col-sm-2">
                <button class="btn btn-sm btn-primary btn-block" id="customer-import-button">インポート</button>
              </div>
              <div class="col-sm-3 col-sm-offset-2">
		            <s:form id="form-customer-list" action="customer-list">
                <table border="0" width="350"><tr><td width="50"></td>
								<td><a href="#" class="prev" 
									<s:if test="%{!paging.prevPage}">style="display:none"</s:if>
								><</a>　<span id="current_page">${paging.currentPageNumber}</span>　<a href="#" class="next"
									<s:if test="%{!paging.nextPage}">style="display:none"</s:if>
								>></a>
								</td>
                <td>
                    <input type="text" name="filter" class="form-control" value="${requestScope.filter}"
 										    id="customer-filter" placeholder="フィルター(CAON IDまたは企業名)">
					          <input type="hidden" name="currentNumber" id="currentNumber" value="">
                </td></tr></table>
					      </s:form>
              </div>
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <table class="table" id="customer-list">
              <thead>
                <tr>
                  <th>UID</th>
                  <th colspan="2">企業名</th>
                  <th colspan="2">事業部名</th>
                  <th colspan="2">サービス</th>
                  <th>社員</th>
                  <th>グループ</th>
                  <th>更新</th>
                  <th>削除</th>
                </tr>
              </thead>
              <tbody>
              <s:iterator value="paging.items" status="stts">
                <tr>
                  <td id="uid">${customerId}</td>
                  <td id="cid">${caonId}</td>
                  <td id="name">${name}</td>
                  <td id="did">${devisionId}</div>
                  <td id="division">${devision}</td>
                  <td id="sid">${serviceId}</div>
                  <td id="service">${service}</td>
                  <td><a href="#" class="assign-num" val_id="${id}"><s:property value="(employees==null)?0:employees.size()"/></a></td>
                  <td><a href="#" class="group-num" val_id="${id}"><s:property value="(groups==null)?0:groups.size()"/></a></td>
                  <td><button class="btn btn-sm btn-primary btn-block customer-update-button" val_id="${id}">更新</button></td>
                  <td><button class="btn btn-sm btn-primary btn-block customer-remove-button" val_id="${id}">削除</button></td>
                </tr>
              </s:iterator>
              </tbody>
            </table>
          </div>
        </div>
      </div> <!-- row -->
    </div><!-- /.container -->

    <!-- customer add dialog -->
    <div id="customer-add-dialog" title="顧客追加" style="display:none;">
    <%@ include file="customer-add-input-container.jsp" %>
    </div>

    <!-- update confirm dialog -->
    <div id="customer-update-dialog" title="顧客更新" style="display:none;">
    <%@ include file="customer-update-input-container.jsp" %>
    </div>

    <!-- remove confirm dialog -->
    <div id="customer-remove-dialog" title="顧客削除" style="display:none;">
    <%@ include file="customer-delete-input-container.jsp" %>
    </div>

    <!-- assign member list dialog -->
    <div id="assign-list-dialog" title="社員一覧" style="display:none;">
    <%@ include file="customer-employee-acl-input-container.jsp" %>
    </div>

    <!-- assign group list dialog -->
    <div id="assign-group-dialog" title="グループ一覧" style="display:none;">
    <%@ include file="customer-group-acl-input-container.jsp" %>
    </div>

    <!-- import dialog -->
    <div id="customer-import-dialog" title="顧客インポート" style="display:none;">
    <%@ include file="customer-import-input-container.jsp" %>
    </div

    <!-- export -->
    <s:form id="form-customer-export" action="customer-export" />
