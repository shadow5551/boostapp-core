var app = angular.module('app', ['ngRoute']);

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
        .when('/projects/create', {
            templateUrl: 'modules/projects/create/projects.details.view.html',
            controller: 'ProjectsController'
        })
        .when('/projects/:id', {
            templateUrl: 'modules/projects/project.page.view.html',
            controller: 'ProjectsController'
        })
        .when('/projects/:id/:action', {
            templateUrl: 'modules/projects/create/projects.details.view.html',
            controller: 'ProjectsController'
        })
        .when('/companies', {
            templateUrl: 'modules/companies/company.view.html',
            controller: 'CompaniesController'
        })
        .when('/companies/create', {
            templateUrl: 'modules/companies/company-create.view.html',
            controller: 'CompaniesController'
        })
        .when('/signup', {
            templateUrl: 'modules/signup/signup.view.html',
            controller: 'SignupController'
        }); //add new routes here
}]);

app.controller('boostapp', function ($scope) {
    //global app controller
});