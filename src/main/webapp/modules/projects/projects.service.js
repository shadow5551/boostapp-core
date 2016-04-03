app.factory('ProjectsService', ['Service', function(Service) {
    return {
        getProjects: function() {
            return Service.request('/api/projects');
        },

        create: function(data) {
            return Service.request('/api/projects', 'POST', data);
        },

        updateOrDelete: function(data) {
            return Service.request('/api/projects', 'PUT', data);
        }
    }
}]);
