<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>アクセス権管理 | <tiles:insertAttribute name="title"/></title>
    <!-- css -->
    <link href="/ams/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ams/css/ams.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/ams/js/bootstrap.min.js"></script>
    <!-- jQuery UI -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
</head>
<body>
        <div id="container">
        <div id="head">
        <tiles:insertAttribute name="head" />
        </div>
        <div id="body">
        <tiles:insertAttribute name="body" />
        </div>
        <div id="foot">
        <tiles:insertAttribute name="footer" />
        </div>
        </div>
</body>
</html>
