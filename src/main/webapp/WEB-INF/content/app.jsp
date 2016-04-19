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
    <a href="/">Home</a> - <a href="/projects">Projects</a> - <a href="/companies">Companies</a>
</div>

<div ng-controller="boostapp">
    <div style="float: right" ng-if="context">
        <a href="/user/{{context.id}}">{{context.email}}</a><br>
        <a ng-click="signout()" href="#">Logout</a>
    </div>
    <div style="float: right" ng-if="!context.email">
        You are not logged in. <a href="/signin">Login</a>
    </div>
    <div ng-view></div>
</div>

<script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>
<script src="<s:url value="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-cookies.js" />"></script>
<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="internal/service.js" />"></script>
<script src="<s:url value="modules/home/home.controller.js" />"></script>
<script src="<s:url value="modules/signup/signup.controller.js" />"></script>
<script src="<s:url value="modules/signin/signin.controller.js" />"></script>
<script src="<s:url value="modules/projects/projects.controller.js" />"></script>
<script src="<s:url value="modules/projects/details/projects.details.controller.js" />"></script>
<script src="<s:url value="modules/projects/page/projects.page.controller.js" />"></script>
<script src="<s:url value="shared/user.service.js" />"></script>
<script src="<s:url value="shared/context.js" />"></script>
<script src="<s:url value="modules/home/home.service.js" />"></script>
<script src="<s:url value="modules/projects/projects.service.js" />"></script>
<script src="<s:url value="modules/companies/company.service.js" />"></script>
<script src="<s:url value="modules/companies/company.controller.js" />"></script>
<script src="<s:url value="modules/companies/company.create.controller.js" />"></script>
<script src="<s:url value="modules/404/404.controller.js" />"></script>
</body>
</html>
