app.controller("cartCtrl", ["$scope", "$route", "CategoryService", "ProductService", "CartService", '$cookies', '$http', 
    function ($scope, $route, CategoryService, ProductService, CartService, $cookies, $http) {
		
		_refreshProductData();

        function _refreshProductData() {
            ProductService.getProducts()
                    .then(function success(response) {
                        $scope.products = response.data;
                     }, function error(response) {

                     });
        }
        
        $scope.getProductById = function(id) {
        	for (var i in $scope.products) {
        		if ($scope.products[i].id == id) {
        			return $scope.products[i];
        		}
        	}
        }
		
		$scope.btnXoaClick = function(cartId, productId) {
        	
        	CartService.deleteItemFromCart(cartId, productId)
        		.then(function(response) {
        			window.location.reload();
        		}, function(reason) {
        			
        		}, function(value) {
        			
        		})
        }
		
		$scope.btnCapNhatClick = function(cartId, productId) {
        	console.log($scope.quantity);
        	CartService.updateQuantityOfItem(cartId, productId, $scope.quantity)
        		.then(function(response) {
        			window.location.reload();
        		}, function(reason) {
        			
        		}, function(value) {
        			
        		})
        }
	}
]);