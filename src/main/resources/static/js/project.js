$(function () {
    $("#menuOpen, #menuClose").on("click", function () {
        toggleMenuIcon();
    });

    // Search bar AJAX call
    $("#searchBar input").autocomplete({
        source: function (request, response) {
            $.getJSON("/api/component", request, function (data) {
                response($.map(data.components, function (item) {
                    return {
                        label: item.name,
                        value: item.name + "",
                        id: item.id
                    }
                }))
            })
        },
        select: function (event, ui) {
            window.location.href = "/component/" + ui.item.id;
        }
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
    let editorHTML = ace.edit("editorHTML");
    let editorCSS = ace.edit("editorCSS");
    let editorJS = ace.edit("editorJS");
    let editorCode = ace.edit("editorCode");

    let editorArray = [editorHTML, editorCSS, editorJS, editorCode];
    let modeArray = ["html", "css", "javascript", "java"];
    let nameArray = ["html", "css", "js", "code"];

    editorArray.forEach(editor => {
        editor.setOptions({
            theme: "ace/theme/chrome",
            mode: "ace/mode/" + modeArray[editorArray.indexOf(editor)],
            fontSize: "14pt",
            minLines: 2,
            maxLines: 40,
            useSoftTabs: true,
            autoScrollEditorIntoView: true
        });
        editor.renderer.setScrollMargin(10, 10, 10, 10);

        // Adds event listeners to each editor
        editor.on("change", evt => {
            $("#" + nameArray[editorArray.indexOf(editor)])
                .val(editorArray[editorArray.indexOf(editor)].getValue());
            updatePreview(editorArray);
        });
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
    $("#sidebar-collapses").toggleClass("hiddenSideMenu");
    $("#pageContent").toggleClass("reverseMargin");
}

function updatePreview(editorArray) {
    $("#previewWindow").attr("srcdoc", "" +
        "<html>" +
            "<head>" +
                "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' " +
                      "integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' " +
                      "crossorigin='anonymous'>" +
                "<style>" +
                    "body{background-color: transparent !important}" +
                    editorArray[1].getValue() +
                "</style>" +
            "</head>" +
            "<body>" +
                    editorArray[0].getValue() +
                "<script>" +
                    editorArray[2].getValue() +
                "</script>" +
            "</body>" +
        "</html>");
}
