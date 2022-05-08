$('.center').slick({
    centerMode: true,
    centerPadding: '300px',
    slidesToShow: 3,
    prevArrow:'<span class="prev_arrow"><i class="fa-solid fa-angle-left"></i></span>',
    nextArrow:'<span class="next_arrow"><i class="fa-solid fa-angle-right"></i></span>',
    responsive: [{
            breakpoint: 768,
            settings: {
                arrows: false,
                centerMode: true,
                centerPadding: '40px',
                slidesToShow: 3
            }
        },
        {
            breakpoint: 480,
            settings: {
                arrows: false,
                centerMode: true,
                centerPadding: '40px',
                slidesToShow: 3
            }
        }
    ]
});



