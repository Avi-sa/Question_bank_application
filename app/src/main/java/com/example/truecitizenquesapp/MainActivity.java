package com.example.truecitizenquesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueClick;
    private Button falseClick;
    private ImageButton nextClick;
    private ImageButton previousclick;
    private TextView questionText;
    private int correntQuestionIndext;

    {
        correntQuestionIndext = 0;
    }

    private Question[] questionsbank;

    {
        questionsbank = new Question[]{
                new Question(R.string.question_declaration,true),
                new Question(R.string.question_amendments,false),
                new Question(R.string.question_constitution,true),
                new Question(R.string.question_government,false),
                new Question(R.string.question_government_senators,true),
                new Question(R.string.question_government_feds,false),
                new Question(R.string.question_independent_right,true),
                new Question(R.string.question_01,true),
                new Question(R.string.question_02,true),
                new Question(R.string.question_03,false),


        };
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueClick=findViewById(R.id.true_button1);
        falseClick=findViewById(R.id.false_button2);
        nextClick=findViewById(R.id.next_button);
        previousclick=findViewById(R.id.previous_button);
        questionText=findViewById(R.id.answer_text_view);
        

        trueClick.setOnClickListener(this);
        falseClick.setOnClickListener(this);
        nextClick.setOnClickListener(this);
        previousclick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.true_button1:
             checkAnswer(true);
                break;
            case R.id.false_button2:
                checkAnswer(false);
                break;
            case R.id.next_button:
                correntQuestionIndext=(correntQuestionIndext+1)%questionsbank.length;
                updateQuestion();
                break;

            case R.id.previous_button:
                if(correntQuestionIndext>0) {
                    correntQuestionIndext = ( correntQuestionIndext - 1 ) % questionsbank.length;
                    updateQuestion();
                }


        }
    }

    private void updateQuestion(){
        Log.d("clicked next","onClick: " + correntQuestionIndext);
        questionText.setText(questionsbank[correntQuestionIndext].getAnswerResId());

    }
    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue=questionsbank[correntQuestionIndext].isAnswerTrue();
        int toastmassageId=0;
        if(userChooseCorrect==answerIsTrue){
            toastmassageId=R.string.correct_ans;
        }
        else
            toastmassageId=R.string.incorrect_ans;
        Toast.makeText(MainActivity.this,toastmassageId,Toast.LENGTH_SHORT).show();


    }
}
