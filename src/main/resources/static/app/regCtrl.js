app.controller('regCtrl', ['$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state){
    debugger;
    $rootScope.authenticate();
    $scope.error = [];
    $scope.checkLogin = function(){
        if ($scope.login && /^(?!.*[_\W]).{3,}$/.test($scope.login)){
           return;
        }
        $scope.error.push({msg: 'Логин - только английские символы, не меньше 3х, без спецсимволов'});
    };

    $scope.checkPassword = function(){
        if ($scope.password && /^(?=.*[A-Z])(?=.*\d).{5,}$/.test($scope.password)){
            return;
        }
        $scope.error.push({msg: 'Пароль - содержание минимум 1 цифры и 1 заглавной, минимум 5 символов'});
    };

    $scope.checkFio = function(){
        if ($scope.fio && $scope.fio.length >= 5){
            return;
        }
        $scope.error.push({msg: 'ФИО - минимум 5 символов'});
    };

    $scope.send = function(){
        debugger;
        $scope.error = [];
        $scope.checkLogin();
        $scope.checkPassword();
        $scope.checkFio();
        !$scope.error.length && $http({
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
        }).success(function(data){
            debugger;
            if (data.data === 'OK'){
                $scope.success = true;
                $rootScope.username = $scope.username
                setTimeout(function(){
                    $state.go('login');
                }, 3000);
            } else {
                $scope.success = false;
                $scope.error.push({msg: data.error});
            }
        });
    };
}]);