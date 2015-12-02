app.controller('regCtrl', ['$scope', '$http', function($scope, $http){
    $scope.send = function(){
        debugger;
        $http({
            method: 'POST',
            url: '/adduser',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
                //'Upgrade-Insecure-Requests': "1",
                //'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'
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
            debugger;
        });
    };
}]);