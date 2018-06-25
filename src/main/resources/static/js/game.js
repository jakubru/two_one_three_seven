var nick;
var field;
var players;
var canvasField;

function play() {
    nick = document.getElementById("nick").value;
    var resp = httpPost("/addPlayer", "nick=" + nick);
    var loader = document.getElementById("game-loader");
    var input = document.getElementById("game-nick-input");
    var gameMenu = document.getElementById("game-menu");
    if (resp === "true") {
        if (loader.style.display === "block") {
            loader.style.display = "none";
        }
        else {
            loader.style.display = "block";
            input.style.display = "none";
        }
        if (gameMenu.style.display === "none") {
            gameMenu.style.display = "block";

        } else {
            gameMenu.style.display = "none";
        }
        startGame();
    }
    else {
        alert("nie ok");
    }
}

function httpPost(url, params) {
    var http = new XMLHttpRequest();
    http.open('POST', url, false);

    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    http.send(params);
    return http.responseText;
}


async function startGame() {
    while (true) {
        var resp = httpPost("/startGame");
        var loader = document.getElementById("game-loader");
        var field = document.getElementById("game-field");
        console.log(resp);
        if (resp === "true") {
            loader.style.display = "none";
            field.style.display = "block";
            canvasField = document.getElementById("myCanvas")
            getPlayers();
            getField();
            updatePlayers();
            return;
        }
        await sleep(1000);
    }
}

function updatePlayers() {
    setInterval(function () {
        getPlayers();
        drawPlayers();
    }, 1000)
}


function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function getField() {
    field = httpPost("/getField");
    field = JSON.parse(field);
    drawField(field);
}


function drawField() {
    drawCircle(getScaledShiftedX(400, 500, field.circle.mid.x), getScaledShiftedY(400, 500, field.circle.mid.y), scaledR(400, field.circle.radius), "black");
    for (i = 0; i < field.lines.length; i++) {
        var p1;
        var p2;
        line = field.lines[i];
        var k = 0;
        for (j = 0; j < line.pointList.length; j++) {
            point = line.pointList[j];
            if (point.onCircle && k == 0) {
                p1 = point;
                k++;
            }
            else if (point.onCircle) {
                p2 = point;
            }
        }
        drawLine(getScaledShiftedX(400, 500, p1.x), getScaledShiftedY(400, 500, p1.y), getScaledShiftedX(400, 500, p2.x), getScaledShiftedY(400, 500, p2.y));
    }
    drawPlayers();
    canvasField.addEventListener("click", move);
}

function drawPlayers() {
    for (i = 0; i < players.length; i++) {
        if (players[i].nick === nick) {
            drawCircle(getScaledShiftedX(400, 500, players[i].point.x), getScaledShiftedY(400, 500, players[i].point.y), 10, "red");
        } else {
            drawCircle(getScaledShiftedX(400, 500, players[i].point.x), getScaledShiftedY(400, 500, players[i].point.y), 10, "black");
        }
        console.log("narysowany")
    }
}

function drawLine(x1, y1, x2, y2) {
    var ctx = canvasField.getContext("2d");
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.stroke();
}

function drawCircle(x, y, r, colour) {
    var ctx = canvasField.getContext("2d");
    ctx.strokeStyle = colour;
    ctx.beginPath();
    ctx.arc(x, y, r, 0, 2 * Math.PI);
    ctx.stroke();
}

function getScaledShiftedX(scale, xShift, x) {
    var scaledX = x * scale;
    return Math.ceil(scaledX + xShift);
}

function getScaledShiftedY(scale, yShift, y) {
    var scaledY = y * scale;
    return Math.ceil(scaledY + yShift);
}

function scaledR(scale, radius) {
    return Math.ceil(radius * scale);
}

function move(e) {
    console.log(e);
    httpPost("/move", "nick=" + nick + "&pointID=" + getNearest(e.offsetX, e.offsetY));
}

function distance(x1, y1, x2, y2) {
    var a = x1 - x2;
    var b = y1 - y2;
    return Math.sqrt(a * a + b * b);
}

function getPlayers() {
    players = JSON.parse(httpPost("/getPlayers", ""));
}

function getNearest(x, y) {
    var pointID = 0;
    var dist = 1000;
    for (i = 0; i < field.lines.length; i++) {
        pointList = field.lines[i].pointList;
        for (j = 0; j < pointList.length; j++) {
            a = distance(x, y, getScaledShiftedX(400, 500, pointList[j].x), getScaledShiftedY(400, 500, pointList[j].y));
            if (a < dist) {
                dist = a;
                pointID = pointList[j].id;
                console.log("ok");
            }
        }
    }
    return pointID;
}