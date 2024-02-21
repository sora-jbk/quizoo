SET NAMES utf8;
/*トリガーの削除*/
DROP TRIGGER calculate_rating;
DROP TRIGGER question_count_AND_correct_rate;

/* 外部キーを持つテーブルを先に削除 */
DROP TABLE IF EXISTS answerhistory;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS nickname;

/* 外部キーを持ちつつ参照先となるテーブルを削除 */
DROP TABLE IF EXISTS quiz;


/* 外部キーの参照先となるテーブルを次に削除 */
DROP TABLE IF EXISTS userinfo;
DROP TABLE IF EXISTS genre;

/* データベース自体を削除 */
DROP DATABASE IF EXISTS quizoo;

/* ユーザーを削除 */
DROP USER IF EXISTS 'quizoo_admin'@'localhost';
DROP USER IF EXISTS 'quizoo_app'@'%';

/* 権限をリフレッシュ */
FLUSH PRIVILEGES;
