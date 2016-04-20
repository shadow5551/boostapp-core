app.controller('ProjectsController', function ($scope, HomeService, ProjectsService, $routeParams, context) {
    $scope.projects = [];
    $scope.comments = [];

    $scope.context = context.get();

    $scope.save = function(model) {
        if (model.isEdit) {
            model.remove = false;
            return ProjectsService.updateOrDelete(model);
        } else {
            return ProjectsService.create(model);
        }
    };

    $scope.remove = function(id) {
        return ProjectsService.updateOrDelete({id: id, remove: true})
            .then(function() {
                $scope.projects = $scope.projects.filter(function(p) {
                    return p.id != id;
                });
            })
    };

    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projects;
        });
});
