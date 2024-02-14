/**
 * 
 */

window.addEventListener('load', function() {
	var queryString = null;
	queryString = location.search;
	//console.log(queryString);

	if (queryString == '?faild') {
		document.getElementById('miss').classList.remove('none');
	}
});
 //ログイン
var loginInputId = document.getElementById("login-username");

// wataru
loginInputId.addEventListener('input', function(event) {
	//入力された文字列を取得
	var inputValue = event.target.value;
  
	// 無効な文字を削除
	var sanitizedValue = "";
	var test="";
	var flag = 0;
	for (var i = 0; i < inputValue.length; i++) {
	  var char = inputValue[i];
	  if (!/[^a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/.test(char)) {
		sanitizedValue += char;
	  } else {
		test += char;
		flag = 1;	
	  }
	}
	console.log("");
	console.log("入力された文字:"+inputValue);
	console.log("消された文字："+test);
	console.log("フラグ:"+flag);
	if (flag === 1) {
		console.log("消された文字数*"+test.length);
		//event.target.value = inputValue
		event.target.value = sanitizedValue.slice(0,-1*(test.length));
		console.log("表示される文字:"+event.target.value);
	}
  
	// エラーメッセージを表示/非表示
	if (inputValue !== sanitizedValue) {
	  document.getElementById("login-errorMessage").style.display = "inline";
	} else {
	  document.getElementById("login-errorMessage").style.display = "none";
	}
});

//地引のやつ
// loginInputId.addEventListener('input',check);
// let oldval = '';
// function check(event){
// 	var inputValue = event.target.value;

// 	console.log(inputValue);
// 	console.log();


// 	var regexp = /[^a-z^A-Z^0-9^!^@]/
// 	//正規表現外ならfalse
// 	var isMatch = regexp.test(inputValue);

// 	if (isMatch){
// 		// 無視する文字が含まれている場合はエラーメッセージを表示
// 		document.getElementById("login-errorMessage").style.display = "inline";
// 		console.log(event.target.value);
// 		event.target.value = inputValue.match(/[a-zA-Z0-9!@]/);
// 		console.log("表示される文字："+event.target.value);
// 	} else {
// 		// エラーメッセージをクリア
// 		document.getElementById("login-errorMessage").style.display = "none";
// 		oldval = inputValue;
// 	}
// }



//signin
var registInputId = document.getElementById("register-userid");
registInputId.addEventListener('input', function(event) {
	change(event);
});


function change(event) {
	// 全角英数字を半角に変換
	if (event.isComposing || event.key === 'Process') {
		// IME入力中
		event.target.disabled = true;
		event.target.disabled = false;
		event.target.value = "";

		setTimeout(() => {
			event.target.focus();
		}, 5);
		document.getElementById("signup-errorMessage").textContent = "英数字および記号以外は使えません";
	} else {
		// IME入力中でない
		document.getElementById("signup-errorMessage").textContent = "";
		var inputValue = event.target.value;
		// 英数字と記号以外の文字を無視
		var sanitizedValue = inputValue.replace(/[^a-z^A-Z^0-9^!^@]/g,"");
		console.log("IME外:"+ inputValue);
		if (inputValue !== sanitizedValue) {
			// 無視する文字が含まれている場合はエラーメッセージを表示
			document.getElementById("signup-errorMessage").textContent = "英数字および記号以外は使えません";
		} else {
			// エラーメッセージをクリア
			document.getElementById("signup-errorMessage").textContent = "";
		}
		// 入力を修正
		if (inputValue !== sanitizedValue) {
			event.target.value = sanitizedValue;
		}	
	}
  }
