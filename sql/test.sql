/*set_questin_count���e�X�g���镶�@question_count�ɓ��鐔�͉��ł������B�����I��quiz�\��question_count�ɕς��*/
insert into answerhistory(user_no, quiz_id, answered_time, correct_count) values(5,1,now(),1);



/*calculate_rating*���e�X�g���镶*/
update userinfo set total_answer=2, correct_answer=2 where user_no=5;