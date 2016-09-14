app.factory('NewsService', ['Service', function(Service) {
    return {
        getNews: function(companyId) {
            return Service.request('/api/news?companyId=' + companyId);
        },

       /* getNews: function() {
            return Service.request('/api/news');
        },*/

        create: function(data) {
        return Service.request('/api/news', 'POST', data);
        },

        update: function(data) {
            return Service.request('/api/news', 'PUT', data);
        },

        delete: function(data) {
            data.remove = true;
            return Service.request('/api/news', 'PUT', data);
        },


        getById: function(id) {
            return Service.request('api/new?id='+id);
        }
    }


}]);
