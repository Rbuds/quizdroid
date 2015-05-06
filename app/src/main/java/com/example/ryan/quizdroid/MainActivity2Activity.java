package com.example.ryan.quizdroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.HashMap;
import java.util.Map;


public class MainActivity2Activity extends Activity {

    private static final Map<String, String> descriptions = new HashMap<String, String>();
    private String topic = "";
    public int correct = 0;
    private Question[] questionQueue = new Question[2];
    public String selected = "";
    public int attempted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Intent intent = getIntent();
        this.topic = intent.getStringExtra("topic");
        Bundle extras = new Bundle();
        extras.putString("topic", topic);
        extras.putInt("correct", correct);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DescriptionFrag fragment2 = new DescriptionFrag();
        fragment2.setArguments(extras);
        fragmentTransaction.replace(R.id.container, fragment2);
        fragmentTransaction.commit();
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

    public void loadFragment2() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (attempted == 2) {
            Intent intent = new Intent(MainActivity2Activity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Bundle extras = new Bundle();
            extras.putInt("attempted", attempted);
            extras.putString("topic", topic);
            extras.putInt("correct", correct);
            DescriptionFrag fragment2 = new DescriptionFrag();
            fragment2.setArguments(extras);
            ft.replace(R.id.container, fragment2);
        }
        ft.commit();
    }

    public void loadFragment3() {
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

    public void loadFragment4() {
        Bundle extras = new Bundle();
        extras.putString("chosen", selected);
        extras.putString("answer", questionQueue[attempted].answer);
        extras.putInt("attempted", attempted);
        extras.putString("topic", topic);
        extras.putInt("correct", correct);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ResultsFrag fragment4 = new ResultsFrag();
        fragment4.setArguments(extras);
        ft.replace(R.id.container, fragment4);   // where , what
        ft.commit();
    }

}
