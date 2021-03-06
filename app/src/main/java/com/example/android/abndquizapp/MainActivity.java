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
import android.widget.TextView;
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

        String toastMessage = score + "%";
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

    // reset score, uncheck boxes, revert radio button states, empty input boxes
    public void resetApp(View view) {
        score = 0;
        resetQ1();
        resetQ2();
        resetQ3();
        resetQ4();
        resetQ5();
    }

    private void resetQ1() {
        // reset q1
        // trying something from: https://stackoverflow.com/questions/10497921/uncheck-all-radiobutton-in-a-radiobuttongroup
        RadioGroup q1_radiogroup = findViewById(R.id.q1_group);
        q1_radiogroup.clearCheck();
    }

    // uncheck checkboxes for question 2
    private void resetQ2() {
        // checkbox a
        CheckBox q2_CheckBox_a = findViewById(R.id.q2_a);
        q2_CheckBox_a.setChecked(false);
        // checkbox b
        CheckBox q2_CheckBox_b = findViewById(R.id.q2_b);
        q2_CheckBox_b.setChecked(false);
        // checkbox c
        CheckBox q2_CheckBox_c = findViewById(R.id.q2_c);
        q2_CheckBox_c.setChecked(false);
        // checkbox d
        CheckBox q2_CheckBox_d = findViewById(R.id.q2_d);
        q2_CheckBox_d.setChecked(false);
    }

    private void resetQ3() {
        //trying something from https://android--code.blogspot.com/2015/08/android-edittext-clear.html
        // also trying from https://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
        EditText q3_EditText = findViewById(R.id.q3_user_answer);
        q3_EditText.setText("", TextView.BufferType.EDITABLE);
    }

    private void resetQ4() {
        // reset q4
        // trying something from: https://stackoverflow.com/questions/10497921/uncheck-all-radiobutton-in-a-radiobuttongroup
        RadioGroup q4_radiogroup = findViewById(R.id.q4_group);
        q4_radiogroup.clearCheck();
    }

    private void resetQ5() {
        // reset q4
        // trying something from: https://stackoverflow.com/questions/10497921/uncheck-all-radiobutton-in-a-radiobuttongroup
        EditText q5_EditText = findViewById(R.id.q5_user_answer);
        q5_EditText.setText("", TextView.BufferType.EDITABLE);
    }
}
