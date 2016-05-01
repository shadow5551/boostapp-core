app.controller('AdminController', function ($scope, UserService, context, $location) {

    $scope.context = context.get();

    if (!$scope.context || $scope.context.roleId !== 24) {
        $location.path('/404');
    }

    $scope.blockUser = function(id) {
        return UserService.blockUser(id)
            .then(function(res) {
                if (res.validateErrors && res.validateErrors.length > 0) {
                    $scope.errors = res.validateErrors;
                } else {
                    $scope.users = $scope.users.map(function(u) {
                        if ( u.id == id) {
                            u.isArchived = true;
                        }
                        return u;

                    });
                }
            })
    };

    $scope.unBlockUser = function(id) {
        return UserService.unBlockUser(id)
            .then(function(res) {
                if (res.validateErrors && res.validateErrors.length > 0) {
                    $scope.errors = res.validateErrors;
                } else {
                    $scope.users = $scope.users.map(function(u) {
                        if ( u.id == id) {
                            u.isArchived = false;
                        }
                        return u;
                    });
                }
            });
    };

    return UserService.list()
        .then(function(data) {
            $scope.users = data.data.users;
        });
});
