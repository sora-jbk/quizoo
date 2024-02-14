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
		
		setTimeout(() => {
			event.target.focus();
		}, 5);
		event.target.value = event.target.value.replace(regexp,"");
		console.log("IME中"+event.target.value);

		
		document.getElementById(message).style.display = "inline";
	} else {
		// IME入力中でない
		event.target.disabled = true;
		event.target.disabled = false;
		
		setTimeout(() => {
			event.target.focus();
		}, 5);
		
		inputValue = event.target.value;
		console.log("IME外:"+ inputValue);
		if(regexp.test(inputValue)) {
			console.log("非正規：");
			event.target.value = inputValue.replace(regexp,"");
		}

		// エラーメッセージをクリア
		document.getElementById(message).style.display = "none";
	}
  }
