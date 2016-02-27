angularStrutsApp.factory('DataService', ['$http', '$q', function($http, $q) {

    var DataService = {
        urls : {
            projects : "data/projects"
        }
    };

    DataService._request = function(url, method, model){
        if(!method) {
            method = 'GET';
        }
        var def = $q.defer();
        if(method === 'GET') {
            return $http.get(url).success(function(data) {
                DataService.data = data;
                def.resolve(data);
            }).error(function() {
                def.reject("Failed to get data");
            });
        } else if(method === 'POST'){
            $http.post(url, model).success(function(data) {
                DataService.data = data;
                def.resolve(data);
            }).error(function() {
                def.reject("Failed to post data");
            });
        }
        return def.promise;
    };

    DataService.getProjects = function () {
        return this._request(this.urls.projects);
    };

    return DataService;
}]);
