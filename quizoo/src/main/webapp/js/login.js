/**
 * 
 */

window.addEventListener('load', function() {
	displayLoginForm();

	document.querySelector("#select-login").addEventListener('click', displayLoginForm);
	document.querySelector("#select-signup").addEventListener('click', displaySignUpForm);
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