/**
 * 
 */

let quizAndQuestions;
let quiz_id;
let currentQuestionNo;

window.addEventListener('load', async function () {
    questionlist = this.document.querySelector('#question_list');


    // quiz_idをクエリ文字列から取得
    var currentURL = new URL(this.window.location.href);
    var params = currentURL.searchParams;
    quiz_id = params.get('quiz_id');

    quizAndQuestions = await loadQuiz(quiz_id);

    await displayQuestionsList();
})


async function loadQuiz(quiz_id) {
    var fetchResponse = await fetch('/quizoo/quizquestion?quiz_id=' + quiz_id);
    if (fetchResponse.ok) {
        quizQuestionJson = fetchResponse.json();
        return quizQuestionJson;
    } else {
        // redirect('/quizoo/error');
        alert('fetchError!! \n ');
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
    if (questionNo === currentQuestionNo) {
        return;
    }

    saveCurrentAnswer();


    var willDisplay = quizAndQuestions['question'][questionNo - 1];

    oldSentence = document.querySelector('#question-sentence');
    newSnetence = document.createElement('div');
    newSnetence.setAttribute('class', 'sentence');
    newSnetence.setAttribute('id', 'question-sentence');
    newSnetence.innerText = willDisplay['question'];

    oldSentence.replaceWith(newSnetence);

}

async function saveCurrentAnswer() {

}