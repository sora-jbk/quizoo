<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>index</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/header.css">
		<script src="https://kit.fontawesome.com/44f79b56c2.js" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic:wght@500&display=swap" rel="stylesheet">
	<link rel="icon" type="image/png" href="img/favicon.png">
	<link href="https://fonts.googleapis.com/earlyaccess/nicomoji.css" rel="stylesheet">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet"
		href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
	<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
</head>

<body>
    <!-- 上のヘッダー -->
    <%@ include file="header.jsp" %>
    <!-- 下のヘッダー-->
    <header class="order-menu">
		<a id="order_btn">new</a>
		<a id="order_btn">popular</a>
			<select id="genre_selector" class="genre_selector">
			</select>
    </header>
    <!-- クイズリスト  -->
    <div class="quiz_list" id="quiz_list">
        <div class="quiz">
            <div class="width row">
                <div class="title col">
                    <p>常識クイズ</p>
                </div>
            </div>
        </div>
    </div>
    <div class="fixed-bottom text-right create-quiz-btn">
        <a id="create-quiz-open" class="create-quiz-open" data-slide-index="1"> 
        	<i class="fa-solid fa-pen"></i>
        </a>
    </div>
	<!-- トップに戻るボタン -->

	<div class="go-top-btn" id="go-top-btn">
		<a href="#top"></a>
		<i class="up-arrow fa-solid fa-arrow-up"></i>
	</div>
	<!--作問ポップアップ-->
	<div class="create-quiz-modal-back" id="create-quiz-modal-back">
		<div class="create-quiz-modal">
			<span class="modal-close-btn" id="create-close-btn"></span>
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
						<input type="radio" name="choice" id="choice-radio"><!-- 隙間をなくすためのコメント
						 --><input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢1">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio"><!-- 隙間をなくすためのコメント
						 --><input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢2">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio"><!-- 隙間をなくすためのコメント
						 -->
						<input type="text" id="choice-text" class="choice-text"
							placeholder="選択肢3">
					</div>
					<div class="choice">
						<input type="radio" name="choice" id="choice-radio"><!-- 隙間をなくすためのコメント
						 --><input type="text" id="choice-text" class="choice-text"
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
		<div class="metadata-modal">
			<span class="modal-close-btn" id="metadata-close-btn"></span>
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
	
	<div class="posting-snackbar" id="posting-snackbar">投稿中です...。</div>
	
	<div class="completion-snackbar" id="completion-snackbar">投稿完了しました！</div>



    
    <script src="js/index.js"></script>
    <script src="js/createQuiz.js"></script>
</body>

</html>
