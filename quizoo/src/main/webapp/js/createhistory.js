/**
 * 
 */

let createHistoryList;

window.addEventListener("load", async () => {
    try{
        const response = await fetch("/quizoo/createhistoryjson", {
            credentials: "include"
        });
        
        if(!response.ok){
            throw new Error(response.statusText);
        }

        createHistoryList = await response.json();
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "/quizoo/login-page";
    }

    // createHistoryList が存在するか確認
    if (!Array.isArray(createHistoryList) || createHistoryList.length === 0) {
        console.error("Error: createHistoryList is null or undefined");
        return;
    }

    // 修正: リストを表示する関数を呼び出し
    displayCreateHistory(createHistoryList);

});

function displayCreateHistory(createHistoryData) {
    // 修正: 既存の要素を取得
    const quizListElement = document.getElementById('quiz_list');

    for (let quiz of createHistoryData) {
        const box = document.createElement('div');
        box.setAttribute('class', 'quiz');

        const row = document.createElement('div');
        row.setAttribute('class', 'width row');

        // タイトル
        const title = document.createElement('div');
        title.setAttribute('class', 'title col');
        title.innerText = quiz['title'];
        row.appendChild(title);

        // 説明
        const explanation = document.createElement('div');
        explanation.setAttribute('class', 'explanation d-flex align-items-center col');
        explanation.innerText = quiz['explanation'] ? quiz['explanation'] : "";
        row.appendChild(explanation);

        // ゴミ箱
        const garbageCan = document.createElement('a');
        garbageCan.setAttribute('href', '#');
        garbageCan.setAttribute('class', 'garbage_can col');
        garbageCan.setAttribute('data-bs-toggle', 'modal');
        garbageCan.setAttribute('data-bs-target', '#myModal');

        const trashIcon = document.createElement('i');
        trashIcon.setAttribute('class', 'fa-regular fa-trash-can');

        garbageCan.appendChild(trashIcon);
        row.appendChild(garbageCan);

        const info = document.createElement('div');
        info.setAttribute('class', 'information text-right');

        const create_time = document.createElement('a');
        create_time.setAttribute('class', 'create_time');
        create_time.innerText = quiz['createTime'];

        const genre = document.createElement('a');
        // コード修正: genre の href 属性を確認する
        genre.setAttribute('href', quiz['genre_no']);
        genre.innerText = quiz['genre'];

        const ratio = document.createElement('a');
        ratio.setAttribute('class', 'raito');
        if (quiz['questionCount'] == 0) {
            ratio.innerText = 'nodata'
        } else {
            ratio.innerText = ' ' + (parseFloat(quiz['correctRate']) / parseFloat(quiz['questionCount'])).toFixed(2)
        }
        
        info.appendChild(create_time);
        info.appendChild(genre);
        info.appendChild(ratio);

        box.appendChild(row);
        box.appendChild(info);

        // 修正: 既存の要素にボックスを追加
        quizListElement.appendChild(box);
    }

    return list;
}
