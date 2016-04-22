app.factory('PaymentService', ['Service', function(Service) {
    return {
        createPayment: function(payment) {
            return Service.request('/api/payments', 'POST', payment);
        },
        getAllByProjectId: function(projectId) {
            return Service.request('/api/payments?projectId='+projectId);
        },
        getByUserId: function(userId) {
            return Service.request('/api/payments?userId='+userId);
        }
    }
}]);
