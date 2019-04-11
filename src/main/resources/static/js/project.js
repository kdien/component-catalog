$(function() {
    $("nav #menuToggle").on("click", function() {
        toggleMenuIcon();
    });

    // Used for material design form inputs
    const setActive = (el, active) => {
        const formField = el.parentNode.parentNode;
        if (active) {
            formField.classList.add('form-field--is-active')
        } else {
            formField.classList.remove('form-field--is-active');
            el.value === '' ?
                formField.classList.remove('form-field--is-filled') :
                formField.classList.add('form-field--is-filled')
        }
    };

    [].forEach.call(
        document.querySelectorAll('.form-field-input, .form-field-textarea'),
        (el) => {
            el.onblur = () => {
                setActive(el, false)
            };
            el.onfocus = () => {
                setActive(el, true)
            }
        }
    )
});

function toggleMenuIcon() {
    $("nav #menuToggle").toggleClass("fa-bars fa-chevron-circle-down");
}

// Hide create form by default
$(".create-form").hide();

// Click plus button to show and hide form
$(".create-button").click(function () {
    $(this).find("i").toggleClass("rotate");
    $(this).toggleClass("button-click");
    $(".create-form").toggle(400);
    $(".create-form input[type='text']").focus();
});


