app.controller('CompanyCreateController', function ($scope, CompanyService, $location, context) {
    $scope.model = {};
    $scope.context = context.get();

    if (!$scope.context) {
        $location.path('/404');
    }

    $scope.create = function() {
        $scope.model.userId = $scope.context.id;

        return CompanyService.create($scope.model)
            .then(function() {
                $location.path("/companies");
            });
    };
});
