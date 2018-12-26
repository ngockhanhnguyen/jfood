app.controller("productCtrl", ["$scope", "CategoryService", "ProductService", '$cookies','$http', 
    function ($scope, CategoryService, ProductService, $cookies, $http) {
//		$scope.categories = [];
	
//		$scope.disableLuuBtn = false;
//		console.log($scope.disableLuuBtn && (!formProduct.$dirty || (formProduct.$dirty && formProduct.$invalid)));
	
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
	
		$scope.addSuccess = false;
	    $scope.updateSuccess = false;

        _loadCategorySelect();
        _refreshProductData();

        $scope.productForm = {
            id: "",
            name: "",
            price: "",
//			categoryId: $scope.categories[1].id,
            description: ""
        }

        function _loadCategorySelect() {
            CategoryService.getCategories()
                    .then(function (response) {
                        $scope.categories = response.data;
                        $scope.productForm.categoryId = $scope.categories[0].id;
                    }, function (reason) {

                    }, function (value) {

                    })
        }

        function _success(res) {
            _refreshProductData();
            _clearFormData();
        }

        function _error(res) {

        }

        function _refreshProductData() {
            ProductService.getProducts()
                    .then(function success(response) {
                        $scope.products = response.data;
//                        console.log($scope.products);
                     }, function error(response) {

                     });
            $scope.nameSearch = '';
        }

        function _clearFormData() {
            $scope.productForm.name = "";
            $scope.productForm.price = "";
            $scope.productForm.description = "";
            $scope.productForm.categoryId = $scope.categories[0].id;
            angular.forEach(
                    angular.element("input[type='file']"),
                    function (inputElem) {
                        angular.element(inputElem).val(null);
                    });
            $scope.formProduct.$setPristine();
        }

        $scope.btnLuuClick = function () {
            ProductService.saveProduct($scope.productForm)
                    .then(function suceess(response) {
                        $scope.productForm.id = response.data.id;
                        ProductService.addPrimaryImage($scope.productForm)
                        .then(function (response) {
                            console.log(response.data);
                        }, function (response) {
                            console.log(response.data);
                        });
                        
                        _refreshProductData();
                        _clearFormData();
                        
                    });
            

//		    for (var i = 0; i < $scope.productForm.otherImages.length; i++) {
//		        ProductService.addOtherImage($scope.productForm.id, $scope.productForm.otherImages[i])
//		                .then(function (response) {
//		                    console.log(response.data);
//		                }, function (response) {
//		                    console.log(response.data);
//		                });
//		    }
           
            $scope.addSuccess = true;
    	    $scope.updateSuccess = false;

        }
        
        $scope.btnResetClick = function () {
            _clearFormData();
            $scope.addSuccess = false;
    	    $scope.updateSuccess = false;

        }
        
        $scope.getCategoryById = function(id) {
//        	console.log(id);
        	for (var i in $scope.categories) {
        		if ($scope.categories[i].id == id) {
//        			console.log($scope.categories[i]);
        			return $scope.categories[i].title;
        		}
        	}
        }
        
        $scope.btnSuaOnTableClick = function(product) {
        	$scope.productForm.id = product.id;
            $scope.productForm.name = product.name;
            $scope.productForm.description = product.description;
            $scope.productForm.categoryId = product.categoryId;
            $scope.productForm.price = product.price;

            $scope.formProduct.$setDirty();
            $scope.addSuccess = false;
            $scope.updateSuccess = false;
//            window.location.href = "index.html#!product#formThemSua";
//            $scope.disableLuuBtn = true;
//    		console.log("*************" + (!formProduct.$dirty || (formProduct.$dirty && formProduct.$invalid)));
//    		console.log("*************" + ((!formProduct.$dirty || (formProduct.$dirty && formProduct.$invalid)) && $scope.disableLuuBtn));


        }
        
        $scope.btnSuaOnFormClick = function(product) {
        	ProductService.updateProduct($scope.productForm)
            		.then(_success, _error);
        	
            $scope.addSuccess = false;
            $scope.updateSuccess = true;
        }
        
        $scope.btnXoaClick = function (productId) {
            ProductService.deleteProduct(productId)
                    .then(_success, _error);
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
        
        $scope.reverse = false;

        $scope.orderProduct = function (attribute) {
            $scope.attribute = attribute;
            $scope.reverse = !$scope.reverse;
        }
    }
]);
    