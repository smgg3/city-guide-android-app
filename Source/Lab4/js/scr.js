angular.module('weather', []).controller('weatherctrl', function($scope, $http) {

    $scope.getWeather = function() {
      $scope.showWeather = true;
      $http.get('http://api.wunderground.com/api/36b799dc821d5836/conditions/q/MO/'+$scope.fromCity+'%20City.json').success(function(data) {
        $scope.fromData=data.current_observation.display_location;
        temp = data.current_observation.temp_f;
        icon = data.current_observation.icon_url;
        weather = data.current_observation.weather;
          $scope.fromTemp= "Currently " + temp + "  F and " + weather;
          $scope.fromcurrentIcon = icon;
          if($scope.fromTemp==undefined || $scope.fromTemp == null || $scope.fromTemp==""){
             $scope.fromTemp="Sorry weather report not available for the given City "+$scope.fromCity+"Please provide a valid city name";
          }
          $scope.showfromWeather = true;
      });
      $http.get(
'http://api.wunderground.com/api/36b799dc821d5836/conditions/q/MO/'+$scope.toCity+'%20City.json').success(function(data) {
        $scope.toData=data.current_observation.display_location;
        temp = data.current_observation.temp_f;
        icon = data.current_observation.icon_url;
        weather = data.current_observation.weather;
          $scope.toTemp= "Currently " + temp + "  F and " + weather;
          $scope.tocurrentIcon = icon;
          if($scope.toTemp==undefined || $scope.toTemp == null || $scope.toTemp==""){
           $scope.toTemp="Sorry weather report not available for the given City "+$scope.toCity+" Please provide a valid city name";
          }
          $scope.showtoWeather = true;
      });
    }
  });

