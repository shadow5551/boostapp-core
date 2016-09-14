app.factory('HomeService', ['Service', function(Service) {
    return {
       getProjects: function() {
            return Service.request('/api/projects');
        },
        getNews: function () {
            return Service.request('/api/news')
        }
    }
}]);
