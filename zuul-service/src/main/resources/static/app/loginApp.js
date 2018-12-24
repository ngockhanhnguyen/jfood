var loginApp = angular.module('loginApp', ["ngResource","ngRoute","ngCookies"]);


loginApp.controller('loginCtrl', 
		  function($scope, $resource, $http, $httpParamSerializer, $cookies) {
	
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
	
			$scope.loginFail = false;
		     
		    $scope.data = {
		        grant_type:"password", 
		        username: "", 
		        password: ""
//		        client_id: "jfood"
		    };
		    
		    
		    $scope.encoded = btoa("jfood:1234");
		     
		    $scope.login = function() {   
		    	
		    	var formData = new FormData();
	            formData.append("grant_type", "password");
	            formData.append("username", $scope.data.username);
	            formData.append("password", $scope.data.password);
		        var req = {
		            method: 'POST',
		            url: "http://quangduy:2108/oauth/token?" + "grant_type=password&username=" + 
		            		$scope.data.username + "&password=" + $scope.data.password,
		            headers: {
		                "Authorization": "Basic " + $scope.encoded,
		                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
		            },
//		            data: $httpParamSerializer($scope.data)
		            
		        }
		        $http(req).then(
		        		function(data){
				            $http.defaults.headers.common.Authorization = 
				              'Bearer ' + data.data.access_token;
				            $cookies.put("access_token", data.data.access_token);
				            window.location.href="index.html";
		        		},
		        		function(data) {
							$scope.loginFail = true;
						});   
		   }    
		});