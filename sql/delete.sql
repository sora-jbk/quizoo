-- 外部キーを持つテーブルを先に削除
DROP TABLE IF EXISTS AnswerHistory;
DROP TABLE IF EXISTS Choise;
DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS Quiz;

-- 外部キーの参照先となるテーブルを次に削除
DROP TABLE IF EXISTS UserInfo;
DROP TABLE IF EXISTS Genre;

-- データベース自体を削除
DROP DATABASE IF EXISTS quizoo;

-- ユーザーを削除
DROP USER IF EXISTS 'quizoo_admin'@'localhost';
DROP USER IF EXISTS 'quizoo_app'@'%';

-- 権限をリフレッシュ
FLUSH PRIVILEGES;
