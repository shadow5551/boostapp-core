app.controller('CompanyPageController', function ($scope, HomeService, CompanyService, ProjectsService, $routeParams, $location, context) {
    $scope.model = {};
    $scope.context = context.get();
    $scope.projects = [];

    return CompanyService.getCompanies()
        .then(function(data) {
            $scope.companies = data.data.companies;

            if ($routeParams.id) {
                $scope.model = $scope.companies.find(function(p) {
                    if(+p.id === +$routeParams.id) {
                        return p;
                    }
                });
            }

            if (!$routeParams.id || !$scope.model) {
                $location.path('/404');
            } else {
                return ProjectsService.getProjects();
            }
        })
        .then(function(projects) {
            if (projects) {
                $scope.projects = projects.data.projects.filter(function(p) {
                    if(+p.companyId === +$routeParams.id) {
                        return p;
                    }
                });
            }
        })
});
