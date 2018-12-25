function getDivCol() {
    var divCol = document.createElement("div");
    divCol.className = "col-md-4";
    return divCol;
}

function getImg() {
    var img = document.createElement("img");
    img.className = "card-img-top border border-primary";
    img.src = "/images/logoRoom.jpg";
    img.setAttribute("data-holder-rendered", "true");
    return img;
}

function getCardTitle(room) {
    var h3 = document.createElement("h3");
    h3.className = "card-title";
    h3.innerText = room.owner;
    return h3;
}

function getCardText(room) {
    var p = document.createElement("p");
    p.className = "card-text";
    p.innerText = room.numberOfUsers;
    return p;
}

function getCardButton(room) {
    var a = document.createElement("a");
    a.className = "btn btn-sm btn-outline-secondary";
    a.innerText = "Join";
    a.href = "/rooms/" + room.owner;
    if (room.numberOfUsers === 2) {
        a.style.pointerEvents = "none";
    }
    return a;
}

function getDivCardBody(room) {
    var divCardBody = document.createElement("div");
    divCardBody.className = "card-body";
    var h3 = getCardTitle(room);
    divCardBody.appendChild(h3);
    var p = getCardText(room);
    divCardBody.appendChild(p);
    var button = getCardButton(room);
    divCardBody.appendChild(button);
    return divCardBody;
}

function getDivCard(room) {
    var divCard = document.createElement("div");
    divCard.className = "card mb-4 box-shadow";
    var img = getImg();
    divCard.appendChild(img);
    var divCardBody = getDivCardBody(room);
    divCard.appendChild(divCardBody);
    return divCard;
}

function outputRooms(rooms, roomsDiv) {
    for (var i = 0; i < rooms.length; i++) {
        var divCol = getDivCol();
        var divCard = getDivCard(rooms[i]);

        divCol.appendChild(divCard);
        roomsDiv.appendChild(divCol);
    }
}

function updateRooms() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/listRooms", true);
    xhr.onload = function () {
        var rooms = JSON.parse(xhr.responseText);
        var roomsDiv = document.getElementById("rooms");
        while (roomsDiv.firstChild) {
            roomsDiv.removeChild(roomsDiv.firstChild);
        }
        outputRooms(rooms, roomsDiv);
    };
    xhr.send();
}

updateRooms();
setInterval(updateRooms, 2000);