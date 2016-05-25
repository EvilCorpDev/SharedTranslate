$(function() {
	var path = /(\/\w+)(\/\w+)?/g;
	var matches = path.exec(window.location.pathname);
	console.log(matches[1].substr(1));
	require([matches[1].substr(1), 'jquery'], function(configurer, $) {
		hoganRenderer.renderTemplate(configurer, matches);
	});
});

var hoganRenderer = {

	renderTemplate: function(configurer, matches) {
		var self = this;
		$.get(matches[1] + '/content/', function(templateRaw) {
			configurer.getData(matches, self.appendContent, templateRaw);
		});
	},

	appendContent: function(data, templateRaw) {
		var template = Hogan.compile(templateRaw);
		$('.main-content').append(template.render(data));
		$.get('/user/data', function(data) {
			if(data) {
				$('.base-html___login').text(data.login);
				$('.base-html___user-panel').show();
			}
		});
	}
}