$(document).ready(function () {
    var userList = $('#user-list');
    var users;
    var theParent = document.querySelector("#user-list");

    localStorage.clear();

    $.ajax({
        url: 'http://localhost:8765/portfolio/api/users',
        method: 'get',
        dataType: 'json',
        success: function (response) {
            users = response._embedded.users; 

            $.each(users, function(_index, user) {
                userList.append(
                    '<a href="'+
                    '#'+'"><li class="list-group-item list-group-item-info" id="'
                    +user.id+
                    '"><h6>'
                    +user.title+'</h6>'+user.description+'</li></a>')
            });

            for (var i = 0; i < theParent.children.length; i++) {
                var childElement = theParent.children[i];
                childElement.addEventListener('click', doSomething, false);
                console.log(childElement);
            }
            
            function doSomething(e) {
                localStorage.setItem('user-id', e.target.id)
                console.log(localStorage.getItem('user-id'))
                location.replace("user.html")
            }
        },
        error: function (err) {
            alert(err);
        }
    });

});