<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>スコア</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/score.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://kit.fontawesome.com/c82cac4dcf.js"
	crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/earlyaccess/nicomoji.css"
	rel="stylesheet">

</head>

<body>
	<header>
		<nav class="navbar navbar-light fixed-top">
			<div class="header-img">
				<a href="index"><img src="img/quizoo_nobackground.png" alt="quizoo"></a>
			</div>

			<!-- ハンバーガーメニュー -->
			<buttom class="drawer__button"> <span></span> <span></span>
			<span></span> </buttom>
			<nav class="drawer__nav">
				<div class="drawer__nav__inner">
					<ul class="drawer__nav__menu">
						<li class="drawer__nav__item"><a class="drawer__nav__link"
							href="profile">プロフィール編集</a></li>
						<li class="drawer__nav__item"><a class="drawer__nav__link"
							href="createhistory">作成履歴</a></li>
						<li class="drawer__nav__item"><a class="drawer__nav__link"
							href="score">スコア詳細</a></li>
						<li class="drawer__nav__item"><a class="drawer__nav__link"
							href="#">ログアウト</a></li>
					</ul>
				</div>
			</nav>
		</nav>

	</header>
	<h1 style="text-align: center">スコア詳細</h1>
	<div class="container">
		<div class="score_accuracyrate row">
			<div class="score col-sm-6">
				<span class="border border-primary-subtle border-4"> score
					10050 </span>
			</div>

			<div class="accuracyrate col-sm-6">
				<span class="border border-primary-subtle border-4"> 正解率
					70.5% </span>
			</div>
		</div>
	</div>

	<!--	ハンバーガー -->
	<script>
		$(function() {
			// ハンバーガーボタンクリックで実行
			$(".drawer__button").click(function() {
				$(this).toggleClass("active");
				$(".drawer__nav").toggleClass("active");
			});

			$(".drawer__nav__link").click(function() {
				$(".drawer__button").removeClass("active");
				$(".drawer__nav").removeClass("active");
			});

			// ページ内スクロール
			$('a[href^="#"]').click(function() {
				const speed = 400;
				let href = $(this).attr("href");
				let target = $(href == "#" || href == "" ? "html" : href);
				let position = target.offset().top;
				$("body,html").animate({
					scrollTop : position
				}, speed, "swing");
				return false;
			});
			// function
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
	<script
		src="js/Graph.js"></script>
</body>

</html>