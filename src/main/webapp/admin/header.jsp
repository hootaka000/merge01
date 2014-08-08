<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">アクセス権管理</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a id="menu_assign" href="/ams/admin/assign/setting">アクセス権設定</a></li>
            <li><a id="menu_customers" href="/ams/admin/customer/customer-list">顧客管理</a></li>
            <li><a id="menu_groups" href="/ams/admin/groups/groups-list">グループ管理</a></li>
            <li><a id="menu_belongs" href="/ams/admin/belongs/belongs-list">所属管理</a></li>
            <li><a id="menu_system" href="/ams/admin/system/setting">システム設定</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">山田太郎<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="login.html">ログアウト</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
