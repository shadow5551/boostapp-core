app.controller('ProjectsPageController', function ($scope, HomeService, ProjectsService, $routeParams, $location) {
    $scope.project = {};
    $scope.comments = [];

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

            if (!$routeParams.id || !$scope.model) {
                $location.path('/404');
            }

            return ProjectsService.getComments($routeParams.id);
        })
        .then(function(res) {
            if (res.data.comments) {
                $scope.comments = res.data.comments;
            }
        })
});
