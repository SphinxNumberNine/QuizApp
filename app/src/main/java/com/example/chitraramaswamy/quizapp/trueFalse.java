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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class trueFalse extends Fragment {

    String question;
    String selectedAnswer;
    Boolean correctAnswer;
    RadioGroup radioGroup;
    RadioButton trueButton;
    RadioButton falseButton;
    TextView questionTextView;

    public trueFalse() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_true_false, null);
        Bundle bundle = this.getArguments();
        question = bundle.getString("Question");
        correctAnswer = bundle.getBoolean("CorrectChoice");
        questionTextView = (TextView) fragmentView.findViewById(R.id.questionTextView);
        radioGroup = (RadioGroup) fragmentView.findViewById(R.id.radioGroup);
        trueButton = (RadioButton) fragmentView.findViewById(R.id.trueButton);
        falseButton = (RadioButton) fragmentView.findViewById(R.id.falseButton);
        questionTextView.setText(question);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton button = (RadioButton) fragmentView.findViewById(i);
                String selected = button.getText().toString();
                selectedAnswer = selected;
            }
        });
        if(bundle.getString("selectedAnswer") != null){
            String selected = bundle.getString("selectedAnswer");
            if(selected.toUpperCase().equals("TRUE")){
                trueButton.setChecked(true);
            }
            else if(selected.toUpperCase().equals("FALSE")){
                falseButton.setChecked(true);
            }
        }
        return fragmentView;
    }

}
