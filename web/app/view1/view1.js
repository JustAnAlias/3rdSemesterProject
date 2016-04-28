'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/view1', {
        templateUrl: 'app/view1/view1.html',
        controller: 'View1Ctrl'
    });
}])

.controller('View1Ctrl', ["$scope", "$http", function($scope, $http) {
    $scope.output = {};
    $scope.getFlights = function() {
        return $http({
            method: 'GET',
            url: 'http://angularairline-plaul.rhcloud.com/api/flightinfo/CPH/2016-01-01T00:00:00.000Z/2',
//            url: 'api/meta/' + $scope.from + '/' + $scope.dest + '/' + $scope.date + '/' + $scope.number,
            skipAuthorization: true,
            contentType: "application/json"
        }).success(function(data, status, headers, config) {
            $scope.output = data;
        }).
        error(function(data, status, headers, config) {
        })
    };




}])