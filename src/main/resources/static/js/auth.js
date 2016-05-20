define(
	'auth',
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
		$('.auth-page___auth-form').submit(function(event) {
			event.preventDefault();
			var formData = $('.auth-page___form-content').find('#main-contact-form').serializeArray();
			$.post('auth', formData, function(data) {
				console.log(data);
				if(data.granted) {
					location.href = '/user'
				} else {
					$('.auth-page___error').css('display', 'flex')
				}
			});
		});
	}
}