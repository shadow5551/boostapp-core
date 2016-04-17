app.factory('CompanyService', ['Service', function(Service) {
    return {
        getCompanies: function() {
            return Service.request('/api/companies');
        },

        create: function(data) {
            return Service.request('/api/companies', 'POST', data);
        },

        updateOrDelete: function(data) {
            return Service.request('/api/companies', 'PUT', data);
        }
    }
}]);
