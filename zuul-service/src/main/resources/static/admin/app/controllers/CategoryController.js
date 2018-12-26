app.controller("categoryCtrl", ["$scope", "CategoryService", '$cookies', '$http', 
    function ($scope, CategoryService, $cookies, $http) {
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
        $scope.size = 5;
        $scope.keySearch = '';
        $scope.disableLuuBtn = false;
        $scope.addSuccess = false;
        $scope.updateSuccess = false;
        $scope.categoryForm = {
            id: "",
            title: "",
            description: ""
        };



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

        function _clearFormData() {
            $scope.categoryForm.title = "";
            $scope.categoryForm.description = "";
            $scope.formCategory.$setPristine();
        }

        $scope.btnLuuClick = function () {
            CategoryService.saveCategory($scope.categoryForm)
                    .then(_success, _error);
            $scope.addSuccess = true;
            $scope.updateSuccess = false;
        }

        $scope.btnSuaOnTableClick = function (category) {
            $scope.categoryForm.id = category.id;
            $scope.categoryForm.title = category.title;
            $scope.categoryForm.description = category.description;

            $scope.formCategory.$setDirty();
            $scope.addSuccess = false;
            $scope.updateSuccess = false;
        }

        $scope.btnSuaOnFormClick = function () {
            CategoryService.updateCategory($scope.categoryForm)
                    .then(_success, _error);
            $scope.disableLuuBtn = !$scope.disableLuuBtn;
            $scope.addSuccess = false;
            $scope.updateSuccess = true;
        }

        $scope.btnResetClick = function (category) {
            $scope.categoryForm.title = "";
            $scope.categoryForm.description = "";
            $scope.formCategory.$setPristine();
            $scope.addSuccess = false;
            $scope.updateSuccess = false;
        }

        $scope.btnXoaClick = function (categoryId) {
            CategoryService.deleteCategory(categoryId)
                    .then(_success, _error);
        }

        $scope.searchCategoryByName = function () {
            CategoryService.searchCategoryByName($scope.nameSearch)
                    .then(
                            function success(response) {
                                $scope.categories = response.data;
                            },
                            function error(response) {
                            }
                    );
        }

        $scope.reverse = false;

        $scope.orderCategory = function (attribute) {
            $scope.attribute = attribute;
            $scope.reverse = !$scope.reverse;
        }
    }]);