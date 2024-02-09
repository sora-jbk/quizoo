/**
 * 
 */
let nicknameList;
window.addEventListener("load", async () => {
    nicknameList = await fetch("/quizoo/nickname", {
        credentials: "include"
    })
    nicknameList = await nicknameList.json();
    nickname = nicknameList[0];
    document.getElementById('profile-nickname').value = nickname;

});

function sendData() {
}

function changeMessage(){
	var bar = document.getElementById("snackbar");
	bar.className = "show";
  	setTimeout(function(){ bar.className = bar.className.replace("show", ""); }, 2000);
}


// 'submit' イベントのハンドラーを追加
form.addEventListener("submit", async (event) => {
    var newName = document.querySelector("#profile-nickname");

    console.log(newName.value);

    var result = fetch("/quizoo/updatenickname?nickname=test",{
        head:{
            "Content-Type":"application/json"
        },
        body:`{nickname=${newName.value}}`,
        method:"post",
        credentials:"include",
    })

    event.preventDefault();
	
	changeMessage();
    sendData();
});