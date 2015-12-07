app.controller('regCtrl', ['$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state){
    $scope.send = function(){
        debugger;
        $http({
            method: 'POST',
            url: '/adduser',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data: {
                login: $scope.login,
                fio: $scope.fio,
                password: $scope.password
            }
        }).success(function(response){
            $state.go('phonebook');
            $rootScope.setTabs('phonebook');
        });
    };
}]);