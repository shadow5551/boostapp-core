app.controller('HomeController', function ($scope, HomeService, $rootScope) {
    if($scope.context && $scope.context.isArchived && $window.location.pathname !== '/404') {
        $window.location = '/404';
    }

    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projects;
        });
});
