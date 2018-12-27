app.controller("loginCtrl", ["$scope", "CategoryService", "ProductService", 
	'$cookies','$http', "$routeParams",
    function ($scope, CategoryService, ProductService, $cookies, $http, $routeParams) {
	
	if($cookies.get("access_token")){
        $http.defaults.headers.common.Authorization = 
          'Bearer ' + $cookies.get("access_token");
        window.location.href = "index.html";
    }
	
	$scope.loginFail = false;
    
    
    
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
//            data: $httpParamSerializer($scope.data)
            
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
    }
]);
    