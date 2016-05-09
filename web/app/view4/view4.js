'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {

                $scope.output = {};
                $scope.getBookings = function () {
                    return $http({
                        method: 'GET',
                        url: 'api/reservation/all',
                        skipAuthorization: true,
                        contentType: "application/json"
                    }).success(function (data, status, headers, config) {
                        $scope.output = data;
                    }).error(function (data, status, headers, config) {
                    })
                };
                
                $scope.getBookings();

    }]);