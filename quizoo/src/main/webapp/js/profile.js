/**
 * 
 */
const form = document.getElementById("login-form");

function showCompletionMessage(){
    document.querySelector("#snackbar").classList.add("show");
    setInterval(function(){
        document.querySelector("#snackbar").classList.remove("show");
    },2000);
}


//// 'submit' イベントのハンドラーを追加
form.addEventListener("submit", async (event) => {
    var newName = document.querySelector("#profile-nickname");
   
    
    try{
        var res = await fetch("updatenickname?nickname=test",{
          head:{
                "Content-Type":"application/json"
            },
            body:`{nickname=${newName.value}}`,
            method:"post",
            credentials:"include",
        })
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "login-page";
    }

	showCompletionMessage();
    event.preventDefault();

    sendData();
});

// 'submit' イベントのハンドラーを追加
form.addEventListener("submit", async (event) => {
    var newpassword = document.querySelector("#profile-password");

   
    try{
        var res = fetch("updatepassword?password=test",{
            head:{
                "Content-Type":"application/json"
            },
            body:`{password=${newpassword.value}}`,
            method:"post",
            credentials:"include",
        })
        if(!res.ok){
            throw new Error(res.statusText);
        }
    }catch(e){
        // エラーが発生しました
//        alert("エラーが発生しました。\nログインページに戻ります。");
//        window.location.href = "login-page";
    }

    showCompletionMessage();
    event.preventDefault();

    sendData();
});