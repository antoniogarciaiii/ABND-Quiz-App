package com.example.android.quiz;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.abndquizapp.R;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    //each question adds 20 to the score--as in 20%, 'cause basic math.
    // finals, because they don't change
    final int q1_Answer = R.id.q1_radiobutton_d;
    /* question 2 will be handled by its own method, because it's a
       checkbox w/2 answers that needs to work off a conditional statement */
    final String q3_Answer = "development";
    final int q4_Answer = R.id.q4_b;
    final String q5_Answer = "calculateTip";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // gets student's name, checks answers, displays score
    public void checkAnswers(View view){

        checkQuestion1();
        checkQuestion2();
        checkQuestion3();
        checkQuestion4();
        checkQuestion5();

        String toastMessage = "you got " + score + "%";
        Toast grades = Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG);
        grades.show();
    }



    //checks question 1
    private /*boolean*/ void checkQuestion1() {
        //so, when i kept hitting the 'get a grade' button, it kept adding.
        //this is kind of a poor man's solution, but i'll find the proper solution later.
        score = 0;
        RadioGroup rg = (RadioGroup) findViewById(R.id.q1_group);

        if( rg.getCheckedRadioButtonId() == q1_Answer ) {
            score += 20;
//            return true;
        }

//        return false; // it's false, but we're not going to do anything with it. we're just kinda gonna move on.
    }

    // method to check question 2. the idea is to gather their states, one by one, then check them.
    private void checkQuestion2() {
        CheckBox q2CA = (CheckBox) findViewById(R.id.q2_a);
        CheckBox q2CB = (CheckBox) findViewById(R.id.q2_b);
        CheckBox q2CC = (CheckBox) findViewById(R.id.q2_c);
        CheckBox q2CD = (CheckBox) findViewById(R.id.q2_d);
        // if, and only if: A is checked, AND B is checked, AND C is not checked, AND C is not checked, return true.
        // if true is returned, add 20 to score.
        if (q2CA.isChecked() && q2CB.isChecked() && !q2CC.isChecked() && !q2CD.isChecked()) {
            score += 20;
        }
    }

    // method to check question 3.
    public void checkQuestion3() {
        //extract value from q3 answer field, convert to string
        EditText q3UserAnswerET = findViewById(R.id.q3_user_answer);
        String q3UserAnswer = q3UserAnswerET.getText().toString();
        //convert to lowercase, to make it more user friendly for this question.
        //question 5 needs to be correct with capitalization, but this one is fine.
        q3UserAnswer = q3UserAnswer.toLowerCase();
        if (q3UserAnswer.equals(q3_Answer)){
            score += 20;
        }
    }


    //check question 4
    private void checkQuestion4() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.q4_group);

        if( rg.getCheckedRadioButtonId() == q4_Answer ) {
            score += 20;
        }

    }

    // method to check question 5.
    private void checkQuestion5() {
        //same logic as question 3.
        EditText q5UserAnswerET = findViewById(R.id.q5_user_answer);
        String q5UserAnswer = q5UserAnswerET.getText().toString();
        if (q5UserAnswer.equals(q5_Answer)) {
            score += 20;
        }
    }

}
