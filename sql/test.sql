/*set_questin_count���e�X�g���镶�@question_count�ɓ��鐔�͉��ł������B�����I��quiz�\��question_count�ɕς��*/
insert into answerhistory(user_no, quiz_id, answered_time, question_count, correct_count) values(3,2,now(),1,1);

/*calculate_rating*���e�X�g���镶*/
update userinfo set total_answer=2, correct_answer=2 where user_no=5;