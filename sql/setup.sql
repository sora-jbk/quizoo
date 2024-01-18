/* �R�}���h�v�����v�g����ڑ� */
/*mysql --local-infile=1 -u root -p*/

/* �f�[�^�x�[�X */
create database quizoo;

/* ���[�U�[ */
create user 'quizoo_admin'@'localhost' identified by 'admin';

create user 'quizoo_app'@'%' identified by 'app';

grant select, update, insert, create, delete, drop on quizoo.* to 'quizoo_admin'@'localhost';

grant select, update, insert on quizoo.* to 'quizoo_app'@'%';

use quizoo


/* �e�[�u�� */
CREATE TABLE userinfo (
    user_id VARCHAR(256) PRIMARY KEY,
	user_no MEDIUMINT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    password CHAR(64) NOT NULL,
	total_answer MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
	correct_answer MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
    rating FLOAT UNSIGNED NOT NULL DEFAULT 0 
);

CREATE TABLE nickname (
	user_no MEDIUMINT NOT NULL UNIQUE REFERENCES userinfo(user_no),
	nickname VARCHAR(50)
);

CREATE TABLE genre (
    genre_no TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    genre_title VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE quiz (
    quiz_id MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    author_no MEDIUMINT UNSIGNED NOT NULL REFERENCES userinfo(user_no),
    title VARCHAR(100) NOT NULL,
	question_count TINYINT NOT NULL,
    genre_no TINYINT UNSIGNED NOT NULL REFERENCES genre(genre_no),
    explanation VARCHAR(200) DEFAULT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT now(0),
	correct_rate FLOAT UNSIGNED NOT NULL DEFAULT 0,
	total_participants MEDIUMINT UNSIGNED NOT NULL DEFAULT 0
);

CREATE TABLE question (
	quiz_id MEDIUMINT UNSIGNED REFERENCES quiz(quiz_id),
	question_id TINYINT UNSIGNED,
	question VARCHAR(500) NOT NULL,
	choice_1 VARCHAR(50) NOT NULL,
	choice_2 VARCHAR(50) NOT NULL,
	choice_3 VARCHAR(50),
	choice_4 VARCHAR(50),
	judge BIT(4) NOT NULL,
	PRIMARY KEY(quiz_id, question_id)
);

CREATE TABLE answerhistory (
	user_no MEDIUMINT UNSIGNED NOT NULL REFERENCES userinfo(user_no),
	quiz_id MEDIUMINT UNSIGNED NOT NULL REFERENCES quiz(quiz_id),
	answered_time TIMESTAMP NOT NULL DEFAULT  now(0),
	question_count TINYINT UNSIGNED,
	correct_count TINYINT UNSIGNED NOT NULL
);

/* �g���K�[
userinfo��update����Ƃ��̃g���K�[�B�@��񕶂�update���B
�g�[�^���A���T�[�͍X�V��ɍX�V�O�{�P���Ă�̂ŁAupdate����̂�correct_answer�����ŗǂ��B
rating�ɂ́Acorrect_answer�̓�抄��total_anwser������@DELIMITER�͈�A�̏�����\���B�@*/
DELIMITER //
CREATE TRIGGER calculate_rating
BEFORE UPDATE ON userinfo
FOR EACH ROW
BEGIN 
	IF NEW.user_id = OLD.user_id THEN
		IF NEW.total_answer IS NOT NULL AND NEW.correct_answer IS NOT NULL THEN
			IF NEW.correct_answer > NEW.total_answer THEN
				SIGNAL SQLSTATE '45001'
				SET MESSAGE_TEXT = 'correct_answer��total_answer���傫��';
			END IF;
			SET
				NEW.total_answer = OLD.total_answer + NEW.total_answer,
				NEW.correct_answer = OLD.correct_answer + NEW.correct_answer,
				NEW.rating = POW(NEW.correct_answer,2) / NEW.total_answer;
		END IF;
	END IF;
END;
//
DELIMITER ;


/* answerhistory��insert�����Ƃ��ɁAquiz��update����g���K�[�B*/
DELIMITER //
CREATE TRIGGER question_count_AND_correct_rate
BEFORE INSERT ON answerhistory
FOR EACH ROW
BEGIN
    SET NEW.question_count = (SELECT question_count FROM quiz WHERE quiz_id = NEW.quiz_id);

	UPDATE quiz SET 
		correct_rate = ((correct_rate * total_participants) + (NEW.correct_count / NEW.question_count)) / (total_participants + 1),
		total_participants = total_participants+1
	WHERE quiz_id = NEW.quiz_id;
END;
//
DELIMITER ;


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/userinfo.csv' 
	INTO TABLE userinfo
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (user_id,password,total_answer,correct_answer,rating);


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/quiz.csv' 
	INTO TABLE quiz
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (author_no,title,question_count,genre_no,explanation,create_time,total_participants);


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/question.csv' 
	INTO TABLE question
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (quiz_id,question_id,question,choice_1,choice_2,choice_3,choice_4,judge);


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/nickname.csv' 
	INTO TABLE nickname
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (user_no,nickname);


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/genre.csv' 
	INTO TABLE genre
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (genre_no,genre_title);


LOAD DATA LOCAL INFILE 'C:/repos/QuiZoo/quizoo/testdata/answerhistory.csv' 
	INTO TABLE answerhistory
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
	IGNORE 1 ROWS
    (user_no,quiz_id,answered_time,question_count,correct_count);
