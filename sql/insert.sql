/* userinfo テーブルへのデータ挿入 */
INSERT INTO userinfo (user_id, password, total_answer, correct_answer, rating) VALUES
('watoru5151@gmail.com', 'password1', 2, 1, 0.5),
('jibiki@gmail.com', 'password2', 3, 0, 0),
('eiya@gmail.com', 'password3', 15, 15, 15),
('niko@gmail.com', 'password4', 100, 80, 64),
('tester', 'test', 0, 0, 0);



/* nickname テーブルへのデータ挿入 */
INSERT INTO nickname (user_no, nickname) VALUES
(1, 'wataru'),
(2, 'bikki-daddydirty'),
(3, 'A8'),
(4, 'hachiware');


/* genre テーブルへのデータ挿入 */
INSERT INTO  genre(genre_no, genre_title) VALUES
(1, 'スポーツ'),
(2, '音楽'),
(3, '映画'),
(4, 'ゲーム'),
(5, '雑学'),
(6, '宇宙'),
(7, 'エンタメ・芸能'),
(8, 'なぞなぞ'),
(9, '生き物'),
(10, '歴史'),
(11, '数学'),
(12, '漫画'),
(13, '生活'),
(14, '地理'),
(15, 'お酒'),
(16, '美術'),
(17, 'ことば'),
(18, '文化'),
(19, '料理'),
(20, 'アニメ');



/* quiz テーブルへのデータ挿入 */
INSERT INTO quiz (author_no, title, question_count, genre_no, explanation, create_time, total_participants) VALUES
(1, '亘クイズ', 3, 5, 'おれに関するクイズやで', now(0), 1),
(2, 'びきクイズ', 1, 7, NULL, now(0), 1),
(1, '動物クイズ', 2, 9, 'いろんな動物のクイズ', now(0), 2);


/* question テーブルへのデータ挿入 */
INSERT INTO question (quiz_id, question_id, question, choice_1, choice_2, choice_3, choice_4, judge) VALUES
(1, 1, '亘の好きな食べ物は？', 'ラーメン', '寿司', 'カツオ', 'プリン', b'1100'),
(1, 2, '亘の嫌いな食べ物は？', 'カツオ', 'プリン', 'カリフラワー', '大根', b'0010'),
(1, 3, '亘の身長は？', '169', '170', '171', '172', b'0010'),
(2, 1, '地引の居住地は？', '神栖', '筑波', '大洗', '取手', b'0001'),
(3, 1, '「く」から始まる幸せな顔をした動物は？', 'クシナダ', 'クオッカ', 'クジラ', 'バナナ', b'0100'),
(3, 2, 'オランウータンの握力は何キロ？', '100', '150', '200', '250', b'0010');


INSERT INTO answerhistory (user_no, quiz_id, answered_time, correct_count) VALUES
(1, 2, NOW(), 1),
(3, 1, NOW(), 2),
(1, 3, NOW(), 2),
(4, 3, NOW(), 1);

