app.controller("cartCtrl", ["$scope", "$route", "CategoryService", "ProductService", "CartService", '$cookies', '$http', 
    function ($scope, $route, CategoryService, ProductService, CartService, $cookies, $http) {
		function initCartTable() {
			
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