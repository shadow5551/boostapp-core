app.controller('CompanyPageController', function ($scope, HomeService, CompanyService, ProjectsService, $routeParams, $location, context) {
    $scope.model = {};
    $scope.context = context.get();
    $scope.projects = [];

    return CompanyService.getById($routeParams.id)
        .then(function(data) {
            $scope.model = data.data.company;

            if (!$routeParams.id || !$scope.model) {
                $location.path('/404');
            } else {
                return ProjectsService.getProjects($routeParams.id);
            }
        })
        .then(function(projects) {
            $scope.projects = projects.data.projects;
        });
});
