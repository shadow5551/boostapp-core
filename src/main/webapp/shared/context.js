app.factory('context', ['$cookieStore', function ($cookieStore) {
    return {
        set: function(data) {
            $cookieStore.put('context', data);
        },

        get: function() {
            return $cookieStore.get('context');
        },

        unset: function() {
            return $cookieStore.remove('context');
        }
    }
}]);
