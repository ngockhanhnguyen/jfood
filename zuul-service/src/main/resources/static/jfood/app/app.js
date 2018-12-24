var app = angular.module("jfood", ["ngResource", 'ngRoute',"ngCookies"]);

app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "main.html",
                controller: "mainCtrl"
            })
            .when("/product", {
                templateUrl: "products.html",
                controller: "productCtrl"
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
