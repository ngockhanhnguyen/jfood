var app = angular.module('jfood', ["ngResource", 'ngRoute',"ngCookies"]);


app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "templates/home.html"
            })
            .when("/category", {
                templateUrl: "templates/category.html",
                controller: "categoryCtrl"
            })
            .when("/product", {
                templateUrl: "templates/product.html",
                controller: "productCtrl"
            })
            .when("/productList", {
                templateUrl: "templates/productList.html",
                controller: "productListCtrl"
            })
            .when("/order", {
                templateUrl: "templates/order.html",
                controller: "orderCtrl"
            });
});

app.controller("jfoodCtrl", 
    function ($scope, $cookies, $http) {
	
	var isLoginPage = window.location.href.indexOf("login") != -1;
	  if(isLoginPage){
	      if($cookies.get("access_token")){
	          window.location.href = "index.html";
	      }
	  } else{
	      if($cookies.get("access_token")){
	          $http.defaults.headers.common.Authorization = 
	            'Bearer ' + $cookies.get("access_token");
	      } else{
	          window.location.href = "login.html";
	      }
	}
	  
    $scope.btnDangXuatClick = function() {
    	var req = {
	            method: 'POST',
	            url: "http://quangduy:2108/v1/account/logout",
	            headers: {
	                "Authorization": "Bearer " + $cookies.get("access_token"),
	                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
	            }
	            
	        }
	        $http(req).then(
	        		function(data){
	        			$cookies.remove("access_token");
			            window.location.href="login.html";
	        		},
	        		function(data) {
					});
    }
});
