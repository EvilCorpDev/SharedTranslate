$(function() {
	hoganRenderer.renderTemplate();
});

var hoganRenderer = {
	
	$sentence: undefined,
	speed: 300,

	renderTemplate: function() {
		var self = this;
		$.get(window.location.pathname + '/content/', function(templateRaw) {
			var template = Hogan.compile(templateRaw);
			$.get(window.location.pathname + '/data', function(data) {
				$('.main-content').append(template.render({"original":data}));
				self.appendHandlers(self);
				self.appendActionHandlers(self);
			});
		});
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