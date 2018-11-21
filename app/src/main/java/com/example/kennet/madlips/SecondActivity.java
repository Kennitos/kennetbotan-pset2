package com.example.kennet.madlips;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(5);
        List<String> alist = new ArrayList<String>();
        alist.add("madlib0_simple.txt");
        alist.add("madlib1_tarzan.txt");
        alist.add("madlib2_university.txt");
        alist.add("madlib3_clothes.txt");
        alist.add("madlib4_dance.txt");

        // String value = alist.get(1); //returns the 2nd item from list, in this case "madlib1_tarzan.txt"
        String value = alist.get(randomInt);
        TextView textView = findViewById(R.id.testText);
        textView.setText(value);
    }


    public void goToThird(View view) {
        TextView word = findViewById(R.id.testText);
        String file = word.getText().toString();

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("fileName",file);

        startActivity(intent);
        finish();
    }

    public void radioChecked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        TextView textView = findViewById(R.id.testText);
        switch(view.getId()){
            case R.id.radioSimple:
                if (checked) {
                    textView.setText("madlib0_simple.txt");
                }
                break;
            case R.id.radioTarzan:
                if (checked) {
                    textView.setText("madlib1_tarzan.txt");
                }
                break;
            case R.id.radioUniversity:
                if (checked) {
                    textView.setText("madlib2_university.txt");
                }
                break;
            case R.id.radioClothes:
                if (checked) {
                    textView.setText("madlib3_clothes.txt");
                }
                break;
            case R.id.radioDance:
                if (checked) {
                    textView.setText("madlib4_dance.txt");
                }
                break;
            case R.id.radioRandom:
                if (checked) {
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(5);
                    List<String> alist = new ArrayList<String>();
                    alist.add("madlib0_simple.txt");
                    alist.add("madlib1_tarzan.txt");
                    alist.add("madlib2_university.txt");
                    alist.add("madlib3_clothes.txt");
                    alist.add("madlib4_dance.txt");

                    // String value = alist.get(1); //returns the 2nd item from list, in this case "banana"
                    String value = alist.get(randomInt);
                    textView.setText(value);
                }
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView textView = findViewById(R.id.testText);
        String filename = textView.getText().toString();
        outState.putString("filename", filename);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String filename = savedInstanceState.getString("filename");
        TextView textView = findViewById(R.id.testText);
        textView.setText(filename);

    }

}
