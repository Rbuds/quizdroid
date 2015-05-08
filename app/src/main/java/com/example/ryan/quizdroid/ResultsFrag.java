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
import android.widget.TextView;

/**
 * Created by Ryan on 5/3/2015.
 */
public class ResultsFrag extends Fragment implements View.OnClickListener {

    private Activity host;
    public String topic;
    public int correct;
    public String chosen;
    public String answer;
    public int attempted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.topic = getArguments().getString("topic");
            this.correct = getArguments().getInt("correct");
            this.chosen = getArguments().getString("chosen");
            this.answer = getArguments().getString("answer");
            this.attempted = getArguments().getInt("attempted");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View fragView = inflater.inflate(R.layout.activity_results, container, false);
        Button next = (Button) fragView.findViewById(R.id.button6);
        next.setOnClickListener(this);
        TextView guessed = (TextView) fragView.findViewById(R.id.guess);
        guessed.setText("You chose: " + chosen);
        TextView answerText = (TextView) fragView.findViewById(R.id.answer);
        answerText.setText("The answer was: " + answer);
        if (chosen.equals(answer)) {
            this.correct++;
        }
        attempted++;
        TextView title = (TextView) fragView.findViewById(R.id.score);
        title.setText("You have " + correct + " out of " + attempted + " correct");
        if (attempted == 2) {
            Button button6 = (Button) fragView.findViewById(R.id.button6);
            button6.setText("Finish");
        }
        return fragView;
    }

    public void onClick(View v) {
        if (host instanceof MainActivity2Activity) {
            ((MainActivity2Activity) host).loadFragment3();
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

}