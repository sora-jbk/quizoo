/* userinfo �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO userinfo (user_id, password, total_answer, correct_answer, rating) VALUES
('watoru5151@gmail.com', 'password1', 2, 1, 0.5),
('jibiki@gmail.com', 'password2', 3, 0, 0),
('eiya@gmail.com', 'password3', 15, 15, 15),
('niko@gmail.com', 'password4', 100, 80, 64),
('tester', 'test', 0, 0, 0);


/* nickname �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO nickname (user_no, nickname) VALUES
(1, 'wataru'),
(2, 'bikki-daddydirty'),
(3, 'A8'),
(4, 'hachiware');


/* genre �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO genre (genre_no, genre_title) VALUES
(1, '�X�|�[�c'),
(2, '���y'),
(3, '�f��'),
(4, '�Q�[��'),
(5, '�G�w'),
(6, '�F��'),
(7, '�G���^���E�|�\'),
(8, '�Ȃ��Ȃ�'),
(9, '������'),
(10, '���j'),
(11, '���w'),
(12, '����');


/* quiz �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO quiz (author_no, title, question_count, genre_no, explanation, create_time, total_participants) VALUES
(1, '�j�N�C�Y', 3, 5, '����Ɋւ���N�C�Y���', now(0), 1),
(2, '�т��N�C�Y', 1, 7, NULL, now(0), 1),
(1, '�����N�C�Y', 2, 9, '�����ȓ����̃N�C�Y', now(0), 2);


/* question �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO question (quiz_id, question_id, question, choise_1, choise_2, choise_3, choise_4, judge) VALUES
(1, 1, '�j�̍D���ȐH�ו��́H', '���[����', '���i', '�J�c�I', '�v����', b'1100'),
(1, 2, '�j�̌����ȐH�ו��́H', '�J�c�I', '�v����', '�J���t�����[', '�卪', b'0010'),
(1, 3, '�j�̐g���́H', '169', '170', '171', '172', b'0010'),
(2, 4, '�n���̋��Z�n�́H', '�_��', '�}�g', '���', '���', b'0001'),
(3, 5, '�u���v����n�܂�K���Ȋ�����������́H', '�N�V�i�_', '�N�I�b�J', '�N�W��', '�o�i�i', b'0100'),
(3, 6, '�I�����E�[�^���̈��͉͂��L���H', '100', '150', '200', '250', b'0010');


/* answerhistory �e�[�u���ւ̃f�[�^�}�� */
INSERT INTO answerhistory (user_no, quiz_id, answered_time, question_count, correct_count) VALUES
(1, 2, NOW(), 1, 1),
(3, 1, NOW(), 3, 2),
(1, 3, NOW(), 2, 2),
(4, 3, NOW(), 2, 1);

