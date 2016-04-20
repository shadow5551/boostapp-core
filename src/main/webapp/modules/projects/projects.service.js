app.factory('ProjectsService', ['Service', function(Service) {
    return {
        getProjects: function(companyId) {
            return Service.request('/api/projects?companyId=' + companyId);
        },

        create: function(data) {
            return Service.request('/api/projects', 'POST', data);
        },

        updateOrDelete: function(data) {
            return Service.request('/api/projects', 'PUT', data);
        },

        createComment: function(data) {
            return Service.request('/api/comments', 'POST', data);
        },

        getComments: function(projectId) {
            return Service.request('api/comments?projectId=' + projectId, 'GET')
        },

        getById: function(id) {
            return Service.request('api/project?id='+id);
        }
    }
}]);
