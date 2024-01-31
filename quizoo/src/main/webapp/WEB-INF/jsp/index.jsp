<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>index</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/44f79b56c2.js"
	crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/earlyaccess/nicomoji.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

<link rel="stylesheet"
	href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/header.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic:wght@500&display=swap"
	rel="stylesheet">


</head>

<body>
	<!-- 上のヘッダー -->
	<%@ include file="header.jsp" %>
	<!--	下のヘッダー-->
	<header class="secondary-header">
		<ul>
			<li><a class="order_btn">new</a></li>
			<li><a class="order_btn">genre</a></li>
			<li><a class="order_btn">popular</a></li>
		</ul>

	</header>
	<!--　クイズリスト  -->
	<div class="quiz_list mx-auto" id="quiz_list">
		<div class="quiz">
			<div class="width row">
				<div class="title col">
					<p class="ex">常識クイズ</p>
				</div>
				<div class="input-group" style="width: 30%;">
					<input type="text" class="form-control" placeholder="Search" id="search_text">
				</div>
				<!-- ハンバーガーメニュー -->
				<button class="drawer__button">
					<span></span>
					<span></span>
					<span></span>
				</button>
				<nav class="drawer__nav">
					<div class="drawer__nav__inner">
						<ul class="drawer__nav__menu">
							<li class="drawer__nav__item">
								<a class="drawer__nav__link" href="profile">プロフィール編集</a>
							</li>
							<li class="drawer__nav__item">
								<a class="drawer__nav__link" href="createhistory">作成履歴</a>
							</li>
							<li class="drawer__nav__item">
								<a class="drawer__nav__link" href="score">スコア詳細</a>
							</li>
							<li class="drawer__nav__item">
								<a class="drawer__nav__link" href="logout">ログアウト</a>
							</li>
						</ul>
					</div>
				</nav>

			</nav>
		</header>
		<!--	下のヘッダー-->
		<header class="secondary-header">
			<ul>
				<li><a class="order_btn">new</a></li>
				<li><a class="order_btn">popular</a></li>
				<li>
					<select id="genre_selector">
					<select>
				</li>
			</ul>
		</header>
		<!--　クイズリスト  -->
		<div class="quiz_list mx-auto" id="quiz_list">
			<div class="quiz">
				<div class="width row">
					<div class="title col"><p class="ex">常識クイズ</p></div>
				</div>
				<div class="d-flex align-items-center col">常識に関するクイズです</div>
			</div>
			<div class="information text-right">
				作成者:<a class="author">たまい</a>ジャンル：<a class="genre">雑学</a><br>
				作成日：<a class="create_time">2023/10/27</a> 正解率：<a class="raito">22.2%</a>

			</div>


		</div>
	</div>

	<div class="fixed-bottom text-right">
		<a href="#" id="myModal1-open"
			class="btn btn-border-shadow btn-border-shadow--color2"
			data-slide-index="1"> <i class="fa-solid fa-pen"></i>
		</a>
	</div>


	<!--作問ポップアップ-->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-lg create-modal-wrap "
			role="document">
			<div class="modal-content create-modal-content ">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">問題を作成する</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body create-modal-body">
					<div id="questions-wrap" class="questions-wrap">
						<div class="question1">
							<div class="question-overview">
								<h6>問題文の最初...</h6>
							</div>
						</div>
					</div>
					<button id="add-question-btn" type="button"
						class="add-question-btn">＋</button>
					<div id="question" class="question-wrap">
						<div id="create-question-text" class="create-question-text">
							<textarea placeholder="問題文を入力"></textarea>
						</div>
						<div class="create-question-choises">
							<fieldset>
								<div class="choise-wrap">
									<input type="radio" id="choise1" name="choise" /> <label
										id="choise-label1" for="choise1"><input type="text"
										name="choise-text1" placeholder="選択肢を入力" /></label>
								</div>
								<div class="choise-wrap">
									<input type="radio" id="choise2" name="choise" /> <label
										id="choise-label2" for="choise3"><input type="text"
										name="choise-text2" /></label>
								</div>
								<div class="choise-wrap">
									<input type="radio" id="choise3" name="choise" /> <label
										id="choise-label3" for="choise3"><input type="text"
										name="choise-text3" /></label>
								</div>
								<div class="choise-wrap">
									<input type="radio" id="choise4" name="choise" /> <label
										id="choise-label3" for="choise4"><input type="text"
										name="choise-text4" /></label>
								</div>
							</fieldset>
						</div>
					</div>
					<button type="button" id="create-btn-primary"
						class="confirmation-btn">確認画面へ</button>
				</div>
			</div>
		</div>
	</div>

	<div id="post-roading" class="post-roading-back">
		<h1 class="post-roading-msg">問題を投稿しています</h1>
		<div class="post-roading-anime"></div>
	</div>


	<!--　作問確認ポップアップ　-->

	<!-- Modal -->
	<div class="modal fade confirm-modal" id="comfirm-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog confirm-modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<h5>以下の内容で投稿します</h5>
				</div>
				<div id="quiz-metadata" class="quiz-metadata text-center">
					<div id="quiz-title">
						<!-- <h6>タイトルを入力</h6> -->
						<input type="text" name="title" class="quiz_title"
							placeholder="タイトル" />
					</div>
					<select name="genre" class="genre">
					</select>
					<div id="explanation">
						<input type="text" name="explanation" class="explanation"
							placeholder="説明" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="correction" data-dismiss="modal">修正する</button>
					<button type="button" id="confirm-btn-primary" class="post">投稿する</button>
				</div>
			</div>
		</div>
	</div>

	<!--	チェックボックスを一つしか選ばせないようにする-->
	<script>
		function limitCheckbox(clickedCheckbox) {
			// クリックされたチェックボックスの親要素からグループを取得
			var checkboxGroup = clickedCheckbox.closest('.clickedCheckbox');

			// グループ内の他のチェックボックスを取得
			var otherCheckboxes = checkboxGroup
					.querySelectorAll('.form-check-input');

			// クリックされたチェックボックス以外の選択を解除
			otherCheckboxes.forEach(function(checkbox) {
				if (checkbox !== clickedCheckbox) {
					checkbox.checked = false;
				}
			});
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="js/index.js"></script>
	<script src="js/createQuiz.js"></script>


</body>

</html>