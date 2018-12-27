app.controller("mainCtrl", ["$scope", "ProductService", "CartService", '$cookies','$http', 
    function ($scope, ProductService, CartService, $cookies, $http) {
//		$scope.categories = [];
	
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
	
		$scope.page = 1;
		$scope.size = 8;
		
		
        _refreshProductData();
        


        function _success(res) {
            _refreshProductData();
        }

        function _error(res) {

        }

        function _refreshProductData() {
            ProductService.getProducts()
                    .then(function success(response) {
                        $scope.products = response.data;
                        console.log($scope.products);
                     }, function error(response) {

                     });
        }


        $scope.btnLuuClick = function () {
        }
        
        $scope.btnResetClick = function () {

        }
        
        $scope.getCategoryById = function(id) {
        	for (var i in $scope.categories) {
        		if ($scope.categories[i].id == id) {
//        			console.log($scope.categories[i]);
        			return $scope.categories[i].title;
        		}
        	}
        }
        
        $scope.btnSuaOnTableClick = function(product) {
        }
        
        $scope.btnSuaOnFormClick = function(product) {
        }
        
        $scope.btnXoaClick = function (productId) {
        }
        
        $scope.searchProductByName = function () {
            ProductService.searchProductByName($scope.nameSearch)
                    .then(
                            function success(response) {
                                $scope.products = response.data;
                            },
                            function error(response) {
                            }
                    );
        }
    }
]);
    