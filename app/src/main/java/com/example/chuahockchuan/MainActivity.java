package com.example.chuahockchuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

//sending data packages C-GPT
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    private EditText editTextusername, editTextpassword;
    private Button btnLogin;
    private int testVariable1, testVariable2, testVariable3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextusername = findViewById(R.id.editTextUsername);
        editTextpassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnFeedback);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText
                String strUsername = editTextusername.getText().toString();
                String strPassword = editTextpassword.getText().toString();

                // sending data packages C-GPT
                new Thread(() -> {
                    try {
                        // URL of your Servlet
                        URL url = new URL("http://10.0.2.2:9999");

                        // Open connection
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);

                        // Send data
                        String postData = URLEncoder.encode("testVariable1", "UTF-8") + "=" + URLEncoder.encode(""+testVariable1, "UTF-8") +
                                "&" + URLEncoder.encode("testVariable2", "UTF-8") + "=" + URLEncoder.encode(""+testVariable2, "UTF-8") +
                                "&" + URLEncoder.encode("testVariable3", "UTF-8") + "=" + URLEncoder.encode(""+testVariable3, "UTF-8");
                        OutputStream os = conn.getOutputStream();
                        os.write(postData.getBytes());
                        os.flush();
                        os.close();

                        // Check response
                        int responseCode = conn.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            // Request successful
                            System.out.println("http OK");
                        } else {
                            // Request failed
                            System.out.println("http FAIL");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                //sending data packages C-GPT

                // Start SecondActivity and pass the user input as an extra
                Intent intent = new Intent(MainActivity.this, WaitingForQuestion.class);
                //intent.putExtra("userInput", userInput);
                startActivity(intent);
            }
        });
    }
}