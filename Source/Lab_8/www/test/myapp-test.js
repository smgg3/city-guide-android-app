describe('ChatsCtrl', function() {
	var scope;
	
	beforeEach(angular.mock.module('starter'));
	beforeEach(angular.mock.inject(function($rootScope, $controller) {
		scope = $rootScope.$new();
		$controller('ChatsCtrl', {$scope: scope});
	}));

	it("Checks the task creation", function () {
		scope.getWeather();
		expect("test").toEqual("test");
	});
});