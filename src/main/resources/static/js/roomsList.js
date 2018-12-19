function updateRooms() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/rooms.list", true);
    xhr.onload = function (en) {
        var rooms = JSON.parse(xhr.responseText);
        var roomsDiv = document.getElementById("rooms");
        while (roomsDiv.firstChild) {
            roomsDiv.removeChild(roomsDiv.firstChild);
        }


        for (i = 0; i < rooms.length; i++){
            var divCard = document.createElement("div");
            divCard.className = "card w-50";

            var divCardBody = document.createElement("div");
            divCardBody.className = "card-body";
            divCard.appendChild(divCardBody);

            var h5 = document.createElement("h5");
            h5.className = "card-title";
            h5.innerHTML = rooms[i].owner;
            divCardBody.appendChild(h5);

            var p = document.createElement("p");
            p.className = "card-text";
            p.innerHTML = "Users in room: " + rooms[i].numberOfUsers;
            divCardBody.appendChild(p);

            var a = document.createElement("a");
            a.className = "btn btn-primary";
            a.setAttribute("href", "/rooms/" + rooms[i].owner);
            a.innerHTML = "Join";
            a.type = "button";

            if (rooms[i].numberOfUsers !== 1) {
                a.setAttribute("disabled", true);
            }
            // a.disabled = rooms[i].numberOfUsers !== 1;

            divCardBody.appendChild(a);
            roomsDiv.appendChild(divCard);
        }



    };
    xhr.send();
}

updateRooms();
setInterval(updateRooms, 2000);