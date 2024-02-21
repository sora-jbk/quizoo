<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div class="index-link">
		<a href="index"></a> <img src="img/quizoo_nobackground.png"
			alt="quizoo">
	</div>
	<input class="search-txtbox" id="search-textbox" type="text"
		placeholder="Search">
	<button class="side-menu-drawer" id="side-menu-drawer">
		<span></span> <span></span> <span></span>
	</button>
</header>

<!-- ハンバーガー -->
<div class="side-menu" id="side-menu">
	<ul id="side-menu-body">
		<li>
			<p> プロフィール編集 </p>
			<a href="profile"></a>
		</li>
		<li>
			<p>作成履歴</p>
			<a href="createhistory"></a>
		</li>
		<li>
			<p>スコア詳細</p>
			<a href="score"></a>
		</li>
		<li>
			<p>ログアウト</p>
			<a href="logout"></a>
		</li>
	</ul>
</div>
<script src="js/header.js"></script>
