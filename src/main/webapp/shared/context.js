app.factory('context', ['$cookieStore', function ($cookieStore) {
    return {
        set: function(data) {
            if (data) {
                $cookieStore.put('context', data);
            } else {
                this.unset();
            }
        },

        get: function() {
            return $cookieStore.get('context');
        },

        unset: function() {
            return $cookieStore.remove('context');
        }
    }
}]);
