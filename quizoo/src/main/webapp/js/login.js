/**
 * 
 */

window.addEventListener('load', function() {
	displayLoginForm();

	document.querySelector("#select-login").addEventListener('click', displayLoginForm);
	document.querySelector("#select-signup").addEventListener('click', displaySignUpForm);
	document.querySelector("#new-user-id").addEventListener('input', checkUsedUsername);
	this.document.querySelector("#signup-form").addEventListener("submit",checkUsedUsername);
});

function displayLoginForm() {
	document.querySelector('#signup-form').style.display = 'none';
	document.querySelector('#login-form').style.display = 'block';
	document.querySelector("#select-signup").classList.remove('active');
	document.querySelector("#select-login").classList.add('active');
}

function displaySignUpForm() {
	document.querySelector('#login-form').style.display = 'none';
	document.querySelector('#signup-form').style.display = 'block';
	document.querySelector("#select-login").classList.remove('active');
	document.querySelector("#select-signup").classList.add('active');
}

async function checkUsedUsername(event) {
	if(!event.target.value) return;

	var username = event.target.value;

	var response = await fetch("checkuserid", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"

		},
		body: JSON.stringify({
			"userid": username
		})
	});
	response = await response.json();

	if(response["isUsed"]){
		event.target.setCustomValidity("このIDは既に使われています");
	}else{
		event.target.setCustomValidity("");
	}
	event.target.reportValidity();

	return !response["isUsed"];
}