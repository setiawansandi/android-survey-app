package com.example.chuahockchuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//receiving data packages C-GPT
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class WaitingForQuestion extends AppCompatActivity {

    private Handler handler = new Handler();
    private int interval = 5000; // 5 seconds interval for polling
    private boolean continueFetching = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.waiting_for_question);

        startPolling();
    }

    private void startPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (continueFetching) {
                    new FetchDataTask().execute("http://10.0.2.2:9999/surveyapp/status");
                    handler.postDelayed(this, interval);
                }
            }
        }, interval);
    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.getString("status");
                    String qnNo = jsonObject.getString("no");
                    if (qnNo.equals("6")) {
                        continueFetching = false;
                        Intent intent = new Intent(WaitingForQuestion.this, EndscreenFeedback.class);
                        startActivity(intent);
                    } else {
                        if (status.equals("true")) {
                            Toast.makeText(WaitingForQuestion.this,"Commencing Question " + qnNo, Toast.LENGTH_LONG).show();
                            continueFetching = false;
                            Intent intent = new Intent(WaitingForQuestion.this, ChooseOption.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(WaitingForQuestion.this,"Please Wait...", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(WaitingForQuestion.this,"Error Fetching Question", Toast.LENGTH_SHORT).show();;
            }
        }
    }
}