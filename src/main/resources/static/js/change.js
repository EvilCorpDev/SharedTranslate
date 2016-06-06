define(
	'change',
	['jquery'],
	function($) {
		return configurer;
	}
);

var configurer = {

	getData: function(matches, callback, templateRaw) {
		var self = this;
		$.ajax({
			url: matches[1] + '/data',
			success: function(data) {
				callback(data, templateRaw)
				self.afterRender();
			}
		});
	},

	afterRender: function() {
		
	}
}