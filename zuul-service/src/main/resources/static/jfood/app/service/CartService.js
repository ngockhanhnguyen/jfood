app.service("CartService", ["$http", function ($http) {
    
        var baseUrl = "http://localhost:2708/api/cart";
    
//      var baseUrl = "http://localhost:2008";
        this.getCart = function getProducts(cartUrl) {
            return $http({
                method: "GET",
                url: cartUrl
            });
        }

        this.createCart = function createCart() {
            return $http({
                method: "POST",
                url: baseUrl + "/v1/cart",
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                }
            });
        }
        
        this.updateProduct = function updateProduct(product) {
            return $http({
                method: "PUT",
                url: baseUrl + "/v1/products/" + product.id,
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                data: {
                    name: product.name,
                    description: product.description,
                    enabled: true,
                    price: product.price,
                    categoryId: product.categoryId
                }
            });
        }
        
        this.deleteProduct = function deleteProduct(productId) {
            return $http({
                method: "DELETE",
                url: baseUrl + "/v1/products/" + productId,
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "Accept-type": "application/json; charset=utf-8"
                }
            });
        }

        this.addPrimaryImage = function addPrimaryImage(product) {
            var url = baseUrl + "/v1/images/product/" + product.id;

            console.log(product.primaryImage);
            var data = new FormData();
            data.append('primaryImage', product.primaryImage);

            var config = {
                transformRequest: angular.identity,
                transformResponse: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            }

            return $http.post(url, data, config);
        }

        this.addOtherImage = function addOtherImage(productId, otherImage) {
            var url = baseUrl + "/v1/images/other/product/" + productId;

            var data = new FormData();
            data.append('otherImage', otherImage);

            var config = {
                transformRequest: angular.identity,
                transformResponse: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            }

            return $http.post(url, data, config);
        }
        
        this.searchProductByName = function searchProductByName(productName) {
            return $http({
                method: "GET",
                url: baseUrl + "/v1/products/search?key=" + productName,
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "Accept-type": "application/json; charset=utf-8"
                }
            });
        }

//    this.updateCategory = function updateCategory(category) {
//      return $http({
//          method: "PUT",
//          url: "http://localhost:2708/api/product/v1/categories/" + category.id,
//          headers: {
//                "Content-type": "application/json; charset=utf-8"
//            },
//          data : {
//                title : category.title,
//                description: category.description,
//                enabled: true
//            }
//      });
//    }
//    
//    this.deleteCategory = function deleteCategory(categoryId) {
//      return $http({
//          method: "DELETE",
//          url: "http://localhost:2708/api/product/v1/categories/" + categoryId,
//          headers: {
//                "Content-type": "application/json; charset=utf-8",
//                "Accept-type" : "application/json; charset=utf-8"
//            },
//          data : {
//          }
//        });
//    }
//    
//    this.searchCategoryByName = function searchCategoryByName(categoryName) {
//      return $http({
//          method: "GET",
//          url: "http://localhost:2708/api/product/v1/categories/search?key=" + categoryName,
//          headers: {
//                "Content-type": "application/json; charset=utf-8",
//                "Accept-type" : "application/json; charset=utf-8"
//            }
//        });
//    }
    }]);