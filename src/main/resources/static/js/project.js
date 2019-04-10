$(function() {
    $("nav #menuToggle").on("click", function() {
        toggleMenuIcon();
    });


});

function toggleMenuIcon() {
    $("nav #menuToggle").toggleClass("fa-bars");
    $("nav #menuToggle").toggleClass("fa-chevron-circle-down");
}