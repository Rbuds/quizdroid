package com.example.ryan.quizdroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.HashMap;
import java.util.Map;


public class MainActivity2Activity extends Activity {

    private static final Map<String, String> descriptions = new HashMap<String, String>();
    private String topic = "";
    public int correct = 0;
    public Question[] questionQueue;
    public String selected;
    public int attempted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Intent intent = getIntent();
        this.topic = intent.getStringExtra("topic");
        this.attempted = 0;
        fill_queue(topic);
        fill_descriptions();
        if (savedInstanceState == null) {
            Bundle extras = new Bundle();
            extras.putString("topic", topic);
            extras.putInt("correct", correct);
            FragmentManager fragmentManager = getFragmentManager();
            DescriptionFrag fragment2 = new DescriptionFrag();
            fragment2.setArguments(extras);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment2);
            fragmentTransaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadFragment3() {
        if (attempted == 2) {
            Intent intent = new Intent(MainActivity2Activity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Bundle extras = new Bundle();
            extras.putString("topic", topic);
            extras.putInt("correct", correct);
            extras.putInt("attempted", attempted);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            QuestionFrag fragment3 = new QuestionFrag();
            fragment3.setArguments(extras);
            ft.replace(R.id.container, fragment3);   // where , what
            ft.commit();
        }
    }

    public void loadFragment4() {
        Bundle extras = new Bundle();
        if (selected.equals(questionQueue[attempted].answer)) {
            extras.putInt("correct", correct++);
        } else {
            extras.putInt("correct", correct);
        }
        extras.putString("chosen", selected);
        extras.putString("answer", questionQueue[attempted].answer);
        extras.putInt("attempted", attempted++);
        extras.putString("topic", topic);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ResultsFrag fragment4 = new ResultsFrag();
        fragment4.setArguments(extras);
        ft.replace(R.id.container, fragment4);   // where , what
        ft.commit();
    }

    public void fill_descriptions() {
        descriptions.put("Math", "Mathematics, often shortened to maths or math, is the study of topics such as quantity, structure, space, and change. There is a range of views among mathematicians and philosophers as to the exact scope and definition of mathematics.");
        descriptions.put("Physics", "the branch of science concerned with the nature and properties of matter and energy. The subject matter of physics, distinguished from that of chemistry and biology, includes mechanics, heat, light and other radiation, sound, electricity, magnetism, and the structure of atoms.");
        descriptions.put("Marvel Super Heroes", "The Marvel Super Heroes[1] is an American / Canadian animated television series starring five comic-book superheroes from Marvel Comics. The first TV series based on Marvel characters, it debuted in syndication on U.S. television in 1966.");
    }

    public void fill_queue(String topic) {
        questionQueue = new Question[2];
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
