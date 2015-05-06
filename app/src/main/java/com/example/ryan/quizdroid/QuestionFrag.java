package com.example.ryan.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Ryan on 5/3/2015.
 */
public class QuestionFrag extends Fragment implements View.OnClickListener {

    private Activity host;
    public String topic;
    public int correct;
    public int attempted;
    private Question[] questionQueue = new Question[2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = getArguments().getString("topic");
            correct = getArguments().getInt("correct");
            attempted = getArguments().getInt("attempted");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View fragView = inflater.inflate(R.layout.activity_question_page, container, false);
        fill_queue(topic);
        TextView title = (TextView) fragView.findViewById(R.id.textView2);
        title.setText(questionQueue[attempted].question);
        Button button = (Button) fragView.findViewById(R.id.button5);
        button.setVisibility(View.INVISIBLE);

        RadioButton radio1 = (RadioButton) fragView.findViewById(R.id.radioButton);
        radio1.setText(questionQueue[attempted].answers[0]);
        RadioButton radio2 = (RadioButton) fragView.findViewById(R.id.radioButton2);
        radio2.setText(questionQueue[attempted].answers[1]);
        RadioButton radio3 = (RadioButton) fragView.findViewById(R.id.radioButton3);
        radio3.setText(questionQueue[attempted].answers[2]);
        RadioButton radio4 = (RadioButton) fragView.findViewById(R.id.radioButton4);
        radio4.setText(questionQueue[attempted].answers[3]);

        final RadioGroup radioGroup = (RadioGroup) fragView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragView.findViewById(R.id.button5).setVisibility(View.VISIBLE);
            }
        });
        return fragView;
    }

    public void onClick(View v) {
        View fragView = v;
        if (v instanceof Button) {
            RadioGroup radioGroup = (RadioGroup) fragView.findViewById(R.id.radioGroup);
            if (radioGroup.getCheckedRadioButtonId() != -1) {
                Button button = (Button) fragView.findViewById(R.id.button5);
                button.setVisibility(View.VISIBLE);
                int id = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(id);
                int radioId = radioGroup.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                String selected = (String) btn.getText().toString();
                if (host instanceof MainActivity2Activity) {
                    ((MainActivity2Activity) host).loadFragment4();
                }

            }

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.host = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    public void fill_queue(String topic) {
        if (topic.equals("Math")) {
            questionQueue[0] = new Question("What is 2 x 2?", "8", "16", "2", "4");
            questionQueue[1] = new Question("What is a prime number?", "A blue bird", "A squared integer", "Something cool", "A number only divisible by itself and 1");
        } else if (topic.equals("Physics")) {
            questionQueue[0] = new Question("What direction does gravity pull?", "up", "left", "right", "down");
            questionQueue[1] = new Question("How is velocity different from speed?", "it's purple", "it's louder", "I can do it", "it's speed with a vector");
        } else {
            questionQueue[0] = new Question("What colors does Spider-man wear?", "blue and black", "red and yellow", "purple", "red and blue");
            questionQueue[1] = new Question("What group is Wolverine a part of?", "DC comics", "Fantastic Four", "beep boops", "X-Men");
        }
    }
}

