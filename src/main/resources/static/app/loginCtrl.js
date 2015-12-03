app.controller(
    'loginCtrl',
    function ($rootScope, $scope, $state) {
        $scope.login = function () {
            $rootScope.authenticate($scope.credentials, function(){
                if (!$rootScope.authenticated){
                    //$rootScope.setTabs('login');
                    $state.go('login');
                } else {
                    //$rootScope.setTabs('phonebook');
                    $state.go('phonebook');
                }
            });
        };
    }
);