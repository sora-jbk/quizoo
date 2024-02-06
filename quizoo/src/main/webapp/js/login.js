/**
 * 
 */

window.addEventListener('load', function() {
	var queryString = null;
	queryString = location.search;
	console.log(queryString);

	if (queryString == '?faild') {
		document.getElementById('miss').classList.remove('none');

	}
});

var loginInputId = document.getElementById("login-username");
loginInputId.addEventListener('input', function(event) {

	// 入力された文字列を取得
	var inputValue = event.target.value;

	// 英数字と記号以外の文字を無視
	var sanitizedValue = inputValue.replace(/[^a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/g, '');

	if (inputValue !== sanitizedValue) {
		// 無視する文字が含まれている場合はエラーメッセージを表示
		document.getElementById("login-errorMessage").textContent = "英数字および記号以外は使えません";
	} else {
		// エラーメッセージをクリア
		document.getElementById("login-errorMessage").textContent = "";
	}

	// 入力を修正
	event.target.value = sanitizedValue;
});


var registInputId = document.getElementById("register-userid");
registInputId.addEventListener('input', function(event) {

	// 入力された文字列を取得
	var inputValue = event.target.value;

	// 英数字と記号以外の文字を無視
	var sanitizedValue = inputValue.replace(/[^a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/g, '');

	if (inputValue !== sanitizedValue) {
		// 無視する文字が含まれている場合はエラーメッセージを表示
		document.getElementById("signup-errorMessage").textContent = "英数字および記号以外は使えません";
	} else {
		// エラーメッセージをクリア
		document.getElementById("signup-errorMessage").textContent = "";
	}

	// 入力を修正
	event.target.value = sanitizedValue;
});
