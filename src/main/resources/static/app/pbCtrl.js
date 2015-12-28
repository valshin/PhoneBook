app.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

app.controller('pbCtrl', function($rootScope, $scope, $filter, $http) {
    $rootScope.authenticate();
    $scope.searchConditions = [
        {id: 'name', title: 'Имени '},
        {id: 'lastName', title: 'Фамилии '},
        {id: 'phone', title: 'Номеру телефона '}
    ];

    $scope.curCondition = $scope.searchConditions[0];
    $scope.setCondition = function(index){
        $scope.curCondition = $scope.searchConditions[index];
    };

    $scope.search = function(){
        if (!$scope.searchValue){
            return;
        }
        debugger;
        switch ($scope.curCondition.id){
            case 'name':
                $scope.getByName($scope.searchValue);
                break;
            case 'lastName':
                $scope.getByLastName($scope.searchValue);
                break;
            case 'phone':
                $scope.getByPhone($scope.searchValue);
                break;
        }
    };

    $scope.notes = [];
    $scope.setNotes = function(data){
        $scope.notes = [];
        if (!(data.data)){
            return;
        }
        var notes = data.data;
        for (var i in notes){
            var note = notes[i];
            $scope.notes.push({
                id: i,
                data: note
            });
        }
        $scope.notes.sort(function(a, b){
            return (a.lastName || '' + a.name || '' + a.secondName || '').localeCompare(b.lastName || '' + b.name || '' + b.secondName || '');
        });
    };

    $scope.getAll = function(callback) {
        $http.get('/phonebook/get_all').success(function(data) {
            debugger;
            $scope.setNotes(data);
            callback && callback();
        });
    };

    $scope.getByName = function(name, callback) {
        $http.get(
            '/phonebook/get_by_name',
            {params: {name: name}}
        ).success(function(data) {
                debugger;
                $scope.setNotes(data);
                callback && callback();
            });
    };

    $scope.getByLastName = function(lastName, callback) {
        $http.get(
            '/phonebook/get_by_lastname',
            {params: {lastName: lastName}}
        ).success(function(data) {
                debugger;
                $scope.setNotes(data);
                callback && callback();
            });
    };

    $scope.getByPhone = function(phone, callback) {
        $http.get(
            '/phonebook/get_by_phone',
            {params: {phone: phone}}
        ).success(function(data) {
                $scope.setNotes(data);
                callback && callback();
            });
    };

    $scope.saveNote = function(data, index) {
        debugger;
        var noteId = $scope.notes[index].id;

        if (noteId.indexOf('local') + 1){
            $scope.addNote(data, function(id){
                debugger;
                $scope.notes[index].id = id;
            })
        } else {
            $scope.updateNote(data, noteId);
        }
        //$scope.addNote(data);
    };

    $scope.addNote = function(data, callBack) {
        debugger;
        $http.post('/phonebook/add', data).success(function(data) {
            debugger;
            callBack && callBack(data.data);
        }).error(function(data){
            debugger;
        });
    };

    $scope.updateNote = function(data, id, callBack) {
        debugger;
        $http.post('/phonebook/update', {note:data, id:id}).success(function(data) {
            debugger;
            callBack && callBack(data);
        }).error(function(data){
            debugger;
        });
    };

    $scope.removeNote = function(index) {
        $scope.notes.splice(index, 1);
    };

    $scope.addLocalNote = function() {
        $scope.inserted = {
            id: 'local' + $scope.notes.length+1,
            data: {
                name: '',
                secondName: '',
                lastName: '',
                phone: '+380(00)0000000',
                homePhone: '',
                address: '',
                email: ''
            }
        };
        $scope.notes.push($scope.inserted);
    };

    $scope.checkName = function(data) {
        if (data.length < 4) {
            return 'Менее 4 символов';
        } else if (data.length > 200){
            return 'Сделайте короче';
        }
    };

    $scope.checkPhone = function(data, isRequired) {
        if (!isRequired && data.length == 0) return;
        if (!((/\+380\(\d{2}\)\d{7}/).test(data))){
            return "Неверный формат";
        }
    };

    $scope.checkAddress = function(data) {
        if (!data) return;
        if (data.length > 200){
            return "Слишклм длинный";
        }
    };

    $scope.checkEmail = function(data) {
        if (!data) return;
        var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (!(re.test(data))){
            return "Неверный формат";
        }
    };
});