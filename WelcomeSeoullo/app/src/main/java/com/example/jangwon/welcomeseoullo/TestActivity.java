package com.example.jangwon.welcomeseoullo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, PathInfoActivity.class);
                intent.putExtra("Path", "wow");
                startActivityForResult(intent, 1);
            }
        });

        button2.setOnClickListener(new EditText.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestActivity.this, GuideInfoActivity.class));
            }
        });
    }
}
