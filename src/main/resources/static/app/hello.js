var app = angular.module('hello', ['ngRoute'])
app.config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'homeCtrl'
    }).when('/log_in', {
        templateUrl: 'log_in.html',
        controller: 'navCtrl'
    }).when('/register', {
        templateUrl: 'register.html',
        controller: 'regCtrl'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
//app.controller('navigation',function($scope){}).controller('home', function ($scope, $http) {});

app.controller(
    'navCtrl',

    function ($rootScope, $scope, $http, $location, $route) {

        $scope.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {
            debugger;
            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('user', {
                headers: headers
            }).success(function (data) {
                debugger;
                if (data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }).error(function () {
                debugger;
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }

        authenticate();

        $scope.credentials = {};
        $scope.login = function () {
            authenticate($scope.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function (data) {
                console.log("Logout failed")
                $rootScope.authenticated = false;
            });
        }

    }
);

app.controller('homeCtrl', function ($scope, $http) {
    $http.get('/resource/').success(function (data) {
        $scope.greeting = data;
    })
});

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

