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

public class WaitingForCurrentQuestionToClose extends AppCompatActivity {

    private Handler handler = new Handler();
    private int interval = 5000; // 5 seconds interval for polling
    private boolean continueFetching = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.waiting_for_currentquestiontoclose);

        startPolling2();
    }

    private void startPolling2() {
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
                    //String qnNo = jsonObject.getString("no");
                    if (status.equals("false")) {
                        continueFetching = false;
                        Intent intent = new Intent(WaitingForCurrentQuestionToClose.this, WaitingForQuestion.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(WaitingForCurrentQuestionToClose.this,"Please Wait...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(WaitingForCurrentQuestionToClose.this,"Error Querying Status", Toast.LENGTH_SHORT).show();;
            }
        }
    }
}