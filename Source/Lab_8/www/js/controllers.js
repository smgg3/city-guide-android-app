angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope,$http) {
         
    $scope.getFood=function(){
    console.log ("-----"+$scope.food);
    var resp = $http.get("https://api.foursquare.com/v2/venues/search?near=overland park&query=pizza&limit=10&client_id=Y1EOBQ2AIFFGJNQCEZFBPA51O2ZJMSU0TZCOSSLTUXC0DFVQ&client_secret=D4HFGEORKEQMOQYUYPJF2X35DYAFNZFAYUU2SK5FR2FJXZLO&v=20160212");
        
            resp.success(function(data,status,headers,config){
            console.log("data : "+data.response.venues[1]);
            $scope.venues = data.response.venues;
          });
    	resp.error(function(data,status,headers,config){
    		alert("Failure during service call!!")
    	});  
   
  
  };
})

.controller('ChatsCtrl', function($scope, $http) {
  $scope.getWeather=function() {
    	
    	var resp = $http.get("http://api.openweathermap.org/data/2.5/weather?q="+$scope.city+"&units=imperial&appid=4abfba92c8f67fbedd1c22d789206dc8");
    	resp.success(function(data,status,headers,config){
    		console.log("data : "+data.weather[0].id);
    		$scope.temperatureSource = data.main.temp;
    		$scope.pressureSource = data.main.pressure;
    		
    	});
    	resp.error(function(data,status,headers,config){
    		alert("Failure during service call!!")
    	});
     };

})

.controller('ChatDetailCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
})


