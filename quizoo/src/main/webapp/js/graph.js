
let historyList;

function showDivByCondition() {
	if (historyList == null) {
		document.getElementById('existScore').style.display = 'none';
		console.log("スコアがない");
		document.getElementById('noScore').innerHTML = '<p>スコアがありません</p>';

	} else {
		document.getElementById('noScore').style.display = 'none';
	}	
}

window.addEventListener('load', async () => {
	try{
		historyList = await fetch("answerhistory", {
			credentials: "include"
		});
		if(!historyList.ok){
			throw new Error(historyList.statusText);
		}
		historyList = await historyList.json();
	}catch(e){
        // エラーが発生しました
        alert("エラーが発生しました。\nログインページに戻ります。");
        window.location.href = "login-page";
    }

	showDivByCondition();

	let correctCount = 0;
	let questionCount = 0;
	// 正解率をだすfor文
	for(var i = 0; i < historyList.length; i++) {
		
		correctCount += historyList[i].correctCount;
		questionCount += historyList[i].questionCount;
		var quizCount = i+1;

		var correctRate = Math.round(((correctCount / questionCount) * 100) * 10) / 10;
		config.data.datasets[0].data.push(correctRate);
		config.data.labels.push(quizCount);

		var rate = Math.round(((correctCount * correctCount) / questionCount) * 10) / 10; 
		config2.data.datasets[0].data.push(rate);
		config2.data.labels.push(quizCount);

	}
	// 正解率のChart 
//	const ctx = document.getElementById('chart').getContext('2d');
//	const myChart = new Chart(ctx, config);
//	document.getElementById('rate').innerHTML = 'レート ' + rate;


	// レートのChart 
	const ctx = document.getElementById('chart').getContext('2d');
	const myChart = new Chart(ctx, config);
	document.getElementById('correctRate').innerHTML = '正解率 ' + correctRate + '%';
});

let config = {
	type: 'line',
	data: {
		labels: [],
		datasets: [
			{
				label: '正解率',
				data: [],
				borderColor: 'rgba(255, 99, 132, 1)',
				backgroundColor: 'rgba(255, 99, 132, 0.2)',
			},	          // ... 追加の datasets
		]
	},
	options: {
		responsive: true,
		plugins: {
			legend: {
				position: 'top',
				labels: {
					usePointStyle: true,
				},
			},
			title: {
				display: true,
			}
		},
		scales: {
			x: {
				min:0,
				stepSize: 1,
				title: {
					display: true,
					text: '回答数',
				},
			},
			y: {				   
				min:0,
				max:100,
				stepSize: 1,
				title: {
					display: true,
					text: '正答率(%)',
					padding: {
						top: 0,
						bottom: 0,
					},
				},	
			}
		}	
	}
};


let config2 = {
	type: 'line',
	data: {
		labels: [],
		datasets: [
			{
				label: 'レーティング',
				data: [],
				borderColor: 'rgba(255, 99, 132, 1)',
				backgroundColor: 'rgba(255, 99, 132, 0.2)',
			},
		]
	},
	options: {
		responsive: true,
		plugins: {
			legend: {
				position: 'top',
				labels: {
					usePointStyle: true,
				},
			},
			title: {
				display: true,
			}
		},
		scales: {
			x: {
				min:0,
				stepSize: 1,
				title: {
					display: true,
					text: '回答数',
				},
			},
			y: {				   
				min:0,
				stepSize: 1,
				title: {
					display: true,
					text: 'レート',
					padding: {
						top: 0,
						bottom: 0,
					},
				},	
			}
		}
	}
};

