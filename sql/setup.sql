create database quizoo;

create user 'quizoo_admin'@'localhost' identified by 'admin';

create user 'quizoo_app'@'%' identified by 'app';

grant select, update, insert, create, drop on quizoo.* to 'quizoo_admin'@'localhost';

grant select, update, insert on quizoo.* to 'quizoo_app'@'%';

use quizoo

CREATE TABLE userinfo (
    user_id VARCHAR(256) PRIMARY KEY,
	user_no MEDIUMINT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    password CHAR(64) NOT NULL,
	total_answer MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
	correct_answer MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
    rating FLOAT UNSIGNED NOT NULL DEFAULT 0,
);

CREATE TABLE nickname (
	user_no MEDIUMINT NOT NULL UNIQUE REFERENCES userinfo(user_no),
	nickname VARCHAR(50) NOT NULL,
)

CREATE TABLE genre (
    genre_no TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    genre_title VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE quiz (
    quiz_id MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    author_no MEDIUMINT UNSIGNED NOT NULL REFERENCES userinfo(user_no),
    title VARCHAR(100) NOT NULL,
	question_count TINYINT NOT NULL,
    genre TINYINT UNSIGNED NOT NULL REFERENCES genre(genre_no),
    explanation VARCHAR(200) DEFAULT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT now(0),
	correct_rate FLOAT UNSIGNED NOT NULL DEFAULT 0,
	total_participants MEDIUMINT UNSIGNED NOT NULL DEFAULT 0
);


CREATE TABLE question (
	quiz_id MEDIUMINT UNSIGNED REFERENCES quiz(quiz_id),
	question_id TINYINT UNSIGNED,
	question VARCHAR(500) NOT NULL,
	choise_1 VARCHAR(50) NOT NULL,
	choise_2 VARCHAR(50) NOT NULL,
	choise_3 VARCHAR(50),
	choise_4 VARCHAR(50),
	judge BIT(4) NOT NULL,
	PRIMARY KEY(quiz_id, question_id)
);

CREATE TABLE answeraistory (
	user_no MEDIUMINT UNSIGNED NOT NULL REFERENCES userinfo(user_id),
	quiz_id MEDIUMINT UNSIGNED NOT NULL REFERENCES quiz(quiz_id),
	answer_time TIMESTAMP NOT NULL DEFAULT  now(0),
	question_count TINYINT UNSIGNED NOT NULL,
	correct_count TINYINT UNSIGNED NOT NULL
);