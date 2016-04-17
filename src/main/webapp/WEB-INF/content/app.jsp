<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
    <meta charset="utf-8">
    <title>My AngularJS Struts2 App</title>

    <s:url var="ctxUrl" forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" ></s:url>
    <base href="<s:property value="ctxUrl"/>">
</head>
<body>

<div>
    <a href="/home">Home</a> - <a href="/projects">Projects</a> - <a href="/companies">Companies</a>
</div>

<div ng-controller="boostapp">
    <div ng-view></div>
</div>

<script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>
<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="internal/service.js" />"></script>
<script src="<s:url value="modules/home/home.controller.js" />"></script>
<script src="<s:url value="modules/signup/signup.controller.js" />"></script>
<script src="<s:url value="modules/projects/projects.controller.js" />"></script>
<script src="<s:url value="shared/user.service.js" />"></script>
<script src="<s:url value="modules/home/home.service.js" />"></script>
<script src="<s:url value="modules/projects/projects.service.js" />"></script>
<script src="<s:url value="modules/companies/company.service.js" />"></script>
<script src="<s:url value="modules/companies/company.controller.js" />"></script>
</body>
</html>
