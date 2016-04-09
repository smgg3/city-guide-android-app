var app = angular.module("myApp", [])

app.controller("RegisterController", function ($scope, $http, $httpParamSerializerJQLike) {

    $scope.pageClass = 'register';
$scope.register = function(username, password, email, confirmPass) {
   console.log("inside login function");
$http({
    method: 'POST',
    url : 'http://localhost:9090/MongoDBRestService/controlService/createUser',
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
    
    
    $scope.msg ="Account has been created successfully";
        })
}
    
}); 
				   