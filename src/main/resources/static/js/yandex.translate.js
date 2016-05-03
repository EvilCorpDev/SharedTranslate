define(
	'yandex.translate',
	['jquery'],
	function($) {
		return translator;
	}
);

var translator = {

	api_key: 'trnsl.1.1.20160427T110355Z.bc8794f856d8d76f.ebf0b505dc10fc85d2414338fb977f16ca9d6d75',
	uri: 'https://translate.yandex.net/api/v1.5/tr.json/translate?',

	translate: function(text, callback, link, lang='ru') {
		$.post(this.getUri(text, lang), function(translationData) {
			callback(translationData, link)
		});
	},

	getUri: function(text, lang = 'ru') {
		return this.uri + 'key=' + this.api_key + '&text=' + text + '&lang=' + lang;
	}
}