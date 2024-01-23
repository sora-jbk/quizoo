/**
 * 
*/
var list_box;

window.addEventListener('load',function(){
    list_box = document.querySelector(".quiz_list");
    
    orderBtns = document.querySelectorAll(".order_btn");
    for(var btn of orderBtns){
        btn.addEventListener("click",function () {
            history.replaceState(null,null, window.location.pathname + "?order=" + this.innerText);
            getQuizList();
            
        });
        
    }
    (async ()=>{
        quizList = await getQuizList();
    
        list = await quizlistFactory(quizList);
    
    
        list_box.replaceWith(list); 
    
    })();
})

async function getQuizList() {
    params = new URLSearchParams(window.location.search);

    param = params.get("order");

    if(param){
        param = "?order="+param;
    }else{
        param = "";
    }

    var quizList = await fetch("/quizoo/quizlist" + param);

    quizList = await quizList.json();

    return quizList;

}

// クイズリストを更新するための関数を追加
function updateQuizList(data) {
    list = quizlistFactory(data);
    list_box.replaceWith(list);
    console.log("こちらも成功ですよ");
}

// getQuizListByGenre関数を修正
function getQuizListByGenre(genreNo) {
    // Ajax リクエストを修正
    $.ajax({
        type: "GET",
        url: "/quizoo/quizlist",
        data: { genreNo: genreNo }, // ジャンル情報を渡す
        success: function (data) {
            updateQuizList(data); // 受信したデータでupdateQuizList関数を呼び出す
            console.log("成功ですよ");
        },
        error: function () {
            console.error("ジャンル別のクイズの取得中にエラーが発生しました。");
        }
    });
}

async function quizlistFactory(quizList){

    var list = document.createElement('div');
    list.setAttribute('class','quiz_list');
    
    for(quiz of quizList){

        box = document.createElement('div');
        box.setAttribute('class','box');
        row = document.createElement('div');
        row.setAttribute('class','width row');
        title = document.createElement('div');
        title.setAttribute('class','title col');
        title.innerText = quiz['title'];
        row.appendChild(title);

        explanation = document.createElement('div');
        explanation.setAttribute('class','d-flex align-items-center col');
        explanation.innerText = quiz['explanation'];

        row.appendChild(explanation);

        info = document.createElement('div');
        info.setAttribute('class','information text-right');

        author = document.createElement('a');
        author.setAttribute('class','author');
        author.innerText = quiz['nickname'];
        
        create_time = document.createElement('a');
        create_time.setAttribute('class','create_time');
        create_time.innerText = quiz['createTime'];

        genre = document.createElement('a');
        genre.setAttribute('href',quiz['genre_no']);
        genre.innerText = quiz['genre'];
        
        ratio = document.createElement('a');
        ratio.setAttribute('class','raito');
        ratio.innerText = ' ' + (parseFloat(quiz['correctRate'])/parseFloat(quiz['questionCount'])).toFixed(2);


        info.appendChild(author);
        info.appendChild(create_time);
        info.appendChild(genre);
        info.appendChild(ratio);    


        box.appendChild(row);
        box.append(info);

        list.appendChild(box);

    }

    return list;
}

