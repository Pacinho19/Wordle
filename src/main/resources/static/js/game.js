var stompClient = null;
var privateStompClient = null;

socket = new SockJS('/ws');
privateStompClient = Stomp.over(socket);
privateStompClient.connect({}, function (frame) {
    var gameId = document.getElementById('gameId').value;
    privateStompClient.subscribe('/reload-board/' + gameId, function (result) {
        if(result.body)
            alert(result.body);
        else{
            updateBoard();
            currentLetter = 1;
        }
    });
});

stompClient = Stomp.over(socket);

function updateBoard() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            $("#board").replaceWith(xhr.responseText);
        }
    }
    xhr.open('GET', "/wordle/game/" + document.getElementById("gameId").value + "/board/reload", true);
    xhr.send(null);
}

let currentLetter=1
document.addEventListener('keyup', (e) => {

    if(document.getElementById('gameStatus').value == 'FINISHED')
        return;

    var key = event.keyCode || event.charCode;
    if (event.keyCode >= 65 && event.keyCode <= 90) {
        letterSpan  = document.getElementById('letter' + currentLetter);

        if(letterSpan==null)
            return;

        letterSpan.innerHTML = String.fromCharCode(event.keyCode).toUpperCase();
        currentLetter++;
    }else if( (key == 8 || key == 46) && currentLetter > 1 ){
     console.log(currentLetter);
        letterSpan  = document.getElementById('letter' + (currentLetter-1));
        if(letterSpan==null)
            return;

        letterSpan.innerHTML = "_";
        currentLetter--;
    }else if (event.key === 'Enter' || event.keyCode === 13) {
        sendAnswer();
    }
});

function sendAnswer() {
    if(!validate())
        return;

     var xhr = new XMLHttpRequest();
     var url = '/wordle/game/' + document.getElementById("gameId").value + '/answer';
     xhr.open("POST", url, true);
     xhr.setRequestHeader("Content-Type", "application/json");
     xhr.onreadystatechange = function () { };

     const answerRequest = new Object();
     answerRequest.word = getAnswerWord();
     var data = JSON.stringify(answerRequest);
     xhr.send(data);
}

function getAnswerWord() {
    let word = '';
    for (let i = 1; i <=document.getElementById('wordLength').value; i++) {
      word = word + (document.getElementById('letter' + i).innerHTML);
    }
    return word;
}

function validate(){
    return getAnswerWord().length == document.getElementById('wordLength').value;
}