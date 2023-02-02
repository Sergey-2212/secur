angular.module('admin', []).controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/secure/api/v1';

    $scope.loadUsers = function () {
        console.log("loadUsers")

        $http({
            url: contextPath + '/users/',
            method: 'GET',
        }).then(function (response) {
            console.log(response);
            $scope.UsersList = response.data;
            $scope.RolesList = response.data.roles;
            console.log(response.data.roles);

        })
    }

    $scope.loadUsers();
});