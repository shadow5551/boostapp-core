app.controller('UserPageController', function ($scope, UserService,ProjectsService, PaymentService, InviteService, $routeParams, CompanyService, $location, context) {
    $scope.context = context.get();
    $scope.model = {};
    $scope.errors = [];
    $scope.payments = [];

    $scope.invite = function () {
        return InviteService.invite({
            userId: $routeParams.id,
            companyId: $scope.model.companyId
        })
        .then(function(data) {
            if (data.validateErrors && data.validateErrors.length > 0) {
                $scope.errors = data.validateErrors;
            } else {
                $scope.errros = [];
            }
        });
    };

    $scope.accept = function(inviteId, companyId) {
        return InviteService.setStatus({
            id: inviteId,
            userId: $routeParams.id,
            companyId: companyId,
            status: 'accept'
        });
    };

    $scope.decline = function(inviteId, companyId) {
        return InviteService.setStatus({
            id: inviteId,
            userId: $routeParams.id,
            companyId: companyId,
            status: 'decline'
        });
    };

    return UserService.getById($routeParams.id)
        .then(function(data) {
            $scope.user = data.data.user;

            if (!$scope.user) {
                return $location.path('/404');
            }

            return CompanyService.getCompanies();
        })
        .then(function(data) {
            $scope.companies = data.data.companies;

            return InviteService.getInvitesByUserId($routeParams.id);
        })
        .then(function(invites) {
            invites.data.invites.forEach(function(i) {
                return UserService.getById(i.userId)
                    .then(function(user) {
                        i.user = user.data.user;
                        return CompanyService.getById(i.companyId);
                    })
                    .then(function(company) {
                        i.company = company.data.company;
                    });
            });

            $scope.invites = invites.data.invites;

            return PaymentService.getByUserId($routeParams.id);
        })
        .then(function(payments) {
            if (payments.data.payments) {
                $scope.payments = payments.data.payments;

                $scope.payments.forEach(function(p) {
                    return ProjectsService.getById(p.projectId)
                        .then(function(project) {
                            p.project = project.data.project;
                        });
                });
            }
        });
});
