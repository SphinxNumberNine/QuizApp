package com.example.chitraramaswamy.quizapp;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class multipleChoice extends Fragment {

    String question;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String correctAnswer;
    String selectedAnswer;
    TextView questionTextView;
    RadioGroup answerChoices;
    RadioButton choiceA;
    RadioButton choiceB;
    RadioButton choiceC;
    RadioButton choiceD;

    public multipleChoice() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View fragmentView = inflater.inflate(R.layout.multiplechoice, null);

        Bundle bundle = this.getArguments();
        answerA = bundle.getString("ChoiceA");
        answerB = bundle.getString("ChoiceB");
        answerC = bundle.getString("ChoiceC");
        answerD = bundle.getString("ChoiceD");
        question = bundle.getString("Question");
        correctAnswer = bundle.getString("CorrectChoice");

        questionTextView = (TextView) fragmentView.findViewById(R.id.questionTextView);
        questionTextView.setText(question);
        answerChoices = (RadioGroup) fragmentView.findViewById(R.id.answerChoices);
        choiceA = (RadioButton) fragmentView.findViewById(R.id.answerChoiceA);
        choiceA.setText(answerA);
        choiceB = (RadioButton) fragmentView.findViewById(R.id.answerChoiceB);
        choiceB.setText(answerB);
        choiceC = (RadioButton) fragmentView.findViewById(R.id.answerChoiceC);
        choiceC.setText(answerC);
        choiceD = (RadioButton) fragmentView.findViewById(R.id.answerChoiceD);
        choiceD.setText(answerD);
        questionTextView.setText(this.question);
        answerChoices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton button = (RadioButton) fragmentView.findViewById(i);
                String selected = button.getText().toString();
                selectedAnswer = selected;
            }
        });

        if(bundle.getString("selectedAnswer") != null){
            String selected = bundle.getString("selectedAnswer");
            if(selected.toUpperCase().equals(answerA.toUpperCase())){
                choiceA.setChecked(true);
            }
            else if(selected.toUpperCase().equals(answerB.toUpperCase())){
                choiceB.setChecked(true);
            }
            else if(selected.toUpperCase().equals(answerC.toUpperCase())){
                choiceC.setChecked(true);
            }
            else if(selected.toUpperCase().equals(answerD.toUpperCase())){
                choiceD.setChecked(true);
            }
        }



        return fragmentView;
    }



}
