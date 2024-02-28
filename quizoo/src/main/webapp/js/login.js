/**
 * 
 */

window.addEventListener('load', function() {
	displayLoginForm();

	document.querySelector("#select-login").addEventListener('click', displayLoginForm);
	document.querySelector("#select-signup").addEventListener('click', displaySignUpForm);
	document.querySelector("#new-user-id").addEventListener('input', checkUsedUsername);
	this.document.querySelector("#signup-form").addEventListener("submit",async(event)=>{
		event.preventDefault();
		if(await checkUsedUsername() && checkPasswordAgain()){
			this.document.querySelector("#signup-form").submit();
		}
	});
	this.document.querySelector("#submit-password-again").addEventListener("input",()=>{
		checkPasswordAgain();
	});
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

async function checkUsedUsername() {

	var target = document.querySelector("#new-user-id");

	var username = target.value;

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
		target.setCustomValidity("このIDは既に使われています");
	}else{
		target.setCustomValidity("");
	}
	target.reportValidity();

	return !response["isUsed"];
}

/**
 * 
 */

window.addEventListener('load', function() {
	displayLoginForm();

	document.querySelector("#select-login").addEventListener('click', displayLoginForm);
	document.querySelector("#select-signup").addEventListener('click', displaySignUpForm);
	document.querySelector("#new-user-id").addEventListener('input', checkUsedUsername);
	this.document.querySelector("#signup-form").addEventListener("submit",async(event)=>{
		event.preventDefault();
		if(await checkUsedUsername() && checkPasswordAgain()){
			this.document.querySelector("#signup-form").submit();
		}
	});
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

async function checkUsedUsername() {

	var target = document.querySelector("#new-user-id");

	var username = target.value;

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
		target.setCustomValidity("このIDは既に使われています");
	}else{
		target.setCustomValidity("");
	}
	target.reportValidity();

	return !response["isUsed"];
}

function checkPasswordAgain(){
	var password = document.querySelector("#submit-password");
	var passwordAgain = document.querySelector("#submit-password-again");

	if(password.value != passwordAgain.value){
		passwordAgain.setCustomValidity("パスワードが一致しません");
		passwordAgain.reportValidity();
		return false;
	}else{
		passwordAgain.setCustomValidity("");
		passwordAgain.reportValidity();
		return true;
	}
}