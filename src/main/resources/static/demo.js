
function ajax(url, data, type) {
    return $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: data
    })
}


function updateName() {
    var nameValue = $('#updateName').val();
    var emailValue = $('#hiddenEmail').val();

    var url = "/user/name";
    var data = JSON.stringify({name:nameValue, email:emailValue});
    console.log(data);

    ajax(url, data, "PUT")
    .done(function(data) {
        $('#titleName').text(data + ", Welcome");
    }).fail(function(error) {
        console.log(error)
    })

/*    $.ajax({
        url: url,
        type: "PUT",
        contentType: 'application/json',
        // dataType: "json",
        data: data
    }).done(function(data) {
        $('#titleName').text(data + ", Welcome");
    }).fail(function(xhr, status, error) {
        console.log(error)
    }).always(function() {

    });*/
}



function deleteUser() {
    var passwdValue = $('input[name=passwd]').val();
    var emailValue = $('input[name=email]').val();

    var url = "/user";
    var data = JSON.stringify({passwd:passwdValue, email:emailValue});
    console.log(data);

    ajax(url, data, "DELETE")
        .done(function(data) {
            $(location).attr('href', "/");
        }).fail(function(error) {
        console.log(error);
    })
}