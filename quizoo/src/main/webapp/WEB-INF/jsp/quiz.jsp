
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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic:wght@500&display=swap" rel="stylesheet">
<link rel="icon" type="image/png" href="img/favicon.png">
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
				<ol  id="question_list">
					<li>20歳</li>
					<li>長野県</li>
					<li>東中野</li>
					<li>東中野</li>
				</ol>
				<!-- Button trigger modal -->
				<button type="button" class="close-btn" id="endButton">回答完了</button>
			</div>
			
			<!-- 確認モーダル -->
			<div class="confirm-modal" id="confirm-modal">
				<div class="confirm-modal-body">
					<p class="fonfirm-msg">回答を終了しますか？</p>
					<div class="confirm-choices">
						<button type="button" class="cancel-btn" id="dontSendButton">いいえ</button>
						<button type="button" class="send-btn" id="sendAnswerButton">はい</button>
					</div>
				</div>
			</div>

			<!-- 結果モーダル -->
			<div class="result-modal" id="result-modal">
				<div class="result-modal-body">
					<div class="resultborder">
						<p class="title">結果</p>
						<div class="result-rate" id="result-rate">
							正解率<a>2/3</a>
						</div>
					</div>
					<div class="result-list" id="result-list">
						<div>1.〇</div>
						<div>2.×</div>
						<div>3.〇</div>
					</div>
					<div class="closeButton" id="closeButton">	
						トップページに戻る
					</div>
				</div>
			</div>

			<div class="Problem-statement">
				<div class="border border-dark rounded bg-white">
					<div class="sentence" id="question-sentence">
						こまむらえいやの誕生日は何月何日でしょう？
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
						ああああああああああああああああああああああああああああああああああああああああああああ
					</div>

				</div>

				<table class="answers" id="question-choices">
					<tr>
						<td>
							<button type="button" class="btn btn--orange" id="answer-btn">1</button>
						</td>
						<td>
							<div class="choice">1月1日</div>
						</td>
						<td>
							<button type="button" class="btn btn--orange" id="answer-btn">2</button>
						</td>
						<td>
							<div class="choice">2月18日</div>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" class="btn btn--orange" id="answer-btn">3</button>
						</td>
						<td>
							<div class="choice">4月5日</div>
						</td>
						<td>
							<button type="button" class="btn btn--orange" id="answer-btn">4</button>
						</td>
						<td>
							<div class="choice">5月3日</div>
						</td>
					</tr>
				</table>
			</div>

		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="importmap">
    {
      "imports": {
        "@popperjs/core": "https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/esm/popper.min.js",
        "bootstrap": "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.esm.min.js"
      }
    }
    </script>
	<script type="module" src="js/quiz.js"></script>

</body>

</html>