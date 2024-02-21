package db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.bean.QuestionBean;
import db.bean.QuizBean;
import db.bean.QuizQuestionBean;
import frame.exception.ResourceException;

/**
 * QuizQuestionDao表にアクセスします
 */
public class QuizQuestionDao extends Dao{
	/**
	 * 指定されたQuizと関連付けられた全てのQuestionを同時に取得します
	 * @param quizid 取得するquizのquiz_id
	 * @return 取得するqiuz,questionのquiz_id
	 * @throws ResourceException データ取得時に例外が発生した場合
	 */
	public QuizQuestionBean selectQuizWithQuestion(int quizid) throws ResourceException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		QuizQuestionBean quizQuestionBean = new QuizQuestionBean();
		ArrayList<QuestionBean> questionList = new ArrayList<>(); 
		
		try {
			connect();
			//quiz表とgenre表をgenre_noで内部結合しデータを取得
			String quiz_sql = "SELECT * FROM quiz INNER JOIN genre USING(genre_no) WHERE quiz_id = ?";
			
			st = cn.prepareStatement(quiz_sql);
			st.setInt(1, quizid);
			rs = st.executeQuery();
			
			QuizBean  quizBean = new QuizBean();
			
			//QuizBeanにデータセット
			if(rs.next()) {			
        quizBean.setQuizId(rs.getInt("quiz_id"));
        quizBean.setAuthorNo(rs.getInt("author_no"));
        quizBean.setTitle(rs.getString("title"));
        quizBean.setQuestionCount(rs.getInt("question_count"));
        quizBean.setGenreNo(rs.getInt("genre_no"));
        quizBean.setGenre(rs.getString("genre_title"));
        quizBean.setExplanation(rs.getString("explanation"));
        quizBean.setCreateTime(rs.getString("create_time"));
        quizBean.setCorrectRate(rs.getFloat("correct_rate"));
        quizBean.setTotalParticipants(rs.getInt("total_participants"));
        quizBean.setDeleted(rs.getBoolean("deleted"));
			}
			
			quizQuestionBean.setQuiz(quizBean);
			
			
			//question表からデータを取得
			String question_sql = "SELECT * FROM question WHERE quiz_id = ?";
			
			st = cn.prepareStatement(question_sql);
			st.setInt(1, quizid);
			rs = st.executeQuery();
			
			while(rs.next()) {
				QuestionBean questionBean = new QuestionBean();
				//QuestionBeanにデータセット

				questionBean.setQuizId(rs.getInt("quiz_id"));
				questionBean.setQuestionId(rs.getInt("question_id"));
				questionBean.setQuestion(rs.getString("question"));
				questionBean.setChoice1(rs.getString("choice_1"));
				questionBean.setChoice2(rs.getString("choice_2"));
				questionBean.setChoice3(rs.getString("choice_3"));
				questionBean.setChoice4(rs.getString("choice_4"));
				byte judgeByte = rs.getByte("judge");
	            // ビット列をboolean[]に変換するメソッドを呼び出してセット
	            boolean[] judgeArray = byteToBooleanArray(judgeByte);
	            questionBean.setJudge(judgeArray);
				
				questionList.add(questionBean);
				
			}
			
			quizQuestionBean.setQuestion(questionList);
			
			
		} catch(SQLException e) {
			//例外処理、必要に応じてロールバック
            try{
                cn.rollback();
            } catch(SQLException e2) {
                throw new ResourceException(e2.getMessage(), e2);
            }
            throw new ResourceException(e.getMessage(), e);
        } finally {
            try {
            	//リソースを閉じる
                if(rs != null) {
                    rs.close();
                }
                if(st != null) {
                    st.close();
                }
            } catch(SQLException e2) {
                throw new ResourceException(e2.getMessage(), e2);
            } finally {
                close();
            }
        }
		return quizQuestionBean;
	}
	
	/**
	 * QuizとQustionを同時に挿入します
	 * @param quizQuestionBean 挿入するデータ
	 * @throws ResourceException 挿入時に例外が発生した場合
	 */
	public void insertQuizQuestion(int user_no,QuizQuestionBean quizQuestionBean) throws ResourceException {
	    PreparedStatement st = null;
	    
	    System.out.println(quizQuestionBean.toString());

	    try {
	        connect();
	        
	        String sqlQuiz = "INSERT INTO quiz(quiz_id, author_no, title, question_count, genre_no, explanation) VALUES(?, ?, ?, ?, ?, ?)";
			st = cn.prepareStatement(sqlQuiz,Statement.RETURN_GENERATED_KEYS);
			
            QuizBean quiz = quizQuestionBean.getQuiz();
          //QuestionBeanにデータセット
			st.setInt(1, quiz.getQuizId());
			st.setInt(2, user_no);
			st.setString(3, quiz.getTitle());
			st.setInt(4, quiz.getQuestionCount());
			st.setInt(5, quiz.getGenreNo());
			st.setString(6, quiz.getExplanation());
			
			st.executeUpdate();
			
            System.out.println("quizのcommit完了");
            
            try(ResultSet geneletedKeys = st.getGeneratedKeys()){
            	//ResultSetから生成されたキーが存在する場合
            	if(geneletedKeys.next()) {
            		//生成されたキー(quiz-id)を取得
            		int quizId = geneletedKeys.getInt(1);
            		System.out.println(quizId);
            		//新しいSQL文を作成
            		String sqlQuestion = "INSERT INTO question (quiz_id,question_id, question, choice_1, choice_2, choice_3, choice_4, judge) " +
            				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            		st = cn.prepareStatement(sqlQuestion);
            		
            		
            		ArrayList<QuestionBean> questions = quizQuestionBean.getQuestion();
            		//Questionの数だけ繰り返し
            		for(int i = 0 ; i< questions.size() ; i++) {
            			QuestionBean question = questions.get(i);
            			//各列にデータをセット
            			st.setInt(1, quizId);
            			st.setInt(2, i);
            			st.setString(3, question.getQuestion());
            			st.setString(4, question.getChoice1());
            			st.setString(5, question.getChoice2());
            			st.setString(6, question.getChoice3());
            			st.setString(7, question.getChoice4());
            			
            			
            			// boolean[]からbyteに変換するメソッドを呼び出してセット
            			byte judgeByte = booleanArrayToByte(question.getJudge());
            			st.setByte(8, judgeByte);
            			
            			System.out.println("Quiz ID: " + question.getQuizId());
            			System.out.println("Question ID: " + question.getQuestionId());
            			System.out.println("Question: " + question.getQuestion());
            			System.out.println("Choice 1: " + question.getChoice1());
            			System.out.println("Choice 2: " + question.getChoice2());
            			System.out.println("Choice 3: " + question.getChoice3());
            			System.out.println("Choice 4: " + question.getChoice4());
            			System.out.println("Judge: " + judgeByte);
            			
            			System.out.println("executeUpdate直前");
            			
            			//新しいQuestionを挿入
            			st.executeUpdate();
            			System.out.println("executeUpdate完了");
            		}
            		
            	}
            	
            }

	        cn.commit();
            System.out.println("questionのcommit完了");

	    } catch (SQLException e) {
	        try {
	            cn.rollback();
	        } catch (SQLException e2) {
	            throw new ResourceException(e2.getMessage(), e2);
	        }
	        throw new ResourceException(e.getMessage(), e);
	    } finally {
	        close();
	    }
	}
	
    /**
	 * バイトをboolean[]に変換するメソッド
     * @param b 変換前のデータ
     * @return ビット毎にbooleanにしたデータ
     */
    private boolean[] byteToBooleanArray(byte b) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) {
            result[i] = (b & (1 << i)) != 0;
        }
        return result;
    }
	

    /**
     * boolean[]をバイトに変換するメソッド
     * @param boolArray 変換前のデータ
     * @return booleanを各bitに変換したデーター
     */
    private byte booleanArrayToByte(boolean[] boolArray) {
        if (boolArray.length > 8) {
            throw new IllegalArgumentException("Boolean array size should not exceed 8 elements.");
        }

        byte result = 0;
        for (int i = 0; i < boolArray.length; i++) {
            if (boolArray[i]) {
                result |= (1 << i);
            }
        }
        return result;
    }

}
