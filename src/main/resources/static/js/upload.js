define(
	'upload',
	['jquery'],
	function($, translator) {
		return configurer;
	}
);

var configurer = {

	afterRender: function() {
		this.appendFileChoserHandler(this);
		//this.appendActionHandlers(this);
	},

	appendFileChoserHandler: function(self) {
		$('.upload_page___file-btn').on('click', function(e) {
			e.preventDefault();
			$('.upload-page___file-chooser').click();
		});

		$('.upload-page___file-chooser').change(function(data) {
			var selectedFile = $(this).val();
			selectedFile = selectedFile.substr(selectedFile.lastIndexOf('\\') + 1)
			$('.upload_page___chosen-file').text(selectedFile);
		});
	}

}