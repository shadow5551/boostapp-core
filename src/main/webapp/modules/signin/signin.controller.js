app.controller('SigninController', function ($scope, UserService, $window) {
    $scope.signin = function() {
        const user = {
            email: $scope.email,
            password: $scope.password
        };

        return UserService.signin(user)
            .then(function(res) {
               if (res.validateErrors && res.validateErrors.length > 0) {
                   $scope.errors = res.validateErrors;
               } else {
                   $window.location = '/';
               }
            });
    }
});
