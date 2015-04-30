package com.example.ryan.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;


public class MainActivity2Activity extends Activity implements View.OnClickListener {

    private static final Map<String, String> descriptions = new HashMap<String, String>();
    private String topic = "";
    public int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        String topic = getIntent().getStringExtra("topic");
        fill_descriptions();
        TextView quesTotal = (TextView) findViewById(R.id.textView3);
        quesTotal.setText("There are 2 questions in this topic");
        String desc = descriptions.get(topic);
        TextView view = (TextView) findViewById(R.id.textView);
        view.setText(desc);
        this.topic = topic;
        Button go = (Button) findViewById(R.id.button4);
        go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity2Activity.this, QuestionPage.class);
        Bundle extras = getIntent().getExtras();
        extras.putString("topic", topic);
        extras.putInt("correct", correct);
        intent.putExtras(extras);
        startActivity(intent);
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

    public void fill_descriptions() {
        descriptions.put("Math", "Mathematics, often shortened to maths or math, is the study of topics such as quantity, structure, space, and change. There is a range of views among mathematicians and philosophers as to the exact scope and definition of mathematics.");
        descriptions.put("Physics", "the branch of science concerned with the nature and properties of matter and energy. The subject matter of physics, distinguished from that of chemistry and biology, includes mechanics, heat, light and other radiation, sound, electricity, magnetism, and the structure of atoms.");
        descriptions.put("Marvel Super Heroes", "The Marvel Super Heroes[1] is an American / Canadian animated television series starring five comic-book superheroes from Marvel Comics. The first TV series based on Marvel characters, it debuted in syndication on U.S. television in 1966.");
    }
}
