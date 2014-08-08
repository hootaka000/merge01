<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

    <link href="/ams/css/login.css" rel="stylesheet">
    <div class="container">
      <s:if test="hasActionErrors()"><div class="alert alert-danger"><s:actionerror/></div></s:if>
      <!-- Login Form -->
      <form class="form-signin" action="/ams/admin/system/login_do" method="post">
        <h2 class="form-signin-heading">ログインして下さい。</h2>
        <input type="text" class="form-control" name="username" placeholder="社員番号" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="パスワード" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">ログイン</button>
      </form>
    </div>
