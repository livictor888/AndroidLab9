package com.example.androidlab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editNumberOne = findViewById(R.id.editNumberOne);
        EditText editNumberTwo = findViewById(R.id.editNumberTwo);

        View generateJsonButton = findViewById(R.id.generateJSONButton);
        generateJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //{ “operation” : { “add” : { “a” : 5, “b” : 7 } } }
                JSONObject numbers = new JSONObject();
                JSONObject addition = new JSONObject();
                JSONObject operation = new JSONObject();
                int firstNumber = Integer.parseInt(editNumberOne.getText().toString());
                int secondNumber = Integer.parseInt(editNumberTwo.getText().toString());
                try {
                    numbers.put("a", firstNumber);
                    numbers.put("b", secondNumber);
                    addition.put("add", numbers);
                    operation.put("operation", addition);
                    String outputString = operation.toString(4);
                    EditText displayJSON = findViewById(R.id.jsonOutput);
                    displayJSON.setText(outputString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        View calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText displayJSON = findViewById(R.id.jsonOutput);
                String jsonOutputString = displayJSON.getText().toString();

                try {
                    JSONObject jsonObject = new JSONObject(jsonOutputString);
                    int firstNumber = jsonObject
                            .getJSONObject("operation")
                            .getJSONObject("add")
                            .getInt("a");
                    int secondNumber = jsonObject
                            .getJSONObject("operation")
                            .getJSONObject("add")
                            .getInt("b");
                    int sum = firstNumber + secondNumber;
                    StringBuilder toastMessage = new StringBuilder("The sum of a and b is " + sum + "!");
                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}