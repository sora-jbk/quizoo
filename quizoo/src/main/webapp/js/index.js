/**
 * 
*/
var list_box;

window.addEventListener('load',async function(){
	
    genres = await fetch("/quizoo/genres");

    genres = await genres.json();

    // メタデータのモーダルが非表示になった時の処理
    const createQuizModalObserver = new MutationObserver(mutations => {
        if (mutations[0].target.style.display == "none") {
            updateQuizList();
        }
    });
    createQuizModalObserver.observe(document.querySelector("#metadata-modal-back"), {
        attributes: true
    });
    

    // ジャンルのプルダウンを初期化
    var genreSelector = document.querySelector("#genre_selector");
    
    var emptyChoice = document.createElement("option");
    emptyChoice.innerText = "genre";
    emptyChoice.value = -1;
    genreSelector.appendChild(emptyChoice);
    
    for(var i = 0 ; i < genres.length ; i++){
        var genreChoce = document.createElement("option");
        genreChoce.innerText = genres[i].genre_title;
        genreChoce.value = genres[i].genre_no;
        genreSelector.appendChild(genreChoce);
    }
    
    genreSelector.addEventListener("change",() => filterGenre(genreSelector.value));
    
    
    list_box = document.querySelector("#quiz_list");
    

    // ソートボタンをクリックした時の処理
    orderBtns = document.querySelectorAll(".order_btn");
    for(var btn of orderBtns){
        btn.addEventListener("click",function () {

            let url = new URL(window.location.href);
            url.searchParams.set("order",this.innerText);
            window.history.pushState(null,null,url);
            updateQuizList();
            
        });
        
    }
    
    this.document.querySelector("#search_text").addEventListener("input",searchTitle);
    
    
    updateQuizList();

    // ハンバーガーボタンクリックで実行
    document.querySelector(".drawer__button").addEventListener('click', function () {
        this.classList.toggle("active");
        document.querySelector(".drawer__nav").classList.toggle("active");
    });

    // ドロワーナビゲーションリンククリックで非アクティブ化
    document.querySelectorAll(".drawer__nav__link").forEach(function (link) {
        link.addEventListener('click', function () {
            document.querySelector(".drawer__button").classList.remove("active");
            document.querySelector(".drawer__nav").classList.remove("active");
        });
    });
    
})

async function searchTitle(){
    var searchStr = document.querySelector("#search_text").value;
    var url = new URL(window.location.href);
    if(searchStr == null || searchStr == ""){
        url.searchParams.delete("search");
    }else{
        url.searchParams.set("search",searchStr);
    }
    window.history.pushState(null,null,url);
    updateQuizList();
}

async function updateQuizList() {	


    setTimeout(fadeOutLoader, 3000);
    
    quizList = await getQuizList();
    var list;
    
    if(!quizList){
        var nodata = document.createElement('div');
        nodata.setAttribute('class','noquizdata');
        var nodataMsg = document.createElement('p');
        nodataMsg.innerText = 'クイズがありません。';
        nodata.appendChild(nodataMsg);
        list = nodata;
    }else{
        list = quizlistFactory(quizList);
    }
    
    list_box.replaceWith(list); 
    list_box = list;


}

async function getQuizList() {
    params = new URLSearchParams(window.location.search);


    var quizList = await fetch("/quizoo/quizlist?" + params);

    quizList = await quizList.json();

    return quizList;

}

function quizlistFactory(quizList){
    var list = document.createElement('div');
    list.setAttribute('class','quiz_list');
    list.setAttribute('id','quiz_list');
    for(quiz of quizList){
        box = document.createElement('div');
        box.setAttribute('class','quiz');
        
        row = document.createElement('div');
        row.setAttribute('class','width row');
        title = document.createElement('div');
        title.setAttribute('class','title col');
        title.innerText = quiz['title'];
        row.appendChild(title);
        
        explanation = document.createElement('div');
        explanation.setAttribute('class','d-flex align-items-center col');
        explanation.innerText = quiz['explanation'] ? quiz['explanation'] : "";
        
        row.appendChild(explanation);
        
        info = document.createElement('div');
        info.setAttribute('class','information text-right');
        
        
        create_time = document.createElement('a');
        create_time.setAttribute('class','create_time');
        create_time.innerText = quiz['createTime'];
        
        genre = document.createElement('a');
        genre.innerText = quiz['genre'];
        genre.setAttribute('class','genre');
        genre.addEventListener('click',filterGenre.bind(null,quiz['genreNo']));
        
        ratio = document.createElement('a');
        ratio.setAttribute('class','raito');
        if(quiz['questionCount'] ==  0){
            ratio.innerText = 'nodata'
        }else{
            ratio.innerText = ' ' + (parseFloat(quiz['correctRate'])/parseFloat(quiz['questionCount'])).toFixed(2)
        }
        
        author = document.createElement('a');
        author.setAttribute('class','author');

        let nickname = quiz['authorNickname'];
        author.innerText = nickname ? nickname : "ななし";
        author.setAttribute('href','profile?user_no='+quiz['authorNo']);
        
        info.appendChild(author);
        info.appendChild(create_time);
        info.appendChild(genre);
        info.appendChild(ratio);    
        
        
        box.appendChild(row);

        link = document.createElement('a');
        link.setAttribute('href','answer?quiz_id='+quiz['quizId']);
        link.setAttribute('class','answer-link')
        box.appendChild(link);
        
        box.append(info);

        list.appendChild(box);

    }

    return list;
}


// チェックボックスを一つしか選ばせないようにする
function limitCheckbox(clickedCheckbox) {
    // クリックされたチェックボックスの親要素からグループを取得
    var checkboxGroup = clickedCheckbox.closest('.clickedCheckbox');

    // グループ内の他のチェックボックスを取得
    var otherCheckboxes = checkboxGroup.querySelectorAll('.form-check-input');

    // クリックされたチェックボックス以外の選択を解除
    otherCheckboxes.forEach(function (checkbox) {
        if (checkbox !== clickedCheckbox) {
            checkbox.checked = false;
        }
    });
}

// ジャンルを指定する
function filterGenre(genreNo) {
    let url = new URL(window.location.href);
    if(genreNo == -1){
        url.searchParams.delete("genre_no");
    }else{
        url.searchParams.set("genre_no",genreNo);
    }
    window.history.pushState(null,null,url);
    var genrePulldown = document.querySelector("#genre_selector");
    genrePulldown.value = genreNo;
    updateQuizList();
}

    // ローダーをフェードアウトする関数
function fadeOutLoader() {
    // ローダーの要素にフェードアウト用のクラスを追加
    loaderSection.classList.add('fadeOut');
    // 2秒後にローダーの要素を非表示にする
    setTimeout(function() {
        document.querySelector('#loader').style.display = 'none';
    }, 3000);
}
