app.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

app.directive('multiFileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.multiFileModel);
                var isMultiple = attrs.multiple;
                var modelSetter = model.assign;
                element.bind('change', function () {
                    var values = [];
//            angular.forEach(element[0].files, function (item) {
//                values.push(item);
//            });
                    for (var i = 0; i < element[0].files.length; i++) {
                        values.push(element[0].files[i]);
                    }
                    scope.$apply(function () {
                        modelSetter(scope, values);
                    });
                });
            }
        };
    }]);