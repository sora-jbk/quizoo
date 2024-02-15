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


 //login
var loginInputId = document.getElementById("login-username");
loginInputId.addEventListener('input', function(event) {
	message = "login-errorMessage";
	change(event, message);
});

//signin
var registInputId = document.getElementById("register-userid");
registInputId.addEventListener('input', function(event) {
	message = "signup-errorMessage";
	change(event, message);
});



function change(event) {
	var regexp = /[^a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/;
	if (event.isComposing || event.key === 'Process') {
		// IME入力中

		event.target.disabled = true;
		event.target.disabled = false;

		//タイムラグつけないとforcsできない
		sleep(event, 10);
		//IME中に入った全角を消す
		event.target.value = event.target.value.replace(regexp,"");

		//エラーメッセージ表示
		document.getElementById(message).style.display = "inline";
	} else {
		// IME入力中でない
		event.target.disabled = true;
		event.target.disabled = false;

		//タイムラグつけないとforcsできない
		setTimeout(() => {
			event.target.focus();
		}, 5);
		console.log("たまに打てる");
		//おそらく、forcsまでに入力された全角文字が入るから消す
		event.target.value = event.target.value.replace(regexp,"");

		// エラーメッセージをクリア
		document.getElementById(message).style.display = "none";
	}
  }
function sleep(event,ms) {
	setTimeout(() => {
		event.target.focus();
	}, 5);
	console.log("ss");
	return new Promise(resolve => setTimeout(resolve, ms));
};

