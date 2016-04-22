app.factory('InviteService', ['Service', function(Service) {
    return {
        getInvitesByCompanyId: function(id) {
            return Service.request('/api/invites?companyId=' + id);
        },

        invite: function(invite) {
            return Service.request('/api/invites', 'POST', invite);
        },

        getInvitesByUserId: function(id) {
            return Service.request('api/invites?userId='+id);
        },

        setStatus: function(data) {
            return Service.request('api/invites', 'PUT', data);
        }
    }
}]);
