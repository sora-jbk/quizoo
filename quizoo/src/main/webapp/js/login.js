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


var inputId = document.getElementById("register-userid");
inputId.addEventListener('input', function() {
	var inputValue = document.getElementById("register-userid").value;
	var regex = /^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]+$/;

	if (!regex.test(inputValue)) {

		alert("半角の英数字か記号でお願いします");

		document.getElementById("register-userid").value=inputValue.slice(0, -1);
		
		return false;
	}

	

	return true;
})
