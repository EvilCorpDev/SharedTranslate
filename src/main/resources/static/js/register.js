define(
	'register',
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
		$('.register-page___confirm').on('input', function() {
			if($('.register-page___confirm').val() === $('.register-page___password').val()) {
				$('.register-page___secure').show();
				$('.register-page___danger').hide();
			} else {
				$('.register-page___secure').hide();
				$('.register-page___danger').show();
			}
		});
	}

}