app.controller('CompanyPageController', function ($scope, HomeService, InviteService, CompanyService, ProjectsService, UserService, $routeParams, $location, context) {
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

            return InviteService.getInvitesByCompanyId($routeParams.id);
        })
        .then(function(invites) {
            invites.data.invites.forEach(function(i) {
               return UserService.getById(i.userId)
                   .then(function(user) {
                       i.user = user.data.user;
                   });
            });

            $scope.invites = invites.data.invites;
        });
});
