app.controller('NewsController', function ($scope, HomeService, NewsService, $routeParams, context, $window) {
    $scope.news = [];


    $scope.context = context.get();

    if($scope.context && $scope.context.isArchived && $window.location.pathname !== '/404') {
        $window.location = '/404';
    }

    $scope.save = function(model) {
        if (model.isEdit) {
            model.remove = false;
            return NewsService.update(model);
        } else {
            return NewsService.create(model);
        }
    };

    $scope.remove = function(id) {
        return NewsService.update({id: id, remove: true})
            .then(function() {
                $scope.news = $scope.news.filter(function(n) {
                    return n.id != id;
                });
            })
    };

    return HomeService.getNews()
        .then(function(data) {
            //console.log(data);
            $scope.news = data.data.news;
        });
});
