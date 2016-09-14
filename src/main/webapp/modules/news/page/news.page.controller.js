app.controller('NewsPageController', function ($scope, context, PaymentService, UserService, HomeService, NewsService, $routeParams, $location) {
    $scope.news = {};
    $scope.errors = [];

    $scope.context = context.get();


    $scope.remove = function() {
        return NewsService.delete({id: $routeParams.id, remove: true})
            .then(function(data) {
                if (data.validateErrors && data.validateErrors.length > 0) {
                    $scope.errors = data.validateErrors;
                } else {
                    $location.path('/news');
                }
            })
    };


    return NewsService.getById($routeParams.id)
        .then(function(data) {
            $scope.model = data.data.news;
             if (!$routeParams.id || !$scope.model) {
                $location.path('/404');
            }
        })
        .then(function(res) {

        })
});
