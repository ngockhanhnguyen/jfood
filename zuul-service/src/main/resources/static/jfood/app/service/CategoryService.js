app.service("CategoryService", ["$http", function ($http) {
		
		var baseUrl = "http://localhost:2708/api/product";

		
        this.getCategories = function getCategories() {
            return $http({
                method: "GET",
                url: baseUrl + "/v1/categories/"
            });
        }
        
        this.getCategoryById = function getCategoryById(categoryId) {
        	return $http({
        		method: "GET",
        		url: baseUrl + "/v1/category/" + categoryId
        	});
        }

        this.saveCategory = function saveCategory(category) {
            return $http({
                method: "POST",
                url: baseUrl + "/v1/categories",
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                data: {
                    title: category.title,
                    description: category.description,
                    enabled: true
                }
            });
        }

        this.updateCategory = function updateCategory(category) {
            return $http({
                method: "PUT",
                url: baseUrl + "/v1/categories/" + category.id,
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                data: {
                    title: category.title,
                    description: category.description,
                    enabled: true
                }
            });
        }

        this.deleteCategory = function deleteCategory(categoryId) {
            return $http({
                method: "DELETE",
                url: baseUrl + "/v1/categories/" + categoryId,
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "Accept-type": "application/json; charset=utf-8"
                },
                data: {
                }
            });
        }

        this.searchCategoryByName = function searchCategoryByName(categoryName) {
            return $http({
                method: "GET",
                url: baseUrl + "/v1/categories/search?key=" + categoryName,
                headers: {
                    "Content-type": "application/json; charset=utf-8",
                    "Accept-type": "application/json; charset=utf-8"
                }
            });
        }
    }]);