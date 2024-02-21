<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/unexpected.css">
<title>unexpected</title>
<link rel="icon" type="image/png" href="img/favicon.png">
</head>
<body>
    
<div class="container">
  <div class="boo-wrapper">
    <div class="boo">
      <div class="face"></div>
    </div>
    <div class="shadow"></div>
    <h1>予期せぬエラーが発生しました。</h1>

    <p>
      ご迷惑をおかけし、申し訳ございません。
      
    </p>
    <p id="timer"></p>
  </div>
</div>
</body>
<script>
window.addEventListener('load', function() {
  const timer = document.querySelector('#timer');

  var seconds = 10;
  var timerId = setInterval(function() {
    seconds--;
    timer.textContent = seconds;
    if (seconds <= 0) {
      clearInterval(timerId);
      window.location = '/quizoo/';
    }
  }, 1000);
});

</script>
</html>