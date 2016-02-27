var angularStrutsApp = angular.module('angularStrutsApp', ['ngRoute']);

angularStrutsApp.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {

        $locationProvider.html5Mode(true).hashPrefix('!');

        $routeProvider
          .when('/home', {
            templateUrl: 'partials/home.html',
            controller: 'HomeController'
        })
        .otherwise({ redirectTo: '/home' });
    }
]);
