const $frequent = $('.guide-frequent');
const $use = $('.guide-use');
const $reserve = $('.guide-reserve');

const $frequentSelect = $('.guide-frequent-select');
const $useSelect = $('.guide-use-select');
const $reserveSelect = $('.guide-reserve-select');

// $frequent.hide();
//     $use.show();
//     $reserve.show();

//     $frequentSelect.show();
//     $useSelect.hide();
//     $reserveSelect.hide();

    // min-width: 0px;
    // width: 0%;

// 초기값
$frequent.css("width", "0%")
$frequent.css("min-width", "0%")
$frequentSelect.css("width", "378px");

$use.css("width", "104px");
$useSelect.css("width", "0%");
$useSelect.css("min-width", "0%");


$reserve.css("width", "104px");
$reserveSelect.css("width", "0%");
$reserveSelect.css("min-width", "0%");



$frequent.mouseover(function(){
    $frequent.css("width", "0%")
    $frequent.css("min-width", "0%")
    $frequentSelect.css("width", "378px");
    $frequentSelect.css("min-width", "378px");

    $use.css("width", "104px");
    $use.css("min-width", "104px");
    $useSelect.css("width", "0%");
    $useSelect.css("min-width", "0%");

    $reserve.css("width", "104px");
    $reserve.css("min-width", "104px");
    $reserveSelect.css("width", "0%");
    $reserveSelect.css("min-width", "0%");
});
$use.mouseover(function(){
    $frequent.css("width", "104px")
    $frequent.css("min-width", "104px")
    $frequentSelect.css("width", "0%");
    $frequentSelect.css("min-width", "0%");

    $use.css("width", "0%");
    $use.css("min-width", "0%");
    $useSelect.css("width", "378px");
    $useSelect.css("min-width", "378px");

    $reserve.css("width", "104px");
    $reserve.css("min-width", "104px");
    $reserveSelect.css("width", "0%");
    $reserveSelect.css("min-width", "0%");

});
$reserve.mouseover(function(){
    $frequent.css("width", "104px")
    $frequent.css("min-width", "104px")
    $frequentSelect.css("width", "0%");
    $frequentSelect.css("min-width", "0%");

    $use.css("width", "104px");
    $use.css("min-width", "104px");
    $useSelect.css("width", "0%");
    $useSelect.css("min-width", "0%");

    $reserve.css("width", "0%");
    $reserve.css("min-width", "0%");
    $reserveSelect.css("width", "378px");
    $reserveSelect.css("min-width", "378px");

});

$('.recruit-swiper-inner-container').each((i, e) => {
    $(e).mouseover(() => {
       $($(".recruit-box-context")[i]).css("transform", "translate(0, -180px)");
       $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.4))");
    });
    $(e).mouseout(() => {
       $($(".recruit-box-context")[i]).css("transform", "translate(0, 0px)");
       $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0))");
    });
});

// 


HTMLCollection.prototype.forEach = Array.prototype.forEach;
const eventBanner = document.querySelector('div.event-banner');
const eventImageDiv = document.querySelectorAll('div.event-banner div .event-banner-image');
const eventFirstImageDiv = document.createElement('div');
const eventNext = document.querySelector('div.event-next');
const eventPrev = document.querySelector('div.event-prev');
const eventButtons = document.querySelectorAll('.event-buttons button');
