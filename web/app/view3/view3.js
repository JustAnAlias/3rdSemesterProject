'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', ['$scope', '$http', 'InfoFactory', function ($scope, $http, InfoFactory) {
                $scope.book = InfoFactory.getBooking();

                console.log($scope.book);



                $scope.booking = function () {

                    console.log(($scope.book));
                    $scope.book.ReserveeName = $scope.username;
                    $scope.book.to_ = $scope.book.to;




                    $http({
                        method: 'POST',
                        url: 'api/reservation',
                        data: $scope.book
                    }).then(function successCallback(response) {
                        InfoFactory.setResponse(response);
                    }, function errorCallback(response) {
                        $scope.data = response;
                    });
                };
//
//
//
//                    $http.post("api/reservation" + $scope.book.flightID)
//
//                            .success(function (response) {
//                                InfoFactory.setResponse(response);
//                                console.log(InfoFactory.getResponse());
//                                $scope.data = response;
//                                console.log($scope.data);
//                            })
//                            .error(function (response) {
//                                $scope.data = "error";
//                            });


            }]);
 