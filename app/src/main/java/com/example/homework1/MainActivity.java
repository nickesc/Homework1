package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static String api_url="http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private Button button2;
    private String title;
    private TextView textView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateMadLibs(v);
                //launchNextActivity(v);
            }
        });


    }

    public void toastTest(View view, String text){
        Toast toastTest = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toastTest.show();
        textView15=findViewById(R.id.textView15);
        textView15.setText(text);

    }

    public void generateMadLibs(View view){
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try{
                    JSONObject json = new JSONObject(new String(responseBody));

                    String response = new String(responseBody);

                    launchNextActivity(view, response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });
    }

    public void launchNextActivity(View view, String json){
        Intent intent = new Intent(this, BlanksActivity.class);
        intent.putExtra("madLib", json);
        startActivity(intent);
    }
}