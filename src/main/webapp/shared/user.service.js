app.factory('UserService', ['Service', function(Service) {
    return {
        signup: function(user) {
            return Service.request('/api/users', 'POST', user);
        },

        signin: function(user) {
            return Service.request('/api/users', 'PUT', user);
        },

        signout: function() {
            return Service.request('/api/users', 'PUT', {signout: true});
        },

        getCurrentUser: function() {
            return Service.request('/api/users', 'GET');
        }
    }
}]);
