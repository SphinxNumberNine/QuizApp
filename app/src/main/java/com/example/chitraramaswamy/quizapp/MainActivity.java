package com.example.chitraramaswamy.quizapp;

import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Output;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    ArrayList<Bundle> questions;
    Button nextButton;
    Button prevButton;
    Button submitButton;
    TextView progressTextView;
    int currentQuestion = 0;
    ArrayList<String> userAnswers;
    ArrayList<String> correctAnswers;
    int score = 0;
    FragmentManager fragmentManager;
    Button saveButton;
    Button loadButton;
    String file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = "content.txt";
        nextButton = (Button) findViewById(R.id.nextQuestionButton);
        prevButton = (Button) findViewById(R.id.previousQuestionButton);
        prevButton.setClickable(false);
        submitButton = (Button) findViewById(R.id.submitButton);
        saveButton = (Button) findViewById(R.id.saveAndQuitButton);
        progressTextView = (TextView) findViewById(R.id.scoreTextView);
        loadButton = (Button) findViewById(R.id.loadButton);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        questions = new ArrayList<>();
        userAnswers = new ArrayList<>();
        correctAnswers = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            userAnswers.add(null);
        }


        Bundle q1Bundle = new Bundle();
        q1Bundle.putString("Question", "In what year was Counter Strike: Global Offensive's first 'Major'?");
        q1Bundle.putString("ChoiceA", "2006");
        q1Bundle.putString("ChoiceB", "2012");
        q1Bundle.putString("ChoiceC", "2014");
        q1Bundle.putString("ChoiceD", "2013");
        q1Bundle.putString("CorrectChoice", "2013");

        Bundle q2Bundle = new Bundle();
        q2Bundle.putString("Question", "Which tournament organizer organized the first CS:GO Major of 2017?");
        q2Bundle.putString("ChoiceA", "ESL");
        q2Bundle.putString("ChoiceB", "MLG");
        q2Bundle.putString("ChoiceC", "ELEAGUE");
        q2Bundle.putString("ChoiceD", "Dreamhack");
        q2Bundle.putString("CorrectChoice", "ELEAGUE");

        Bundle q3Bundle = new Bundle();
        q3Bundle.putString("Question", "The following famous quote was said by which CS:GO caster?: 'Let's not count him out of this, we're in the Big Apple and Snax is hungry'");
        q3Bundle.putString("ChoiceA", "Anders Blume");
        q3Bundle.putString("ChoiceB", "Auguste 'Semmler' Massonat");
        q3Bundle.putString("ChoiceC", "Matthew 'Sadokist' Trivett");
        q3Bundle.putString("ChoiceD", "James 'JZFB' Bardolph");
        q3Bundle.putString("CorrectChoice", "Matthew 'Sadokist' Trivett");

        Bundle q4Bundle = new Bundle();
        q4Bundle.putString("Question", "With the addition of 's1mple' to their starting lineup, Natus Vincere have won 1 tournament. Which?");
        q4Bundle.putString("ChoiceA", "CounterPit Season 2");
        q4Bundle.putString("ChoiceB", "Dreamhack Leipzig 2017");
        q4Bundle.putString("ChoiceC", "ESL One New York 2016");
        q4Bundle.putString("ChoiceD", "StarSeries i-League Starladder Season 3: Kiev");
        q4Bundle.putString("CorrectChoice", "ESL One New York 2016");

        Bundle q5Bundle = new Bundle();
        q5Bundle.putString("Question", "Which of the following players was named HLTV's #1 player of 2016?");
        q5Bundle.putString("ChoiceA", "Coldzera (SK Gaming)");
        q5Bundle.putString("ChoiceB", "NiKo (Mousesports)");
        q5Bundle.putString("ChoiceC", "FalleN (SK Gaming)");
        q5Bundle.putString("ChoiceD", "Olofmeister (Fnatic)");
        q5Bundle.putString("CorrectChoice", "Coldzera (SK Gaming)");

        Bundle q6Bundle = new Bundle();
        q6Bundle.putString("Question", "True or False: ESL One Cologne 2017 will be a Major");
        q6Bundle.putBoolean("CorrectChoice", false);

        Bundle q7Bundle = new Bundle();
        q7Bundle.putString("Question", "True or False: KennyS is the AWPer of G2 eSports");
        q7Bundle.putBoolean("CorrectChoice", true);

        Bundle q8Bundle = new Bundle();
        q8Bundle.putString("Question", "True or False: Astralis won ELEAGUE Major 2017");
        q8Bundle.putBoolean("CorrectChoice", true);

        Bundle q9Bundle = new Bundle();
        q9Bundle.putString("Question", "True or False: the first CS:GO event to be broadcast live on American telivision was the ELEAGUE Major");
        q9Bundle.putBoolean("CorrectChoice", false);

        Bundle q10Bundle = new Bundle();
        q10Bundle.putString("Question", "True or False: the first dominant team in CS:GO was Ninjas in Pyjamas, who started their CS:GO campaign with an 87 game winning streak on LAN");
        q10Bundle.putBoolean("CorrectChoice", true);

        questions.add(q1Bundle);
        questions.add(q2Bundle);
        questions.add(q3Bundle);
        questions.add(q4Bundle);
        questions.add(q5Bundle);
        questions.add(q6Bundle);
        questions.add(q7Bundle);
        questions.add(q8Bundle);
        questions.add(q9Bundle);
        questions.add(q10Bundle);

        for(int i = 0;  i < questions.size(); i++){
            if(i <= 4) {
                correctAnswers.add((String) questions.get(i).get("CorrectChoice"));
            }
            else{
                boolean a = (Boolean) questions.get(i).get("CorrectChoice");
                String x;
                if(a){
                    x = "TRUE";
                }
                else{
                    x = "FALSE";
                }
                correctAnswers.add(x);
            }
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        final multipleChoice fragment = new multipleChoice();
        fragment.setArguments(questions.get(0));
        fragmentTransaction.add(R.id.linearLayout, fragment);
        fragmentTransaction.commit();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion < 9) {
                    if(currentQuestion <= 4){
                        multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                        userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                    }
                    else{
                        trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                        userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                    }
                    if(currentQuestion != 9) {
                        currentQuestion++;
                    }
                }

                selectUserAnswer();

                if(currentQuestion <= 4){
                    multipleChoice question = new multipleChoice();
                    question.setArguments(questions.get(currentQuestion));
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLayout, question);
                }
                else{
                    trueFalse question = new trueFalse();
                    question.setArguments(questions.get(currentQuestion));
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLayout, question);
                }

                fragmentTransaction.commit();

                selectUserAnswer();

                if(currentQuestion == 9){
                    nextButton.setClickable(false);
                }
                else{
                    nextButton.setClickable(true);
                }

                if(currentQuestion == 0){
                    prevButton.setClickable(false);
                }
                else{
                    prevButton.setClickable(true);
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion > 0) {
                    if(currentQuestion <= 4){
                        multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                        userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                    }
                    else{
                        trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                        userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                    }

                    if(currentQuestion != 0) {
                        currentQuestion--;
                    }
                }

                selectUserAnswer();

                if(currentQuestion <= 4){
                    multipleChoice question = new multipleChoice();
                    question.setArguments(questions.get(currentQuestion));
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLayout, question);
                }
                else{
                    trueFalse question = new trueFalse();
                    question.setArguments(questions.get(currentQuestion));
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLayout, question);
                }

                fragmentTransaction.commit();

                //selectUserAnswer();

                if(currentQuestion == 9){
                    nextButton.setClickable(false);
                }
                else{
                    nextButton.setClickable(true);
                }

                if(currentQuestion == 0){
                    prevButton.setClickable(false);
                }
                else{
                    nextButton.setClickable(true);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentQuestion <= 4){
                    multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                    userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                }
                else{
                    trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                    userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                }

                for(int x = 0; x < userAnswers.size(); x++){
                    if(userAnswers.get(x) != null) {
                        if (userAnswers.get(x).toUpperCase().equals(correctAnswers.get(x).toUpperCase())) {
                            score++;
                        }
                    }
                }
                progressTextView.setText("Score: " + score);

                AlertDialog.Builder popUpWindow = new AlertDialog.Builder(MainActivity.this);
                popUpWindow.setTitle("Game over! Your score was " + score +"/10");
                CharSequence [] options = new CharSequence[] {"Play again", "Quit"};
                popUpWindow.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i) {
                            case 0:
                                fragmentTransaction = fragmentManager.beginTransaction();
                                final multipleChoice fragment = new multipleChoice();
                                fragment.setArguments(questions.get(0));
                                fragmentTransaction.replace(R.id.linearLayout, fragment);
                                fragmentTransaction.commit();
                                currentQuestion = 0;
                                progressTextView.setText("Previous Score: " + score);
                                score = 0;
                                nextButton.setClickable(true);
                                prevButton.setClickable(false);
                                for(int x = 0; x < userAnswers.size(); x++){
                                    userAnswers.set(x, null);
                                }
                                break;
                            case 1:
                                MainActivity.this.finishAffinity();
                                break;
                        }
                    }
                });
                popUpWindow.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion <= 4){
                    multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                    userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                }
                else{
                    trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                    userAnswers.set(currentQuestion, currentFragment.selectedAnswer);
                }
                try{
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE));
                    for(int x = 0; x < userAnswers.size(); x++){
                        if(userAnswers.get(x) == null){
                            writer.write("NULL" + "\n");
                        }
                        else {
                            writer.write(userAnswers.get(x) + "\n");
                        }
                    }
                    writer.close();
                }
                catch(Exception e){
                    Log.d("DEBUGGING", e.getLocalizedMessage());
                }

                //MainActivity.this.finishAffinity();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(file)));
                    for(int x = 0; x < userAnswers.size(); x++){
                        String line = reader.readLine();
                        if(!(line.toUpperCase().equals("NULL"))){
                            userAnswers.set(x, line);
                        }
                    }
                    reader.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                if(currentQuestion <= 4){
                    multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                    if(userAnswers.get(currentQuestion) != null){
                        if(userAnswers.get(currentQuestion).toUpperCase().equals(currentFragment.answerA.toUpperCase())){
                            currentFragment.choiceA.setChecked(true);
                        }
                        else if(userAnswers.get(currentQuestion).toUpperCase().equals(currentFragment.answerB.toUpperCase())){
                            currentFragment.choiceB.setChecked(true);
                        }
                        else if(userAnswers.get(currentQuestion).toUpperCase().equals(currentFragment.answerC.toUpperCase())){
                            currentFragment.choiceC.setChecked(true);
                        }
                        else if(userAnswers.get(currentQuestion).toUpperCase().equals(currentFragment.answerD.toUpperCase())){
                            currentFragment.choiceD.setChecked(true);
                        }
                    }
                }
                else{
                    trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                    if(userAnswers.get(currentQuestion) != null){
                        if(userAnswers.get(currentQuestion).toUpperCase().equals("TRUE")){
                            currentFragment.trueButton.setChecked(true);
                        }
                        else if(userAnswers.get(currentQuestion).toUpperCase().equals("FALSE")){
                            currentFragment.falseButton.setChecked(true);
                        }
                    }
                }

                try{
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE));
                    writer.write("");
                    writer.close();
                }
                catch(Exception e){

                }

            }
        });

    }

    public void selectUserAnswer(){
        /**if(userAnswers.get(currentQuestion) != null){
            String userSelected = userAnswers.get(currentQuestion);
            if(currentQuestion <= 4){
                multipleChoice currentFragment = (multipleChoice) fragmentManager.findFragmentById(R.id.linearLayout);
                if(userSelected.toUpperCase().equals(currentFragment.answerA.toUpperCase())){
                    currentFragment.choiceA.setSelected(true);
                }
                else if(userSelected.toUpperCase().equals(currentFragment.answerB.toUpperCase())){
                    currentFragment.choiceB.setSelected(true);
                }
                else if(userSelected.toUpperCase().equals(currentFragment.answerC.toUpperCase())){
                    currentFragment.choiceC.setSelected(true);
                }
                else if(userSelected.toUpperCase().equals(currentFragment.answerD.toUpperCase())){
                    currentFragment.choiceD.setSelected(true);
                }
            }
            else{
                trueFalse currentFragment = (trueFalse) fragmentManager.findFragmentById(R.id.linearLayout);
                if(userSelected.toUpperCase().equals("TRUE")){
                    currentFragment.trueButton.setSelected(true);
                }
                else if(userSelected.toUpperCase().equals("FALSE")){
                    currentFragment.falseButton.setSelected(true);
                }
            }
        }*/
        if(userAnswers.get(currentQuestion) != null){
            questions.get(currentQuestion).putString("selectedAnswer", userAnswers.get(currentQuestion));
        }
    }
}
