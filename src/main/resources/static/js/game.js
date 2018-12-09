var stompClient = null;
var sock = null;

function connect(options) {
    sock = new SockJS('/ws');
    stompClient = Stomp.over(sock);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var room = location.pathname.split('/')[2];
        stompClient.subscribe('/topic/roomSocket/' + room, function (greeting) {
            var json = JSON.parse(greeting.body);
            if (json.act != null){
                if (json.act === "close") {
                    window.location.replace("/rooms");
                } else {
                    showGreeting(json.act)
                }
            } else {
                showGreeting(json.username + " x:" + json.x + " y:" + json.y);
            }
        });
    });
}

// noinspection JSUnusedGlobalSymbols
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
    var room = location.pathname.split('/')[2];
    $.ajax({
        url: '/rooms/' + room,
        type: 'DELETE'
    });
    window.location.replace("/rooms");
}

function sendName() {
    var room = location.pathname.split('/')[2];
    stompClient.send("/app/roomSocket/" + room, {}, JSON.stringify({'username': $("#name").val()}));
}

function showGreeting(message) {
    $("#userinfo").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});

connect();