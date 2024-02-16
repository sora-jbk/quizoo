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
<link rel="icon" type="image/png" href="img/favicon.png">
</head>

<body>
	<!-- 上のヘッダー -->
	<%@ include file="header.jsp"%>
	<!--	下のヘッダー-->
	<header class="secondary-header">
		<ul>
			<li><a class="order_btn">new</a></li>
			<li><a class="order_btn">popular</a></li>
			<li><select class="genre-selector" id="genre_selector">
			</select></li>
		</ul>

	</header>
	<!--　クイズリスト  -->
	<div class="quiz_list mx-auto" id="quiz_list">
		<div class="loader" id="loader">
			<p class="txt">ローディング中...</p>
		</div>

	</div>

	<div class="fixed-bottom text-right">
		<a href="#" id="create-quiz-open"
			class="btn btn-border-shadow btn-border-shadow--color2"
			data-slide-index="1"> <i class="fa-solid fa-pen"></i>
		</a>
	</div>


	<!--作問ポップアップ-->
	<div class="create-quiz-modal-back" id="create-quiz-modal-back">
		<span class="close_btn" id="create-close-btn"></span>
		<div class="create-quiz-modal">
			<div class="stored-question" id="stored-question">
				<div class="add-question" id="add-question">
					<span class="plus-icon"></span><span>問題を追加する</span>
				</div>
			</div>
			<div class="question-editor" id="question-editor">
				<textarea type="text" name="question-text" id="question-text"
					class="question-text" placeholder="問題文"></textarea>
				<fieldset class="choices" id="choices">
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio">
						<!-- 隙間をなくすためのコメント
						 -->
						<input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢1">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio">
						<!-- 隙間をなくすためのコメント
						 -->
						<input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢2">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio">
						<!-- 隙間をなくすためのコメント
						 -->
						<input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢3">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio">
						<!-- 隙間をなくすためのコメント
						 -->
						<input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢4">
					</div>
				</fieldset>
			</div>
			<div class="submit-quiz-btn" id="submit-quiz-btn">
				<i class="next-btn fa-solid fa-arrow-right"></i>
			</div>
		</div>
	</div>

	<!--　作問確認ポップアップ　-->
	<div class="metadata-modal-back" id="metadata-modal-back">
		<span class="close_btn" id="metadata-close-btn"></span>
		<div class="metadata-modal">
			<h5>以下の内容で投稿します</h5>
			<div id="quiz-metadata" class="quiz-metadata">
				<input type="text" name="title" id="post-title" placeholder="タイトル">
				<select name="genre" id="post-genres">
				</select> <input type="text" name="explanation" id="post-explanation"
					placeholder="説明">
			</div>
			<div class="metadata-buttons">
				<button class="cancel-quiz-btn" id="cancel-quiz-btn">
					<i class="fa-solid fa-arrow-rotate-left"></i> 修正
				</button>
				<button class="post-quiz-btn" id="post-quiz-btn">
					<i class="fa-solid fa-check"></i> 投稿
				</button>
			</div>
		</div>
	</div>

	<div class="snackbar" id="snackbar">投稿中です...。</div>

	<!-- Modal -->

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