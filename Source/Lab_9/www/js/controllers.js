angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope,$http) {
         
    $scope.getFood=function(){
    console.log ("-----"+$scope.food);
    var resp = $http.get("https://api.foursquare.com/v2/venues/search?near=overland park&query=donut&limit=5&client_id=Y1EOBQ2AIFFGJNQCEZFBPA51O2ZJMSU0TZCOSSLTUXC0DFVQ&client_secret=D4HFGEORKEQMOQYUYPJF2X35DYAFNZFAYUU2SK5FR2FJXZLO&v=20160212");
        
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
    
    $scope.getLocation=function() {
          console.log('done');
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}
  function showPosition(position) {
      console.log('entered showPosition')
     $scope.lat=position.coords.latitude;
     $scope.lon=position.coords.longitude;

    	
    	
    	
     };

})

.controller('ChatDetailCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
})


