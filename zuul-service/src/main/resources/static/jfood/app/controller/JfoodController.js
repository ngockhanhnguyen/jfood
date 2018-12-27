app.controller("jfoodCtrl", ["$scope", "CategoryService", "CartService", '$cookies', '$http', 
    function ($scope, CategoryService, CartService, $cookies, $http) {
//		var isLoginPage = window.location.href.indexOf("login") != -1;
//		  if(isLoginPage){
//		      if($cookies.get("access_token")){
//		          window.location.href = "index.html";
//		      }
//		  } else{
//		      if($cookies.get("access_token")){
//		          $http.defaults.headers.common.Authorization = 
//		            'Bearer ' + $cookies.get("access_token");
//		      } else{
//		          window.location.href = "login.html";
//		      }
//		}
	
		if ($cookies.get("access_token")){
	        $http.defaults.headers.common.Authorization = 
	          'Bearer ' + $cookies.get("access_token");
	        $scope.chuaLogin = false;
	    } else {
	    	$scope.chuaLogin = true;
	    }
		
		$scope.logout = function() {
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
				            window.location.href="index.html";
				            $scope.chuaLogin = true;
				            console.log("success");
		        		},
		        		function(data) {
		        			console.log("false");
						});
		}

		var cartUrl;
		
		$scope.cart = [];
		
		if ($cookies.get("cart_url")) {
			cartUrl = $cookies.get("cart_url");
			$scope.cart = _getCart(cartUrl);
		} else {
			_createCart();
		}
		
        
        function _createCart() {
        	CartService.createCart()
        			.then(function(response) {
        				cartUrl = response.headers("Location");
        				$cookies.put("cart_url", cartUrl);
        				$scope.cart = response.data;
        			}, function(reason) {
        				
        			}, function(value) {
        				
        			})
        }
        
        function _getCart(cartUrl) {
        	CartService.getCart(cartUrl)
        			.then(function(response) {
        				$scope.cart = response.data;
        				console.log($scope.cart);
        			}, function(reason) {
        				
        			}, function(value) {
        				
        			})
        }

        _refreshCategoryData();

        function _success(res) {
            _refreshCategoryData();
            _clearFormData();
        }

        function _error(res) {

        }

        function _refreshCategoryData() {
            CategoryService.getCategories()
                    .then(
                            function success(response) {
                                $scope.categories = response.data;
                            },
                            function error(response) {

                            }
                    );
            $scope.nameSearch = '';
        }

        
    }]);