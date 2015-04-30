package com.example.ryan.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionPage extends Activity {

    private Question[] questionQueue = new Question[2];
    public String topic;
    public String selected;
    public int attempted = 0;
    public int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        Bundle extras = getIntent().getExtras();
        attempted = extras.getInt("attempted");
        topic = extras.getString("topic");
        correct = extras.getInt("correct");
        fill_queue(topic);
        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText(questionQueue[attempted].question);
        Button button = (Button) findViewById(R.id.button5);
        button.setVisibility(View.INVISIBLE);

        RadioButton radio1 = (RadioButton) findViewById(R.id.radioButton);
        radio1.setText(questionQueue[attempted].answers[0]);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radioButton2);
        radio2.setText(questionQueue[attempted].answers[1]);
        RadioButton radio3 = (RadioButton) findViewById(R.id.radioButton3);
        radio3.setText(questionQueue[attempted].answers[2]);
        RadioButton radio4 = (RadioButton) findViewById(R.id.radioButton4);
        radio4.setText(questionQueue[attempted].answers[3]);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                findViewById(R.id.button5).setVisibility(View.VISIBLE);
            }
        });
    }

    /*@Override*/
    public void onClick(View v) {
        if (v instanceof Button) {
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            if (radioGroup.getCheckedRadioButtonId() != -1) {
                Button button = (Button) findViewById(R.id.button5);
                button.setVisibility(View.VISIBLE);
                int id = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(id);
                int radioId = radioGroup.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                selected = (String) btn.getText().toString();
                Intent intent = new Intent(QuestionPage.this, Results.class);
                Bundle extras = new Bundle();
                extras.putString("chosen", selected);
                extras.putString("answer", questionQueue[attempted].answer);
                extras.putInt("attempted", attempted);
                extras.putString("topic", topic);
                extras.putInt("correct", correct);
                intent.putExtras(extras);
                startActivity(intent);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_page, menu);
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
