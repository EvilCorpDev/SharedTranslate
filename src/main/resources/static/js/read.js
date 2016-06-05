define(
	'read',
	['jquery'],
	function($) {
		return configurer;
	}
);

var configurer = {

	getData: function(matches, callback, templateRaw) {
		var self = this;
		$.ajax({
			url: matches[0] + '/data',
			success: function(data) {
				callback(data, templateRaw)
				self.afterRender();
			}
		});
	},

	afterRender: function() {
		$('.reading-page___sentence').click(function() {
			var marginShift = ($(this).height() + $(this).find('.reading-page___original').height() + 10) * -1;
			$(this).find('.reading-page___original').css('margin-top', marginShift);
			$(this).find('.reading-page___original').toggle();
		});
	}
}