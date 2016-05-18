define(
	'upload',
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
		this.appendFileChoserHandler(this);
	},

	appendFileChoserHandler: function(self) {
		$('.upload_page___file-btn').on('click', function(e) {
			e.preventDefault();
			$(this).siblings('.upload-page___file-chooser').click();
		});

		$('.upload-page___file-chooser').change(function(data) {
			var selectedFile = $(this).val();
			selectedFile = selectedFile.substr(selectedFile.lastIndexOf('\\') + 1)
			$(this).siblings('.upload_page___chosen-file').text(selectedFile);
		});
	}

}