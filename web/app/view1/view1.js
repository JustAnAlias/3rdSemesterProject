'use strict';
function populate(s1, s2) {
    var s1 = document.getElementById(s1);
    var s2 = document.getElementById(s2);
    s2.innerHTML = "";
    if (s1.value == "CPH") {
        var optionArray = ["|", "SXF|Berlin", "BCN|Barcelona", "STN|London", "CDG|Paris"];
    } else if (s1.value == "SXF") {
        var optionArray = ["|", "CPH|Copenhagen", "BCN|Barcelona", "STN|London", "CDG|Paris"];
    } else if (s1.value == "BCN") {
        var optionArray = ["|", "CPH|Copenhagen", "SXF|Berlin", "STN|London", "CDG|Paris"];
    } else if (s1.value == "STN") {
        var optionArray = ["|", "CPH|Copenhagen", "SXF|Berlin", "BCN|Barcelona", "CDG|Paris"];
    } else if (s1.value == "CDG") {
        var optionArray = ["|", "CPH|Copenhagen", "SXF|Berlin", "BCN|Barcelona", "STN|London"];
    }
    for (var option in optionArray) {
        var pair = optionArray[option].split("|");
        var newOption = document.createElement("option");
        newOption.value = pair[0];
        newOption.innerHTML = pair[1];
        s2.options.add(newOption);
    }
}
angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl'
                });
            }])

        .controller('View1Ctrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {
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
                    $scope.hidden = true;
                      $scope.shown = false;
                    var year = $scope.date.getFullYear();
                    var month = $scope.date.getMonth();
                    var day = $scope.date.getDate();
                    var date = new Date(year, month, day).toISOString();
                    return $http({
                        method: 'GET',
                        url: 'api/meta/' + $scope.from + '/' + $scope.dest + '/' + date + '/' + $scope.number,
                        skipAuthorization: true,
                        contentType: "application/json"
                    }).success(function (data, status, headers, config) {
                        $scope.output = data;
                        $scope.hidden = false;
                      $scope.shown = true;
                    }).error(function (data, status, headers, config) {
                    })
                };
                
                // Booking
                // Variables
                var booking = {};
                var passenger = {};

                // Set variables
                $scope.setSeats = function (seats){
                    $scope.seats = seats;
                };
                $scope.setAttri = function (airline, flightId, destination, currentUser) {
                    booking.airlineName = airline;
                    booking.flightID = flightId;
                    booking.to = destination;
                    booking.reserveeName = currentUser;
                };
                $scope.setBook = function () {
                    booking = {
                        'from': '' + $scope.from,
                        'to': '' + $scope.to,
                        'flightID': $scope.flightToUse,
                        'airlineName': '',
                        'reserveeName': $scope.username,
                        'reservePhone': '',
                        'reserveeEmail': '',
                        'numberOfSeats': $scope.seats,
                        'passengers': []
                    };
                    passenger = {
                        'firstName': '',
                        'lastName': ''
                    };
                    // Loop through numberOfSeats to generate passengers
                    for (var i = 0; i < booking.numberOfSeats; i++) {
                        booking.passengers.push(Object.create(passenger));
                    };
                    InfoFactory.setBooking(booking);
                };




            }])