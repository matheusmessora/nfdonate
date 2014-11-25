$(function () {

    // initialize PANDOX SYSTEM
    PANDOX.SYSTEM.init();

});

var PANDOX = PANDOX || {};

/*=====================================================================================================
 * Pandox SYSTEM Module
 *======================================================================================================*/
PANDOX.SYSTEM = function () {
    var init = function () {
        console.log("PANDOX.SYSTEM.init");
    };

    var bindDateButton = function(){
        $('#datetimepicker6').datetimepicker({
            pickTime: false,
            useCurrent: true,
             language:'pt-br'
        });
    };

    return {
        init: init,
        bindDateButton: bindDateButton
    }

}();
