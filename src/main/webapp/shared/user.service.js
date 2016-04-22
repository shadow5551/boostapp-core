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

        invite: function(invite) {
            return Service.request('/api/invites', 'POST', invite);
        },

        getInvitesByUserId: function(id) {
            return Service.request('api/invites?userId='+id);
        },

        getCurrentUser: function() {
            return Service.request('/api/users', 'GET');
        },

        getById: function(id) {
            return Service.request('/api/user?id='+id);
        }
    }
}]);
