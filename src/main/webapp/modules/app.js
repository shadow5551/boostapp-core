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
        .when('/projects/:id/edit', {
            templateUrl: 'modules/projects/create/projects.details.view.html',
            controller: 'ProjectsController'
        })
        .when('/signup', {
            templateUrl: 'modules/signup/signup.view.html',
            controller: 'SignupController'
        }); //add new routes here
}]);

app.controller('boostapp', function ($scope) {
    //global app controller
});