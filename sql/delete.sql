-- �O���L�[�����e�[�u�����ɍ폜
DROP TABLE IF EXISTS answerhistory;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS nickname;

--�@�O���L�[�������Q�Ɛ�ƂȂ�e�[�u�����폜
DROP TABLE IF EXISTS quiz;

-- �O���L�[�̎Q�Ɛ�ƂȂ�e�[�u�������ɍ폜
DROP TABLE IF EXISTS userinfo;
DROP TABLE IF EXISTS genre;

-- �f�[�^�x�[�X���̂��폜
DROP DATABASE IF EXISTS quizoo;

-- ���[�U�[���폜
DROP USER IF EXISTS 'quizoo_admin'@'localhost';
DROP USER IF EXISTS 'quizoo_app'@'%';

-- ���������t���b�V��
FLUSH PRIVILEGES;
