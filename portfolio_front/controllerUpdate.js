$(document).ready(function(){

    var userId = localStorage.getItem('user-id')
    var url = 'http://localhost:8765/portfolio/api/users/'+userId;

    var title = localStorage.getItem('title')
    var username = localStorage.getItem('username')
    var desc = localStorage.getItem('desc')
    var image = localStorage.getItem('image')

    document.getElementById('titleOld').innerHTML = 'old: '+title;
    document.getElementById('usernameOld').innerHTML = 'old: '+username;
    document.getElementById('descriptionOld').innerHTML = 'old: '+desc;
    document.getElementById('imageUrlOld').innerHTML = 'old: '+image;

    $("#update-button").on('click', function(){

        var form = $("#form").serializeArray();

        var data = '{"'+form[0].name+'":"'+form[0].value+'",'+
                    '"'+form[1].name+'":"'+form[1].value+'",'+
                    '"'+form[2].name+'":"'+form[2].value+'",'+
                    '"'+form[3].name+'":"'+form[3].value+'"}';

        $.ajax({
            url: url, 
            type : "PUT", 
            dataType : 'json', 
            data : data, 
            contentType: 'application/json',
            crossDomain: true,
            crossOrigin: true,
            success : function(result) {
                console.log(result);
                location.replace("user.html");
            },
            error: function(xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        })
    });
});
