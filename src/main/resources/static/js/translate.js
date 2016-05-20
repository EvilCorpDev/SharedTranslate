define(
	'translate',
	['jquery', 'yandex.translate', 'alertify'],
	function($, translator, alertify) {
		configurer.alertify = alertify;
		console.log(configurer.alertify);
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

		$('.translate-page___translate-form').submit(function(event) {
			event.preventDefault();
			var data = $(this).serializeArray();
			$.post('/translate/save', data, function(result) {
			}).done(function() {
				var item = {translation: $('.translate-page___translate-area').text(),
							author: $('.base-html___login').text()};
				self.appendTranslationItem(item);
				self.alertify.success('Translation was successfuly added')
			}).fail(function() {
				self.alertify.error('Sorry, something was wrong try latter')
			});
		});
	},

	appendActionHandlers: function(self) {
		$('.translate-page___manual-translate').on('click', function() {
			$('.translate-page___selected-sentence').text(self.$sentence.find('.translate-page___content').text());
			$('.translate-page___translate-area').focus();
			$('.translate-page___original-id').val($(self.$sentence).attr('id'));
		});
		$('.translate-page___yandex-translate').on('click', function() {
			translator.translate(self.$sentence.find('.translate-page___content').text(), 
				self.translatorCallback, self);
			$('.translate-page___original-id').val($(self.$sentence).attr('id'));
		});
	},

	showElement: function(element) {
		this.$sentence = $(element).parent();
		this.$sentence.find('.translate-page___actions').show(this.speed);
		$(element).addClass('translate-page___highlighted-text');
		this.showTranslations($(this.$sentence).attr('id'));
	},

	translatorCallback: function(translationData, self) {
		$('.translate-page___selected-sentence').text(self.$sentence.find('.translate-page___content').text());
		$('.translate-page___translate-area').text(translationData.text[0]);
		$('.translate-page___translate-area').focus();
	},

	showTranslations: function(id) {
		$('.translate-page___translated').empty();
		var self = this;
		var article = JSON.parse(localStorage.getItem('article'));
		var translations = article.filter(function(sentence) {
			return sentence.id == id;
		})[0].translations;

		if(translations.length == 0) {
			$('.translate-page___no-translation').text('No translation yet');
		} else {
			$('.translate-page___no-translation').hide();
			translations.forEach(function(item) {
				self.appendTranslationItem(item);
			});
		}
	},

	appendTranslationItem: function(item) {
		$('.translate-page___translated').append('<blockquote class="translate-page___translation">' 
					+ item.translation + '<cite class="translate-page___author"><a href="/user/' + item.author +'">' 
					+ item.author + '</a></cite></blockquote>');
	}
}