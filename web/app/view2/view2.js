'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/view2', {
        templateUrl: 'app/view2/view2.html',
        controller: 'View2Ctrl'
    });
}])

.controller('View2Ctrl', ["InfoFactory", "InfoService", "$scope", "$http", function(InfoFactory, InfoService, $scope, $http) {
                 // Calendar
                $scope.today = function () {
                    $scope.date = new Date();
                };

                $scope.today(); // Presets date to todays date
                $scope.clear = function () {
                    $scope.date = null;
                }; // date clear button

                $scope.minDate = new Date(); // Disable everything before todays date
                $scope.maxDate = new Date(2020, 1, 1); // Sets max date

                $scope.open = function ($event) {
                    $scope.status.opened = true;
                };

                $scope.setDate = function (year, month, day) {
                    $scope.date = new Date(year, month, day);
                };

                $scope.dateOptions = {
                    startingDay: 1 // Monday
                };

                $scope.status = {
                    opened: false
                };

                $scope.getFlights = function () {
                    var year = $scope.date.getFullYear();
                    var month = $scope.date.getMonth();
                    var day = $scope.date.getDate();
                    var date = new Date(year, month, day).toISOString();
                    return $http({
                        method: 'GET',
                        url: 'api/meta/' + $scope.from + '/' + '/' + date + '/' + $scope.number,
                        skipAuthorization: true,
                        contentType: "application/json"
                    }).success(function (data, status, headers, config) {
                        $scope.output = data;
                    }).error(function (data, status, headers, config) {
                    })
                };
                
                
            }])