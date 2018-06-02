package com.example.android.abndquizapp;

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

    //each question adds 20 to the score--as in 20%
    // using finals for the answer, because they are static
    final int q1_Answer = R.id.q1_radiobutton_d;
    /* question 2 will be handled by its own method, because it's a
       checkbox w/2 answers that needs to work off a conditional statement */
    final String q3_Answer = "development";
    final int q4_Answer = R.id.q4_b;
    final String q5_Answer = "x += 2;";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // sets score to zero, checks answers, displays score
    public void checkAnswers(View view) {
        score = 0;
        checkQuestion1();
        checkQuestion2();
        checkQuestion3();
        checkQuestion4();
        checkQuestion5();

        // find way to use string resource for score preamble. alternatively, display score only
        String toastMessage = "you got " + score + "%";
        Toast grades = Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG);
        grades.show();
    }


    //check question 1
    private void checkQuestion1() {
        //if the pressed radio button is the same as the answer, add 20 pts.
        RadioGroup rg = (RadioGroup) findViewById(R.id.q1_group);

        if (rg.getCheckedRadioButtonId() == q1_Answer) {
            score += 20;
        }
    }

    // check question 2. the idea is to gather their states, one by one, then check them.
    private void checkQuestion2() {
        CheckBox q2CA = (CheckBox) findViewById(R.id.q2_a);
        CheckBox q2CB = (CheckBox) findViewById(R.id.q2_b);
        CheckBox q2CC = (CheckBox) findViewById(R.id.q2_c);
        CheckBox q2CD = (CheckBox) findViewById(R.id.q2_d);
        // IF A is checked, & B is checked, & C is not checked, & D is not checked, add 20 pts to score.
        if (q2CA.isChecked() && q2CB.isChecked() && !q2CC.isChecked() && !q2CD.isChecked()) {
            score += 20;
        }
    }

    // check question 3.
    public void checkQuestion3() {
        //extract value from q3 answer field, convert to string
        EditText q3UserAnswerET = findViewById(R.id.q3_user_answer);
        String q3UserAnswer = q3UserAnswerET.getText().toString();
        //convert to lowercase, to make it more user friendly for this question.
        q3UserAnswer = q3UserAnswer.toLowerCase();
        if (q3UserAnswer.equals(q3_Answer)) {
            score += 20;
        }
    }


    //check question 4
    private void checkQuestion4() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.q4_group);
        // if correct radio button is pressed, add 20 pts to score
        if (rg.getCheckedRadioButtonId() == q4_Answer) {
            score += 20;
        }

    }

    // method to check question 5.
    private void checkQuestion5() {
        // extracts the user answer, converts into a string.
        // then, the checks to see if it's the same as the answer.
        // if same, add 20 pts to score
        EditText q5UserAnswerET = findViewById(R.id.q5_user_answer);
        String q5UserAnswer = q5UserAnswerET.getText().toString();
        if (q5UserAnswer.equals(q5_Answer)) {
            score += 20;
        }
    }

    // reset score, uncheck boxes, unpress radio buttons, empty input boxes
    private void resetAnswers() {
        score = 0;
        // reset q1


    }

}
