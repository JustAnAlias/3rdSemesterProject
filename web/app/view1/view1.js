'use strict';
function populate(s1,s2){
	var s1 = document.getElementById(s1);
	var s2 = document.getElementById(s2);
	s2.innerHTML = "";
	if(s1.value == "CPH"){
		var optionArray = ["|","SXF|Berlin","BCN|Barcelona","STN|London","CDG|Paris"];
	} else if(s1.value == "SXF"){
		var optionArray = ["|","CPH|Copenhagen","BCN|Barcelona","STN|London","CDG|Paris"];
	} else if(s1.value == "BCN"){
		var optionArray = ["|","CPH|Copenhagen","SXF|Berlin","STN|London","CDG|Paris"];
        } else if(s1.value == "STN"){
		var optionArray = ["|","CPH|Copenhagen","SXF|Berlin","BCN|Barcelona","CDG|Paris"];
        }else if(s1.value == "CDG"){
		var optionArray = ["|","CPH|Copenhagen","SXF|Berlin","BCN|Barcelona","STN|London"];
        }
	for(var option in optionArray){
		var pair = optionArray[option].split("|");
		var newOption = document.createElement("option");
		newOption.value = pair[0];
		newOption.innerHTML = pair[1];
		s2.options.add(newOption);
	}
}
angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/view1', {
        templateUrl: 'app/view1/view1.html',
        controller: 'View1Ctrl'
    });
}])

.controller('View1Ctrl', ["$scope", "$http", function($scope, $http) {
//    $scope.output = {};
    $scope.getFlights = function() {
        return $http({
            method: 'GET',
//            url: 'http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-01T00:00:00.000Z/2',
            url: 'api/meta/' + $scope.from + '/' + $scope.dest + '/' + $scope.date + '/' + $scope.number,
            skipAuthorization: true,
            contentType: "application/json"
        }).success(function(data, status, headers, config) {
            $scope.output = data;
            if($scope.output.length === 0){
//                alert("No flights found. Please try again.");
            }
        }).
        error(function(data, status, headers, config) {
        })
    };




}])