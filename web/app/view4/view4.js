'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {

                $scope.hidden = true;
                $scope.shown = false;
                $scope.getBookings = function () {
                    $http.get("api/reservation/all")
                            .success(function (response) {
                                $scope.data = response;
                            })
                            .error(function (response) {
                                $scope.data = "error";
                            });
                };

                setTimeout(function () {
                    $scope.$apply(function () {
                        $scope.getBookings();
                        $scope.hidden = false;
                        $scope.shown = true;

                    });
                }, 1500);

                $scope.getBookings();


            }]);
        