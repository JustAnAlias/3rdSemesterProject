'use strict';

angular.module('myApp.login', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/login', {
                    templateUrl: 'app/login/login.html',
                    controller: 'LoginCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('LoginCtrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {
                // Calendar

                $('.form').find('input, textarea').on('keyup blur focus', function (e) {

                    var $this = $(this),
                            label = $this.prev('label');

                    if (e.type === 'keyup') {
                        if ($this.val() === '') {
                            label.removeClass('active highlight');
                        } else {
                            label.addClass('active highlight');
                        }
                    } else if (e.type === 'blur') {
                        if ($this.val() === '') {
                            label.removeClass('active highlight');
                        } else {
                            label.removeClass('highlight');
                        }
                    } else if (e.type === 'focus') {

                        if ($this.val() === '') {
                            label.removeClass('highlight');
                        }
                        else if ($this.val() !== '') {
                            label.addClass('highlight');
                        }
                    }

                });

                $('.tab a').on('click', function (e) {

                    e.preventDefault();

                    $(this).parent().addClass('active');
                    $(this).parent().siblings().removeClass('active');

                    target = $(this).attr('href');

                    $('.tab-content > div').not(target).hide();

                    $(target).fadeIn(600);

                });


                //Create user
                var self = this;

                self.createUser = function () {
                    $http.post("api/create", self.user).success(function () {
                        self.user = {};
                        alert("User created!");

                    }).error(function () {
                        alert("ERROR");
                    });
                    console.log(self.user);
                };

                $scope.redirect = function (url, refresh) {
                    if (refresh || $scope.$$phase) {
                        $window.location.href = url;
                    } else {
                        $location.path(url);
                        $scope.$apply();
                    }
                }

            }]);