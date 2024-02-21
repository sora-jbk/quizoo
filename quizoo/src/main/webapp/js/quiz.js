/**
 * 
 */

/**
 * 
 */

import { Modal } from "bootstrap";

let quizAndQuestions;
let quiz_id;
let currentQuestionNo;
let selectedAnswers;
let answerBtns;
let questionlist; // Declare questionlist variable

window.addEventListener('load', async function () {
    // 繝｢繝ｼ繝繝ｫ繧貞叙蠕?

    questionlist = this.document.querySelector('#question_list');

    // quiz_id繧偵け繧ｨ繝ｪ譁?蟄怜?励°繧牙叙蠕?
    var currentURL = new URL(this.window.location.href);
    var params = currentURL.searchParams;
    quiz_id = params.get('quiz_id');

    quizAndQuestions = await loadQuiz(quiz_id);

    selectedAnswers = new Array(quizAndQuestions['question'].length);

    // 驕ｸ謚櫁い繝懊ち繝ｳ繧定ｨｭ螳?
    answerBtns = document.querySelectorAll('#answer_btn');

    for(let i = 0; i < answerBtns.length; i++) {
        answerBtns[i].addEventListener('click', function() {
            choiceBtnClickHandler(i);
            chengeSelected(i);
        });
    }

    // 蝗樒ｭ秘∽ｿ｡縺ｮ遒ｺ隱咲判髱｢繧定｡ｨ遉ｺ
    document.querySelector('#endButton').addEventListener('click', ()=>{
        showConfirmModal();
    });

    this.document.querySelector("#sendAnswerButton").addEventListener('click', async ()=>{
        hideConfirmModal();
        showResultModal();
        scoring();
    });

    this.document.querySelector("#dontSendButton").addEventListener('click', ()=>{
        hideConfirmModal();
    });


    this.document.querySelector("#closeButton").addEventListener('click',()=>{
        this.window.location.href = '/quizoo/index';
    })

    await displayQuestionsList();

    await displayQuestionDetails(1);

})




async function loadQuiz(quiz_id) {
    try{
        var fetchResponse = await fetch('/quizoo/quizquestion?quiz_id=' + quiz_id);
    
        var quizQuestionJson = fetchResponse.json();

        return quizQuestionJson;
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "/quizoo/login-page";
    }

}

async function displayQuestionsList() {
    const newQuestionlist = document.createElement('ol');
    newQuestionlist.setAttribute('id', 'question_list');

    for (const question of quizAndQuestions['question']) {
        const questionElement = document.createElement('li');
        questionElement.innerText = question['question'];

        questionElement.addEventListener('click', () => {
            return displayQuestionDetails(question.questionId);
        });
        
        newQuestionlist.appendChild(questionElement);
    }

    questionlist.replaceWith(newQuestionlist);
}

function displayQuestionDetails(questionNo) {
    if (questionNo === currentQuestionNo)  return;
    if (questionNo > quizAndQuestions['question'].length || questionNo < 0) return;
    let oldSentence = document.querySelector('#question-sentence');

    currentQuestionNo = questionNo;
    
    let newSnetence = createSentenceNode(questionNo);
    oldSentence.replaceWith(newSnetence);

    let oldChoicesWrapper = document.querySelector('#question-choices');
    let newChoicesWrapper = createChoiseNodes(questionNo);
    for(let i = 0; i < 4; i++) {
        newChoicesWrapper.querySelectorAll("#answer-btn")[i].addEventListener('click',choiceBtnClickHandler.bind(this,i+1));
    }
    oldChoicesWrapper.replaceWith(newChoicesWrapper);
    
    chengeSelected(selectedAnswers[currentQuestionNo - 1]);
}


function choiceBtnClickHandler(ClickedNo){
    selectedAnswers[currentQuestionNo - 1] = ClickedNo;
    displayQuestionDetails(currentQuestionNo + 1);
}


function createSentenceNode(questionNo) {
    let willDisplay = quizAndQuestions['question'][questionNo - 1];
    let newSnetence = document.createElement('div');
    newSnetence.setAttribute('class', 'sentence');
    newSnetence.setAttribute('id', 'question-sentence');
    newSnetence.innerText = willDisplay['question'];

    return newSnetence;
}

function createChoiseNodes(questionNo) {
    let newChoicesWrapper = document.createElement('table');
    newChoicesWrapper.setAttribute('class','answer');
    newChoicesWrapper.setAttribute('id','question-choices');

    let question = quizAndQuestions['question'][questionNo - 1];

    let tr;
    for(var i = 1; i <= 4; i++) {
        if((i % 2) === 1){
            tr = document.createElement('tr');
            newChoicesWrapper.appendChild(tr);
        }
        var td = document.createElement('td');
        var button = document.createElement('button');
        button.innerText = i;
        button.setAttribute('class','btn btn--orange');
        button.setAttribute('id','answer-btn');

        td.appendChild(button);

        tr.appendChild(td.cloneNode(true));

        var td = document.createElement('td');
        var div = document.createElement('div');
        div.setAttribute('class','choice');
        div.innerText = question['choice'+i];

        td.appendChild(div.cloneNode(true));

        tr.appendChild(td.cloneNode(true));
    }

    return newChoicesWrapper;

}



function chengeSelected(selectedNo = 0) {
    for(let i = 0; i < answerBtns.length; i++) {
        if(selectedNo === i){
            answerBtns[i].classList.add('selected');
        }else{
            answerBtns[i].classList.remove('selected');
        }
    }
}

function scoring() {
    let questionResult = document.createElement('div');
    questionResult.setAttribute('class','result-list');
    let score = 0;
    for(let i = 0; i < quizAndQuestions['question'].length; i++) {
        var result = document.createElement('div');
        result.innerText = (i+1) + "."
        if(quizAndQuestions['question'][i]['judge'][selectedAnswers[i]]) {
            result.innerText+= "縲?";
            score++;
        }else{
            result.innerText+= "笨?";
        }
        questionResult.appendChild(result);
    }
    
    document.querySelector("#result-list").replaceWith(questionResult);

    var rate = Math.round(score/quizAndQuestions['question'].length * 100);
    document.querySelector("#result-rate a").innerText = rate + "%";
    sendAnswer(score);
    
}

async function sendAnswer(score){
    try{
        var res = await fetch('/quizoo/submitanswer', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'quiz_id': quiz_id,
                'score': score,
                'question_num':quizAndQuestions['question'].length,
            })
        });
        if(!res.ok){
            throw new Error(res.statusText);
        }
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "/quizoo/login-page";
    }

}

function showConfirmModal() {
    document.querySelector("#confirm-modal").style.display = "block";
}

function hideConfirmModal() {
    document.querySelector("#confirm-modal").style.display = "none";
}

function showResultModal() {
    document.querySelector("#result-modal").style.display = "block";
}