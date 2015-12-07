app.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

app.controller('pbCtrl', function($scope, $filter, $http) {
    $scope.searchConditions = [
        {id: 'name', title: 'Имени'},
        {id: 'lastName', title: 'Фамилии'},
        {id: 'phone', title: 'Номеру телефона'}
    ];

    $scope.notes = [];
    $scope.setNotes = function(data){
        $scope.notes = [];
        if (!(data.data)){
            return;
        }
        var notes = data.data;
        for (var i in notes){
            $scope.notes[i] = {
                id: i,
                data: notes[i]
            }
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
                $scope.setNotes(data);
                callback && callback();
            });
    };

    $scope.getByLastName = function(lastName, callback) {
        $http.get(
            '/phonebook/get_by_lastname',
            {params: {lastName: lastName}}
        ).success(function(data) {
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

    $scope.checkName = function(data, nameType) {
        if (data.length < 4) {
            return nameType + ' содержит меньше 4 символов';
        } else if (data.length > 200){
            return nameType + ' содержит слишком много символов';
        }
    };

    $scope.checkPhone = function(data, isRequired) {
        if (!isRequired && data.length == 0) return;
        if (!((/\+380\(\d{2}\)\d{7}/).test(data))){
            return "Неверный формат номера";
        }
    };

    $scope.checkAddress = function(data) {
        if (!data) return;
        if (data.length > 200){
            return "Адрес слишклм длинный";
        }
    };

    $scope.checkEmail = function(data) {
        if (!data) return;
        var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (!(re.test(data))){
            return "Неверный формат e-mail";
        }
    }
});