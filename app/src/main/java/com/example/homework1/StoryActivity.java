package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StoryActivity extends AppCompatActivity {
    TextView storyText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        String story = intent.getStringExtra("story");

        storyText=findViewById(R.id.storyText);
        storyText.setText(story);
    }

    public void goHome(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
