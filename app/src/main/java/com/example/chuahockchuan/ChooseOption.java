package com.example.chuahockchuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForQuestion.class);
                startActivity(intent);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button B is pressed
                choice = "B";
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForQuestion.class);
                startActivity(intent);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button C is pressed
                choice = "C";
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForQuestion.class);
                startActivity(intent);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send over to tomcat server that button D is pressed
                choice = "D";
                // Go back to WaitingForQuestion until server sends over the next question
                Intent intent = new Intent(ChooseOption.this, WaitingForQuestion.class);
                startActivity(intent);
            }
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.waitingForQuestion), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}
