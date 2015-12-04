app.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

app.controller('pbCtrl', function($scope, $filter, $http) {
    $scope.searchConditions = [
        {id: 'name', title: 'Имени'},
        {id: 'lastName', title: 'Фамилии'},
        {id: 'phone', title: 'Номеру телефона'}
    ];

    $scope.notes = [
        {
            id: 1,
            data: {
                name: 'Валерий',
                secondName: 'Викторович',
                lastName: 'Шинкаренко',
                phone: '0509426859',
                homePhone: '0501234567',
                address: 'Леся Курбаса, 18г',
                email: 'izy@kk.uu'
            }
        }
    ];

    $scope.getAll = function(callback) {
        $http.get('/phonebook/get_all').success(function(data) {
            debugger;
            $scope.notes = data;
            callback && callback();
        });
    };

    $scope.getByName = function(name, callback) {
        $http.get(
            '/phonebook/get_by_name',
            {params: {name: name}}
        ).success(function(data) {
                $scope.notes = data;
                callback && callback();
            });
    };

    $scope.getByLastName = function(lastName, callback) {
        $http.get(
            '/phonebook/get_by_lastname',
            {params: {lastName: lastName}}
        ).success(function(data) {
                $scope.notes = data;
                callback && callback();
            });
    };

    $scope.getByPhone = function(phone, callback) {
        $http.get(
            '/phonebook/get_by_phone',
            {params: {phone: phone}}
        ).success(function(data) {
                $scope.notes = data;
                callback && callback();
            });
    };

    $scope.saveNote = function(data, id) {
        debugger;
        $http.post('/phonebook/save', data).success(function(data) {
            debugger;
        }).error(function(data){
            debugger;
        });
    };

    $scope.checkName = function(data, id) {

    };

    $scope.removeNote = function(index) {
        $scope.notes.splice(index, 1);
    };

    $scope.addNote = function() {
        $scope.inserted = {
            id: $scope.notes.length+1,
            data: {
                name: '',
                secondName: '',
                lastName: '',
                phone: '',
                homePhone: '',
                address: '',
                email: ''
            }

        };
        $scope.notes.push($scope.inserted);
    };
});