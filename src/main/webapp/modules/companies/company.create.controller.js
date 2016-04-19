app.controller('CompanyCreateController', function ($scope, CompanyService, $location, context) {
    $scope.model = {};
    $scope.context = context.get();

    if (!$scope.context) {
        $location.path('/404');
    }

    $scope.create = function() {
        return CompanyService.create($scope.model)
            .then(function() {
                $location.path("/companies");
            });
    };
});
