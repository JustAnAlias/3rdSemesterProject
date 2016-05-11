'use strict';

angular.module('myApp.AddAirline', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/addAirline', {
    templateUrl: 'app/addAirline/addAirline.html',
    controller: 'AddAirline',
    controllerAs : 'ctrl'
  });
}])

.controller('AddAirline', ["$http", function($http) {
        var self = this;

        self.createAirline = function(){
            $http.post("api/airline", self.airline).success(function(){
               self.airline = {};
               alert("Airline created!");
               
            }).error(function(){
                alert("ERROR");
            });
            console.log(self.airline);
        };
}]);