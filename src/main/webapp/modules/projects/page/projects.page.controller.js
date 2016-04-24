app.controller('ProjectsPageController', function ($scope, context, PaymentService, UserService, HomeService, ProjectsService, $routeParams, $location) {
    $scope.project = {};
    $scope.comments = [];
    $scope.payments = [];
    $scope.errors = [];

    $scope.context = context.get();

    $scope.createComment = function(comment) {
        return ProjectsService.createComment({
            projectId: +$routeParams.id,
            userId: +$scope.context.id,
            commentText: comment
        })
        .then(function(res) {
                if (res.validateErrors && res.validateErrors.length > 0) {
                    $scope.errors = res.validateErrors;
                } else {
                    $scope.errors = [];
                    $scope.comments.push({commentText: comment, createdOn: new Date()});
                }
        });
    };

    $scope.remove = function() {
        return ProjectsService.delete({id: $routeParams.id, remove: true})
            .then(function(data) {
                if (data.validateErrors && data.validateErrors.length > 0) {
                    $scope.errors = data.validateErrors;
                } else {
                    $location.path('/projects');
                }
            })
    };

    $scope.addPayment = function() {
        return PaymentService.createPayment({
            projectId: +$routeParams.id,
            userId: $scope.context.id,
            amountInCents: !$scope.model.payment ? 0 : $scope.model.payment * 100
        })
        .then(function(data) {
            if (data.validateErrors && data.validateErrors.length > 0) {
                $scope.errors = data.validateErrors;
            } else {
                $scope.model.paymentAmount += (data.amountInCents / 100);
                $scope.model.payment = 0;
                $scope.errors = [];
            }
        });
    };

    return ProjectsService.getById($routeParams.id)
        .then(function(data) {
            $scope.model = data.data.project;

            $scope.model.amount = $scope.model.amount / 100;
            $scope.model.paymentAmount = $scope.model.paymentAmount / 100;

            if (!$routeParams.id || !$scope.model) {
                $location.path('/404');
            }

            return ProjectsService.getComments($routeParams.id);
        })
        .then(function(res) {
            if (res.data.comments) {
                $scope.comments = res.data.comments;
            }

            $scope.comments.forEach(function(c) {
                return UserService.getById(c.userId)
                    .then(function(user) {
                        c.user = user.data.user;
                    });
            });

            return PaymentService.getAllByProjectId($routeParams.id);
        })
        .then(function(res) {
            if (res.data.payments) {
                $scope.payments = res.data.payments;

                $scope.payments.forEach(function(p) {
                   return UserService.getById(p.userId)
                        .then(function(user) {
                           p.user = user.data.user;
                    })
                });
            }
        })
});
