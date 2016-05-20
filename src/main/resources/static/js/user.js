define(
	'user',
	['jquery'],
	function($) {
		return configurer;
	}
);

var configurer = {

	getData: function(matches, callback, templateRaw) {
		callback({}, templateRaw);
		this.afterRender();
	},

	afterRender: function() {
		
	}
}