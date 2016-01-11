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
        PANDOX.FORM.init();
        PANDOX.DONATE.init();
        PANDOX.UI.init();
    };

    var bindDateButton = function () {
        $('#i-donate-date').datetimepicker({
            pickTime: false,
            useCurrent: true,
            language: 'pt-br'
        });
    };

    var isBlank = function (text) {
        return (!text || / ^ \s * $ /.test(text));
    };;

    return {
        init: init,
        bindDateButton: bindDateButton,
        isBlank: isBlank
    }

}();


PANDOX.UI = function () {
    
    var init = function() {
        bindDoarBtn();
        bindMenuDoarBtn();
        bindMenuContatoBtn();
        
    };
    
    var bindMenuContatoBtn = function() {
        $('.contactUsMenuBtn').click(function (e) {
            e.preventDefault();
            scrollToDivName("contato");
            $(".menu-mobile-btn").click();
        });  
    };
    
    var bindDoarBtn = function () {
        $('.donateNowBtn').click(function (e) {
            e.preventDefault();
            scrollToDivName("doar");
        });
    };

    var bindMenuDoarBtn = function () {
        $('.donateNowMenuBtn').click(function (e) {
            e.preventDefault();
            scrollToDivName("doar");
            $(".menu-mobile-btn").click();
            
        });
    };
    
    var scrollToDivName = function (name) {
        var aid = name;
        var aTag = $("div[name='"+ aid +"']");
        $('html,body').animate({scrollTop: aTag.offset().top},'slow');
    };

    return {
        init: init
    }

}();


PANDOX.FORM = function () {

    var init = function () {
        var inputs = ["donate-date", "donate-coo", "donate-value"];
        $.each(inputs, function (i, input) {
            $("#i-" + input).focus(function () {
                clearInput(input);
            });

        });
    };

    var markErrorOnField = function (field, msg) {
        $("#g-" + field).addClass("has-error has-feedback");
        $("#ig-" + field).addClass("glyphicon-remove");

        $("#h-" + field).hide();
        $("#e-" + field).show();
        console.log(field);
        console.log(msg);
        $("#e-" + field).html(msg);
    };


    var clearInput = function (field) {
        $("#g-" + field).removeClass("has-success has-feedback has-error");
        $("#ig-" + field).removeClass("glyphicon-ok glyphicon-remove");
        $("#h-" + field).show();
        $("#e-" + field).html('');
        $("#e-" + field).hide();
    };

    var markSuccessOnField = function (field) {
        $("#g-" + field).addClass("has-success has-feedback");
        $("#ig-" + field).addClass("glyphicon-ok");
    };


    return {
        clearInput: clearInput,
        markSuccessOnField: markSuccessOnField,
        init: init,
        markErrorOnField: markErrorOnField
    };
}();

PANDOX.DONATE = function () {

    var init = function () {
        bindDonateBtn();
    };

    var bindDonateBtn = function () {
        $('#donate').click(function (e) {
            e.preventDefault();
            //$("#donate-loading").show();
            //$("#donate").hide();

            var hasError = false;
            var date = $("#i-donate-date").val();
            if (PANDOX.SYSTEM.isBlank(date)) {
                hasError = true;
                PANDOX.FORM.markErrorOnField("donate-date", "Data é obrigatória");
            }else {
                var parts = date.split('/');
                date = new Date(parts[2],parts[1]-1,parts[0]); 
            }   

            var coo = $("#i-donate-coo").val();
            if (PANDOX.SYSTEM.isBlank(coo)) {
                hasError = true;
                PANDOX.FORM.markErrorOnField("donate-coo", "COO é obrigatório");
            }

            var value = $("#i-donate-value").val();
            if (PANDOX.SYSTEM.isBlank(value)) {
                hasError = true;
                PANDOX.FORM.markErrorOnField("donate-value", "Valor é obrigatório");
            }else {
                value = value.replace(",", ".");   
                value = parseFloat(value);
            }
            
            var cnpj = $("#i-donate-cnpj").val();
            if (PANDOX.SYSTEM.isBlank(value)) {
                cnpj = "12791298000184"; // AMPARA
            }

            if (hasError) {

            } else {
                var json = {
                    cnpj: cnpj,
                    purchaseDate: date.toISOString(),
                    coo: coo,
                    value: value
                };

                var request = $.ajax({
                    url: "/api/fiscal_note",
                    type: "POST",
                    data: JSON.stringify(json),
                    contentType: "application/json"
                });

                request.done(function (data, textStatus, jqXHR) {

                    $("#account-template").hide().loadTemplate("/conta/sucesso.html", data).fadeIn(1500);
                });

                request.fail(function (promise) {
                    var result = promise.responseJSON;

                    $.each(result, function (i, erro) {
                        PANDOX.FORM.markErrorOnField(erro.field, erro.message);
                    });

                    $("#loading").hide();
                    $("#btn-submit").show();
                });
            }

            $("#donate-loading").hide();
            $("#donate").show();
        });
    };


    return {
        init: init
    };


}();
