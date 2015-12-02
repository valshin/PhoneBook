var app = angular.module('app', ['ngRoute', 'ui.bootstrap']);
app.config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'pbCtrl'
    }).when('/log_in', {
        templateUrl: 'log_in.html',
        controller: 'loginCtrl'
    }).when('/register', {
        templateUrl: 'register.html',
        controller: 'regCtrl'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

app.controller('mainCtrl', function($rootScope, $location, $http){
    $rootScope.tabClick = function(path){
        $location.path(path);
    };
    $location.path("/");
    $rootScope.logout = function () {
        $http.post('logout', '').success(function () {
            $rootScope.authenticated = false;
            $location.path("/");

        }).error(function () {
            console.log("Logout failed")
            $rootScope.authenticated = false;
        });
    };
});
