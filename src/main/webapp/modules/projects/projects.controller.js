app.controller('ProjectsController', function ($scope, HomeService, ProjectsService, $routeParams) {
    $scope.projects = [];
    $scope.comments = [];

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

    $scope.createComment = function(comment) {
      return ProjectsService.createComment({projectId: $routeParams.id, commentText: comment})
          .then(function() {
             $scope.comments.push({commentText: comment, createdOn: new Date()});
          });
    };

    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projects;

            if ($routeParams.id) {
                $scope.model = $scope.projects.find(function(p) {
                    if(+p.id === +$routeParams.id) {
                        $routeParams.action === 'edit' ? p.isEdit = true : p.view = true;
                        return p;
                    }
                });
            }

            if ($scope.model.view) {
                return ProjectsService.getComments($routeParams.id);
            }
        })
        .then(function(res) {
            if (res.data.comments) {
                $scope.comments = res.data.comments;
            }
        })
});
