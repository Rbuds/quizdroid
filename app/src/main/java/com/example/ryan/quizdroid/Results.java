package com.example.ryan.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class Results extends Activity implements View.OnClickListener {

    public int attempted;
    public int correct;
    String chosen;
    String answer;
    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Button next = (Button) findViewById(R.id.button6);
        next.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        chosen = extras.getString("chosen");
        answer = extras.getString("answer");
        attempted = extras.getInt("attempted");
        topic = extras.getString("topic");
        correct = extras.getInt("correct");
        TextView guessed = (TextView) findViewById(R.id.guess);
        guessed.setText("You chose: " + chosen);
        TextView answerText = (TextView) findViewById(R.id.answer);
        answerText.setText("The answer was: " + answer);
        if (chosen.equals(answer)) {
            correct++;
        }
        attempted++;
        TextView title = (TextView) findViewById(R.id.score);
        title.setText("You have " + correct + " out of " + attempted + " correct");
        if (attempted == 2) {
            Button button6 = (Button) findViewById(R.id.button6);
            button6.setText("Finish");
        }
    }

    public void onClick(View v) {
        if (attempted == 2/*last question in list*/) {
            Button button = (Button) v;
            button.setText("Finish");
            Intent intent = new Intent(Results.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Results.this, QuestionPage.class);
            Bundle extras = new Bundle();
            extras.putInt("attempted", attempted);
            extras.putString("topic", topic);
            extras.putInt("correct", correct);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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
}
