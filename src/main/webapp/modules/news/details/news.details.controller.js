app.controller('NewsDetailsController', function ($scope, HomeService, CompanyService, NewsService, $routeParams, context, $location) {
    $scope.news = [];
    $scope.companies = [];
    $scope.errors = [];

    $scope.context = context.get();

    if (!$scope.context) {
       return $location.path('/404');
    }

    if ($routeParams.id === 'new') {
        $scope.isNew = true;
    }

    $scope.save = function(model) {
        return _save(model)
            .then(function(data) {
                if (data.validateErrors && data.validateErrors.length > 0) {
                    $scope.errors = data.validateErrors;
                } else {
                    $location.path('/news');
                }
            });
    };

    function _save(model) {
        console.log(model);
        if (!$scope.isNew) {
            model.remove = false;
            if (model.companyId) {
                model.companyId = +model.companyId;
            }
            return NewsService.update(model);
        } else {
            return NewsService.create(model);
        }
    }

    CompanyService.getCompanies()
        .then(function(data) {
            $scope.companies = data.data.companies;
        });

    if (!$scope.isNew) {
        return NewsService.getById($routeParams.id)
            .then(function (data) {
                $scope.model = data.data.news;

                if (!$scope.model) {
                    $location.path('/404');
                }
            });

    }
});
