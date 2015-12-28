app.controller(
    'loginCtrl',
    function ($rootScope, $scope) {
        $scope.login = function () {
            $rootScope.authenticate($scope.credentials);
        };
        $rootScope.authenticate();
    }
);