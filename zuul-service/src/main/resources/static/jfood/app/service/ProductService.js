app.service("ProductService", ["$http", function ($http) {
	
		var baseUrl = "http://localhost:2708/api/product";
	
//		var baseUrl = "http://localhost:2008";
        this.getProducts = function getProducts() {
            return $http({
                method: "GET",
                url: baseUrl + "/v1/products/"
            });
        }
        
        this.getProductById = function getProductById(id) {
        	return $http({
                method: "GET",
                url: baseUrl + "/v1/products/" + id
            });
        }
        
        this.getProductsByCategory = function getProductsByCategory(categoryId) {
        	var req = {
        		method: "GET",
        		url: baseUrl + "/v1/products/category/" + categoryId
        	}
        	
        	return $http(req);
        }

        this.saveProduct = function saveProduct(product) {
            return $http({
                method: "POST",
                url: baseUrl + "/v1/products",
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                data: {
                    name: product.name,
                    description: product.description,
                    price: product.price,
                    enabled: true,
                    categoryId: product.categoryId
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
    }]);