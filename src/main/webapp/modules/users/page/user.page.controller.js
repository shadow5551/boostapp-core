app.controller('UserPageController', function ($scope, UserService, $location) {
    return UserService.getById(2);
});
