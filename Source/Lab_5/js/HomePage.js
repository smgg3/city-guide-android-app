angular.module('MyModule',[])
    .controller('MyController', function ($scope,$http) {

        $scope.retrieveData = function (){
            document.getElementById('results').style.visibility="visible";
            var query = document.getElementById('query').value;
            var place=document.getElementById('city').value;
            $scope.query=query;
            var resp=$http.get("https://api.foursquare.com/v2/venues/search&query="+query&near=place+"&limit=6&client_id=Y1EOBQ2AIFFGJNQCEZFBPA51O2ZJMSU0TZCOSSLTUXC0DFVQ&client_secret=D4HFGEORKEQMOQYUYPJF2X35DYAFNZFAYUU2SK5FR2FJXZLO&v=20130815");
            resp.success(function(data,status, headers,config){
                $scope.name0 = data.response.venues[0].name;
                $scope.name1 = data.response.venues[1].name;
                $scope.name2 = data.response.venues[2].name;
                $scope.name3 = data.response.venues[3].name;
                                $scope.address0 = data.response.venues[0].location.address+" ("+data.response.venues[0].location.crossStreet+"), "+data.response.venues[0].location.cc+", "+data.response.venues[0].location.city+", "+data.response.venues[0].location.state+", "+data.response.venues[0].location.postalCode+", "+data.response.venues[0].location.country+", ";
                $scope.contact0 = data.response.venues[0].contact.formattedPhone;
                $scope.address1 = data.response.venues[1].location.address+" ("+data.response.venues[1].location.crossStreet+"), "+data.response.venues[1].location.cc+", "+data.response.venues[1].location.city+", "+data.response.venues[1].location.state+", "+data.response.venues[1].location.postalCode+", "+data.response.venues[1].location.country+", ";
                $scope.contact1 = data.response.venues[1].contact.formattedPhone;
                $scope.address2 = data.response.venues[2].location.address+" ("+data.response.venues[2].location.crossStreet+"), "+data.response.venues[2].location.cc+", "+data.response.venues[2].location.city+", "+data.response.venues[2].location.state+", "+data.response.venues[2].location.postalCode+", "+data.response.venues[2].location.country+", ";
                $scope.contact2 = data.response.venues[2].contact.formattedPhone;
                $scope.address3 = data.response.venues[3].location.address+" ("+data.response.venues[3].location.crossStreet+"), "+data.response.venues[3].location.cc+", "+data.response.venues[3].location.city+", "+data.response.venues[3].location.state+", "+data.response.venues[3].location.postalCode+", "+data.response.venues[3].location.country+", ";
                $scope.contact3 = data.response.venues[3].contact.formattedPhone;
                            });
            resp.error(function(data,status,headers, config){
                window.alert("response not received 1, Something went wrong");
            });
        }
        $scope.calcWeather = function (){
             var place=document.getElementById('city').value;
            document.getElementById('weather-canvas').style.visibility="visible";
            document.getElementById('weatherheader').style.visibility="visible";
            var resp=$http.get("http://api.openweathermap.org/data/2.5/weather?q=place&units=imperial&appid=4abfba92c8f67fbedd1c22d789206dc8");
            resp.success(function(data,status, headers,config){
                $scope.sourcetemp = data.main.temp+"` F";
                $scope.sourcedes=data.weather[0].main;
                                
                $scope.sourcewind=data.wind.speed+" miles/hour";
                console.log("data :"+ $scope.sourcetemp);
            });
            resp.error(function(data,status,headers, config){
                window.alert("response not received 1, Something went wrong");
            });
        }

    });
