app.controller('HomeController', function ($scope, HomeService, $rootScope) {
    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projects;
        });
});
