'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {

                $scope.getBookings = function () {
                    $http.get("api/reservation/all")
                            .success(function (response) {
                                $scope.data = response;
                                $scope.getBookings();
                            })
                            .error(function (response) {
                                $scope.data = "error";
                            });
                };
                $scope.getBookings();


            }]);