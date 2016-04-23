app.factory('CompanyService', ['Service', function(Service) {
    return {
        getCompanies: function() {
            return Service.request('/api/companies');
        },

        create: function(data) {
            return Service.request('/api/companies', 'POST', data);
        },

        update: function(data) {
            return Service.request('/api/companies', 'PUT', data);
        },

        delete: function(data) {
            data.remove = true;
            return Service.request('/api/companies', 'PUT', data);
        },

        getById: function(id) {
            return Service.request('/api/company?id=' + id);
        }
    }
}]);
