package com.example.kennet.madlips;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import java.io.*;
import java.util.*;

public class ThirdActivity extends AppCompatActivity {

    private Story testStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra("fileName");

        TextView textView = findViewById(R.id.textViewstory);
        textView.setText(receivedText);


        try {
            InputStream is = getAssets().open(receivedText);
            testStory = new Story(is);
            testStory.read(is);

            String nextPlaceholder = testStory.getNextPlaceholder();
            Log.d("testprint","nextplaceholder: "+nextPlaceholder);
            TextView nextword = findViewById(R.id.nextplaceholder);
            nextword.setText(String.format("please type a/an %s", nextPlaceholder));

            int count = testStory.getPlaceholderRemainingCount();
            Log.d("testprint","count: "+count);
            TextView remainingCount = findViewById(R.id.remainingcount);
            remainingCount.setText(String.format("%d word(s) left", count));

            EditText editInput = findViewById(R.id.editText);
            editInput.setHint(nextPlaceholder);

            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Log.d("printtxt","text: "+sb);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextWord(View view) {
        Log.d("testStory","story: "+testStory.toString() );
        Log.d("testcount","remaining: "+testStory.getPlaceholderRemainingCount());

        String nextPlaceholder = testStory.getNextPlaceholder();

        // EditText
        EditText word = findViewById(R.id.editText);
        String editText = word.getText().toString();
        if (!editText.equals("")) {
            Log.d("testcount","string: "+editText+".");
            testStory.fillInPlaceholder(editText);
        }
        else {
            testStory.fillInPlaceholder(nextPlaceholder);
        }

        // placeholder
        TextView nextword = findViewById(R.id.nextplaceholder);
        nextword.setText(String.format("please type a/an %s", nextPlaceholder));

        // remaining
        int count = testStory.getPlaceholderRemainingCount();
        TextView remainingCount = findViewById(R.id.remainingcount);
        remainingCount.setText(String.format("%d word(s) left", count));


        // clear the text and set a hint
        word.setHint(testStory.getNextPlaceholder());
        word.setText("");

        TextView dubbeltest = findViewById(R.id.textView3);
        dubbeltest.setText(testStory.toString());

        if (testStory.getPlaceholderRemainingCount()==0) {
            Log.d("testcount","count: OVER");
            TextView textView = findViewById(R.id.textViewstory);
            String file = textView.getText().toString();

            String completeStory = testStory.toString();
            Intent intent = new Intent(this, FourthActivity.class);

            intent.putExtra("fileName",file);
            intent.putExtra("finalStory", completeStory);

            startActivity(intent);
            finish();
        }

    }

    public void goToFourth(View view) {
        TextView textView = findViewById(R.id.textViewstory);
        String file = textView.getText().toString();

        String completeStory = testStory.toString();
        Intent intent = new Intent(this, FourthActivity.class);

        intent.putExtra("fileName",file);
        intent.putExtra("finalStory", completeStory);

        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Story savestory = testStory;
        String nextPlaceholder = testStory.getNextPlaceholder();
        int count = testStory.getPlaceholderRemainingCount();

        outState.putSerializable("save", savestory);
        outState.putString("nextplaceholder", nextPlaceholder);
        outState.putInt("count",count);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        testStory = (Story) savedInstanceState.getSerializable("save");
        String nextPlaceholder = savedInstanceState.getString("nextplaceholder");
        int count = savedInstanceState.getInt("count");

        TextView nextword = findViewById(R.id.nextplaceholder);
        nextword.setText(String.format("please type a/an %s", nextPlaceholder));

        EditText editInput = findViewById(R.id.editText);
        editInput.setHint(nextPlaceholder);

        TextView remainingCount = findViewById(R.id.remainingcount);
        remainingCount.setText(String.format("%d word(s) left", count));
    }
}

