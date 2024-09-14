window.onscroll = function () {
    scrollFunction()
};
function scrollFunction() {
    if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
        document.getElementById("regresar").style.display = "block";
    } else {
        document.getElementById("regresar").style.display = "none";
    }
}
(function ($) {
    var btn = $('#regresar');

    btn.on('click', function (e) {
        e.preventDefault();
        $('html, body').animate({scrollTop: 0}, '200');
    });

    // Closes responsive menu when a scroll trigger link is clicked
    $('.js-scroll-trigger').click(function () {
        $('.navbar-collapse').collapse('hide');
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $('body').scrollspy({
        target: '#navegador',
        offset: 100
    });

    var btn1 = $('#logo');

    btn1.on('click', function (a) {
        a.preventDefault();
        $('html, body').animate({scrollTop: 0}, '200');
    });
    
    var btn2 = $('#inicio');
    btn2.on('click', function (a) {
        a.preventDefault();
        $('html, body').animate({
            scrollTop: 0
        }, '200');
    })

    // Collapse Navbar
    var navbarCollapse = function () {
        if ($("#navegador").offset().top > 200) {
            $("#navegador").addClass("navbar-encoger");
        } else {
            $("#navegador").removeClass("navbar-encoger");
        }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);

})(jQuery);
$(function () {
    $('[data-toggle="tooltip"]').tooltip('enable');
});
$(".link-scroll").click(function (e) {
    e.preventDefault();
    var aid = $(this).attr("href");
    $('html,body').animate({scrollTop: $(aid).offset().top}, 'slow');
});
function botonCarrito() {
    document.getElementById("checkout").style.display = "block";
}
$(function () {
    $('[data-toggle="tooltip"]').tooltip('enable');
});