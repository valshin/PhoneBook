var app = angular.module('app', ['ui.router', 'ui.bootstrap', 'xeditable']);
app.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: "home.html",
            controller: "homeCtrl"
        })
        .state('phonebook', {
            url: '/phonebook',
            templateUrl: "phonebook.html",
            controller: 'pbCtrl'
        })
        .state('login', {
            url: '/login',
            templateUrl: "log_in.html",
            controller: 'loginCtrl'
        })
        .state('register', {
            url: '/register',
            templateUrl: "register.html",
            controller: 'regCtrl'
        })
        .state('logout', {
            url: '/logout',
            template: '<h1>Вы вышли</h1>',
            controller: 'logoutCtrl'
        });
    $urlRouterProvider.otherwise('/login');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

app.controller('mainCtrl', function($rootScope, $scope, $http, $state){
    $rootScope.$on("$stateChangeSuccess", function () {
        $rootScope.setTabs($state.$current.name);
    });

    $rootScope.getTabVisibility = function(tabRoute){
        return {
            'home' : 1,
            'phonebook' : $rootScope.authenticated,
            'login' : !$rootScope.authenticated,
            'register' : !$rootScope.authenticated,
            'logout' : $rootScope.authenticated
        }[tabRoute];
    };

    $rootScope.setTabs = function(stateName){
        $scope.tabs.forEach(function (tab) {
            tab.visible = $rootScope.getTabVisibility(tab.route);
            tab.active = tab.route === stateName;
        });
    };

    $scope.tabs = [
        { title: "Начальная страница", route: "home", active: true, visible: true},
        { title: "Телефонная книга", route: "phonebook", active: false, visible: false},
        { title: "Войти", route: "login", active: false, visible: true},
        { title: "Регистрация", route: "register", active: false, visible: true},
        { title: "Выход", route: "logout", active: false, visible: false}
    ];

    $rootScope.setTabs('login');

    $rootScope.logout = function (callBack) {
        $http.post('logout', '').success(function () {
            console.log("Logout succeeded");
            $rootScope.authenticated = false;
            callBack && callBack();
        }).error(function () {
            console.log("Logout failed");
            $rootScope.authenticated = false;
            callBack && callBack();
            $state.go('login');
        });
    };

    $rootScope.authenticate = function (credentials, callback) {
        if ($rootScope.authenticated){
            return;
        }
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
                console.log("Login succeeded");
                $state.go('phonebook')
            } else {
                console.log("Login failed");
                $rootScope.authenticated = false;
                $state.go('login')
            }
            callback && callback();
        }).error(function () {
            console.log("Login failed");
            $rootScope.authenticated = false;
            callback && callback();
        });
    };
    $rootScope.authenticate();
});

app.controller('logoutCtrl', function($rootScope, $scope, $http, $state){
    $rootScope.logout(function(){
        if (!$rootScope.authenticated){
            $state.go('login');
        }
    });
});

app.controller('homeCtrl', function($rootScope, $state){
    $rootScope.authenticate();
});
