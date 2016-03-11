angular.module('starter.controllers', [])


.controller('LoginCtrl', function($scope, LoginService, DeleteService, UpdateService, $ionicPopup, $state) {
    $scope.data = {};

    $scope.login = function(username) {
         LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            $state.go('home');
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });

    }
}
)

.controller('RegisterCtrl', function($scope, RegisterService, $ionicPopup, $state) {
     $scope.data = {};

    $scope.register = function(email){

            RegisterService.RegisterUser($scope.data.firstname, $scope.data.lastname,$scope.data.username, $scope.data.password ).success(function(data) {
                $state.go('login');
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
    }
})

.controller('HomeCtrl', function($scope, DeleteService, UpdateService,$ionicPopup, $state) {
   $scope.go= function () {
       $state.go('login');
   }
   $scope.init = function() {
       $scope.data = { firstname: localStorage.firstname,
                      lastname: localStorage.lastname,
                      username: localStorage.username,
                      password: localStorage.password,
                      id:localStorage.id
                     };
    }
   $scope.delete =function()
    {
         DeleteService.deleteUser(localStorage.username).success(function(data) {
         var alertPopup = $ionicPopup.alert({
                title: 'Deleted!',
                template: 'Your account is deleted succesfully!'
            });
             $state.go('login');
    }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
}
       $scope.update =function()
    {
         UpdateService.updateUser($scope.data).success(function(data) {
         var alertPopup = $ionicPopup.alert({
                title: 'Success!',
                template: 'Your account is deleted succesfully!'
            });
    }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Update failed!',
                template: 'Sorry! Failed to update'
            });
        });
}
});
;
;
