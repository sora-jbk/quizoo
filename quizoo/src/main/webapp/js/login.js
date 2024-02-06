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


var registInputId = document.getElementById("register-userid");
registInputId.addEventListener('input', function() {
	var inputValue = document.getElementById("register-userid").value;
	var regex = /^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]+$/;

	if (!regex.test(inputValue)) {

		alert("半角の英数字か記号でお願いします");

		document.getElementById("register-userid").value=inputValue.slice(0, -1);
		
		return false;
	}
	return true;
});

var loginInputId = document.getElementById("login-username");
loginInputId.addEventListener('input', function() {
	var inputValue = document.getElementById("login-username").value;
	var regex = /^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]+$/;

	if (!regex.test(inputValue)) {

		alert("半角の英数字か記号でお願いします");

		document.getElementById("login-username").value=inputValue.slice(0, -1);
		
		return false;
	}
	return true;
});
