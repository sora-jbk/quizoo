create database quizoo;

create user 'quizoo_admin'@'localhost' identified by 'admin';

create user 'quizoo_app'@'%' identified by 'app';

grant select, update, insert, create, drop on quizoo.* to 'quizoo_admin'@'localhost';

grant select, update, insert on quizoo.* to 'quizoo_app'@'%';

use quizoo

CREATE TABLE UserInfo (
    id VARCHAR(300) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    score MEDIUMINT UNSIGNED
);

CREATE TABLE Genre (
    genre_no TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    genre_title VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE Quiz (
    quiz_id MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(300),
    title VARCHAR(100) NOT NULL,
    genre TINYINT UNSIGNED REFERENCES Genre(genre_no),
    explanation VARCHAR(200) DEFAULT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES UserInfo(id)
);


CREATE TABLE Question (
	quiz_id MEDIUMINT UNSIGNED,
	question VARCHAR(500) NOT NULL,
	judge BIT(10) NOT NULL,
	FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id)
);

CREATE TABLE Choise (
	quiz_id MEDIUMINT UNSIGNED,
	choises VARCHAR(100),
	choise_no TINYINT UNSIGNED,
	FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id),
	PRIMARY key (quiz_id,choise_no)
);

CREATE TABLE AnswerHistory (
	user_id VARCHAR(300),
	quiz_id MEDIUMINT UNSIGNED,
	clear_date TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES UserInfo(id),
	FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id)
);