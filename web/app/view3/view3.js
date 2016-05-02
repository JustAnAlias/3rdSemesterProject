'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
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
            
            

            InfoFactory.setResponse(JSON.stringify($scope.book));
//             console.log(InfoFactory.getResponse());
//             $scope.data = response;
//             console.log($scope.data);
            
//            $http.post("api/flightreservation", JSON.stringify($scope.book))
//                    .success(function (response) {
//                        InfoFactory.setResponse(response);
//                        console.log(InfoFactory.getResponse());
//                        $scope.data = response;
//                        console.log($scope.data);
//                    })
//                    .error(function (response) {
//                        $scope.data = "error";
//                    });
        };
        
    }]);
 