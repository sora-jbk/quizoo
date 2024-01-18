/*
* スコア詳細にいれるグラフの作成
*/

window.addEventListener('load',async ()=>{
    var historyList = await fetch("/quizoo/answerhistory",{
		credentials:"include"
	});
    historyList = await historyList.json();
    return historyList;
})
