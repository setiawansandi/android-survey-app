package com.example.chuahockchuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.AsyncTask;

public class ChooseOption extends AppCompatActivity {
    private Button btnA, btnB, btnC, btnD;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.choose_option);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button A is pressed
                choice = "A";
                new SendChoiceTask().execute(choice);
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForCurrentQuestionToClose.class);
                startActivity(intent);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button B is pressed
                choice = "B";
                new SendChoiceTask().execute(choice);
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForCurrentQuestionToClose.class);
                startActivity(intent);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button C is pressed
                choice = "C";
                new SendChoiceTask().execute(choice);
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForCurrentQuestionToClose.class);
                startActivity(intent);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button D is pressed
                choice = "D";
                new SendChoiceTask().execute(choice);
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForCurrentQuestionToClose.class);
                startActivity(intent);
            }
        });
    }

    private class SendChoiceTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String choice = params[0];
            try {
                // Encode choice string
                String encodedChoice = URLEncoder.encode(choice, "UTF-8");
                // Construct the URL
                URL url = new URL("http://10.0.2.2:9999/surveyapp/select?choice=" + encodedChoice);
                // Open connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // If the request was successful, return the response
                    return "Request successful";
                } else {
                    // If the request was not successful, return the error message
                    return "Error: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }
    }
}
