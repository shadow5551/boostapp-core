var app = angular.module('app', ['ngRoute']);

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');

    $routeProvider.when('/', {
        templateUrl: 'modules/home/home.view.html',
        controller: 'HomeController'
    }); //add new routes here
}]);

app.controller('boostapp', function ($scope) {
    //global app controller
});