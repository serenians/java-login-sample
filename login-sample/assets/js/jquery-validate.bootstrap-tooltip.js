/**
 * @preserve
 * jQuery Validation Bootstrap Tooltip extention v0.10.2
 *
 * https://github.com/Thrilleratplay/jQuery-Validation-Bootstrap-tooltip
 *
 * Copyright 2016 Tom Hiller
 * Released under the MIT license:
 * http://www.opensource.org/licenses/mit-license.php
 */
(function ($) {
  var bsMajorVer = 0;
  var bsMinorVer = 0;

  $.extend(true, $.validator, {
    prototype: {
      defaultShowErrors: function () {
        var _this = this;
        var bsVersion = $.fn.tooltip.Constructor.VERSION;

        // Try to determine Bootstrap major and minor versions
        if (bsVersion) {
          bsVersion = bsVersion.split('.');
          bsMajorVer = parseInt(bsVersion[0]);
          bsMinorVer = parseInt(bsVersion[1]);
        }

        $.each(this.errorList, function (index, value) {
            //If Bootstrap 3.3 or greater
            var $currentElement;
            if (bsMajorVer === 3 && bsMinorVer >= 3) {

                // ensure we have tooltip_options for the specific field before we check for the appending Element
                if (_this.settings.tooltip_options) {
                    if (_this.settings.tooltip_options[value.element.name]) {
                        var appendElement = _this.settings.tooltip_options[value.element.name].appendingElement;
                    }
                }

                if (appendElement) {
                    $currentElement = $(appendElement);
                } else {
                    $currentElement = $(value.element);
                }
            
            if ($currentElement.data('bs.tooltip') !== undefined) {
                
              $currentElement.data('bs.tooltip').options.title = value.message;
            } else {
              $currentElement.tooltip(_this.applyTooltipOptions(value.element, value.message));
            }

            $($currentElement).removeClass(_this.settings.validClass)
                            .addClass(_this.settings.errorClass)
                            .tooltip();
            } else {

                $(value.element).removeClass(_this.settings.validClass)
                            .addClass(_this.settings.errorClass)
                            .tooltip(bsMajorVer === 4 ? 'dispose' : 'destroy')
                            .tooltip(_this.applyTooltipOptions(value.element, value.message))
                            .tooltip();
          }

          if (_this.settings.highlight) {
            _this.settings.highlight.call(_this, value.element, _this.settings.errorClass, _this.settings.validClass);
          }
        });

        $.each(_this.validElements(), function (index, value) {

            // ensure we have tooltip_options for the specific field before we check for the appending Element
            if (_this.settings.tooltip_options) {
                if (_this.settings.tooltip_options[value.name]) {
                    var appendElement = _this.settings.tooltip_options[value.name].appendingElement;
                }
            }

            if (appendElement) {
                $(appendElement).removeClass(_this.settings.errorClass)
                    .addClass(_this.settings.validClass)
                    .tooltip(bsMajorVer === 4 ? 'dispose' : 'destroy');
            } else {
                $(value).removeClass(_this.settings.errorClass)
                      .addClass(_this.settings.validClass)
                      .tooltip(bsMajorVer === 4 ? 'dispose' : 'destroy');
            }

          if (_this.settings.unhighlight) {
            _this.settings.unhighlight.call(_this, value, _this.settings.errorClass, _this.settings.validClass);
          }
        });
      },

      applyTooltipOptions: function (element, message) {
        var defaults;

        if (bsMajorVer === 4) {
          defaults = $.fn.tooltip.Constructor.Default;
        } else if (bsMajorVer === 3) {
          defaults = $.fn.tooltip.Constructor.DEFAULTS;
        } else {
          // Assuming BS version 2
          defaults = $.fn.tooltip.defaults;
        }

        var options = {
          /* Using Twitter Bootstrap Defaults if no settings are given */
          animation: $(element).data('animation') || defaults.animation,
          html: $(element).data('html') || defaults.html,
          placement: $(element).data('placement') || defaults.placement,
          selector: $(element).data('selector') || defaults.selector,
          title: $(element).attr('title') || message,
          trigger: 'hover', //$.trim('manual ' + ($(element).data('trigger') || '')),
          delay: $(element).data('delay') || defaults.delay,
          container: $(element).data('container') || defaults.container,
          viewport: $(element).data('viewport') || defaults.viewport,
        };


        if (this.settings.tooltip_options && this.settings.tooltip_options[element.name]) {
            //if (this.settings.tooltip_options && this.settings.tooltip_options[element.name].appendingElement) {
            //    $.extend(options, this.settings.tooltip_options[element.name].appendingElement);
            //} else {
                $.extend(options, this.settings.tooltip_options[element.name]);
            //}
       
        }
        /* jshint ignore:start */
        if (this.settings.tooltip_options && this.settings.tooltip_options['_all_']) {
          $.extend(options, this.settings.tooltip_options['_all_']);
        }
        /* jshint ignore:end */
        return options;
      },
    },
  });
}(jQuery));
