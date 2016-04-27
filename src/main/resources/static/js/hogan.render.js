$(function() {
	hoganRenderer.renderTemplate();
});

var hoganRenderer = {
	renderTemplate: function() {
		$.get(window.location.pathname + '/content/', function(templateRaw) {
			var template = Hogan.compile(templateRaw);
			$.get(window.location.pathname + '/data', function(data) {
				$('.main-content').append(template.render({"data":data}));
				console.log(template.render(data))
			});
		});
	},
}