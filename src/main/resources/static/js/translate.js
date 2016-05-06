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
				callback(data, templateRaw)
				self.afterRender();
				localStorage.setItem('article', JSON.stringify(data.sentences));
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
			$('.translate-page___original-id').attr('id', $(self.$sentence).attr('id'))
			self.showTranslations($(self.$sentence).attr('id'));
		});
		$('.translate-page___yandex-translate').on('click', function() {
			translator.translate(self.$sentence.find('.translate-page___content').text(), 
				self.translatorCallback, self);
			$('.translate-page___original-id').attr('id', $(self.$sentence).attr('id'))
			self.showTranslations($(self.$sentence).attr('id'));
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
	},

	showTranslations: function(id) {
		var article = JSON.parse(localStorage.getItem('article'));
		var translations = article.filter(function(sentence) {
			return sentence.id == id;
		})[0].translations;

		if(translations.length == 0) {
			$('.translate-page___no-translation').text('No translation yet');
		} else {
			$('.translate-page___no-translation').hide();
			translations.forEach(function(item) {
				console.log(item);
				$('.translate-page___translated').append('<div class="translate-page___translation">' 
					+ item.translation + '<div class="author">' + item.author + '</div></div>')
			});
		}
	}
}