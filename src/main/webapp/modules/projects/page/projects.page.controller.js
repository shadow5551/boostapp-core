app.controller('ProjectsPageController', function ($scope, HomeService, ProjectsService, $routeParams, $location) {
    $scope.project = {};
    $scope.comments = [];

    $scope.createComment = function(comment) {
        return ProjectsService.createComment({projectId: $routeParams.id, commentText: comment})
            .then(function() {
                $scope.comments.push({commentText: comment, createdOn: new Date()});
            });
    };

    return ProjectsService.getById($routeParams.id)
        .then(function(data) {
            $scope.model = data.data.project;

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
