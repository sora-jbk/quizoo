-- �O���L�[�����e�[�u�����ɍ폜
DROP TABLE IF EXISTS AnswerHistory;
DROP TABLE IF EXISTS Choise;
DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS Quiz;

-- �O���L�[�̎Q�Ɛ�ƂȂ�e�[�u�������ɍ폜
DROP TABLE IF EXISTS UserInfo;
DROP TABLE IF EXISTS Genre;

-- �f�[�^�x�[�X���̂��폜
DROP DATABASE IF EXISTS quizoo;

-- ���[�U�[���폜
DROP USER IF EXISTS 'quizoo_admin'@'localhost';
DROP USER IF EXISTS 'quizoo_app'@'%';

-- ���������t���b�V��
FLUSH PRIVILEGES;
