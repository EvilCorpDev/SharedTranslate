define(
	'translate',
	['jquery', 'yandex.translate'],
	function($, translator) {
		return configurer;
	}
);

var configurer = {

	$sentence: undefined,
	speed: 300,

	getData: function(matches, callback, templateRaw) {
		var self = this;
		console.log(matches[1] + '/data' + '?article=' + matches[2]);
		$.ajax({
			url: matches[1] + '/data' + '?article=' + matches[2].substr(1),
			success: function(data) {
				console.log('uspeh');
				callback(data, templateRaw)
				self.afterRender();
			}
		});
	},

	afterRender: function() {
		this.appendHandlers(this);
		this.appendActionHandlers(this);
	},

	appendHandlers: function(self) {
		$('.translate-page___content').on('click', function() {
			var element = this;
			var previous = $(self.$sentence).find('.translate-page___content');
			if(self.$sentence) {
				$(previous).removeClass('translate-page___highlighted-text');
				self.$sentence.find('.translate-page___actions').hide(self.speed, function() {
					if($(previous)[0] === $(element)[0]) {
						self.$sentence = undefined;
						return;
					}
					self.showElement(element);
				});
			} else {
				self.showElement(this);
			}
		});
	},

	appendActionHandlers: function(self) {
		$('.translate-page___manual-translate').on('click', function() {
			$('.translate-page___selected-sentence').text(self.$sentence.find('.translate-page___content').text());
			$('.translate-page___translate-area').focus();
		});
		$('.translate-page___yandex-translate').on('click', function() {
			translator.translate(self.$sentence.find('.translate-page___content').text(), 
				self.translatorCallback, self);
		});
	},

	showElement: function(element) {
		this.$sentence = $(element).parent();
		this.$sentence.find('.translate-page___actions').show(this.speed);
		$(element).addClass('translate-page___highlighted-text');
	},

	translatorCallback: function(translationData, self) {
		$('.translate-page___selected-sentence').text(self.$sentence.find('.translate-page___content').text());
		$('.translate-page___translate-area').text(translationData.text[0]);
		$('.translate-page___translate-area').focus();
	}
}