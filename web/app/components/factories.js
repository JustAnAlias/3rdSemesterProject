'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
  factory('InfoFactory', function () {
    var info = "Hello World from a Factory";
    var booking = {};
    var response = {};
    
    return {
    getInfo: function getInfo() {
        return info;
    },
    setInfo: function setInfo(someData) {
        info = someData;
    },
    setBooking: function (bookingIn) {
        booking = bookingIn;
    },
    getBooking: function () {
        return booking;
    },

    setResponse: function (responseIn) {
        response = responseIn;
    },
    getResponse: function () {
        return response;
}
};

    
  });