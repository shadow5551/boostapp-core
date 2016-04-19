var app = angular.module('app', ['ngRoute', 'ngCookies']);

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');

    $routeProvider
        .when('/', {
            templateUrl: 'modules/home/home.view.html',
            controller: 'HomeController'
        })
        .when('/projects', {
            templateUrl: 'modules/projects/projects.view.html',
            controller: 'ProjectsController'
        })
        .when('/projects/:id/page', {
            templateUrl: 'modules/projects/page/projects.page.view.html',
            controller: 'ProjectsPageController'
        })
        .when('/projects/:id', {
            templateUrl: 'modules/projects/details/projects.details.view.html',
            controller: 'ProjectsDetailsController'
        })
        .when('/companies', {
            templateUrl: 'modules/companies/company.view.html',
            controller: 'CompaniesController'
        })
        .when('/companies/create', {
            templateUrl: 'modules/companies/company-create.view.html',
            controller: 'CompanyCreateController'
        })
        .when('/companies/:id', {
            templateUrl: 'modules/companies/page/company.page.view.html',
            controller: 'CompanyPageController'
        })
        .when('/signup', {
            templateUrl: 'modules/signup/signup.view.html',
            controller: 'SignupController'
        })
        .when('/signin', {
            templateUrl: 'modules/signin/signin.view.html',
            controller: 'SigninController'
        })
        .when('/404', {
            templateUrl: 'modules/404/404.view.html',
            controller: '404Controller'
        })
        .otherwise({
            redirectTo: '/404'
        });

}]);

app.controller('boostapp', function ($scope, UserService, context, $window) {
    $scope.init = function() {
        return UserService.getCurrentUser()
            .then(function(res) {
                context.set(res.data.currentUser);
                $scope.context = res.data.currentUser;
            });
    };

    $scope.signout = function() {
        return UserService.signout()
            .then(function() {
                context.unset('context');
                $window.location = '/';
            });
    };

    $scope.init();
});