define(
	'translate',
	['jquery'],
	function($) {
		return configurer;
	}
);

var configurer = {

	$sentence: undefined,
	speed: 300,
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
	},

	showElement: function(element) {
		this.$sentence = $(element).parent();
		this.$sentence.find('.translate-page___actions').show(this.speed);
		$(element).addClass('translate-page___highlighted-text');
	}
}