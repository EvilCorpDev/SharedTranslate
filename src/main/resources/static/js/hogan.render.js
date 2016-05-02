$(function() {
	require([window.location.pathname.substr(1), 'jquery'], function(configurer, $) {
		hoganRenderer.renderTemplate(configurer);
	});
});

var hoganRenderer = {

	renderTemplate: function(configurer) {
		var self = this;
		$.get(window.location.pathname + '/content/', function(templateRaw) {
			var template = Hogan.compile(templateRaw);
			$.get(window.location.pathname + '/data', function(data) {
				$('.main-content').append(template.render({"original":data}));
				configurer.afterRender();
			});
		});
	},	
}