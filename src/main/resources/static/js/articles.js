define(
	'articles',
	['jquery'],
	function($) {
		return configurer;
	}
);

var configurer = {

	getData: function(matches, callback, templateRaw) {
		var self = this;
		$.ajax({
			url: matches[1] + '/data/1/10/',
			success: function(data) {
				callback({articles: data}, templateRaw)
				self.afterRender();
			}
		});
	},

	afterRender: function() {
		$('.articles-page___translate-link').each(function(index, element) {
			var href = $(element).attr('href');
			href = href.replace(/\s/g, '_');
			console.log(href);
			$(element).attr('href', href);
		})
	}

}