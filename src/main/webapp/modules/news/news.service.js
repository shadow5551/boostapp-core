app.factory('NewsService', ['Service', function(Service) {
    return {
        getNews: function() {
            return Service.request('/api/news');
        }
    }
}]);
