package com.example.ryan.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 5/3/2015.
 */
public class DescriptionFrag extends Fragment implements View.OnClickListener {

    private Activity host;
    public String topic;
    public int correct;
    private static final Map<String, String> descriptions = new HashMap<String, String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = getArguments().getString("topic");
            correct = getArguments().getInt("correct");
        }
        fill_descriptions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.activity_main_activity2, container, false);
        TextView quesTotal = (TextView) fragView.findViewById(R.id.textView3);
        quesTotal.setText("There are 2 questions in this topic");
        String desc = descriptions.get(topic);
        TextView view = (TextView) fragView.findViewById(R.id.textView);
        view.setText(desc);
        Button go = (Button) fragView.findViewById(R.id.button4);
        go.setOnClickListener(this);
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

    public void fill_descriptions() {
        descriptions.put("Math", "Mathematics, often shortened to maths or math, is the study of topics such as quantity, structure, space, and change. There is a range of views among mathematicians and philosophers as to the exact scope and definition of mathematics.");
        descriptions.put("Physics", "the branch of science concerned with the nature and properties of matter and energy. The subject matter of physics, distinguished from that of chemistry and biology, includes mechanics, heat, light and other radiation, sound, electricity, magnetism, and the structure of atoms.");
        descriptions.put("Marvel Super Heroes", "The Marvel Super Heroes[1] is an American / Canadian animated television series starring five comic-book superheroes from Marvel Comics. The first TV series based on Marvel characters, it debuted in syndication on U.S. television in 1966.");
    }

}
