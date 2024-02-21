/**
 * 
 */

document.addEventListener('DOMContentLoaded', function () {
    // ハンバーガーボタンクリックで実行
    document.querySelector("#side-menu-drawer").addEventListener('click', function () {
        showSideMenu();
    });

    // ドロワーナビゲーションリンククリックで非アクティブ化
    document.querySelector("#side-menu").addEventListener('click',function () {
        if(event.target == document.querySelector("#side-menu-body")) return;
        hideSideMenu();
    });

});

window.addEventListener('load', function() {
	var pathname = null;
	pathname = location.pathname;

	if (pathname != '/quizoo/index') {
		document.getElementById('input-group').classList.remove('seach-box');

	}
});

function showSideMenu() {
    document.querySelector("#side-menu").style.display = "block";
    setTimeout(()=>{
	    document.querySelector("#side-menu").classList.add("show");		
	})
}

function hideSideMenu() {
    document.querySelector("#side-menu").classList.remove("show");
    setTimeout(() => {
        document.querySelector("#side-menu").style.display = "none";
    }, 500);
}