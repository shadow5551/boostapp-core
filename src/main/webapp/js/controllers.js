angularStrutsApp.controller('AppController', function ($scope) { });

angularStrutsApp.controller('HomeController', function ($scope, DataService) {
  DataService.getProjects().then(function(data) {
      $scope.projects = data.data.projectNames;
  }, function(data) {
      console.log('Could not receive project names.')
  });
});

angularStrutsApp.controller('ApacheProjectsController', function ($scope, $http, DataService) {

});
