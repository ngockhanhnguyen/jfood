var app = angular.module("jfood", ["ngResource", 'ngRoute',"ngCookies"]);

app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "main.html",
                controller: "mainCtrl"
            })
            .when("/product/:categoryId", {
                templateUrl: "products.html",
                controller: "productCtrl"
            })
            .when("/product_detail/:productId", {
            	templateUrl: "product_detail.html",
            	controller: "productDetailCtrl"
            })
            .when("/login", {
            	templateUrl: "login.html",
            	controller: "loginCtrl"
            })
            .when("/register", {
            	templateUrl: "register.html",
            	controller: "registerCtrl"
            })
            .when("/cart", {
            	templateUrl: "cart.html",
            	controller: "cartCtrl"
            })
            .when("/checkout", {
            	templateUrl: "checkout.html",
            	controller: "checkOutCtrl"
            });
//            .when("/productList", {
//                templateUrl: "templates/productList.html",
//                controller: "productListCtrl"
//            })
//            .when("/order", {
//                templateUrl: "templates/order.html",
//                controller: "orderCtrl"
//            });
});
