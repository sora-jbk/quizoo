
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>quiz</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/quiz.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link href="https://fonts.googleapis.com/earlyaccess/nicomoji.css"
	rel="stylesheet">
</head>

<body>
	<header>
		<nav class="navbar navbar-light fixed-top">
			<div class="header-img">
				<img src="img/quizoo_nobackground.png" alt="quizoo">
			</div>
		</nav>
	</header>
	<div class="container">

		<div class="quizlist">
			<div class="d-flex flex-column">
				<a>1.20歳</a> <a>2.長野県</a> <a>3.東中野</a> <a>4.東中野</a><a class="active_question">5.未回答</a>

				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal">回答完了</button>
			</div>

			<!-- 1番目のモーダル -->
			<div class="modal fade text-center" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true"
				data-bs-backdrop="static">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-body">本当に終了しますか？</div>
						<div class="modal-footer">
							<button type="button" class="no-btn btn btn-secondary"
								data-bs-dismiss="modal">いいえ</button>
							<button type="button" class="yes-btn btn btn-primary"
								id="endButton" data-bs-target="#secondModal">はい</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 2番目のモーダル -->
			<div class="modal fade" id="secondModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true"
				data-bs-backdrop="static">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header text-center">
							<h1 class="modal-title fs-7" id="exampleModalLabel">score</h1>
						</div>
						<div class="modal-body">
							<div class="question-result">
								<p>1. 〇</p>
								<p>2. ✕</p>
								<p>3. 〇</p>
								<p>4. 〇</p>
								<p>5. ✕</p>
							</div>
							<div class="score text-end">
								<h3>Points earned</h3>
								<h1>200</h1>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="close-btn btn btn-primary"
								id="closeButton">close</button>
						</div>
					</div>
				</div>
			</div>

			<div class="Problem-statement">
				<div class="text-right">
					<a>5/5</a>
				</div>
				<div class="border border-dark rounded bg-white">
					<div class="sentence">こまむらえいやの誕生日は何月何日でしょう？
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ</div>


				</div>

				<table class="answers">
					<tr>
						<td>
							<button type="button" class="btn btn--orange">1</button>
						</td>
						<td>
							<div class="choice">1月1日</div>
						</td>
						<td>
							<button type="button" class="btn btn--orange">2</button>
						</td>
						<td>
							<div class="choice">2月18日</div>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" class="btn btn--orange">3</button>
						</td>
						<td>
							<div class="choice">4月5日</div>
						</td>
						<td>
							<button type="button" class="btn btn--orange">4</button>
						</td>
						<td>
							<div class="choice">5月3日</div>
						</td>
					</tr>
				</table>
			</div>

		</div>
	</div>

	<script>
		document.getElementById('endButton').addEventListener('click',
				function() {
					// 現在のモーダルを閉じる
					$('#exampleModal').modal('hide');

					// 新しいモーダルを表示
					$('#secondModal').modal('show');
				});
		document.getElementById('closeButton').addEventListener('click',
				function() {
					// closeButtonがクリックされたらindex.htmlに遷移
					window.location.href = 'index';
				});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>