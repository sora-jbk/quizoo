/**
 * 
 */
const form = document.getElementById("login-form");


//// 'submit' イベントのハンドラーを追加
form.addEventListener("submit", async (event) => {
    var newName = document.querySelector("#profile-nickname");
   
    
    try{
        var res = fetch("/quizoo/updatenickname?nickname=test",{
          head:{
                "Content-Type":"application/json"
            },
            body:`{nickname=${newName.value}}`,
            method:"post",
            credentials:"include",
        })
        if(!res.ok){
            throw new Error(res.statusText);
        }
    }catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "/quizoo/login-page";
    }

	
    event.preventDefault();

    sendData();
});

// 'submit' イベントのハンドラーを追加
form.addEventListener("submit", async (event) => {
    var newpassword = document.querySelector("#profile-password");

   
    try{
        var res = fetch("/quizoo/updatepassword?password=test",{
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
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "/quizoo/login-page";
    }

    
    event.preventDefault();

    sendData();
});