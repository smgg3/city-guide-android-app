var app = angular.module("myApp1", [])

app.controller("UpdateController", function ($scope, $http, $httpParamSerializerJQLike) {

    $scope.pageClass = 'update';
$scope.update = function(username, password,confirmPass, email) {
   console.log("inside login function");
$http({
    method: 'PUT',
    url : 'http://localhost:9090/MongoDBRestService/controlService/updateUser',
    data: JSON.stringify({
                name: username,
                password: password,
                confirmPass: confirmPass,
                email: email
            }),
    contentType: "application/json"
}).success(function() {
    $scope.userName ="";
    $scope.password ="";
    $scope.confirmPass="";
    $scope.email ="";
    
    
    $scope.msg ="Account has been updated successfully";
        })
}
    
}); 
				   