var signUpApp = angular.module('signUpApp', ["ngResource","ngRoute","ngCookies"]);

// So sánh mật khẩu trùng khớp
signUpApp.directive('passwordMatch', [function () {
	return {
	restrict: 'A',
	scope:true,
	require: 'ngModel',
	link: function (scope, elem , attrs,control) {
	var checker = function () {
	
	 // lấy giá trị của trường mật khẩu
	 var e1 = scope.$eval(attrs.ngModel);
	
	 // lấy giá trị của xác nhận mật khẩu
	 var e2 = scope.$eval(attrs.passwordMatch);
	 return e1 == e2;
	};
	scope.$watch(checker, function (n) {
	
	 // thiết lập form control
	 control.$setValidity("unique", n);
	 });
	}
	};
}]);


signUpApp.controller('signUpCtrl', 
		  function($scope, $resource, $http, $httpParamSerializer, $cookies) {
			$scope.passwordIsConfirm = false;
			$scope.registerSuccess = false;
			$scope.registerFailed = false;
	
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
			  
			var baseUrl = "http://quangduy:2108";
			
			$scope.checkConfirmPassword = function() {
				passwordIsConfirm = true;
				console.log("here");
			}
			  
			$scope.register = function() {
//				var password = new String($scope.user.password).valueOf();
//				
//				var confirmPassword = new String($scope.user.confirmPassword).valueOf();
//				console.log(password + " " + confirmPassword);
//				if (password != confirmPassword)
					$scope.passwordIsConfirm = true;
				
				var req = {
						method: "POST",
						url: baseUrl + "/v1/account/admin",
						headers: {
							"Content-type": "application/json; charset=utf-8",
							"Authorization": "Bearer " + $cookies.get("access_token")
						},
						data: {
							"firstName": $scope.user.firstName,
							"lastName": $scope.user.lastName,
							"phone": $scope.user.phone,
							"email": $scope.user.email,
							"password": $scope.user.password
						}
				}
				
				$http(req).then(function(response) {
					$scope.registerSuccess = true;
					$cookies.remove("access_token");
		            window.location.href="login.html";
				}, function(response) {
					$scope.registerFailed = true;
					console.log(response);
				});
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
				            var expireDate = new Date (new Date().getTime() + (1000 * data.data.expires_in));
				            $cookies.put("access_token", data.data.access_token, {'expires': expireDate});
				            window.location.href="index.html";
		        		},
		        		function(data) {
							$scope.loginFail = true;
						});   
		   }    
		});