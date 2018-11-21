package com.example.kennet.madlips;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra("fileName");
        String completeStory = intent.getStringExtra("finalStory");

        TextView filledInstory = findViewById(R.id.textView5);
        filledInstory.setText(Html.fromHtml(completeStory));

        try {
            // read the variable file receivedText
            InputStream is = getAssets().open(receivedText);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            // create a new empty string (StringBuilder)
            StringBuilder sb = new StringBuilder();
            String line;
            // loop through all the lines and add them to the empty StringBuilder sb
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Log.d("printtxt","text: "+sb);
            // put the contents of the text into a TextView
            TextView textView = findViewById(R.id.textView4);
            textView.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
