package com.example.chuahockchuan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toast;

//sending data packages C-GPT
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EndscreenFeedback extends AppCompatActivity {

    private EditText editTextFeedback;
    private Button btnSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.endscreen_feedback);

        btnSendFeedback = findViewById(R.id.btnSendFeedback);

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String feedback = String.valueOf(editTextFeedback.getText());
                //new SendFeedbackTask().execute(feedback);
                Toast.makeText(EndscreenFeedback.this,"Feedback Sent!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EndscreenFeedback.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
