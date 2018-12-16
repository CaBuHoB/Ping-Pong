var stompClient = null;
var sock = null;
var name = document.getElementById("username").innerText;
var canOpponentStart = false;
var canIStart = false;
var room = location.pathname.split('/')[2];
var ball, player, opponent, game, start, context;

function connect() {
    // noinspection JSUnresolvedFunction
    sock = new SockJS('/ws');
    // noinspection JSUnresolvedVariable
    stompClient = Stomp.over(sock);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var room = location.pathname.split('/')[2];
        // noinspection JSCheckFunctionSignatures
        stompClient.subscribe('/topic/roomSocket/' + room, function (greeting) {
            var json = JSON.parse(greeting.body);
            if (json.act != null) {
                switch (json.act) {
                    case "BallAndScores":
                        if (json.username !== name) {
                            var ballAndScores = json.ballAndScores;
                            console.log(ballAndScores);
                            ball.x = ballAndScores.x;
                            ball.y = ballAndScores.y;
                            opponent.scores = ballAndScores.opponentScores;
                            player.scores = ballAndScores.playerScores;
                            checkScore();
                        }
                        break;
                    case "check":
                        if (json.username !== name) {
                            canOpponentStart = json.canStart;
                            // noinspection JSJQueryEfficiency
                            $('#readinessOpponentCheck').prop('checked', canOpponentStart);
                            // noinspection JSJQueryEfficiency
                            $('#readinessOpponentCheck').prop('indeterminate', !canOpponentStart);
                        }
                        break;
                    case "close":
                        window.location.replace("/rooms");
                        break;
                    default:
                        break;
                }
            } else {
                if (json.username !== name) {
                    if (name === room) {
                        opponent.y = json.y;
                    } else {
                        player.y = json.y;
                    }
                }
            }
        });
    });
}

// класс определяющий параметры игрового прямоугольника и метод для его отрисовки

function rect(color, x, y, width, height) {
    this.color = color; // цвет прямоугольника
    this.x = x; // координата х
    this.y = y; // координата у
    this.width = width; // ширина
    this.height = height; // высота
    // функция рисует прямоугольник согласно заданным параметрам
    this.draw = function () {
        context.fillStyle = this.color;
        context.fillRect(this.x, this.y, this.width, this.height);
    };
}

// функция проверяет пересекаются ли переданные ей прямоугольные объекты

function collision(objA, objB) {
    return objA.x + objA.width > objB.x && objA.x < objB.x + objB.width && objA.y + objA.height > objB.y && objA.y < objB.y + objB.height;
}

// движение игрока

function playerMove(e) {
    if (start) {
        var y = e.pageY;
        // условие проверяет не выходит ли ракетка за пределы поля
        if (name === room) {
            if (player.height / 2 + 10 < y && y < game.height - player.height / 2 - 10) {
                // привязываем положение мыши к середине ракетки
                player.y = y - player.height / 2;
                stompClient.send("/app/roomSocket/" + room, {}, JSON.stringify({'username': name, 'y': player.y}));
            }
        } else {
            if (opponent.height / 2 + 10 < y && y < game.height - opponent.height / 2 - 10) {
                // привязываем положение мыши к середине ракетки
                opponent.y = y - opponent.height / 2;
                stompClient.send("/app/roomSocket/" + room, {}, JSON.stringify({'username': name, 'y': opponent.y}));
            }
        }
    }
}


function startGame() {
    if (!start) {
        ball.vX = -2;
        ball.vY = 2;
        start = true;
    }
}

// отрисовка игры

function draw() {
    game.draw(); // игровое поле
    // разделительная полоса
    for (var i = 10; i < game.height; i += 45) {
        context.fillStyle = "#ccc";
        context.fillRect(game.width / 2 - 10, i, 20, 30);
    }
    // рисуем на поле счёт
    context.font = 'bold 128px courier';
    context.textAlign = 'center';
    context.textBaseline = 'top';
    context.fillStyle = '#ccc';
    context.fillText(opponent.scores, 100, 0);
    context.fillText(player.scores, game.width - 100, 0);
    opponent.draw(); // левая ракетка
    player.draw(); // ракетка игрока
    ball.draw(); // шарик
    if (!start) {
        // вывод статстики
        context.fillStyle = "#ccc";
        context.globalAlpha = 0.7;
        context.fillRect(0, 0, game.width, game.height);
        context.font = 'bold 16px courier';
        context.textBaseline = 'top';
        context.fillStyle = '#000';
        context.fillText("Total: " + game.total + " Win: " + game.win + " Lose: " + game.lose, game.width / 2, 0);
        context.font = 'bold 60px courier';
        context.textBaseline = 'top';
        context.fillStyle = '#000';
        context.fillText("SUAI Pong", game.width / 2, game.height / 2 - 50);
    }
}

// игровые изменения которые нужно произвести

function checkScore() {
    if (opponent.scores === 10 || player.scores === 10) {
        if (opponent.scores === 10) { // победа opponent
            game.lose++;
            start = false;
            ball.x = game.width - player.width - 1.5 * ball.width - 10;
            ball.y = game.height / 2 - ball.width / 2;
            opponent.y = game.height / 2 - opponent.height / 2;
            player.y = game.height / 2 - opponent.height / 2;
        } else { // победа игрока
            game.win++;
            start = false;
            ball.x = player.width + ball.width;
            ball.y = game.height / 2 - ball.width / 2;
            opponent.y = game.height / 2 - opponent.height / 2;
            player.y = game.height / 2 - opponent.height / 2;
        }
        ball.vX = 0;
        ball.vY = 0;
        game.total++;
        if (room !== name) {
            opponent.scores = 0;
            player.scores = 0;
        }
    }
}

function ballCoordinates() {
    // Движение по оси У
    if (ball.y < 0 || ball.y + ball.height > game.height) {
        // соприкосновение с полом и потолком игрового поля
        ball.vY = -ball.vY;
    }
    // Движение по оси Х
    if (ball.x < 0) {
        // столкновение с левой стеной
        ball.vX = -ball.vX;
        player.scores++;
    }
    if (ball.x + ball.width > game.width) {
        // столкновение с правой
        ball.vX = -ball.vX;
        opponent.scores++;
    }
}

function racketContact() {
    if ((collision(opponent, ball) && ball.vX < 0) || (collision(player, ball) && ball.vX > 0)) {
        // приращение скорости шарика
        if (ball.vX < 9 && -9 < ball.vX) {
            if (ball.vX < 0) {
                ball.vX--;
            } else {
                ball.vX++;
            }
            if (ball.vY < 0) {
                ball.vY--;
            } else {
                ball.vY++;
            }
        }
        ball.vX = -ball.vX;
    }
    // приращение координат
    ball.x += ball.vX;
    ball.y += ball.vY;
}

function sendDataToOpponent() {
    if (start || (opponent.scores === 10 || player.scores === 10) ) {
        stompClient.send("/app/roomSocket/" + room, {},
            JSON.stringify({
                'act': 'BallAndScores', 'username': name,
                'ballAndScores': {
                    'opponentScores': opponent.scores, 'playerScores': player.scores, 'x': ball.x, 'y': ball.y
                }
            }));
        if(opponent.scores === 10 || player.scores === 10) {
            opponent.scores = 0;
            player.scores = 0;
        }
    }
}

function update() {
    // меняем координаты шарика
    ballCoordinates();

    // Если счёт равен десяти то завершаем партию
    checkScore();

    // Соприкосновение с ракетками
    racketContact();

    // Отправляем данные второму игроку
    sendDataToOpponent();
}

function play() {
    draw(); // отрисовываем всё на холсте
    if (name === room) {
        update(); // обновляем координаты
    }
}

function checkPlayers() {
    if (canIStart && canOpponentStart) {
        startGame();
        $('#readinessCheck').prop('checked', false);
        $('#readinessOpponentCheck').prop('checked', false);
        canIStart = false;
        canOpponentStart = false;
    }
}

// Инициализация переменных

function init() {
    start = false;
    // объект который задаёт игровое поле
    // noinspection JSPotentiallyInvalidConstructorUsage
    game = new rect("#000", 0, 0, 480, 320);
    game.total = 0;
    game.win = 0;
    game.lose = 0;
    // Ракетки-игроки
    // noinspection JSPotentiallyInvalidConstructorUsage
    opponent = new rect("#ffffff", 10, game.height / 2 - 40, 20, 80);
    // noinspection JSPotentiallyInvalidConstructorUsage
    player = new rect("#ffffff", game.width - 30, game.height / 2 - 40, 20, 80);
    // количество очков
    opponent.scores = 0;
    player.scores = 0;
    // наш квадратный игровой "шарик"
    // noinspection JSPotentiallyInvalidConstructorUsage
    ball = new rect("#fff", 40, game.height / 2 - 10, 20, 20);
    // скорость шарика
    ball.vX = 0; // скорость по оси х
    ball.vY = 0; // скорость по оси у
    var canvas = document.getElementById("pong");
    canvas.width = game.width;
    canvas.height = game.height;
    context = canvas.getContext("2d");
    canvas.onmousemove = playerMove;
    setInterval(checkPlayers, 1000 / 50);
    setInterval(play, 1000 / 50);
}

// noinspection JSJQueryEfficiency
$('#readinessCheck').prop('indeterminate', true);
// noinspection JSJQueryEfficiency
$('#readinessOpponentCheck').prop('indeterminate', true);

// noinspection JSJQueryEfficiency
$("#readinessCheck").click(function () {
    canIStart = $(this).prop('checked');
    stompClient.send("/app/roomSocket/" + room, {},
        JSON.stringify({'act': 'check', 'username': name, 'canStart': canIStart}));
});

connect();
init();