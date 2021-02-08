package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BlanksActivity extends AppCompatActivity {
    private TextView titleText;
    private String title;
    private JSONArray blanks;
    private JSONArray value;
    //private List<String> words;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blanks);

        Intent intent = getIntent();
        String receivedMessage = intent.getStringExtra("madLib");
        try {
            JSONObject madLib = new JSONObject(receivedMessage);
            setMadLib(madLib);
            buildBlanks();
            Log.d("help",value.length()+"");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void setMadLib(JSONObject madLib) throws JSONException {
        title=madLib.getString("title");

        blanks=madLib.getJSONArray("blanks");
        value=madLib.getJSONArray("value");

        //Log.d("api", blanks.toString());
        //Log.d("api", value.toString());

        titleText=findViewById(R.id.titleText);
        titleText.setText(title);
    }

    public void buildBlanks() throws JSONException {
        linearLayout = findViewById(R.id.blanks_scroll);

        for(int i=0; i<blanks.length();i++){
            EditText editText = new EditText(this);
            editText.setText(blanks.getString(i));
            editText.setPadding(0,0,0,20);
            editText.setTextSize(26);
            editText.setId(i);
            linearLayout.addView(editText);
        }







    }

    public void getWords(View view) throws JSONException {
        String[] words=new String[blanks.length()];
        for(int i=0; i<blanks.length(); i++){
            EditText help=findViewById(i);
            String textTemp=help.getText().toString().trim();
            words[i]=textTemp;

        }
        buildStory(words);
    }

    public void buildStory(String[] words) throws JSONException {
        String story="";
        String helper="";
        if (value.length()==2){
            //Log.d("api","ehpl");
            story=value.getString(0).toString()+words[0];
        }
        else {
            for (int i = 0; i < value.length() - 2; i++) {
                story = story + value.getString(i).toString() + words[i];
                helper = value.getString(i + 1).toString();
            }
            story = story + helper;
        }
        Log.d("api",story);
        Intent intent=new Intent(this,StoryActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }
}
