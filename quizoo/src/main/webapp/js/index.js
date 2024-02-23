/**
 * 
*/
var list_box;

// ブラウザバックを検知
window.addEventListener('popstate',function(){
    if(this.document.referrer.includes("index")){
        updateQuizList();
    }
})

window.addEventListener('load',async function(){

    try{
        genres = await fetch("genres");
        if(!genres.ok){
            throw new Error(genres.statusText);
        }
        genres = await genres.json();
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "login-page";
    }



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
    orderBtns = document.querySelectorAll("#order_btn");
    for(var btn of orderBtns){
        btn.addEventListener("click",function () {

            let url = new URL(window.location.href);
            url.searchParams.set("order",this.innerText);
            window.history.pushState(null,null,url);
            updateQuizList();
            
        });
        
    }
    
    this.document.querySelector("#search-textbox").addEventListener("input",searchTitle);
    
    
    updateQuizList();
    
	//トップに戻るボタンの制御
    window.addEventListener('scroll',goTopBtnControl);
    
})

async function searchTitle(){
    var searchStr = document.querySelector("#search-textbox").value;
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
    var url = new URL(window.location.href);
    if(url.searchParams.get("genre_no") != null && url.searchParams.get("genre_no") != -1){
        document.querySelector("#genre_selector").value = url.searchParams.get("genre_no");   
    }

    quizList = await getQuizList();
    let list;
    
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
    
    list_box = document.querySelector("#quiz_list").cloneNode(false);
    
    for(var i = 0 ; i < list.length ; i++){
        list_box.appendChild(list[i]);
        list[i].style.transitionDelay = i * 0.025 + 's';
    }
    
    document.querySelector("#quiz_list").replaceWith(list_box);
    
    
    setTimeout(() => {
        list = document.querySelectorAll(".quiz");
        
        for(var i = 0 ; i < list.length ; i++){
            list[i].classList.add("show");
        }
    },25);

    setInterval(() => {
        list = document.querySelectorAll(".quiz");
        
        for(var i = 0 ; i < list.length ; i++){
            list[i].style.transitionDelay = null;
        }
    },list.length * 25 + 200);
}

async function getQuizList() {
    params = new URLSearchParams(window.location.search);

    try{
        var quizList = await fetch("quizlist?" + params);
    
        quizList = await quizList.json();

    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "login-page";
    }


    return quizList;

}

function quizlistFactory(quizList){
    var list = [];
    for(quiz of quizList){
        box = document.createElement('div');
        box.setAttribute('class','quiz');
        
        row = document.createElement('div');
        row.setAttribute('class','metadata');
        title = document.createElement('div');
        title.setAttribute('class','title');
        title.innerText = quiz['title'];
        row.appendChild(title);
        
        explanation = document.createElement('div');
        explanation.setAttribute('class','explanation');
        explanation.innerText = quiz['explanation'] ? quiz['explanation'] : "";
        
        row.appendChild(explanation);
        
        info = document.createElement('div');
        info.setAttribute('class','information');
        
        
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

        list.push(box);

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


function goTopBtnControl(){
    if(window.scrollY > 300){
        document.querySelector("#go-top-btn").style.bottom = "27px";
    }else{
        document.querySelector("#go-top-btn").style.bottom = "-80px";
    }
}
