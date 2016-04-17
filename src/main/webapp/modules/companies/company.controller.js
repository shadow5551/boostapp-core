app.controller('CompaniesController', function ($scope, HomeService, CompanyService, $routeParams) {
    $scope.projects = [];
    $scope.model = {};

    $scope.save = function(model) {
        if (model.isEdit) {
            model.remove = false;
            return CompanyService.updateOrDelete(model);
        } else {
            return CompanyService.create(model);
        }
    };

    $scope.remove = function(id) {
        return CompanyService.updateOrDelete({id: id, remove: true});
    };

    $scope.create = function() {
        return CompanyService.create($scope.model);
    };

    return CompanyService.getCompanies()
        .then(function(data) {
            $scope.companies = data.data.companies;

            if ($routeParams.id) {
                $scope.model = $scope.companies.find(function(p) {
                    if(+p.id === +$routeParams.id) {
                        $routeParams.action === 'edit' ? p.isEdit = true : p.view = true;
                        return p;
                    }
                });
            }
        });
});