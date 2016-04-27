$(function() {
	hoganRenderer.renderTemplate();
});

var hoganRenderer = {
	renderTemplate: function() {
		$.get(window.location.pathname, function(data) {
			var template = Hogan.compile(data);
			$.get(window.location.pathname + '/data', function(data) {
				$('.container').html = template.render(data);
			});
		});
	},
}