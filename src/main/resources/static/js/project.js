$(function () {
    $("#menuOpen, #menuClose").on("click", function () {
        toggleMenuIcon();
    });

    // Hide create form by default
    $(".create-form").hide();

// Click plus button to show and hide form
    $(".create-button").click(function () {
        $(this).find("i").toggleClass("rotate");
        $(this).toggleClass("button-click");
        $(".create-form").fadeToggle(400);
        $(".create-form input[type='text']").focus();
    });

    // Ace Editor settings
    var editorHTML = ace.edit("editorHTML", {
        theme: "ace/theme/chrome",
        mode: "ace/mode/html",
        fontSize: "14pt",
        minLines: 2,
        maxLines: 40,
        autoScrollEditorIntoView: true
    });
    editorHTML.renderer.setScrollMargin(10, 10, 10, 10);

    var editorCSS = ace.edit("editorCSS", {
        theme: "ace/theme/chrome",
        mode: "ace/mode/css",
        fontSize: "14pt",
        minLines: 2,
        maxLines: 40,
        autoScrollEditorIntoView: true
    });
    editorCSS.renderer.setScrollMargin(10, 10, 10, 10);

    var editorJS = ace.edit("editorJS", {
        theme: "ace/theme/chrome",
        mode: "ace/mode/javascript",
        fontSize: "14pt",
        minLines: 2,
        maxLines: 40,
        autoScrollEditorIntoView: true
    });
    editorJS.renderer.setScrollMargin(10, 10, 10, 10);

    var editorCode = ace.edit("editorCode", {
        theme: "ace/theme/chrome",
        mode: "ace/mode/java",
        fontSize: "14pt",
        minLines: 2,
        maxLines: 40,
        autoScrollEditorIntoView: true
    });
    editorCode.renderer.setScrollMargin(10, 10, 10, 10);

    // Editor event listeners
    editorHTML.on("change", function () {
        $("#html").val(editorHTML.getValue());
    });

    editorCSS.on("change", function () {
        $("#css").val(editorCSS.getValue());
    });

    editorJS.on("change", function () {
        $("#js").val(editorJS.getValue());
    });

    editorCode.on("change", function () {
        $("#code").val(editorCode.getValue());
    });

    $("#syntaxLanguage").on("change", function (e) {
        e.preventDefault();
        let mode = $("#syntaxLanguage").val();
        editorCode.session.setMode("ace/mode/" + mode)
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
    $("#menuToggleWrapper").toggleClass("ml-0");
    $("#sidebar-collapses").toggleClass("hiddenLeft");
    $("#pageContent").toggleClass("margin50");
}
