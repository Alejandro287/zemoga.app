$(document).ready(function () {
    var title = $('#user-name');
    var description = $('#user-description');
    var imageElement = $('#user-image');
    var tweets = $('#user-tweets');
    var userId = localStorage.getItem('user-id')
    var url = 'http://localhost:8765/portfolio/api/users/'+userId;

    var updateButton= $('#update-button');
    var backButton= $('#back-button');

    $.ajax({
        url: url,
        method: 'get',
        dataType: 'json',
        success: function (response) {
            localStorage.setItem('title', response.title)
            localStorage.setItem('username', response.username)
            localStorage.setItem('desc', response.description)
            localStorage.setItem('image', response.imageUrl)
            title.html('<h2>'+response.title+'</h2>')
            description.html('<h4>About Me</h4>'+ '<p>'+response.description+'</p>')
            imageElement.attr('src', response.imageUrl);
        },
        error: function (err) {
            alert(err);
        }
    });

    $.ajax({
        url: url+'/tweets',
        method: 'get',
        dataType: 'json',
        success: function (response) {
            var content = response._embedded.tweets; 

            $.each(content, function(_index, value) {
                tweets.append(
                    '<a href="'+
                    value._links.self.href+
                    '"><li class="list-group-item list-group-item-info"><h6>'
                    +value.name+'</h6>'+value.text+'</li></a>')
            });
        },
        error: function (err) {
            alert(err);
        }
    });
});

function goList(e) {
    location.replace("userList.html");
}

function goUpdate(e) {
    location.replace("update.html");
}