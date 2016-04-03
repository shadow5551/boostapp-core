app.controller('ProjectsController', function ($scope, HomeService, ProjectsService, $routeParams) {
    $scope.projects = [];

    $scope.save = function(model) {
        if (model.isEdit) {
            model.remove = false;
            return ProjectsService.updateOrDelete(model);
        } else {
            return ProjectsService.create(model);
        }
    };

    $scope.remove = function(id) {
        return ProjectsService.updateOrDelete({id: id, remove: true});
    };

    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projects;

            if ($routeParams.id) {
                $scope.model = $scope.projects.find(function(p) {
                    if(+p.id === +$routeParams.id) {
                        p.isEdit = true;
                        return p;
                    }
                });
            }
        });
});
