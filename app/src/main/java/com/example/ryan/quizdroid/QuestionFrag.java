package com.example.ryan.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
public class QuestionFrag extends Fragment {

    private Activity host;
    public String topic;
    public int correct;
    public int attempted;
    public String selected = "";

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
        MainActivity2Activity ma2 = (MainActivity2Activity) this.host;
        final View fragView = inflater.inflate(R.layout.activity_question_page, container, false);
        TextView title = (TextView) fragView.findViewById(R.id.textView2);
        title.setText(ma2.questionQueue[attempted].question);
        Button submitBtn = (Button) fragView.findViewById(R.id.button5);
        submitBtn.setOnClickListener(radioButtonMethod());
        submitBtn.setVisibility(View.INVISIBLE);
        RadioButton radio1 = (RadioButton) fragView.findViewById(R.id.radioButton);
        radio1.setText(ma2.questionQueue[attempted].answers[0]);
        RadioButton radio2 = (RadioButton) fragView.findViewById(R.id.radioButton2);
        radio2.setText(ma2.questionQueue[attempted].answers[1]);
        RadioButton radio3 = (RadioButton) fragView.findViewById(R.id.radioButton3);
        radio3.setText(ma2.questionQueue[attempted].answers[2]);
        RadioButton radio4 = (RadioButton) fragView.findViewById(R.id.radioButton4);
        radio4.setText(ma2.questionQueue[attempted].answers[3]);

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

    private View.OnClickListener radioButtonMethod() {
        View.OnClickListener voc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View fragView = v;
                if (v instanceof Button) {
                    RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
                    if (radioGroup.getCheckedRadioButtonId() != -1) {
                        Button button = (Button) fragView.findViewById(R.id.button5);
                        button.setOnClickListener(this);
                        button.setVisibility(View.VISIBLE);
                        int id = radioGroup.getCheckedRadioButtonId();
                        View radioButton = radioGroup.findViewById(id);
                        int radioId = radioGroup.indexOfChild(radioButton);
                        RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                        if (host instanceof MainActivity2Activity) {
                            ((MainActivity2Activity) host).loadFragment4();
                        }
                        MainActivity2Activity ma2 = (MainActivity2Activity) host;
                        ma2.selected = (String) btn.getText();
                    }
                }

            }
        };

        return voc;
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

}

