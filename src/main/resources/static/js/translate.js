define(
	'translate',
	['jquery', 'yandex.translate', 'alertify'],
	function($, translator, alertify) {
		configurer.alertify = alertify;
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
		this.appendRatingButtons(this);
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

	appendRatingButtons: function(self) {
		$('.translate-page___translated').on('click', '.translate-page___up-arrow', function(event) {
			self.changeRating(this, true);
		});

		$('.translate-page___translated').on('click', '.translate-page___down-arrow', function(event) {
			self.changeRating(this, false);
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
		this.showTranslations($(this.$sentence).attr('id'));
	},

	translatorCallback: function(translationData, self) {
		$('.translate-page___selected-sentence').text(self.$sentence.find('.translate-page___content').text());
		$('.translate-page___translate-area').text(translationData.text[0]);
		$('.translate-page___translate-area').focus();
	},

	showTranslations: function(id) {
		$('.translate-page___translated').empty();
		$('.translate-page___original-id').val($(this.$sentence).attr('id'));
		var self = this;
		var article = JSON.parse(localStorage.getItem('article'));
		var translations = article.filter(function(sentence) {
			return sentence.id == id;
		})[0].translations;

		if(translations.length == 0) {
			$('.translate-page___no-translation').show();
			$('.translate-page___no-translation').text('No translation yet');
		} else {
			$('.translate-page___no-translation').hide();
			translations.sort(function(first, next) {
				return first.rating > next.rating ? 0 : 1;
			});
			console.log(translations);
			for(i = 0; i < translations.length; i++) {
				self.appendTranslationItem(translations[i]);
			}
		}
	},

	appendTranslationItem: function(item) {
		$.get('/translate/translation-template', function(templateRaw) {
			var template = Hogan.compile(templateRaw);
			$('.translate-page___translated').append(template.render(item));
		});
	},

	changeRating: function(element, increase) {
		var  data = {login: $(element).siblings('.translate-page___author').text().trimLeft(),
					 originalId: $('.translate-page___original-id').val(),
					 increase: increase};
		$.post('/translation/rate', data, function(result) {
			console.log(result)
		});
	}
}