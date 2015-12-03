app.controller(
    'loginCtrl',
    function ($rootScope, $scope, $state) {
        $scope.login = function () {
            $rootScope.authenticate($scope.credentials, function(){
                if (!$rootScope.authenticated){
                    $state.go('login');
                } else {
                    $state.go('phonebook');
                }
            });
        };
    }
);