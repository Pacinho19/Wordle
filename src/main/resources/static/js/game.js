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
            zoomInZoomOutLastAnswer();
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

        var letter = getLetter(e);
        letterSpan.innerHTML = letter.toUpperCase();
        currentLetter++;
    }else if( (key == 8 || key == 46) && currentLetter > 1 ){
        letterSpan  = document.getElementById('letter' + (currentLetter-1));
        if(letterSpan==null)
            return;

        letterSpan.innerHTML = "_";
        currentLetter--;
    }else if (event.key === 'Enter' || event.keyCode === 13) {
        sendAnswer();
    }
});

const specialCharactersMap = initSpecialCharactersMap();
function getLetter(event){
    letter = String.fromCharCode(event.keyCode)

    if(event.altKey){
        specialChar = specialCharactersMap.get(letter);
        if(specialChar!=null){
            letter = specialChar;
        }
    }

    return letter;
}

function initSpecialCharactersMap(){
    map1 = new Map();
    map1.set('a', 'ą');
    map1.set('A', 'ą');
    map1.set('c', 'ć');
    map1.set('C', 'ć');
    map1.set('e', 'ę');
    map1.set('E', 'ę');
    map1.set('l', 'ł');
    map1.set('L', 'ł');
    map1.set('n', 'ń');
    map1.set('N', 'ń');
    map1.set('o', 'ó');
    map1.set('O', 'ó');
    map1.set('s', 'ś');
    map1.set('S', 'ś');
    map1.set('z', 'ż');
    map1.set('Z', 'ż');
    map1.set('x', 'ź');
    map1.set('X', 'ź');
    return map1;
}

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

function zoomInZoomOutLastAnswer(){
    var interval = 500;
    for (let i = 1; i <=document.getElementById('wordLength').value; i++) {
      setTimeout(function () {
             lastAnswerLetterSpan = document.getElementById('lastAnswer' + i);

             if(lastAnswerLetterSpan==null)
                return;

             lastAnswerLetterSpan.innerHTML = lastAnswerLetterSpan.getAttribute("letter");
             lastAnswerLetterSpan.className  = lastAnswerLetterSpan.getAttribute("badge")
             lastAnswerLetterSpan.classList.add("zoom-in-out-box");

      }, i * interval);
    }

}