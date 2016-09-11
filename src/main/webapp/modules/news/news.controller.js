app.controller('NewsController', function ($scope, HomeService, NewsService, $routeParams, context, $window) {
    $scope.news = [];


    $scope.context = context.get();

    if($scope.context && $scope.context.isArchived && $window.location.pathname !== '/404') {
        $window.location = '/404';
    }

   /* $scope.save = function(model) {
        if (model.isEdit) {
            model.remove = false;
            return ProjectsService.update(model);
        } else {
            return ProjectsService.create(model);
        }
    };

    $scope.remove = function(id) {
        return ProjectsService.update({id: id, remove: true})
            .then(function() {
                $scope.projects = $scope.projects.filter(function(p) {
                    return p.id != id;
                });
            })
    };*/

    return NewsService.getNews()
        .then(function(data) {
            //console.log(data);
            $scope.news = data.data.news;
        });
});
