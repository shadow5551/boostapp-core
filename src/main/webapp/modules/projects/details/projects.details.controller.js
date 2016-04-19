app.controller('ProjectsDetailsController', function ($scope, HomeService, CompanyService, ProjectsService, $routeParams, context, $location) {
    $scope.projects = [];
    $scope.comments = [];
    $scope.companies = [];

    $scope.context = context.get();

    if ($routeParams.id === 'new') {
        $scope.isNew = true;
    }

    $scope.save = function(model) {
        return _save(model)
            .then(function() {
               $location.path('/projects');
            });
    };

    function _save(model) {
        if (!$scope.isNew) {
            model.remove = false;
            if (model.companyId) {
                model.companyId = +model.companyId;
            }
            return ProjectsService.updateOrDelete(model);
        } else {
            return ProjectsService.create(model);
        }
    }

    CompanyService.getCompanies()
        .then(function(data) {
            $scope.companies = data.data.companies;
        });

    if (!$scope.isNew) {
        return HomeService.getProjects()
            .then(function (data) {
                $scope.projects = data.data.projects;

                if ($routeParams.id) {
                    $scope.model = $scope.projects.find(function (p) {
                        if (+p.id === +$routeParams.id) {
                            return p;
                        }
                    });
                }

                if (!$scope.model) {
                    $location.path('/404');
                }
            });

    }
});
