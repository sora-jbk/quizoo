/**
 * 
*/
var list_box;

window.addEventListener('load',function(){
    // gneres = fetch("/quizoo/genres");
    
    var genreSelector = document.querySelector("#genre_selector");
    
    var emptyChoice = document.createElement("option");
    emptyChoice.innerText = "ジャンルを指定しない";
    emptyChoice.value = -1;
    genreSelector.appendChild(emptyChoice);
    
    for(var i = 0 ; i < genres.length ; i++){
        var genreChoce = document.createElement("option");
        genreChoce.innerText = genres[i].genre_title;
        genreChoce.value = genres[i].genre_no;
        genreSelector.appendChild(genreChoce);
    }
    
    
    genreSelector.addEventListener("change",()=>{
        let url = new URL(this.window.location.href);
        
        if(genreSelector.value == -1){
            url.searchParams.delete("genre_no");
        }else{
            url.searchParams.set("genre_no",genreSelector.value);
        }
        window.history.pushState(null,null,url);
        
        updateQuizList();
    })
    
    list_box = document.querySelector("#quiz_list");
    
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

    console.log(params.toString());


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
        genre.setAttribute('href',quiz['genre_no']);
        genre.innerText = quiz['genre'];
        
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

