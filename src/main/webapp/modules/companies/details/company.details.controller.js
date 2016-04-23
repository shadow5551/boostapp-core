app.controller('CompanyDetailsController', function ($scope, CompanyService, $location, context, $routeParams) {
    $scope.model = {};
    $scope.context = context.get();
    $scope.errors = [];

    if ($routeParams.id === 'new') {
        $scope.isNew = true;
    }

    if (!$scope.context) {
        $location.path('/404');
    }

    $scope.save = function(model) {
        return _save(model)
            .then(function(data) {
                if (data.validateErrors && data.validateErrors.length > 0) {
                    $scope.errors = data.validateErrors;
                } else {
                    $location.path('/companies/'+data.company.id+'/page');
                }
            });
    };

    function _save(model) {
        if (!$scope.isNew) {
            return CompanyService.update(model);
        } else {
            return CompanyService.create(model);
        }
    }

    if (!$scope.isNew) {
        return CompanyService.getById($routeParams.id)
            .then(function (data) {
                $scope.model = data.data.company;

                if (!$scope.model) {
                    $location.path('/404');
                }
            });
    }
});
