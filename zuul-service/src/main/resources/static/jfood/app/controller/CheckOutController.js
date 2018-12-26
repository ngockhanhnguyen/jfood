app.controller("checkOutCtrl", ["$scope", "$route", "CategoryService", "ProductService", "CartService", '$cookies', '$http', 
    function ($scope, $route, CategoryService, ProductService, CartService, $cookies, $http) {
		  
		  if(!$cookies.get("access_token")) {
			  window.location.href = "index.html#!/login";
		  } 
		  
		  $scope.cart;
		  $scope.products;
		  
		  _getCart($cookies.get("cart_url"));
		  
		  function _getCart(cartUrl) {
	        	CartService.getCart(cartUrl)
	        			.then(function(response) {
	        				$scope.cart = response.data;
	        			}, function(reason) {
	        			}, function(value) {
	        				
	        			});
	      }
		  
		  ProductService.getProducts()
		      	.then(function(value) {
		      		$scope.products = value.data;
		      	}, function(reason) {
		      		
		      	}, function(value) {
		      		
		      	})
		      
		  $scope.getProductById = function(productId) {
		      	for (var i in $scope.products) {
		      		if ($scope.products[i].id == productId) {
		//      			console.log($scope.categories[i]);
		//      			console.log(products[i]);
		      			return $scope.products[i];
		      		}
		      	}
		  }
		  
		  $btnXacNhanClick() {
			  
		  }
		  
//		  $scope.districts;
//		  $http({
//			  method: "GET",
//			  url: "https://thongtindoanhnghiep.co/api/city/3/district",
//			  headers: { 
//				  'Access-Control-Allow-Origin': '', 
//				  'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT', 
//				  'Content-Type': 'application/x-www-form-urlencoded', 
//				  'Accept': '' 
//			  }
//		  }).then(function(response) {
//			  $scope.districts = response.data;
//		  }, function(reason) {
//		  	
//		  }, function(value) {
//		  	
//		  });
	}
]);